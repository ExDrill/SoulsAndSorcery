package com.exdrill.soulsandsorcery.registry;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.block.SoulCageBlock;
import com.exdrill.soulsandsorcery.block.entity.SoulCageBlockEntity;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

public class ModBlocks {

    private static boolean never(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }

    private static int soulCageLightLevel(SoulCageBlockEntity blockEntity, BlockPos pos) {
        return blockEntity.getSoulsStored();
    }

    public static final GlassBlock PETRIFIED_GLASS = new GlassBlock(FabricBlockSettings.of(Material.GLASS).nonOpaque().sounds(ModSounds.PETRIFIED_GLASS).strength(0.3F).suffocates(ModBlocks::never).blockVision(ModBlocks::never).solidBlock(ModBlocks::never));
    public static final SoulCageBlock SOUL_CAGE = new SoulCageBlock(FabricBlockSettings.of(Material.METAL).nonOpaque().sounds(BlockSoundGroup.CHAIN).strength(5.0F, 6.0F).suffocates(ModBlocks::never).blockVision(ModBlocks::never).solidBlock(ModBlocks::never));


    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier(SoulsAndSorcery.MODID, "petrified_glass"), PETRIFIED_GLASS);
        Registry.register(Registry.BLOCK, new Identifier(SoulsAndSorcery.MODID, "soul_cage"), SOUL_CAGE);

    }

    public static void registerClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(PETRIFIED_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(SOUL_CAGE, RenderLayer.getCutout());
    }
}
