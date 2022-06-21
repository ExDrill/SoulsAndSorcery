package com.exdrill.soulsandsorcery;

import com.exdrill.soulsandsorcery.client.render.block.SoulCageBlockEntityRenderer;
import com.exdrill.soulsandsorcery.client.render.entity.SearedHoundEntityRenderer;
import com.exdrill.soulsandsorcery.client.render.entity.model.SearedHoundEntityModel;
import com.exdrill.soulsandsorcery.registry.ModBlockEntityType;
import com.exdrill.soulsandsorcery.registry.ModBlocks;
import com.exdrill.soulsandsorcery.registry.ModEntityType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.Identifier;

public class SoulsAndSorceryClient extends DrawableHelper implements ClientModInitializer {



    @Override
    public void onInitializeClient() {
        ModBlocks.registerClient();


        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) ->
                registry.register(new Identifier(SoulsAndSorcery.MODID, "entity/soul_cage/soul")));

        EntityRendererRegistry.register(ModEntityType.SEARED_HOUND, SearedHoundEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(SearedHoundEntityModel.LAYER_LOCATION, SearedHoundEntityModel::createBodyLayer);

        BlockEntityRendererRegistry.register(ModBlockEntityType.SOUL_CAGE, SoulCageBlockEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(SoulCageBlockEntityRenderer.LAYER_LOCATION, SoulCageBlockEntityRenderer::texturedModelData);
    }
}
