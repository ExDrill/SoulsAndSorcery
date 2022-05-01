package com.exdrill.soulsandsorcery.client.render.entity;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.client.render.entity.feature.DepartedWolfFlamesFeatureRenderer;
import com.exdrill.soulsandsorcery.client.render.entity.model.DepartedWolfEntityModel;
import com.exdrill.soulsandsorcery.entity.DepartedWolfEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class DepartedWolfEntityRenderer extends MobEntityRenderer<DepartedWolfEntity, DepartedWolfEntityModel<DepartedWolfEntity>> {

    private static final Identifier TEXTURE = new Identifier(SoulsAndSorcery.MODID, "textures/entity/departed_wolf/departed_wolf.png");

    public DepartedWolfEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new DepartedWolfEntityModel<>(context.getPart(DepartedWolfEntityModel.LAYER_LOCATION)), 0.5F);
        this.addFeature(new DepartedWolfFlamesFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(DepartedWolfEntity entity) {
        return TEXTURE;
    }
}
