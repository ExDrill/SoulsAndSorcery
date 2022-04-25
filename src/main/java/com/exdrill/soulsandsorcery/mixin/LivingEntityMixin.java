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

    public int soulsAmount = this.getSouls();
    public boolean canSoulHarvest = false;
    private static final TrackedData<Integer> SOULS_AMOUNT = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(require = 1, method = "initDataTracker", at = @At("HEAD"))
    public void onInitDataTracker(CallbackInfo ci) {
        getDataTracker().startTracking(SOULS_AMOUNT, 0);
    }

    // Tick
    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        if (this.getSouls() > 10) {
            this.setSouls(10);
        }

        if (this.getSouls() < 0) {
            this.setSouls(0);
        }
    }

    // Soul Methods
    @Override
    public void addSouls(int soulCount) {
        int i = this.getSouls() + soulCount;
        this.dataTracker.set(SOULS_AMOUNT, i);
    }

    @Override
    public int getSouls() {
        return this.dataTracker.get(SOULS_AMOUNT);
    }

    @Override
    public void setSouls(int soulCount) {
        this.dataTracker.set(SOULS_AMOUNT, soulCount);
    }

    // Nbt Data
    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("SoulsAmount", this.getSouls());
        nbt.putBoolean("IsSoulHarvester", this.canSoulHarvest);
    }


    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        this.setSouls(nbt.getInt("SoulsAmount"));
        this.canSoulHarvest = nbt.getBoolean("IsSoulHarvester");

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
        World world = ((LivingEntity)(Object)this).world;
        if (!world.isClient && adversary != null) {
            if (((LivingEntityAccess) adversary).canSoulHarvest()) {

                /*if (adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING) == 0) {
                    ((LivingEntityAccess) adversary).addSouls(1);
                }
                if (adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING) == 1) {
                    ((LivingEntityAccess) adversary).addSouls(2);
                }
                if (adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING) == 2) {
                    ((LivingEntityAccess) adversary).addSouls(3);
                }
                if (adversary.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING) == 3) {
                    ((LivingEntityAccess) adversary).addSouls(4);
                }*/

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
