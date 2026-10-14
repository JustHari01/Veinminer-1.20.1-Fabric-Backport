package de.miraculixx.veinminerClient.network

import de.miraculixx.veinminer.network.ClientNetworkRouter
import de.miraculixx.veinminer.network.ClientPlatformNetwork
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.minecraft.resources.ResourceLocation
import java.util.concurrent.ConcurrentHashMap

object FabricClientPlatformNetwork : ClientPlatformNetwork {
    private val channels: MutableMap<String, ResourceLocation> = ConcurrentHashMap()

    private fun channelFor(channel: String): ResourceLocation =
        channels.getOrPut(channel) { ResourceLocation("veinminer", channel) }

    // C2S codec is registered by the base mod's FabricPlatformNetwork (real server-side handler).
    override fun registerC2S(channel: String) {
        channelFor(channel)
    }

    // S2C wire registration is owned by the base mod. The addon just plugs its callback in.
    override fun registerS2C(channel: String, handler: (ByteArray) -> Unit) {
        channelFor(channel)
        ClientNetworkRouter.registerClientboundHandler(channel, handler)
    }

    override fun sendC2S(channel: String, payload: ByteArray) {
        val buf = PacketByteBufs.create()
        buf.writeBytes(payload)
        ClientPlayNetworking.send(channelFor(channel), buf)
    }
}
