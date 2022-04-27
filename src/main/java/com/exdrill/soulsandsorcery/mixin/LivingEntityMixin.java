package com.exdrill.soulsandsorcery.mixin;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.access.LivingEntityAccess;
import com.exdrill.soulsandsorcery.registry.ModItems;
import com.exdrill.soulsandsorcery.registry.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
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
public abstract class LivingEntityMixin extends Entity implements LivingEntityAccess {

    private static final TrackedData<Integer> SOULS_AMOUNT = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> CAN_SOUL_HARVEST = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

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


    LivingEntity entity = (LivingEntity)(Object)this;


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
            if (((LivingEntityAccess) adversary).canSoulHarvest()) {

                int getSoulGathering = (int) adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING);
                if (adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING) == getSoulGathering) {
                    ((LivingEntityAccess) adversary).addSouls(getSoulGathering + 1);
                }

                double x = entity.getX();
                double y = entity.getY();
                double z = entity.getZ();
                BlockPos pos = new BlockPos(x, y, z);
                world.playSound(null, pos, SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.PLAYERS, 50.0F, 1.0F);
                ((ServerWorld)world).spawnParticles(ParticleTypes.SOUL, entity.getX(), entity.getY() + (entity.getStandingEyeHeight() * 0.7), entity.getZ(), 1, 0, 0, 0, 0);

                System.out.println("Entity killed by " + adversary.getName().getString() + " with a soul gathering level of " + adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING));
            }
        }
    }

    // Drop Petrified Soul on Death
    @Inject(method = "drop", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;shouldDropLoot()Z"))
    private void drop(DamageSource source, CallbackInfo ci) {
        if (((LivingEntityAccess) entity).canSoulHarvest()) {
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
