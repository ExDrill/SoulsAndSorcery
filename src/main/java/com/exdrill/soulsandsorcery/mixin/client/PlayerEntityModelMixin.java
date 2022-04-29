package com.exdrill.soulsandsorcery.mixin.client;

import com.exdrill.soulsandsorcery.access.LivingEntityAccess;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public class PlayerEntityModelMixin {

    @Shadow @Final public ModelPart leftArm;

    @Shadow @Final public ModelPart rightArm;

    @Inject(method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", at = @At("TAIL"))
    private void setAngles(LivingEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, CallbackInfo ci) {
        if (((LivingEntityAccess) entity).isUsingEvocationTome(entity)) {
            this.rightArm.pitch = MathHelper.cos(animationProgress * 0.6F) * 0.25F;
            this.leftArm.pitch = MathHelper.cos(animationProgress * 0.6F) * 0.25F;
            this.rightArm.roll = 2.3561945F;
            this.leftArm.roll = -2.3561945F;
            this.rightArm.yaw = 0.0F;
            this.leftArm.yaw = 0.0F;

        }

    }
}
