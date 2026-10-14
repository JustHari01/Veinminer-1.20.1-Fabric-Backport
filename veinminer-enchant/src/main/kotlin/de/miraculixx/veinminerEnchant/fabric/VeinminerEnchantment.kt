package de.miraculixx.veinminerEnchant.fabric

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

class VeinminerEnchantment : ModInitializer {
    companion object {
        const val MOD_ID = "veinminer_enchantment"
        val LOGGER = LoggerFactory.getLogger(VeinminerEnchantment::class.java)
        @JvmField
        var SETTINGS = VeinminerEnchantmentSettings()
    }

    override fun onInitialize() {
        SETTINGS = VeinminerEnchantmentSettings.load(LOGGER)
        Registry.register(
            BuiltInRegistries.ENCHANTMENT,
            ResourceLocation(MOD_ID, "veinminer"),
            VeinmineEnchantment(SETTINGS)
        )
        LOGGER.info("Veinminer_Enchantment loaded (fabric)")
    }
}

private class VeinmineEnchantment(
    private val settings: VeinminerEnchantmentSettings
) : Enchantment(
    Rarity.VERY_RARE,
    EnchantmentCategory.DIGGER,
    arrayOf(EquipmentSlot.MAINHAND)
) {
    override fun getMinCost(level: Int): Int = settings.minCost

    override fun getMaxCost(level: Int): Int = settings.maxCost

    override fun getMaxLevel(): Int = 1
}
