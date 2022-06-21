package com.exdrill.soulsandsorcery.entity;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.registry.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

public class SearedHoundEntity extends HostileEntity {


    public static final Predicate<LivingEntity> TARGET_PREDICATE = (entity) -> entity instanceof PlayerEntity || entity instanceof AbstractPiglinEntity;
    private static final UUID SOUL_SPEED_BOOST_ID = UUID.fromString("87f46a96-686f-4796-b035-22e16ee9e038");
    private static final TrackedData<Integer> ANIMATION_FRAME = DataTracker.registerData(SearedHoundEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> DIG_COOLDOWN = DataTracker.registerData(SearedHoundEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> FRIENDLY_DURATION = DataTracker.registerData(SearedHoundEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public final AnimationState runningAnimationState = new AnimationState();


    public SearedHoundEntity(EntityType<? extends SearedHoundEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 5;
        this.setPathfindingPenalty(PathNodeType.WATER, -1.0F);
    }

    @Override
    public boolean isFireImmune() {
        return true;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(4, new PounceAtTargetGoal(this, 0.4F));
        this.goalSelector.add(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.add(4, new SearedHoundActiveTargetGoal<>(this, LivingEntity.class, false, TARGET_PREDICATE));
        this.targetSelector.add(1, (new RevengeGoal(this)).setGroupRevenge());
        this.goalSelector.add(2, new DigUpItemGoal(this));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(3, new FleeEntityGoal<>(this, CreeperEntity.class, 10.0F, 1.0D, 1.2D));
        this.goalSelector.add(8, new LookAroundGoal(this));
        super.initGoals();
    }

    private boolean shouldWalk() {
        return this.onGround && this.getVelocity().horizontalLengthSquared() > 1.0E-6D && !this.isInsideWaterOrBubbleColumn();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_SEARED_HOUND_HURT_EVENT;
    }

    public static boolean canSpawn(EntityType<SearedHoundEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.getDifficulty() != Difficulty.PEACEFUL && !world.getBlockState(pos.down()).isOf(Blocks.NETHER_WART_BLOCK);
    }

    @Override
    public void onAttacking(Entity target) {
        if (this.world.getDifficulty() != Difficulty.EASY && target != null) {
            target.setOnFireFor(5);
        }
        super.onAttacking(target);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.ENTITY_SEARED_HOUND_GROWL_EVENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_SEARED_HOUND_DEATH_EVENT;
    }

    @Override
    public EntityGroup getGroup() {
        return EntityGroup.UNDEAD;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ANIMATION_FRAME, 0);
        this.dataTracker.startTracking(DIG_COOLDOWN, 0);
        this.dataTracker.startTracking(FRIENDLY_DURATION, 0);
    }

    @Override
    protected void applyMovementEffects(BlockPos pos) {
        super.applyMovementEffects(pos);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("AnimationFrame", this.getCurrentFrame());
        nbt.putInt("FriendlyCooldown", this.getDigCooldown());
        nbt.putBoolean("FriendlyDuration", this.isFriendly());

    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setFrame(nbt.getInt("AnimationFrame"));
        this.setDigCooldown(nbt.getInt("FriendlyCooldown"));
        this.setFriendly(nbt.getInt("IsFriendly"));
    }

    public void setFrame(int variant) {
        this.dataTracker.set(ANIMATION_FRAME, variant);
    }

    public int getCurrentFrame() {
        return this.dataTracker.get(ANIMATION_FRAME);
    }

    public void setDigCooldown(int integer) {
        this.dataTracker.set(DIG_COOLDOWN, integer);
    }

    public int getDigCooldown() {
        return this.dataTracker.get(DIG_COOLDOWN);
    }

    public void setFriendly(int integer) {
        this.dataTracker.set(FRIENDLY_DURATION, integer);
    }

    public int getFriendly() {
        return this.dataTracker.get(FRIENDLY_DURATION);
    }

    public boolean isFriendly() {
        return this.dataTracker.get(FRIENDLY_DURATION) > 0;
    }

    @Override
    public void tick() {
        if (age % 2 == 0) {
            this.setFrame((this.getCurrentFrame() + 1) % 16);
        }

        super.tick();
        if (this.world.isClient()) {
            if (shouldWalk()) {
                runningAnimationState.startIfNotRunning(this.age);
            } else {
                runningAnimationState.stop();
            }
        }

        if (this.getDigCooldown() > 0) {
            this.setDigCooldown(this.getDigCooldown() - 1);
        }

        if (this.isFriendly()) {
            this.setFriendly(this.getFriendly() - 1);
        }
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.BONE) && !this.isFriendly()) {
            this.setFriendly(1200);
            itemStack.decrement(1);
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }

    @Override
    public boolean shouldDisplaySoulSpeedEffects() {
        return this.age % 5 == 0 && this.getVelocity().x != 0.0D && this.getVelocity().z != 0.0D && this.isOnSoulSpeedBlock();
    }

    @Override
    protected void addSoulSpeedBoostIfNeeded() {
        if (!this.getLandingBlockState().isAir() && this.isOnSoulSpeedBlock()) {
            EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
            if (entityAttributeInstance == null) {
                return;
            }
            entityAttributeInstance.addTemporaryModifier(new EntityAttributeModifier(SOUL_SPEED_BOOST_ID, "Soul speed boost", 0.03F * (1.0F + (float)20 * 0.35F), EntityAttributeModifier.Operation.ADDITION));
        }
    }


    public static DefaultAttributeContainer.Builder createSearedHoundEntity() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D);

    }

    public static class SearedHoundActiveTargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {
        private final SearedHoundEntity entity;

        public SearedHoundActiveTargetGoal(SearedHoundEntity entity, Class<T> targetClass, boolean checkVisibility, Predicate<LivingEntity> targetPredicate) {
            super(entity, targetClass, checkVisibility, targetPredicate);
            this.entity = entity;
        }

        @Override
        public boolean canStart() {
            return !entity.isFriendly() && super.canStart();
        }

        @Override
        public boolean shouldContinue() {
            return !entity.isFriendly() && super.shouldContinue();
        }

        @Override
        public boolean canStop() {
            return entity.isFriendly() && super.canStop();
        }
    }

    public class DigUpItemGoal extends Goal {
        private final SearedHoundEntity entity;
        private int counter = 0;

        public DigUpItemGoal(SearedHoundEntity entity) {
            this.entity = entity;
        }

        private boolean diggingCondition() {
            return entity.isFriendly() && entity.getDigCooldown() == 0 && entity.getSteppingBlockState().isOf(Blocks.SOUL_SOIL);
        }

        @Nullable
        private BlockPos searchForBlock() {
            BlockPos pos = entity.getBlockPos();
            for (int i = -5; i <= 5; i++) {
                for (int j = -1; j <= 5; j++) {
                    for (int k = -1; k <= 5; k++) {
                        if (world.getBlockState(pos.add(i, j, k)).isOf(Blocks.SOUL_SOIL)) {
                            return new BlockPos(pos.add(i, j, k));
                        }
                    }
                }
            }
            return null;
        }

        private boolean hasReached() {
            return entity.getBlockPos().equals(searchForBlock());
        }

        @Override
        public boolean canStart() {
            return diggingCondition();
        }

        @Override
        public boolean canStop() {
            return !entity.isFriendly() || entity.getDigCooldown() > 0 || !entity.getSteppingBlockState().isOf(Blocks.SOUL_SOIL);
        }

        @Override
        public void start() {
            super.start();
            this.entity.setPose(EntityPose.DIGGING);
            System.out.println("Started digging");
        }

        @Override
        public void stop() {
            super.stop();
            this.entity.setPose(EntityPose.STANDING);
            System.out.println("Stopped digging");
        }

        @Override
        public void tick() {
            World world = entity.getWorld();
            counter++;


            this.entity.getNavigation().stop();



            if (counter % 5 == 0) {
                System.out.println("Digging");
                world.addBlockBreakParticles(entity.getBlockPos(), entity.getSteppingBlockState());
            }
            if (counter >= 60) {
                if (!world.isClient) {
                    LootTable lootTable = Objects.requireNonNull(this.entity.world.getServer()).getLootManager().getTable(SoulsAndSorcery.DUG_UP_ITEMS_GAMEPLAY);
                    net.minecraft.loot.context.LootContext.Builder builder = (new net.minecraft.loot.context.LootContext.Builder((ServerWorld)this.entity.world)).parameter(LootContextParameters.ORIGIN, this.entity.getPos()).parameter(LootContextParameters.THIS_ENTITY, this.entity).random(random);
                    List<ItemStack> list = lootTable.generateLoot(builder.build(LootContextTypes.GENERIC));

                    for (ItemStack itemStack : list) {
                        ItemEntity itemEntity = new ItemEntity(world, entity.getX(), entity.getY(), entity.getZ(), itemStack);
                        world.spawnEntity(itemEntity);
                    }
                    System.out.println("Dropped loot");
                }
                this.counter = 0;
                this.entity.setDigCooldown(100);
                System.out.println("Done!");
            }
        }
    }
}
