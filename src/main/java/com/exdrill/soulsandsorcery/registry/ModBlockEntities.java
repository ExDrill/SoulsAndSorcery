package com.exdrill.soulsandsorcery.registry;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.block.entity.SoulCageBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {


    public static BlockEntityType<SoulCageBlockEntity> SOUL_CAGE;

    public static void register() {
        SOUL_CAGE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(SoulsAndSorcery.MODID, "soul_cage"), FabricBlockEntityTypeBuilder.create(SoulCageBlockEntity::new, ModBlocks.SOUL_CAGE).build(null));
    }
}
