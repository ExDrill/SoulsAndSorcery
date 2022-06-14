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
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Objects;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements SoulComponents, LivingEntityAccess {

    @Shadow public abstract Random getRandom();

    @Shadow public abstract boolean hasStatusEffect(StatusEffect effect);

    private static final TrackedData<Integer> SOULS_AMOUNT = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.INTEGER);
    LivingEntity entity = (LivingEntity)(Object)this;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    // Data Tracker
    @Inject(require = 1, method = "initDataTracker", at = @At("HEAD"))
    public void onInitDataTracker(CallbackInfo ci) {
        getDataTracker().startTracking(SOULS_AMOUNT, 0);
    }

    // Soul Methods
    @Override
    public void addSouls(int soulCount) {

        if (this.hasStatusEffect(SoulsAndSorcery.ALLEVIATING)) {
            return;
        }

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

    // Nbt Data
    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("SoulsAmount", this.getSouls());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        this.setSouls(nbt.getInt("SoulsAmount"));

    }





    // Add Soul Gathering Attribute
    @Inject(method = "createLivingAttributes", at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void addSoulGathering(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue()
                .add(SoulsAndSorcery.GENERIC_SOUL_GATHERING);
    }

    private void soulHarvestingEffects(BlockPos pos) {
        this.world.syncWorldEvent(98761234, pos, 0);
    }

   // Soul Harvesting
    @Inject(method = "onKilledBy", at = @At(value = "HEAD"))
    public void onKilledBy(LivingEntity adversary, CallbackInfo ci) {
        World world = this.world;
        Random random = this.getRandom();
        BlockPos pos = this.getBlockPos();


        if (!world.isClient) {
            if (adversary instanceof PlayerEntity player) {

                float attribute = (float) player.getAttributeValue(SoulsAndSorcery.GENERIC_SOUL_GATHERING) / 10;

                if (random.nextFloat() < 0.1 + attribute) {
                    ((SoulComponents)adversary).addSouls(1);
                    soulHarvestingEffects(pos);
                }



            }

            if (adversary instanceof WolfEntity wolf && ((WolfEntityAccess)wolf).isCollared()) {
                LivingEntity owner = wolf.getOwner();
                if (owner != null && random.nextFloat() < 0.5f) {
                    ((SoulComponents)owner).addSouls(1);
                    soulHarvestingEffects(pos);
                }

            }
        }
    }

    // Drop Petrified Soul on Death
    @Inject(method = "drop", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;shouldDropLoot()Z"))
    private void drop(DamageSource source, CallbackInfo ci) {
        if (entity instanceof WolfEntity wolf && ((WolfEntityAccess) wolf).isCollared()) {
            ItemStack stack = new ItemStack(ModItems.COLLAR_OF_BONDING);
            entity.dropStack(stack);
        }
    }
}
