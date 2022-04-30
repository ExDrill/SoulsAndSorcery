package com.exdrill.soulsandsorcery.item;

import com.exdrill.soulsandsorcery.access.SoulComponents;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.MessageType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

import java.util.UUID;

public class EvocationTomeItem extends AbstractArtifactItem {
    private final int soulUsage;

    public EvocationTomeItem(int soulUsage, Settings settings) {
        super(soulUsage, settings);
        this.soulUsage = soulUsage;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        BlockPos pos = user.getBlockPos();
        if (((SoulComponents) user).getSouls() >= soulUsage || user.getAbilities().creativeMode) {
            world.playSound(user, pos, SoundEvents.ENTITY_EVOKER_PREPARE_ATTACK, SoundCategory.PLAYERS, 100F, 1F);
        }
        return TypedActionResult.consume(itemStack);
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }


    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        Hand hand = Hand.MAIN_HAND;

        double d = Math.min(user.getY(), user.getY() - 1.0D); // Max Y
        double e = Math.max(user.getY(), user.getY()) + 1.0D; // Y

        float f = (float) MathHelper.atan2(user.getYaw() - user.getZ(), user.getYaw() - user.getX());

        // Functions
        if (user.getItemUseTime() > 10 && (((SoulComponents) user).getSouls() >= soulUsage || user instanceof PlayerEntity player && player.getAbilities().creativeMode)) {
            // Circle of Fangs
            if (user.isSneaking()) {
                for (int i = 0; i < 8; ++i) {
                    float g = f + (float) i * 3.1415927F * 2.0F / 8.0F + 1.2566371F;
                    this.conjureFangs(user.getX() + (double) MathHelper.cos(g) * 2.5D, user.getZ() + (double) MathHelper.sin(g) * 2.5D, d, e, g, 3, user);

                }
            // Line of Fangs
            } else {
                for (int i = 0; i < 8; ++i) {
                    float degToRad = 0.01745329252F;
                    float offsetX = MathHelper.sin(-user.getYaw() * degToRad);
                    float offsetZ = MathHelper.cos(user.getYaw() * degToRad);

                    this.conjureFangs(user.getX() + offsetX * (i + 1), user.getZ() + offsetZ * (i + 1), d, e, (user.getYaw() + 90) * degToRad, i, user);
                }
            }
            // Remove 1 Durability
            stack.damage(1, user, (p_220043_1_) -> p_220043_1_.sendToolBreakStatus(hand));

            // Remove Souls
            if (user instanceof PlayerEntity player && !player.getAbilities().creativeMode) {
                ((SoulComponents) user).addSouls(-soulUsage);
            }

        } else if (world.isClient && user.getItemUseTime() > 10) {
            MinecraftClient.getInstance().inGameHud.addChatMessage(MessageType.GAME_INFO, new TranslatableText("gameplay.not_enough_souls"), UUID.randomUUID());
        }
    }

    private void conjureFangs(double x, double z, double maxY, double y, float yaw, int warmup, LivingEntity user) {
        BlockPos blockPos = new BlockPos(x, y, z);
        boolean bl = false;
        double d = 0.0D;

        do {
            BlockPos blockPos2 = blockPos.down();
            BlockState blockState = user.world.getBlockState(blockPos2);
            if (blockState.isSideSolidFullSquare(user.world, blockPos2, Direction.UP)) {
                if (!user.world.isAir(blockPos)) {
                    BlockState blockState2 = user.world.getBlockState(blockPos);
                    VoxelShape voxelShape = blockState2.getCollisionShape(user.world, blockPos);
                    if (!voxelShape.isEmpty()) {
                        d = voxelShape.getMax(Direction.Axis.Y);
                    }
                }

                bl = true;
                break;
            }

            blockPos = blockPos.down();
        } while(blockPos.getY() >= MathHelper.floor(maxY) - 1);

        if (bl) {
            user.world.spawnEntity(new EvokerFangsEntity(user.world, x, (double)blockPos.getY() + d, z, yaw, warmup, user));
        }
    }
}
