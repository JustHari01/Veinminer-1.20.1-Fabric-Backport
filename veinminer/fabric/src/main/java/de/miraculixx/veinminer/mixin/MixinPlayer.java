package de.miraculixx.veinminer.mixin;

import de.miraculixx.veinminer.event.VeinMinerEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class MixinPlayer {
    @Inject(method = "getDestroySpeed", at = @At("RETURN"), cancellable = true)
    private void veinminer$applyMiningSpeedModifier(BlockState state, CallbackInfoReturnable<Float> cir) {
        Player player = (Player) (Object) this;
        double multiplier = VeinMinerEvent.getMiningSpeedModifier(player.getUUID());
        if (multiplier != 0.0D && Double.isFinite(multiplier)) {
            cir.setReturnValue((float) (cir.getReturnValueF() * (1.0D + multiplier)));
        }
    }
}
