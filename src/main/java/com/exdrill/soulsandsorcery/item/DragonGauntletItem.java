package com.exdrill.soulsandsorcery.item;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.access.SoulComponents;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.outercloud.scribe.Scribe;
import net.minecraft.client.render.model.json.ItemModelGenerator;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class DragonGauntletItem extends ArtifactItem {
    private final int soulUsage;
    private static String CHARGED_KEY = "Charged";

    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public DragonGauntletItem(int soulUsage, int attackDamage, float attackSpeed, Settings settings) {
        super(soulUsage, settings);
        this.soulUsage = soulUsage;
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", attackDamage + additionalAttackDamage(), EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", attackSpeed, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!isCharged(itemStack)) {

            if (canUseSoulItem(user)) {
                user.setCurrentHand(hand);
                return TypedActionResult.consume(itemStack);
            }
            user.sendMessage(Text.translatable("gameplay.not_enough_souls"), true);
            return TypedActionResult.fail(itemStack);
        }
        return TypedActionResult.fail(itemStack);
    }

    public static boolean isCharged(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        return nbt != null && nbt.getBoolean("Charged");
    }

    public static void setCharged(ItemStack stack, boolean bool) {
        NbtCompound nbt = stack.getNbt();
        nbt.putBoolean("Charged", bool);

    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    public static float getChargeProgress(int useTicks) {
        float f = (float)useTicks / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        int i = getMaxUseTime(stack) - remainingUseTicks;
        float f = getChargeProgress(i);

        if (!isCharged(stack) && f >= 1.0 && user instanceof PlayerEntity player) {

            ((SoulComponents)player).addSouls(-soulUsage);
            setCharged(stack, true);
        }
    }

    public int additionalAttackDamage() {
        return isCharged(this.getDefaultStack()) ? 4 : 0;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (isCharged(stack)) {

            World world = target.getWorld();

            stack.damage(2, attacker, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));

            if (world instanceof ServerWorld server) {
                server.spawnParticles(Scribe.getParticle(new Identifier(SoulsAndSorcery.MODID, "punch")), target.getX(), target.getY() + 1.0D, target.getZ(), 1, 0, 0, 0, 0);
            }

            target.takeKnockback(1.0F, attacker.getPos().getX() - target.getPos().getX(), attacker.getPos().getZ() - target.getPos().getZ());
            target.velocityModified = true;
            target.setOnFireFor(10);
            setCharged(stack, false);

            target.damage(DamageSource.sonicBoom(attacker), 5.0F);

        } else {
            stack.damage(1, attacker, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }

        return true;
    }




    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }
}
