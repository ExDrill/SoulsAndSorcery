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
    public static final PetrifiedArtifactItem PETRIFIED_ARTIFACT = new PetrifiedArtifactItem(new Item.Settings().group(ItemGroup.MISC).maxCount(1).rarity(Rarity.UNCOMMON).fireproof());
    public static final BlockItem PETRIFIED_GLASS = new BlockItem(ModBlocks.PETRIFIED_GLASS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final EvocationTomeItem EVOCATION_TOME = new EvocationTomeItem(2, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1).maxDamage(318).rarity(Rarity.RARE));
    public static final WindcallingHornItem WINDCALLING_HORN = new WindcallingHornItem(1, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1).maxDamage(204).rarity(Rarity.RARE));
    public static final Item COLLAR_OF_BONDING = new Item(new Item.Settings().group(ItemGroup.COMBAT).maxCount(1).rarity(Rarity.UNCOMMON));
    public static final SpawnEggItem DEPARTED_WOLF_SPAWN_EGG = new SpawnEggItem(ModEntityType.DEPARTED_WOLF, 0x1a1c1f, 0x58defc, new Item.Settings().group(ItemGroup.MISC));
    public static final BlockItem RUSTY_CAGE = new BlockItem(ModBlocks.RUSTY_CAGE, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final BoltSlasherItem BOLT_SLASHER = new BoltSlasherItem(5, -1, 1, 1, new Item.Settings().group(ItemGroup.COMBAT));

    // Item Registry
    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(SoulsAndSorcery.MODID, "haunted_sickle"), HAUNTED_SICKLE);
        Registry.register(Registry.ITEM, new Identifier(SoulsAndSorcery.MODID, "petrified_artifact"), PETRIFIED_ARTIFACT);
        Registry.register(Registry.ITEM, new Identifier(SoulsAndSorcery.MODID, "petrified_glass"), PETRIFIED_GLASS);
        Registry.register(Registry.ITEM, new Identifier(SoulsAndSorcery.MODID, "evocation_tome"), EVOCATION_TOME);
        Registry.register(Registry.ITEM, new Identifier(SoulsAndSorcery.MODID, "windcalling_horn"), WINDCALLING_HORN);
        Registry.register(Registry.ITEM, new Identifier(SoulsAndSorcery.MODID, "collar_of_bonding"), COLLAR_OF_BONDING);
        Registry.register(Registry.ITEM, new Identifier(SoulsAndSorcery.MODID, "departed_wolf_spawn_egg"), DEPARTED_WOLF_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(SoulsAndSorcery.MODID, "rusty_cage"), RUSTY_CAGE);
        Registry.register(Registry.ITEM, new Identifier(SoulsAndSorcery.MODID, "bolt_slasher"), BOLT_SLASHER);
    }
}
