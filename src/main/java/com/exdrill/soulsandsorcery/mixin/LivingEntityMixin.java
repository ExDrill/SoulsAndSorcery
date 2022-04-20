package com.exdrill.soulsandsorcery.mixin;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.misc.PlayerEntityInterface;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    LivingEntity entity = (LivingEntity)(Object)this;

    @Inject(method = "createLivingAttributes", at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void addSoulGathering(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue()
                .add(SoulsAndSorcery.GENERIC_SOUL_GATHERING);
    }

    @Inject(method = "onKilledBy", at = @At(value = "HEAD"))
    public void onKilledBy(LivingEntity adversary, CallbackInfo ci) {
        if (!entity.world.isClient) {
            if (adversary instanceof PlayerEntity && adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING) > 0) {

                int levelSoulGathering = (int) adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING);

                if (adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING) == levelSoulGathering) {
                    ((PlayerEntityInterface) adversary).addSouls(levelSoulGathering);
                }

                System.out.println("Entity killed by " + adversary.getName().getString() + " with a soul gathering level of " + adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING));
            }

        }
    }
}
