package com.exdrill.soulsandsorcery.client.render.entity;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.client.render.entity.feature.SearedHoundFlamesFeatureRenderer;
import com.exdrill.soulsandsorcery.client.render.entity.model.SearedHoundEntityModel;
import com.exdrill.soulsandsorcery.entity.SearedHoundEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SearedHoundEntityRenderer extends MobEntityRenderer<SearedHoundEntity, SearedHoundEntityModel<SearedHoundEntity>> {

    private static final Identifier TEXTURE = new Identifier(SoulsAndSorcery.MODID, "textures/entity/seared_hound/seared_hound.png");

    public SearedHoundEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SearedHoundEntityModel<>(context.getPart(SearedHoundEntityModel.LAYER_LOCATION)), 0.5F);
        this.addFeature(new SearedHoundFlamesFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(SearedHoundEntity entity) {
        return TEXTURE;
    }
}
