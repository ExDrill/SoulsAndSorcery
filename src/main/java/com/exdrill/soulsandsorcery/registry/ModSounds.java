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

    public static final Identifier BLOCK_SOUL_ORE_BREAK = new Identifier(SoulsAndSorcery.MODID, "block.soul_ore.break");
    public static final Identifier BLOCK_SOUL_ORE_PLACE = new Identifier(SoulsAndSorcery.MODID, "block.soul_ore.place");

    public static final Identifier ITEM_WINDCALLING_HORN_BLOW = new Identifier(SoulsAndSorcery.MODID, "item.windcalling_horn.blow");

    public static SoundEvent ITEM_PETRIFIED_ARTIFACT_ABSORB_EVENT = new SoundEvent(ITEM_PETRIFIED_ARTIFACT_ABSORB);
    public static SoundEvent ITEM_PETRIFIED_ARTIFACT_ESCAPE_EVENT = new SoundEvent(ITEM_PETRIFIED_ARTIFACT_ESCAPE);

    public static SoundEvent BLOCK_PETRIFIED_GLASS_BREAK_EVENT = new SoundEvent(BLOCK_PETRIFIED_GLASS_BREAK);
    public static SoundEvent BLOCK_PETRIFIED_GLASS_PLACE_EVENT = new SoundEvent(BLOCK_PETRIFIED_GLASS_PLACE);

    public static SoundEvent BLOCK_SOUL_ORE_BREAK_EVENT = new SoundEvent(BLOCK_SOUL_ORE_BREAK);
    public static SoundEvent BLOCK_SOUL_ORE_PLACE_EVENT = new SoundEvent(BLOCK_SOUL_ORE_PLACE);

    public static SoundEvent ITEM_WINDCALLING_HORN_BLOW_EVENT = new SoundEvent(ITEM_WINDCALLING_HORN_BLOW);

    public static final Identifier ENTITY_SEARED_HOUND_GROWL = new Identifier(SoulsAndSorcery.MODID, "entity.seared_hound.growl");
    public static final Identifier ENTITY_SEARED_HOUND_HURT = new Identifier(SoulsAndSorcery.MODID, "entity.seared_hound.hurt");
    public static final Identifier ENTITY_SEARED_HOUND_DEATH = new Identifier(SoulsAndSorcery.MODID, "entity.seared_hound.death");
    public static final Identifier ENTITY_SEARED_HOUND_STEP = new Identifier(SoulsAndSorcery.MODID, "entity.seared_hound.step");
    public static SoundEvent ENTITY_SEARED_HOUND_GROWL_EVENT = new SoundEvent(ENTITY_SEARED_HOUND_GROWL);
    public static SoundEvent ENTITY_SEARED_HOUND_HURT_EVENT = new SoundEvent(ENTITY_SEARED_HOUND_HURT);
    public static SoundEvent ENTITY_SEARED_HOUND_DEATH_EVENT = new SoundEvent(ENTITY_SEARED_HOUND_DEATH);
    public static SoundEvent ENTITY_SEARED_HOUND_STEP_EVENT = new SoundEvent(ENTITY_SEARED_HOUND_STEP);



    public static void register() {
        Registry.register(Registry.SOUND_EVENT, ITEM_PETRIFIED_ARTIFACT_ABSORB, ITEM_PETRIFIED_ARTIFACT_ABSORB_EVENT);
        Registry.register(Registry.SOUND_EVENT, ITEM_PETRIFIED_ARTIFACT_ESCAPE, ITEM_PETRIFIED_ARTIFACT_ESCAPE_EVENT);
        Registry.register(Registry.SOUND_EVENT, BLOCK_PETRIFIED_GLASS_BREAK, BLOCK_PETRIFIED_GLASS_BREAK_EVENT);
        Registry.register(Registry.SOUND_EVENT, BLOCK_PETRIFIED_GLASS_PLACE, BLOCK_PETRIFIED_GLASS_PLACE_EVENT);
        Registry.register(Registry.SOUND_EVENT, ITEM_WINDCALLING_HORN_BLOW, ITEM_WINDCALLING_HORN_BLOW_EVENT);
        Registry.register(Registry.SOUND_EVENT, ENTITY_SEARED_HOUND_GROWL, ENTITY_SEARED_HOUND_GROWL_EVENT);
        Registry.register(Registry.SOUND_EVENT, ENTITY_SEARED_HOUND_HURT, ENTITY_SEARED_HOUND_HURT_EVENT);
        Registry.register(Registry.SOUND_EVENT, ENTITY_SEARED_HOUND_DEATH, ENTITY_SEARED_HOUND_DEATH_EVENT);
        Registry.register(Registry.SOUND_EVENT, ENTITY_SEARED_HOUND_STEP, ENTITY_SEARED_HOUND_STEP_EVENT);
        Registry.register(Registry.SOUND_EVENT, BLOCK_SOUL_ORE_BREAK, BLOCK_SOUL_ORE_BREAK_EVENT);
        Registry.register(Registry.SOUND_EVENT, BLOCK_SOUL_ORE_PLACE, BLOCK_SOUL_ORE_PLACE_EVENT);
    }
}
