package com.exdrill.soulsandsorcery;

import com.exdrill.soulsandsorcery.client.render.entity.DepartedWolfEntityRenderer;
import com.exdrill.soulsandsorcery.client.render.entity.WeepingEntityRenderer;
import com.exdrill.soulsandsorcery.client.render.entity.model.DepartedWolfEntityModel;
import com.exdrill.soulsandsorcery.client.render.entity.model.WeepingEntityModel;
import com.exdrill.soulsandsorcery.registry.ModBlocks;
import com.exdrill.soulsandsorcery.registry.ModEntityType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.DrawableHelper;

public class SoulsAndSorceryClient extends DrawableHelper implements ClientModInitializer {



    @Override
    public void onInitializeClient() {
        ModBlocks.registerClient();


        EntityRendererRegistry.register(ModEntityType.WEEPING, WeepingEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(WeepingEntityModel.LAYER_LOCATION, WeepingEntityModel::createBodyLayer);
        EntityRendererRegistry.register(ModEntityType.DEPARTED_WOLF, DepartedWolfEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(DepartedWolfEntityModel.LAYER_LOCATION, DepartedWolfEntityModel::getTexturedModelData);
    }
}
