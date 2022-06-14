package com.exdrill.soulsandsorcery.access;

import com.exdrill.soulsandsorcery.registry.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public interface LivingEntityAccess {

    default boolean isUsingEvocationTome(LivingEntity entity) {
        ItemStack stack = entity.getActiveItem();
        return stack.isOf(ModItems.EVOCATION_TOME);
    }
}
