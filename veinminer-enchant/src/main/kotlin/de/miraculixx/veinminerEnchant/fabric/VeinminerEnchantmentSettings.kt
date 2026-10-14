package de.miraculixx.veinminerEnchant.fabric

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.annotations.SerializedName
import net.fabricmc.loader.api.FabricLoader
import org.slf4j.Logger
import java.nio.file.Files
import java.nio.file.Path

data class VeinminerEnchantmentSettings(
    val weight: Int = 1,
    @field:SerializedName("min_cost")
    val minCost: Int = 15,
    @field:SerializedName("max_cost")
    val maxCost: Int = 65,
    @field:SerializedName("anvil_cost")
    val anvilCost: Int = 7
) {
    companion object {
        private val gson = GsonBuilder().setPrettyPrinting().create()
        private val defaults = VeinminerEnchantmentSettings()

        fun load(logger: Logger): VeinminerEnchantmentSettings {
            val path = FabricLoader.getInstance().configDir.resolve("veinminer_enchantment.json")
            return try {
                Files.createDirectories(path.parent)
                val json = if (Files.exists(path)) {
                    JsonParser.parseString(Files.readString(path)).asJsonObject
                } else {
                    JsonObject()
                }
                val settings = VeinminerEnchantmentSettings(
                    weight = json.int("weight", defaults.weight),
                    minCost = json.int("min_cost", defaults.minCost),
                    maxCost = json.int("max_cost", defaults.maxCost),
                    anvilCost = json.int("anvil_cost", defaults.anvilCost)
                )
                require(settings.weight in 1..1000)
                require(settings.minCost in 0..100000)
                require(settings.maxCost in settings.minCost..100000)
                require(settings.anvilCost in 0..1000)
                Files.writeString(path, gson.toJson(settings))
                settings
            } catch (exception: Exception) {
                logger.warn("Invalid Veinminer Enchantment settings at {}; using defaults", path, exception)
                runCatching { Files.writeString(path, gson.toJson(defaults)) }
                defaults
            }
        }

        private fun JsonObject.int(name: String, default: Int): Int =
            get(name)?.takeUnless { it.isJsonNull }?.asInt ?: default
    }
}
