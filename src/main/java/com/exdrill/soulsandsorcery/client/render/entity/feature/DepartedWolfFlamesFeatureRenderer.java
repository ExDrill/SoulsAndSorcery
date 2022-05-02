package com.exdrill.soulsandsorcery.client.render.entity.feature;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.client.render.entity.model.DepartedWolfEntityModel;
import com.exdrill.soulsandsorcery.entity.DepartedWolfEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DepartedWolfFlamesFeatureRenderer extends FeatureRenderer<DepartedWolfEntity, DepartedWolfEntityModel<DepartedWolfEntity>> {

    private static final Identifier[] FRAMES = new Identifier[] {
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/departed_wolf/flames_0.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/departed_wolf/flames_1.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/departed_wolf/flames_2.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/departed_wolf/flames_3.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/departed_wolf/flames_4.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/departed_wolf/flames_5.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/departed_wolf/flames_6.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/departed_wolf/flames_7.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/departed_wolf/flames_8.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/departed_wolf/flames_9.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/departed_wolf/flames_0.png")};

    public DepartedWolfFlamesFeatureRenderer(FeatureRendererContext<DepartedWolfEntity, DepartedWolfEntityModel<DepartedWolfEntity>> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, DepartedWolfEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        int frame = entity.getCurrentFrame();
        renderModel(this.getContextModel(), FRAMES[frame], matrices, vertexConsumers, 15728880, entity, 1, 1, 1);
    }
}
