package com.exdrill.soulsandsorcery.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class AlleviatingStatusEffect extends StatusEffect {

    public AlleviatingStatusEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x5cd8f7);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
