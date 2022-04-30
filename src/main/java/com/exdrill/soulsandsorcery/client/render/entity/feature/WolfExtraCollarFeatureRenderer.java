package com.exdrill.soulsandsorcery.client.render.entity.feature;

import com.exdrill.soulsandsorcery.access.WolfEntityAccess;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.Identifier;

public class WolfExtraCollarFeatureRenderer extends FeatureRenderer<WolfEntity, WolfEntityModel<WolfEntity>> {
    private static final Identifier SKIN = new Identifier("textures/entity/wolf/collar_of_bonding.png");
    private static final Identifier OVERLAY = new Identifier("textures/entity/wolf/collar_of_bonding_overlay.png");

    public WolfExtraCollarFeatureRenderer(FeatureRendererContext<WolfEntity, WolfEntityModel<WolfEntity>> featureRendererContext) {
        super(featureRendererContext);
    }


    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, WolfEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity.isTamed() && !entity.isInvisible() && ((WolfEntityAccess)entity).isCollared()) {
            float[] fs = entity.getCollarColor().getColorComponents();
            renderModel(this.getContextModel(), SKIN, matrices, vertexConsumers, light, entity, fs[0], fs[1], fs[2]);
            renderModel(this.getContextModel(), OVERLAY, matrices, vertexConsumers, light, entity, 1.0F, 1.0F, 1.0F);
        }
    }
}
