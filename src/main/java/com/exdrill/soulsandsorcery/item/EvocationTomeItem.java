package com.exdrill.soulsandsorcery.item;

import com.exdrill.soulsandsorcery.access.SoulComponents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.MessageType;
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
        double d = Math.min(user.getY(), user.getY());
        double e = Math.max(user.getY(), user.getY()) + 1.0D;
        float f = (float)MathHelper.atan2(user.getZ() - user.getZ(), user.getX() - user.getX());
        int i;
        if (((SoulComponents) user).getSouls() >= soulUsage) {
            ItemStack itemStack = user.getStackInHand(hand);
            ((SoulComponents) user).addSouls(soulUsage * -1);
            for(i = 0; i < 8; ++i) {
                double h = 1.25D * (double)(i + 1);
                int j = 1 * i;
                this.conjureFangs(user.getX() + (double)MathHelper.cos(f) * h, user.getZ() + (double)MathHelper.sin(f) * h, d, e, f, j, user);
            }



        } else {
            if (world.isClient) {
                MinecraftClient.getInstance().inGameHud.addChatMessage(MessageType.GAME_INFO, new TranslatableText("gameplay.not_enough_souls"), UUID.randomUUID());
            }
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
