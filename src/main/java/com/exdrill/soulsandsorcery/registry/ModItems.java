package com.exdrill.soulsandsorcery.registry;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.item.SoulWeaponItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item.Settings SETTINGS = new Item.Settings();

    // Item Settings
    public static final SoulWeaponItem HAUNTED_SICKLE = new SoulWeaponItem(6, -3, 1, SETTINGS.group(ItemGroup.COMBAT).maxCount(1).maxDamage(1024));

    // Item Registry
    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(SoulsAndSorcery.MODID, "haunted_sickle"), HAUNTED_SICKLE);
    }
}
