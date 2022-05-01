package com.exdrill.soulsandsorcery.entity;

import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class DepartedWolfEntity extends HostileEntity {

    private static final UUID SOUL_SPEED_BOOST_ID = UUID.fromString("87f46a96-686f-4796-b035-22e16ee9e038");

    private static final TrackedData<Integer> ANIMATION_FRAME = DataTracker.registerData(DepartedWolfEntity.class, TrackedDataHandlerRegistry.INTEGER);


    public DepartedWolfEntity(EntityType<? extends DepartedWolfEntity> entityType, World world) {
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
        this.goalSelector.add(4, new ActiveTargetGoal<>(this, PlayerEntity.class, false));
        this.targetSelector.add(1, (new RevengeGoal(this, new Class[0])).setGroupRevenge(new Class[0]));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        super.initGoals();
    }

    @Override
    public EntityGroup getGroup() {
        return EntityGroup.UNDEAD;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        // Add a frame nbt
        this.dataTracker.startTracking(ANIMATION_FRAME, 0);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("AnimationFrame", this.getCurrentFrame());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setFrame(nbt.getInt("AnimationFrame"));
    }

    public void setFrame(int variant) {
        this.dataTracker.set(ANIMATION_FRAME, variant);
    }

    public int getCurrentFrame() {
        return this.dataTracker.get(ANIMATION_FRAME);
    }

    @Override
    public void tick() {
        if (age % 2 == 0) {
            this.setFrame((this.getCurrentFrame() + 1) % 10);
        }
        super.tick();
    }

    @Override
    public boolean shouldDisplaySoulSpeedEffects() {
        return this.age % 5 == 0 && this.getVelocity().x != 0.0D && this.getVelocity().z != 0.0D && this.isOnSoulSpeedBlock();
    }

    @Override
    protected void addSoulSpeedBoostIfNeeded() {
        if (!this.getLandingBlockState().isAir()) {
            EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
            if (entityAttributeInstance == null) {
                return;
            }
            entityAttributeInstance.addTemporaryModifier(new EntityAttributeModifier(SOUL_SPEED_BOOST_ID, "Soul speed boost", 0.03F * (1.0F + (float)3 * 0.35F), EntityAttributeModifier.Operation.ADDITION));
        }
    }




    public static DefaultAttributeContainer.Builder createDepartedWolfEntity() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D);

    }


}
