package com.exdrill.soulsandsorcery.registry;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.entity.SearedHoundEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntityType {

    public static final EntityType<SearedHoundEntity> SEARED_HOUND = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(SoulsAndSorcery.MODID, "seared_hound"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SearedHoundEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 0.9f))
                    .build()
    );

    public static void register() {
        FabricDefaultAttributeRegistry.register(SEARED_HOUND, SearedHoundEntity.createSearedHoundEntity());
    }
}
