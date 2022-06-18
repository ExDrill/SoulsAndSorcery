package com.exdrill.soulsandsorcery.mixin;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.access.SoulComponents;
import io.netty.util.SuppressForbidden;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Arm;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin extends LivingEntity implements SoulComponents {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    private static final TrackedData<Integer> SOULS_AMOUNT = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);

    // Data Tracker
    @Inject(require = 1, method = "initDataTracker", at = @At("HEAD"))
    private void onInitDataTracker(CallbackInfo ci) {
        getDataTracker().startTracking(SOULS_AMOUNT, 0);
    }

    // Soul Methods
    @Override
    public void addSouls(int soulCount) {
        if (this.hasStatusEffect(SoulsAndSorcery.ALLEVIATING)) {
            this.heal(5);
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
        nbt.putInt("soulsandsorcery:souls_amount", this.getSouls());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        this.setSouls(nbt.getInt("soulsandsorcery:souls_amount"));

    }

    // Add Soul Gathering Attribute
    @Inject(method = "createPlayerAttributes", at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void addSoulGathering(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue()
                .add(SoulsAndSorcery.GENERIC_SOUL_GATHERING);
    }

    @Shadow
    public Iterable<ItemStack> getArmorItems() {
        return null;
    }

    @Shadow
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return null;
    }

    @Shadow
    public void equipStack(EquipmentSlot slot, ItemStack stack) {
    }

    @Shadow
    public Arm getMainArm() {
        return null;
    }
}
