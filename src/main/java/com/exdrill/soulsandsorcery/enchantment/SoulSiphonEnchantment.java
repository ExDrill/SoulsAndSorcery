package com.exdrill.soulsandsorcery.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.SweepingEnchantment;
import net.minecraft.entity.EquipmentSlot;

public class SoulSiphonEnchantment extends Enchantment {
    public SoulSiphonEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMinPower(int level) {
        return 15 + (level * 2);
    }

    @Override
    public int getMaxPower(int level) {
        return 50;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return !(other instanceof SweepingEnchantment) && super.canAccept(other);
    }

}
