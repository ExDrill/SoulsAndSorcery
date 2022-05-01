package com.exdrill.soulsandsorcery.mixin;

import com.exdrill.soulsandsorcery.access.WolfEntityAccess;
import com.exdrill.soulsandsorcery.registry.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WolfEntity.class)
public abstract class WolfEntityMixin extends TameableEntity implements WolfEntityAccess {


    @Shadow public abstract boolean damage(DamageSource source, float amount);

    private static final TrackedData<Boolean> COLLAR_OF_BONDING = DataTracker.registerData(WolfEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    WolfEntity wolfEntity = (WolfEntity) (Object) this;

    protected WolfEntityMixin(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    // Data Tracker
    @Inject(require = 1, method = "initDataTracker", at = @At("HEAD"))
    public void onInitDataTracker(CallbackInfo ci) {
        getDataTracker().startTracking(COLLAR_OF_BONDING, false);
    }

    // Nbt Data
    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("CollarOfBonding", this.isCollared());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        this.setCollared(nbt.getBoolean("CollarOfBonding"));

    }

    // Interactions
    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void onInteractMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack stack = player.getStackInHand(hand);
        if (wolfEntity.isTamed() && wolfEntity.getOwner() == player && stack.isOf(ModItems.COLLAR_OF_BONDING) && !this.isCollared()) {
            this.setCollared(true);
            stack.decrement(1);
            BlockPos pos = player.getBlockPos();
            world.playSound(player, pos, SoundEvents.BLOCK_CHAIN_FALL, SoundCategory.PLAYERS, 100F, 1.0F);
            System.out.println("Gave collar of bonding to wolf");
            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }

    @Inject(method = "tickMovement", at = @At("HEAD"))
    private void onTickMovement(CallbackInfo ci) {
        if (!this.world.isClient && this.isAlive() && this.age % 40 == 0 && this.isCollared()) {
            this.heal(1.0F);
        }
    }


    @Override
    public boolean isCollared() {
        return this.dataTracker.get(COLLAR_OF_BONDING);
    }

    @Override
    public void setCollared(boolean collared) {
        this.dataTracker.set(COLLAR_OF_BONDING, collared);
    }

    @Shadow
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }


}
