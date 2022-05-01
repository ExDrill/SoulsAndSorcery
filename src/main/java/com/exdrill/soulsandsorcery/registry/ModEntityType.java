package com.exdrill.soulsandsorcery.registry;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.entity.DepartedWolfEntity;
import com.exdrill.soulsandsorcery.entity.WeepingEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntityType {

    public static final EntityType<WeepingEntity> WEEPING = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(SoulsAndSorcery.MODID, "weeping"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, WeepingEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 0.9f))
                    .build()
    );

    public static final EntityType<DepartedWolfEntity> DEPARTED_WOLF = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(SoulsAndSorcery.MODID, "departed_wolf"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DepartedWolfEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 0.9f))
                    .build()
    );

    public static void register() {
        FabricDefaultAttributeRegistry.register(WEEPING, WeepingEntity.createWeepingEntity());
        FabricDefaultAttributeRegistry.register(DEPARTED_WOLF, DepartedWolfEntity.createDepartedWolfEntity());
    }
}
