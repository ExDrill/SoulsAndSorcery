package com.exdrill.soulsandsorcery.client.render.block;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.block.entity.SoulCageBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory.Context;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;

public class SoulCageBlockEntityRenderer implements BlockEntityRenderer<SoulCageBlockEntity> {

    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(SoulsAndSorcery.MODID, "soul_cage"), "main");

    public static final SpriteIdentifier SOUL_TEXTURE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier(SoulsAndSorcery.MODID, "entity/soul_cage/soul"));
    private final ModelPart soul;

    public SoulCageBlockEntityRenderer(Context ctx) {
        ModelPart modelPart = ctx.getLayerModelPart(LAYER_LOCATION);
        this.soul = modelPart.getChild("soul");
    }

    public static TexturedModelData texturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData soul = modelPartData.addChild("soul", ModelPartBuilder.create().cuboid(-8.0F, -16.0F, 0.0F, 16.0F, 16.0F, 0.0F).uv(0, 0), ModelTransform.pivot(0, 0, 0));


        return TexturedModelData.of(modelData, 16, 16);
    }


    @Override
    public void render(SoulCageBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        EntityRenderDispatcher dispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();

        matrices.push();
        matrices.translate(0.5D, 0.5D, 0.5D);
        this.soul.pivotX = 0.0F;
        this.soul.pivotY = -8.0F;
        this.soul.visible = entity.getSoulsStored() > 0;
        float scale = (float) entity.getSoulsStored() / 15;
        if (entity.getSoulsStored() > 10) {
            scale = (float) entity.getSoulsStored() / 20;
        }
        matrices.scale(scale, scale, scale);
        matrices.multiply(dispatcher.getRotation());
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(0F));
        this.soul.pivotY = (float) Math.cos(tickDelta * 0.1D) * 0.05F;
        this.soul.render(matrices, SOUL_TEXTURE.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout), 15728880, overlay);
        matrices.pop();
    }
}
