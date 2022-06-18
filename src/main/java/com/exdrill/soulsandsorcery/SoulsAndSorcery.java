package com.exdrill.soulsandsorcery;

import com.exdrill.soulsandsorcery.effect.AlleviatingStatusEffect;
import com.exdrill.soulsandsorcery.entity.DepartedWolfEntity;
import com.exdrill.soulsandsorcery.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class SoulsAndSorcery implements ModInitializer {
	public static final String MODID = "soulsandsorcery";

	public static final EntityAttribute GENERIC_SOUL_GATHERING = new ClampedEntityAttribute("attribute.name.generic.soul_gathering", 0.0D, 0.0D, 9.0D).setTracked(true);
	public static final StatusEffect ALLEVIATING = new AlleviatingStatusEffect();

	@Override
	public void onInitialize() {
		ModItems.register();
		Registry.register(Registry.ATTRIBUTE, new Identifier(MODID, "generic.soul_gathering"), GENERIC_SOUL_GATHERING);
		ModSounds.register();
		ModBlocks.register();
		ModEntityType.register();
		ModBlockEntityType.register();

		Registry.register(Registry.STATUS_EFFECT, new Identifier(MODID, "alleviating"), ALLEVIATING);

		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SOUL_SAND_VALLEY), SpawnGroup.MONSTER, ModEntityType.DEPARTED_WOLF, 2, 3, 4);
		SpawnRestrictionAccessor.callRegister(ModEntityType.DEPARTED_WOLF, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, DepartedWolfEntity::canSpawn);


	}
}
