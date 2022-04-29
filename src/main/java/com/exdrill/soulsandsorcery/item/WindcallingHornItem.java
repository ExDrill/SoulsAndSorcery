package com.exdrill.soulsandsorcery.item;

import com.exdrill.soulsandsorcery.access.SoulComponents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.MessageType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class WindcallingHornItem extends AbstractArtifactItem {
    private final int soulUsage;
    public WindcallingHornItem(int soulUsage, Settings settings) {
        super(soulUsage, settings);
        this.soulUsage = soulUsage;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (((SoulComponents) user).getSouls() >= soulUsage) {
            BlockPos pos = user.getBlockPos();
            ((SoulComponents) user).addSouls(-soulUsage);
            world.playSound(user, pos, SoundEvents.EVENT_RAID_HORN, SoundCategory.PLAYERS, 50.0F, 1.0F);
            List<? extends LivingEntity> list = world.getEntitiesByClass(LivingEntity.class, new Box(pos).expand(4.0D), (e) -> true);
            LivingEntity livingEntity;
            list.iterator();
            for (Iterator<? extends LivingEntity> var2 = list.iterator(); var2.hasNext(); this.knockback(livingEntity, user)) {
                livingEntity = var2.next();
            }
            user.getItemCooldownManager().set(this, 100);
            return TypedActionResult.success(user.getStackInHand(hand));
        } else {
            if (world.isClient) {
                MinecraftClient.getInstance().inGameHud.addChatMessage(MessageType.GAME_INFO, new TranslatableText("gameplay.not_enough_souls"), UUID.randomUUID());
            }
            return TypedActionResult.fail(user.getStackInHand(hand));

        }
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return stack.isOf(this);
    }

    public void knockback(LivingEntity entity, PlayerEntity user) {
        double d = entity.getX() - user.getX();
        double e = entity.getZ() - user.getZ();
        double f = Math.max(d * d + e * e, 0.001D);
        entity.addVelocity(d / f * 4.0D, 0.3D, e / f * 4.0D);
        entity.velocityModified = true;
    }
}
