package com.exdrill.soulsandsorcery.mixin;

import com.exdrill.soulsandsorcery.misc.PlayerEntityInterface;
import com.exdrill.soulsandsorcery.registry.ModItems;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements PlayerEntityInterface {
    public int soulsAmount = 0;
    public boolean canSoulHarvest = false;
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



    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        this.soulsAmount = nbt.getInt("SoulsAmount");
        this.canSoulHarvest = nbt.getBoolean("IsSoulHarvester");

    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("SoulsAmount", this.soulsAmount);
        nbt.putBoolean("IsSoulHarvester", this.canSoulHarvest);
    }

    @Override
    public boolean canSoulHarvest() {
        return this.canSoulHarvest;
    }

    @Override
    public void setSoulHarvester(boolean canSoulHarvest) {
        this.canSoulHarvest = canSoulHarvest;
    }


    // Petrified Artifact Drop
    @Inject(method = "onDeath", at = @At("HEAD"))
    public void onDeath(CallbackInfo ci) {
        if (canSoulHarvest && !playerEntity.world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY)) {
            ItemEntity itemEntity = new ItemEntity(playerEntity.world, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), new ItemStack(ModItems.PETRIFIED_ARTIFACT));
            playerEntity.world.spawnEntity(itemEntity);
            System.out.println("Spawned petrified artifact");
        }
    }
}

