package de.miraculixx.veinminer.networking

import de.miraculixx.veinminer.Veinminer
import de.miraculixx.veinminer.event.VeinMinerEvent
import de.miraculixx.veinminer.network.ClientNetworkRouter
import de.miraculixx.veinminer.network.LocalLoopback
import de.miraculixx.veinminer.network.NetworkManager
import de.miraculixx.veinminer.network.PlatformNetwork
import de.miraculixx.veinminer.utils.mcServer
import net.fabricmc.api.EnvType
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.resources.ResourceLocation
import java.nio.ByteBuffer
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

object FabricPlatformNetwork : PlatformNetwork {
    private val channels: MutableMap<String, ResourceLocation> = ConcurrentHashMap()
    private val registeredC2S: MutableSet<String> = ConcurrentHashMap.newKeySet()
    private val registeredS2C: MutableSet<String> = ConcurrentHashMap.newKeySet()

    private fun channelFor(channel: String): ResourceLocation =
        channels.getOrPut(channel) { ResourceLocation(NetworkManager.PACKET_IDENTIFIER, channel) }

    override fun registerC2S(channel: String, handler: (UUID, ByteArray) -> Unit) {
        if (registeredC2S.add(channel)) {
            ServerPlayNetworking.registerGlobalReceiver(channelFor(channel)) { _, player, _, buf, _ ->
                val bytes = ByteArray(buf.readableBytes())
                buf.readBytes(bytes)
                try {
                    handler(player.uuid, bytes)
                } catch (e: Exception) {
                    Veinminer.LOGGER.warn("Failed to dispatch packet $channel: ${e.message}")
                }
            }
        }
    }

    override fun registerS2C(channel: String) {
        if (registeredS2C.add(channel)) {
            // The base mod owns each clientbound receiver. Configuration packets are passed
            // to the addon; the speed modifier is handled here so base-only clients stay safe.
            if (FabricLoader.getInstance().environmentType == EnvType.CLIENT) {
                ClientPlayNetworking.registerGlobalReceiver(channelFor(channel)) { client, _, buf, _ ->
                    val bytes = ByteArray(buf.readableBytes())
                    buf.readBytes(bytes)
                    client.execute {
                        if (channel == NetworkManager.PACKET_SPEED_MODIFIER_ID) {
                            if (bytes.size == java.lang.Double.BYTES) {
                                client.player?.uuid?.let {
                                    VeinMinerEvent.setClientMiningSpeedModifier(it, ByteBuffer.wrap(bytes).double)
                                }
                            }
                        } else {
                            ClientNetworkRouter.dispatchClientbound(channel, bytes)
                        }
                    }
                }
            }
        }
    }

    override fun sendS2C(playerId: UUID, channel: String, payload: ByteArray) {
        if (LocalLoopback.isLoopbackPlayer(playerId)) {
            if (channel == NetworkManager.PACKET_SPEED_MODIFIER_ID && payload.size == java.lang.Double.BYTES) {
                VeinMinerEvent.setClientMiningSpeedModifier(playerId, ByteBuffer.wrap(payload).double)
            } else {
                ClientNetworkRouter.dispatchClientbound(channel, payload)
            }
            return
        }
        val player = mcServer?.playerList?.getPlayer(playerId) ?: return
        val buf = PacketByteBufs.create()
        buf.writeBytes(payload)
        ServerPlayNetworking.send(player, channelFor(channel), buf)
    }
}
