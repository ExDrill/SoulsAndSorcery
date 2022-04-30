package com.exdrill.soulsandsorcery.mixin;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.access.LivingEntityAccess;
import com.exdrill.soulsandsorcery.access.SoulComponents;
import com.exdrill.soulsandsorcery.access.WolfEntityAccess;
import com.exdrill.soulsandsorcery.registry.ModItems;
import com.exdrill.soulsandsorcery.registry.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.entity.passive.WolfEntity;
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

import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements SoulComponents, LivingEntityAccess {

    private static final TrackedData<Integer> SOULS_AMOUNT = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> CAN_SOUL_HARVEST = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    LivingEntity entity = (LivingEntity)(Object)this;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    // Data Tracker
    @Inject(require = 1, method = "initDataTracker", at = @At("HEAD"))
    public void onInitDataTracker(CallbackInfo ci) {
        getDataTracker().startTracking(SOULS_AMOUNT, 0);
        getDataTracker().startTracking(CAN_SOUL_HARVEST, false);
    }

    // Soul Methods
    @Override
    public void addSouls(int soulCount) {
        int i = Math.min(this.getSouls() + soulCount, 20);
        this.dataTracker.set(SOULS_AMOUNT, i);
    }

    @Override
    public int getSouls() {
        return this.dataTracker.get(SOULS_AMOUNT);
    }

    @Override
    public void setSouls(int soulCount) {
        int i = Math.min(soulCount, 20);
        this.dataTracker.set(SOULS_AMOUNT, i);
    }

    @Override
    public boolean canSoulHarvest() {
        return this.dataTracker.get(CAN_SOUL_HARVEST);
    }

    @Override
    public void setSoulHarvester(boolean canSoulHarvest) {
        this.dataTracker.set(CAN_SOUL_HARVEST, canSoulHarvest);
    }

    // Nbt Data
    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("SoulsAmount", this.getSouls());
        nbt.putBoolean("canSoulHarvest", this.canSoulHarvest());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        this.setSouls(nbt.getInt("SoulsAmount"));
        this.setSoulHarvester(nbt.getBoolean("canSoulHarvest"));

    }





    // Add Soul Gathering Attribute
    @Inject(method = "createLivingAttributes", at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void addSoulGathering(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue()
                .add(SoulsAndSorcery.GENERIC_SOUL_GATHERING);
    }

   // Soul Harvesting
    @Inject(method = "onKilledBy", at = @At(value = "HEAD"))
    public void onKilledBy(LivingEntity adversary, CallbackInfo ci) {
        World world = ((LivingEntity)(Object)this).world;
        if (!world.isClient && adversary != null) {

            // Soul Harvest
            if (((SoulComponents) adversary).canSoulHarvest()) {

                int getSoulGathering = (int) adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING);
                if (adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING) == getSoulGathering) {
                    ((SoulComponents) adversary).addSouls(getSoulGathering + 1);
                }

                // Grabbing Entity Position
                BlockPos pos = entity.getBlockPos();

                // Effects
                world.playSound(null, pos, SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.PLAYERS, 50.0F, 1.0F);
                ((ServerWorld)world).spawnParticles(ParticleTypes.SOUL, entity.getX(), entity.getY() + (entity.getStandingEyeHeight() * 0.7), entity.getZ(), 1, 0, 0, 0, 0);

                // If Cannot Soul Harvest but Equipment can Soul Harvest
            } else if (!((SoulComponents) adversary).canSoulHarvest() && adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING) > 0) {
                int getSoulGathering = (int) adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING);
                if (adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING) == getSoulGathering) {
                    ((SoulComponents) adversary).addSouls(getSoulGathering);
                }
            }

            if (adversary instanceof WolfEntity wolf && ((WolfEntityAccess) wolf).isCollared()) {
                PlayerEntity player = (PlayerEntity) wolf.getOwner();
                System.out.println("Success");
                if (player != null && ((SoulComponents) player).canSoulHarvest()) {
                    BlockPos pos = entity.getBlockPos();
                    ((SoulComponents) player).addSouls(1);
                    world.playSound(null, pos, SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.PLAYERS, 50.0F, 1.0F);
                    ((ServerWorld)world).spawnParticles(ParticleTypes.SOUL, entity.getX(), entity.getY() + (entity.getStandingEyeHeight() * 0.7), entity.getZ(), 1, 0, 0, 0, 0);
                }
            }
        }
    }

    // Drop Petrified Soul on Death
    @Inject(method = "drop", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;shouldDropLoot()Z"))
    private void drop(DamageSource source, CallbackInfo ci) {
        if (((SoulComponents) entity).canSoulHarvest()) {
            // Define Item
            ItemStack stack = new ItemStack(ModItems.PETRIFIED_ARTIFACT);
            entity.dropStack(stack);
            // Get Position and World
            BlockPos pos = entity.getBlockPos();
            World world = entity.world;
            // Play Sound
            if (!world.isClient) {
                world.playSound(null, pos, ModSounds.ITEM_PETRIFIED_ARTIFACT_ESCAPE_EVENT, SoundCategory.PLAYERS, 50.0F, 1.0F);
            }
        }
    }
}
