package com.exdrill.soulsandsorcery.mixin.client;

import com.exdrill.soulsandsorcery.client.render.entity.feature.WolfExtraCollarFeatureRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.WolfEntityRenderer;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WolfEntityRenderer.class)
public class WolfEntityRendererMixin extends MobEntityRenderer<WolfEntity, WolfEntityModel<WolfEntity>> {


    public WolfEntityRendererMixin(EntityRendererFactory.Context context, WolfEntityModel<WolfEntity> entityModel, float f) {
        super(context, entityModel, f);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void WolfEntity(EntityRendererFactory.Context context, CallbackInfo ci) {
        this.addFeature(new WolfExtraCollarFeatureRenderer(this));
    }

    @Shadow
    public Identifier getTexture(WolfEntity entity) {
        return null;
    }
}
