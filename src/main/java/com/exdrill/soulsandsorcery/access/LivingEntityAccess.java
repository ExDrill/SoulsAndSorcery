package com.exdrill.soulsandsorcery.access;

import com.exdrill.soulsandsorcery.registry.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public interface LivingEntityAccess {

    default boolean isUsingEvocationTome(LivingEntity entity) {
        ItemStack stack = entity.getMainHandStack();
        ItemStack stack2 = entity.getOffHandStack();
        if (entity.getActiveHand() == Hand.MAIN_HAND && stack2.isOf(ModItems.EVOCATION_TOME)) {
            return false;
        } else {
            return (stack.isOf(ModItems.EVOCATION_TOME) || stack2.isOf(ModItems.EVOCATION_TOME)) && entity.isUsingItem();
        }

    }
}
