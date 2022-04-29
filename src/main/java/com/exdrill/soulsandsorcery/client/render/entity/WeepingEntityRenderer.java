package com.exdrill.soulsandsorcery.client.render.entity;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.client.render.entity.model.WeepingEntityModel;
import com.exdrill.soulsandsorcery.entity.WeepingEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class WeepingEntityRenderer extends MobEntityRenderer<WeepingEntity, WeepingEntityModel<WeepingEntity>> {
    public WeepingEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new WeepingEntityModel<>(ctx.getPart(WeepingEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public Identifier getTexture(WeepingEntity entity) {
        return new Identifier(SoulsAndSorcery.MODID,"textures/entity/weeping/weeping.png");
    }
}
