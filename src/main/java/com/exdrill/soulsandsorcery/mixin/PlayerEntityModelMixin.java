package com.exdrill.soulsandsorcery.mixin;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityModel.class)
public class PlayerEntityModelMixin<T extends LivingEntity> extends BipedEntityModel<T> {


    public PlayerEntityModelMixin(ModelPart root) {
        super(root);
    }


    @Inject(method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", at = @At("HEAD"))
    public void setAngles(T livingEntity, float f, float g, float h, float i, float j, CallbackInfo ci) {
        this.rightArm.pitch = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
    }
}
