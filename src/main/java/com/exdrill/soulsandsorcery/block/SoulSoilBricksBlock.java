package com.exdrill.soulsandsorcery.block;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.function.ToIntFunction;

public class SoulSoilBricksBlock extends Block {

    public static final BooleanProperty CHARGED = BooleanProperty.of("charged");
    public static final ToIntFunction<BlockState> LIGHT_STATE = (state) -> state.get(CHARGED) ? 5 : 0;

    public SoulSoilBricksBlock(Settings settings) {
        super(settings);
        this.setDefaultState(getDefaultState().with(CHARGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CHARGED);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if ( (itemStack.isOf(Items.FLINT_AND_STEEL) || itemStack.isOf(Items.FIRE_CHARGE)) && !state.get(CHARGED) ) {
            world.setBlockState(pos, state.with(CHARGED, true));

            player.emitGameEvent(GameEvent.BLOCK_CHANGE);

            if (itemStack.isOf(Items.FLINT_AND_STEEL)) {
                itemStack.damage(1, player, (e) -> e.sendToolBreakStatus(hand));
                world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1F, 1F);
            } else {
                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }
                world.playSound(player, pos, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1F, 1F);
            }

            if (!world.isClient) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity) player, pos, itemStack);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }
}
