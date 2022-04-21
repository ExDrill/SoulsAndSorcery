package com.exdrill.soulsandsorcery.mixin;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.misc.PlayerEntityInterface;
import com.exdrill.soulsandsorcery.registry.ModItems;
import com.exdrill.soulsandsorcery.registry.ModSounds;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements PlayerEntityInterface {
    public int soulsAmount = 0;
    public boolean canSoulHarvest = false;

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


    LivingEntity entity = (LivingEntity)(Object)this;


    @Inject(method = "createLivingAttributes", at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void addSoulGathering(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue()
                .add(SoulsAndSorcery.GENERIC_SOUL_GATHERING);
    }

    @Inject(method = "onKilledBy", at = @At(value = "HEAD"))
    public void onKilledBy(LivingEntity adversary, CallbackInfo ci) {
        if (((PlayerEntityInterface) adversary).canSoulHarvest()) {
            if (adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING) == 0) {
                ((PlayerEntityInterface) adversary).addSouls(1);
            }
            if (adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING) == 1) {
                ((PlayerEntityInterface) adversary).addSouls(2);
            }
            if (adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING) == 2) {
                ((PlayerEntityInterface) adversary).addSouls(3);
            }
            if (adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING) == 3) {
                ((PlayerEntityInterface) adversary).addSouls(4);
            }
            World world = ((LivingEntity)(Object)this).world;
            if (!world.isClient) {
                double x = entity.getX();
                double y = entity.getY();
                double z = entity.getZ();
                BlockPos pos = new BlockPos(x, y, z);
                world.playSound(null, pos, SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.PLAYERS, 50.0F, 1.0F);
                ((ServerWorld)world).spawnParticles(ParticleTypes.SOUL, entity.getX(), entity.getY() + (entity.getStandingEyeHeight() * 0.7), entity.getZ(), 1, 0, 0, 0, 0);
            }

            System.out.println("Entity killed by " + adversary.getName().getString() + " with a soul gathering level of " + adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING));
        }
    }

    @Inject(method = "drop", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;shouldDropLoot()Z"))
    private void drop(DamageSource source, CallbackInfo ci) {
        if (((PlayerEntityInterface) entity).canSoulHarvest()) {
            ItemStack stack = new ItemStack(ModItems.PETRIFIED_ARTIFACT);
            entity.dropStack(stack);
            System.out.println("Dropped petrified artifact");
            double x = entity.getX();
            double y = entity.getY();
            double z = entity.getZ();
            BlockPos pos = new BlockPos(x, y, z);
            World world = ((LivingEntity)(Object)this).world;
            if (!world.isClient) {
                world.playSound(null, pos, ModSounds.ITEM_PETRIFIED_ARTIFACT_ESCAPE_EVENT, SoundCategory.PLAYERS, 50.0F, 1.0F);
            }

        }
    }
}
