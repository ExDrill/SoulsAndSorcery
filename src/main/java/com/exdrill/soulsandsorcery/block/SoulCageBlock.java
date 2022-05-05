package com.exdrill.soulsandsorcery.block;

import com.exdrill.soulsandsorcery.access.SoulComponents;
import com.exdrill.soulsandsorcery.block.entity.SoulCageBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.function.ToIntFunction;

public class SoulCageBlock extends BlockWithEntity {

    public static final BooleanProperty HANGING;
    private static final VoxelShape NORMAL_SHAPE;
    private static final VoxelShape HANGING_SHAPE;


    public SoulCageBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(HANGING, false));
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction[] var3 = ctx.getPlacementDirections();

        for (Direction direction : var3) {
            if (direction.getAxis() == Direction.Axis.Y) {
                BlockState blockState = this.getDefaultState().with(HANGING, direction == Direction.UP);
                if (blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
                    return blockState;
                }
            }
        }
        return null;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);

        ItemStack itemStack = player.getStackInHand(hand);

        if (itemStack.isEmpty() && blockEntity instanceof SoulCageBlockEntity rustyCage && rustyCage.getSoulsStored() < 20 && ((SoulComponents) player).getSouls() > 0 && !player.isSneaking()) {
            rustyCage.addStoredSouls(1);
            ((SoulComponents) player).addSouls(-1);
            world.playSound(player, pos, SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.BLOCKS, 0.5F, 1.0F);
            return ActionResult.SUCCESS;
        } else if (itemStack.isEmpty() && blockEntity instanceof SoulCageBlockEntity rustyCage && ((SoulComponents) player).getSouls() < 20 && player.isSneaking() && rustyCage.getSoulsStored() > 0) {
            rustyCage.addStoredSouls(-1);
            ((SoulComponents) player).addSouls(1);
            world.playSound(player, pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.BLOCKS, 0.5F, 1.0F);
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.FAIL;
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    protected static Direction attachedDirection(BlockState state) {
        return state.get(HANGING) ? Direction.DOWN : Direction.UP;
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = attachedDirection(state).getOpposite();
        return Block.sideCoversSmallSquare(world, pos.offset(direction), direction.getOpposite());
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return attachedDirection(state).getOpposite() == direction && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HANGING);
        super.appendProperties(builder);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof SoulCageBlockEntity rustyCage && rustyCage.getSoulsStored() > 0) {
            world.addParticle(ParticleTypes.SOUL, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0, 0, 0);
            world.playSound(player, pos, SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.BLOCKS, 0.5F, 1.0F);
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return state.get(HANGING) ? HANGING_SHAPE : NORMAL_SHAPE;
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }



    static {
        HANGING = Properties.HANGING;
        NORMAL_SHAPE = VoxelShapes.union(
                Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 11.0D, 14.0D), // Main Cube
                Block.createCuboidShape(5.0D, 11.0D, 5.0D, 11.0D, 13.0D, 11.0D), // Top Cube
                Block.createCuboidShape(7.0D, 13.0D, 7.0D, 9.0D, 15.0D, 9.0D)); // Chain

        HANGING_SHAPE = VoxelShapes.union(
                Block.createCuboidShape(2.0D, 1.0D, 2.0D, 14.0D, 12.0D, 14.0D), // Main Cube
                Block.createCuboidShape(5.0D, 12.0D, 5.0D, 11.0D, 14.0D, 11.0D), // Top Cube
                Block.createCuboidShape(7.0D, 14.0D, 7.0D, 9.0D, 16.0D, 9.0D)); // Chain
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SoulCageBlockEntity(pos, state);
    }
}
