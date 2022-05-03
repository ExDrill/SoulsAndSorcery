package com.exdrill.soulsandsorcery.item;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.exdrill.soulsandsorcery.item.SoulWeaponItem.SOUL_GATHERING_MODIFIER_ID;

public class BoltSlasherItem extends AbstractArtifactItem {
    private final int soulUsage;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public BoltSlasherItem( float attackDamage, float attackSpeed, float soulGathering, int soulUsage, Settings settings) {
        super(soulUsage, settings);
        this.soulUsage = soulUsage;
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", attackDamage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", attackSpeed, EntityAttributeModifier.Operation.ADDITION));
        builder.put(SoulsAndSorcery.GENERIC_SOUL_GATHERING, new EntityAttributeModifier(SOUL_GATHERING_MODIFIER_ID, "Weapon modifier", soulGathering, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }

}
