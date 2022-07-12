package com.exdrill.soulsandsorcery;

import com.exdrill.soulsandsorcery.client.render.block.SoulCageBlockEntityRenderer;
import com.exdrill.soulsandsorcery.client.render.entity.SearedHoundEntityRenderer;
import com.exdrill.soulsandsorcery.client.render.entity.model.SearedHoundEntityModel;
import com.exdrill.soulsandsorcery.item.DragonGauntletItem;
import com.exdrill.soulsandsorcery.registry.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class SoulsAndSorceryClient extends DrawableHelper implements ClientModInitializer {



    @Override
    public void onInitializeClient() {
        ModBlocks.registerClient();
        ModParticles.registerClient();


        // Adds in Soul Siphon tooltip.
        ItemTooltipCallback.EVENT.register(Event.DEFAULT_PHASE, (stack, tooltip, advanced) -> {
            int i = EnchantmentHelper.getLevel(SoulsAndSorcery.SOUL_SIPHON, stack);

            if (i > 0) {
                advanced.add(6, Text.literal("+" + i + " ").formatted(Formatting.AQUA).append(Text.translatable("attribute.name.generic.soul_gathering")).formatted(Formatting.AQUA));
            }
        });





        // Hound Client Entity
        EntityRendererRegistry.register(ModEntities.SEARED_HOUND, SearedHoundEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(SearedHoundEntityModel.LAYER_LOCATION, SearedHoundEntityModel::createBodyLayer);

        // Soul Cage Client Block Entity
        BlockEntityRendererRegistry.register(ModBlockEntities.SOUL_CAGE, SoulCageBlockEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(SoulCageBlockEntityRenderer.LAYER_LOCATION, SoulCageBlockEntityRenderer::texturedModelData);
        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) ->
                registry.register(new Identifier(SoulsAndSorcery.MODID, "entity/soul_cage/soul")));

        // Model Predicate Providers
        ModelPredicateProviderRegistry.register(ModItems.WINDCALLING_HORN, new Identifier(SoulsAndSorcery.MODID,"calling"), (itemStack, clientWorld, livingEntity, i) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F);
    }
}
