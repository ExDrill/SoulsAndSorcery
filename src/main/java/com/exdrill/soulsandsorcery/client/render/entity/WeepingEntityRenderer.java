package com.exdrill.soulsandsorcery.client.render.entity;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.entity.WeepingEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class WeepingEntityRenderer extends EntityRenderer<WeepingEntity> {
    public WeepingEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(WeepingEntity entity) {
        return new Identifier(SoulsAndSorcery.MODID,"textures/entity/weeping/weeping.png");
    }
}
