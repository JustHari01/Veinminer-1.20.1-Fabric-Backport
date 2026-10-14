package de.miraculixx.veinminerEnchant.fabric.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import de.miraculixx.veinminerEnchant.fabric.VeinminerEnchantment;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnchantmentInstance.class)
abstract class EnchantmentInstanceMixin {
    @Redirect(
        method = "<init>",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/item/enchantment/Enchantment$Rarity;getWeight()I"
        )
    )
    private static int veinminer$customWeight(
        Enchantment.Rarity rarity,
        @Local(argsOnly = true, ordinal = 0) Enchantment enchantment
    ) {
        ResourceLocation veinminerKey = new ResourceLocation(VeinminerEnchantment.MOD_ID, "veinminer");
        if (veinminerKey.equals(BuiltInRegistries.ENCHANTMENT.getKey(enchantment))) {
            return VeinminerEnchantment.SETTINGS.getWeight();
        }
        return rarity.getWeight();
    }
}
