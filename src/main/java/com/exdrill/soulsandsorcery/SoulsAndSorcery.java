package com.exdrill.soulsandsorcery;

import com.exdrill.soulsandsorcery.effect.AlleviatingStatusEffect;
import com.exdrill.soulsandsorcery.enchantment.SoulSiphonEnchantment;
import com.exdrill.soulsandsorcery.entity.SearedHoundEntity;
import com.exdrill.soulsandsorcery.registry.*;
import com.outercloud.scribe.Scribe;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class SoulsAndSorcery implements ModInitializer {
	public static final String MODID = "soulsandsorcery";

	public static final EntityAttribute GENERIC_SOUL_GATHERING = new ClampedEntityAttribute("attribute.name.generic.soul_gathering", 0.0D, 0.0D, 9.0D).setTracked(true);
	public static final StatusEffect ALLEVIATING = new AlleviatingStatusEffect();
	public static final Identifier DUG_UP_ITEMS_GAMEPLAY = new Identifier(MODID, "gameplay/dug_up_items");
	public static final Enchantment SOUL_SIPHON = new SoulSiphonEnchantment();




	@Override
	public void onInitialize() {
		ModItems.register();
		ModSounds.register();
		ModBlocks.register();
		ModEntities.register();
		ModBlockEntities.register();
		ModParticles.register();

		Scribe.initializeDataDrivenFeatures();

		Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "soul_siphon"), SOUL_SIPHON);
		Registry.register(Registry.ATTRIBUTE, new Identifier(MODID, "generic.soul_gathering"), GENERIC_SOUL_GATHERING);
		RegistryKey<PlacedFeature> ORE_SOUL_LAPIS = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(MODID, "ore_soul_lapis"));
		Registry.register(Registry.STATUS_EFFECT, new Identifier(MODID, "alleviating"), ALLEVIATING);
		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SOUL_SAND_VALLEY), SpawnGroup.MONSTER, ModEntities.SEARED_HOUND, 1, 1, 2);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SOUL_SAND_VALLEY), GenerationStep.Feature.UNDERGROUND_ORES, ORE_SOUL_LAPIS);
		SpawnRestrictionAccessor.callRegister(ModEntities.SEARED_HOUND, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SearedHoundEntity::canSpawn);

	}
}
