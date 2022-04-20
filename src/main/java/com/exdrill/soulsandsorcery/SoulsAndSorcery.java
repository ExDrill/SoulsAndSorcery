package com.exdrill.soulsandsorcery;

import com.exdrill.soulsandsorcery.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoulsAndSorcery implements ModInitializer {
	public static final String MODID = "soulsandsorcery";

	public static final EntityAttribute GENERIC_SOUL_GATHERING = new ClampedEntityAttribute("attribute.name.generic.soul_gathering", 0.0D, 0.0D, 3.0D).setTracked(true);


	@Override
	public void onInitialize() {
		ModItems.registerItems();
		Registry.register(Registry.ATTRIBUTE, new Identifier(SoulsAndSorcery.MODID, "generic.soul_gathering"), GENERIC_SOUL_GATHERING);
	}
}
