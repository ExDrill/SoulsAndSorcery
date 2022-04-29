package com.exdrill.soulsandsorcery.item;

import com.exdrill.soulsandsorcery.access.SoulComponents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EvokerEntity;
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
        if (((SoulComponents) user).getSouls() >= soulUsage) {
            world.playSound(user, pos, SoundEvents.ENTITY_EVOKER_CAST_SPELL, SoundCategory.PLAYERS, 100F, 1F);
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
        double d = Math.min(user.getY(), user.getY()); // Max Y
        double e = Math.max(user.getY(), user.getY()) + 1.0D; // Y
        float f = (float) MathHelper.atan2(user.getYaw() - user.getZ(), user.getYaw() - user.getX());


        float x = (float) user.offsetX(user.getYaw());
        float z = (float) user.offsetZ(user.getYaw());
        float xz = (float) MathHelper.atan2(Math.cos(z), Math.sin(x));

        int i;
        float g;

        // Functions
        if (user.getItemUseTime() > 5 && ((SoulComponents) user).getSouls() >= soulUsage) {
            // Circle of Fangs
            if (user.isSneaking()) {
                for (i = 0; i < 8; ++i) {
                    g = f + (float) i * 3.1415927F * 2.0F / 8.0F + 1.2566371F;
                    this.conjureFangs(user.getX() + (double) MathHelper.cos(g) * 2.5D, user.getZ() + (double) MathHelper.sin(g) * 2.5D, d, e, g, 3, user);

                }
            // Line of Fangs
            } else {
                for (i = 0; i < 8; ++i) {
                    double h = 1.25D * (double) (i + 1);
                    int j = 1 * i;
                    //this.conjureFangs(user.getX() + (double) MathHelper.cos(f) * h, user.getZ() + (double) MathHelper.sin(f) * h, d, e, f, j, user);
                    this.conjureFangs(user.getX() + (double) xz * h, user.getZ() + (double) xz * h, d, e, f, j, user);
                }
            }
            // Remove 1 Durability
            stack.damage(1, user, (p_220043_1_) -> p_220043_1_.sendToolBreakStatus(hand));

            // Remove Souls
            ((SoulComponents) user).addSouls(-soulUsage);

        } else if (world.isClient && user.getItemUseTime() > 5) {
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
