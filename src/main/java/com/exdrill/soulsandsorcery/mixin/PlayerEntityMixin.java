package com.exdrill.soulsandsorcery.mixin;

import com.exdrill.soulsandsorcery.misc.PlayerEntityInterface;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements PlayerEntityInterface {
    private static final TrackedData<Boolean> IS_SOUL_HARVESTER;
    public int soulsAmount = 0;
    PlayerEntity playerEntity = (PlayerEntity) (Object) this;

    // Tick
    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        if (this.soulsAmount > 10) {
            this.soulsAmount = 10;
        }

        if (this.soulsAmount < 0) {
            this.soulsAmount = 0;
        }
    }

    // Soul Methods
    @Override
    public void addSouls(int soulCount) {
        this.soulsAmount += soulCount;
    }

    @Override
    public int getSouls() {
        return this.soulsAmount;
    }


    // Nbt Data
    @Inject(method = "initDataTracker", at = @At("HEAD"))
    protected void initDataTracker(CallbackInfo ci) {
        playerEntity.getDataTracker().startTracking(IS_SOUL_HARVESTER, false);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        this.soulsAmount = nbt.getInt("SoulsAmount");
        this.setSoulHarvester(nbt.getBoolean("IsSoulHarvester"));

    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("SoulsAmount", this.soulsAmount);
        nbt.putBoolean("IsSoulHarvester", this.canSoulHarvest());
    }

    @Override
    public boolean canSoulHarvest() {
        return playerEntity.getDataTracker().get(IS_SOUL_HARVESTER);
    }

    @Override
    public void setSoulHarvester(boolean soulHarvester) {
        playerEntity.getDataTracker().set(IS_SOUL_HARVESTER, soulHarvester);
    }

    static {
        IS_SOUL_HARVESTER = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }
}

