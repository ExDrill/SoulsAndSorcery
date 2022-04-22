package com.exdrill.soulsandsorcery;

import com.exdrill.soulsandsorcery.registry.ModBlocks;
import com.exdrill.soulsandsorcery.registry.ModItems;
import com.exdrill.soulsandsorcery.registry.ModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoulsAndSorcery implements ModInitializer {
	public static final String MODID = "soulsandsorcery";

	public static final EntityAttribute GENERIC_SOUL_GATHERING = new ClampedEntityAttribute("attribute.name.generic.soul_gathering", 0.0D, 0.0D, 3.0D).setTracked(true);

	private static final Identifier NETHER_FORTRESS_LOOT = new Identifier("minecraft", "chests/nether_bridge");

	@Override
	public void onInitialize() {
		ModItems.register();
		Registry.register(Registry.ATTRIBUTE, new Identifier(SoulsAndSorcery.MODID, "generic.soul_gathering"), GENERIC_SOUL_GATHERING);
		ModSounds.register();
		ModBlocks.register();

		UniformLootNumberProvider uniformLootNumberProvider = UniformLootNumberProvider.create(0, 1);
		LootCondition lootCondition = RandomChanceLootCondition.builder(30).build();
		LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
			if (NETHER_FORTRESS_LOOT.equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
						.rolls(uniformLootNumberProvider)
						.withCondition(lootCondition)
						.withEntry(ItemEntry.builder(ModItems.PETRIFIED_ARTIFACT).build());

				supplier.withPool(poolBuilder.build());
			}
		});
	}
}
