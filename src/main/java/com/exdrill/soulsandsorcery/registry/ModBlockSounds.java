package com.exdrill.soulsandsorcery.registry;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;

public class ModBlockSounds {

    public static final BlockSoundGroup PETRIFIED_GLASS  = new BlockSoundGroup(1.0F, 1.0F, ModSounds.BLOCK_PETRIFIED_GLASS_BREAK_EVENT, SoundEvents.BLOCK_GLASS_STEP, ModSounds.BLOCK_PETRIFIED_GLASS_PLACE_EVENT, SoundEvents.BLOCK_GLASS_HIT, SoundEvents.BLOCK_GLASS_FALL);
    public static final BlockSoundGroup SOUL_ORE = new BlockSoundGroup(1.0F, 1.0F, ModSounds.BLOCK_SOUL_ORE_BREAK_EVENT, SoundEvents.BLOCK_SOUL_SOIL_STEP, ModSounds.BLOCK_SOUL_ORE_PLACE_EVENT, SoundEvents.BLOCK_SOUL_SOIL_HIT, SoundEvents.BLOCK_SOUL_SOIL_FALL);
}
