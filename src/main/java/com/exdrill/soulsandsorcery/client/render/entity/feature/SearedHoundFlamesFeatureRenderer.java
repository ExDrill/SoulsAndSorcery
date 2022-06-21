package com.exdrill.soulsandsorcery.client.render.entity.feature;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.client.render.entity.model.SearedHoundEntityModel;
import com.exdrill.soulsandsorcery.entity.SearedHoundEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class SearedHoundFlamesFeatureRenderer extends FeatureRenderer<SearedHoundEntity, SearedHoundEntityModel<SearedHoundEntity>> {

    private static final Identifier[] FRAMES = new Identifier[] {
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/seared_hound/flames_0.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/seared_hound/flames_1.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/seared_hound/flames_2.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/seared_hound/flames_3.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/seared_hound/flames_4.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/seared_hound/flames_5.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/seared_hound/flames_6.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/seared_hound/flames_7.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/seared_hound/flames_8.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/seared_hound/flames_9.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/seared_hound/flames_10.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/seared_hound/flames_11.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/seared_hound/flames_12.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/seared_hound/flames_13.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/seared_hound/flames_14.png"),
            new Identifier(SoulsAndSorcery.MODID, "textures/entity/seared_hound/flames_15.png")};

    public SearedHoundFlamesFeatureRenderer(FeatureRendererContext<SearedHoundEntity, SearedHoundEntityModel<SearedHoundEntity>> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, SearedHoundEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        int frame = entity.getCurrentFrame();
        renderModel(this.getContextModel(), FRAMES[frame], matrices, vertexConsumers, 15728880, entity, 1, 1, 1);
    }
}
