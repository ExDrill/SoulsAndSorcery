package com.exdrill.soulsandsorcery.registry;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.item.PetrifiedArtifactItem;
import com.exdrill.soulsandsorcery.item.SoulWeaponItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ModItems {


    // Item Settings
    public static final SoulWeaponItem HAUNTED_SICKLE = new SoulWeaponItem(6, -3, 1, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1).maxDamage(1024));
    public static final PetrifiedArtifactItem PETRIFIED_ARTIFACT = new PetrifiedArtifactItem(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1).rarity(Rarity.UNCOMMON));


    // Item Registry
    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(SoulsAndSorcery.MODID, "haunted_sickle"), HAUNTED_SICKLE);
        Registry.register(Registry.ITEM, new Identifier(SoulsAndSorcery.MODID, "petrified_artifact"), PETRIFIED_ARTIFACT);
    }
}
