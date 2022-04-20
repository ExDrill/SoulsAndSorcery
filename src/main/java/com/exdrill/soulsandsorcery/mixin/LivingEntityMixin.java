package com.exdrill.soulsandsorcery.mixin;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
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

                int amplifier = (int) (adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING) - 1);

                switch((int) adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING)) {
                    case 1: {
                        adversary.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, amplifier));
                    }
                    case 2: {
                        adversary.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, amplifier));
                    }
                    case 3: {
                        adversary.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, amplifier));
                    }
                }




                System.out.println("Entity killed by " + adversary.getName().getString() + " with a soul gathering level of " + adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING));
            }

        }
    }
}
