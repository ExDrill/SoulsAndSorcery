package com.exdrill.soulsandsorcery.registry;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {

    public static final Identifier ITEM_PETRIFIED_ARTIFACT_ABSORB = new Identifier(SoulsAndSorcery.MODID, "item.petrified_artifact.absorb");
    public static final Identifier ITEM_PETRIFIED_ARTIFACT_ESCAPE = new Identifier(SoulsAndSorcery.MODID, "item.petrified_artifact.escape");
    public static SoundEvent ITEM_PETRIFIED_ARTIFACT_ABSORB_EVENT = new SoundEvent(ITEM_PETRIFIED_ARTIFACT_ABSORB);
    public static SoundEvent ITEM_PETRIFIED_ARTIFACT_ESCAPE_EVENT = new SoundEvent(ITEM_PETRIFIED_ARTIFACT_ESCAPE);

    public static void register() {
        Registry.register(Registry.SOUND_EVENT, ITEM_PETRIFIED_ARTIFACT_ABSORB, ITEM_PETRIFIED_ARTIFACT_ABSORB_EVENT);
        Registry.register(Registry.SOUND_EVENT, ITEM_PETRIFIED_ARTIFACT_ESCAPE, ITEM_PETRIFIED_ARTIFACT_ESCAPE_EVENT);
    }
}
