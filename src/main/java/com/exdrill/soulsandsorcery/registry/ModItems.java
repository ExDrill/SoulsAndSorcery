package com.exdrill.soulsandsorcery.registry;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.item.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ModItems {


    // Item Settings
    public static final SoulWeaponItem HAUNTED_SICKLE = new SoulWeaponItem(6, -3, 1, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1).maxDamage(1024));
    public static final BlockItem PETRIFIED_GLASS = new BlockItem(ModBlocks.PETRIFIED_GLASS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final EvocationTomeItem EVOCATION_TOME = new EvocationTomeItem(2, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1).maxDamage(318).rarity(Rarity.RARE));
    public static final WindcallingHornItem WINDCALLING_HORN = new WindcallingHornItem(1, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1).maxDamage(204).rarity(Rarity.RARE));
    public static final Item COLLAR_OF_BONDING = new Item(new Item.Settings().group(ItemGroup.COMBAT).maxCount(1).rarity(Rarity.UNCOMMON));
    public static final SpawnEggItem SEARED_HOUND_SPAWN_EGG = new SpawnEggItem(ModEntityType.SEARED_HOUND, 0x1a1c1f, 0x58defc, new Item.Settings().group(ItemGroup.MISC));
    public static final BlockItem SOUL_CAGE = new BlockItem(ModBlocks.SOUL_CAGE, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final BlockItem SOUL_SOIL_BRICKS = new BlockItem(ModBlocks.SOUL_SOIL_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem SOUL_LAPIS_ORE = new BlockItem(ModBlocks.SOUL_LAPIS_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    // Item Registry
    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(SoulsAndSorcery.MODID, "haunted_sickle"), HAUNTED_SICKLE);
        Registry.register(Registry.ITEM, new Identifier(SoulsAndSorcery.MODID, "petrified_glass"), PETRIFIED_GLASS);
        Registry.register(Registry.ITEM, new Identifier(SoulsAndSorcery.MODID, "evocation_tome"), EVOCATION_TOME);
        Registry.register(Registry.ITEM, new Identifier(SoulsAndSorcery.MODID, "windcalling_horn"), WINDCALLING_HORN);
        Registry.register(Registry.ITEM, new Identifier(SoulsAndSorcery.MODID, "collar_of_bonding"), COLLAR_OF_BONDING);
        Registry.register(Registry.ITEM, new Identifier(SoulsAndSorcery.MODID, "seared_hound_spawn_egg"), SEARED_HOUND_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(SoulsAndSorcery.MODID, "soul_cage"), SOUL_CAGE);
        Registry.register(Registry.ITEM, new Identifier(SoulsAndSorcery.MODID, "soul_soil_bricks"), SOUL_SOIL_BRICKS);
        Registry.register(Registry.ITEM, new Identifier(SoulsAndSorcery.MODID, "soul_lapis_ore"), SOUL_LAPIS_ORE);
    }
}
