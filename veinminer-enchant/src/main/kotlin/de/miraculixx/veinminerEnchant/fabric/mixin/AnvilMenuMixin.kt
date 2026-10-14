package de.miraculixx.veinminerEnchant.fabric.mixin

import com.llamalad7.mixinextras.sugar.Local
import de.miraculixx.veinminerEnchant.fabric.VeinminerEnchantment
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.inventory.AnvilMenu
import net.minecraft.world.item.enchantment.Enchantment
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.Constant
import org.spongepowered.asm.mixin.injection.ModifyConstant

@Mixin(AnvilMenu::class)
abstract class AnvilMenuMixin {
    @ModifyConstant(
        method = ["createResult"],
        constant = [Constant(intValue = 8)],
        require = 1
    )
    private fun customAnvilCost(
        legacyVeryRareCost: Int,
        @Local(ordinal = 0) enchantment: Enchantment
    ): Int {
        val key = ResourceLocation(VeinminerEnchantment.MOD_ID, "veinminer")
        return if (EnchantmentInstanceMixinHooks.isVeinmine(enchantment, key)) {
            VeinminerEnchantment.SETTINGS.anvilCost
        } else {
            legacyVeryRareCost
        }
    }
}

internal object EnchantmentInstanceMixinHooks {
    fun isVeinmine(enchantment: Enchantment, key: ResourceLocation): Boolean =
        BuiltInRegistries.ENCHANTMENT.getKey(enchantment) == key
}
