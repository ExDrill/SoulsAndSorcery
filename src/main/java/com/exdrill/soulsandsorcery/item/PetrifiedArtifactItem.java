package com.exdrill.soulsandsorcery.item;

import com.exdrill.soulsandsorcery.misc.PlayerEntityInterface;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PetrifiedArtifactItem extends Item {
    public PetrifiedArtifactItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }


    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user.getItemUseTime() > 20) {
            stack.decrement(1);
            double x = user.getX();
            double y = user.getY();
            double z = user.getZ();
            BlockPos pos = new BlockPos(x, y, z);
            world.playSound(null, pos, SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.PLAYERS, 100.0F, 1.0F);
            user.world.addParticle(ParticleTypes.SOUL, user.getX(), user.getY() + 1, user.getZ(), 0, 0, 0);
            ((PlayerEntityInterface) user).setSoulHarvester(true);
        }
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }
}
