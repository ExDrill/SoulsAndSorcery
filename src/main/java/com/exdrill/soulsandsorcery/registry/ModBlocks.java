package com.exdrill.soulsandsorcery.registry;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.block.SoulCageBlock;
import com.exdrill.soulsandsorcery.block.SoulSoilBricksBlock;
import com.exdrill.soulsandsorcery.block.entity.SoulCageBlockEntity;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

public class ModBlocks {

    private static boolean never(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }

    public static final GlassBlock PETRIFIED_GLASS = new GlassBlock(FabricBlockSettings.of(Material.GLASS).nonOpaque().sounds(ModBlockSounds.PETRIFIED_GLASS).strength(0.3F).suffocates(ModBlocks::never).blockVision(ModBlocks::never).solidBlock(ModBlocks::never));
    public static final SoulCageBlock SOUL_CAGE = new SoulCageBlock(FabricBlockSettings.of(Material.METAL).nonOpaque().sounds(BlockSoundGroup.CHAIN).strength(5.0F, 6.0F).suffocates(ModBlocks::never).blockVision(ModBlocks::never).solidBlock(ModBlocks::never));
    public static final SoulSoilBricksBlock SOUL_SOIL_BRICKS = new SoulSoilBricksBlock(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.SOUL_SOIL).strength(0.5F).luminance(SoulSoilBricksBlock.LIGHT_STATE));
    public static final OreBlock SOUL_LAPIS_ORE = new OreBlock(FabricBlockSettings.of(Material.SOIL).sounds(ModBlockSounds.SOUL_ORE).strength(3.0F, 3.0F), UniformIntProvider.create(1, 3));
    public static final PaneBlock BONE_COLUMNS = new PaneBlock(FabricBlockSettings.of(Material.STONE).mapColor(MapColor.PALE_YELLOW).strength(1.5F).sounds(BlockSoundGroup.BONE).nonOpaque());


    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier(SoulsAndSorcery.MODID, "petrified_glass"), PETRIFIED_GLASS);
        Registry.register(Registry.BLOCK, new Identifier(SoulsAndSorcery.MODID, "soul_cage"), SOUL_CAGE);
        Registry.register(Registry.BLOCK, new Identifier(SoulsAndSorcery.MODID, "soul_soil_bricks"), SOUL_SOIL_BRICKS);
        Registry.register(Registry.BLOCK, new Identifier(SoulsAndSorcery.MODID, "soul_lapis_ore"), SOUL_LAPIS_ORE);
        Registry.register(Registry.BLOCK, new Identifier(SoulsAndSorcery.MODID, "bone_columns"), BONE_COLUMNS);
    }

    public static void registerClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(PETRIFIED_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(SOUL_CAGE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BONE_COLUMNS, RenderLayer.getCutoutMipped());

    }
}
