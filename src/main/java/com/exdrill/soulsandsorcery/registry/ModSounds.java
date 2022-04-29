package com.exdrill.soulsandsorcery.registry;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {

    public static final Identifier ITEM_PETRIFIED_ARTIFACT_ABSORB = new Identifier(SoulsAndSorcery.MODID, "item.petrified_artifact.absorb");
    public static final Identifier ITEM_PETRIFIED_ARTIFACT_ESCAPE = new Identifier(SoulsAndSorcery.MODID, "item.petrified_artifact.escape");
    public static final Identifier BLOCK_PETRIFIED_GLASS_BREAK = new Identifier(SoulsAndSorcery.MODID, "block.petrified_glass.break");
    public static final Identifier BLOCK_PETRIFIED_GLASS_PLACE = new Identifier(SoulsAndSorcery.MODID, "block.petrified_glass.place");
    public static final Identifier ITEM_WINDCALLING_HORN_BLOW = new Identifier(SoulsAndSorcery.MODID, "item.windcalling_horn.blow");
    public static SoundEvent ITEM_PETRIFIED_ARTIFACT_ABSORB_EVENT = new SoundEvent(ITEM_PETRIFIED_ARTIFACT_ABSORB);
    public static SoundEvent ITEM_PETRIFIED_ARTIFACT_ESCAPE_EVENT = new SoundEvent(ITEM_PETRIFIED_ARTIFACT_ESCAPE);
    public static SoundEvent BLOCK_PETRIFIED_GLASS_BREAK_EVENT = new SoundEvent(BLOCK_PETRIFIED_GLASS_BREAK);
    public static SoundEvent BLOCK_PETRIFIED_GLASS_PLACE_EVENT = new SoundEvent(BLOCK_PETRIFIED_GLASS_PLACE);
    public static SoundEvent ITEM_WINDCALLING_HORN_BLOW_EVENT = new SoundEvent(ITEM_WINDCALLING_HORN_BLOW);

    public static final BlockSoundGroup PETRIFIED_GLASS  = new BlockSoundGroup(1.0F, 1.0F, BLOCK_PETRIFIED_GLASS_BREAK_EVENT, SoundEvents.BLOCK_GLASS_STEP, BLOCK_PETRIFIED_GLASS_PLACE_EVENT, SoundEvents.BLOCK_GLASS_HIT, SoundEvents.BLOCK_GLASS_FALL);

    public static void register() {
        Registry.register(Registry.SOUND_EVENT, ITEM_PETRIFIED_ARTIFACT_ABSORB, ITEM_PETRIFIED_ARTIFACT_ABSORB_EVENT);
        Registry.register(Registry.SOUND_EVENT, ITEM_PETRIFIED_ARTIFACT_ESCAPE, ITEM_PETRIFIED_ARTIFACT_ESCAPE_EVENT);
        Registry.register(Registry.SOUND_EVENT, BLOCK_PETRIFIED_GLASS_BREAK, BLOCK_PETRIFIED_GLASS_BREAK_EVENT);
        Registry.register(Registry.SOUND_EVENT, BLOCK_PETRIFIED_GLASS_PLACE, BLOCK_PETRIFIED_GLASS_PLACE_EVENT);
    }
}
