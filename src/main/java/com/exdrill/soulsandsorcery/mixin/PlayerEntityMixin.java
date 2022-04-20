package com.exdrill.soulsandsorcery.mixin;

import com.exdrill.soulsandsorcery.misc.PlayerEntityInterface;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements PlayerEntityInterface {

    public int soulsAmount = 0;

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        this.soulsAmount = nbt.getInt("SoulsAmount");
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("SoulsAmount", this.soulsAmount);
    }


    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        if (this.soulsAmount > 10) {
            this.soulsAmount = 10;
        }
        if (this.soulsAmount < 0) {
            this.soulsAmount = 0;
        }
    }

    @Override
    public void addSouls(int soulCount) {
        this.soulsAmount += soulCount;
    }

    @Override
    public int getSouls(int soulCount) {
        return this.soulsAmount;
    }
}

