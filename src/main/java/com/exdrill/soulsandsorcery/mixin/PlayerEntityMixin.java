package com.exdrill.soulsandsorcery.mixin;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.access.SoulComponents;
import io.netty.util.SuppressForbidden;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Objects;
import java.util.UUID;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin extends LivingEntity implements SoulComponents {

    @Shadow @Final private PlayerInventory inventory;
    private static final UUID SOUL_SIPHON_UUID = UUID.fromString("8311f5e9-8e53-4b8d-b7b1-a8bc81fa4af0");
    private static final TrackedData<Integer> SOULS_AMOUNT = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private boolean hasChanged;

    PlayerEntity player = (PlayerEntity) (Object) this;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }





    // Data Tracker
    @Inject(require = 1, method = "initDataTracker", at = @At("HEAD"))
    private void onInitDataTracker(CallbackInfo ci) {
        getDataTracker().startTracking(SOULS_AMOUNT, 0);
    }

    // Soul Methods
    @Override
    public void addSouls(int soulCount) {
        if (this.hasStatusEffect(SoulsAndSorcery.ALLEVIATING) && soulCount > 0) {
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

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        if (shouldRemoveSoulSiphon() && this.inventory.getSwappableHotbarSlot() > 0) {
            removeSoulSiphon();
        }
        addSoulSiphon();

    }

    private boolean shouldRemoveSoulSiphon() {
        ItemStack itemStack = player.getStackInHand(Hand.MAIN_HAND);
        int i = EnchantmentHelper.getLevel(SoulsAndSorcery.SOUL_SIPHON, itemStack);

        return i == 0;
    }

    private void addSoulSiphon() {
        ItemStack itemStack = player.getStackInHand(Hand.MAIN_HAND);
        int i = EnchantmentHelper.getLevel(SoulsAndSorcery.SOUL_SIPHON, itemStack);

        if (i > 0) {
            EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(SoulsAndSorcery.GENERIC_SOUL_GATHERING);
            if (entityAttributeInstance == null) {
                return;
            }

            entityAttributeInstance.addPersistentModifier(new EntityAttributeModifier(SOUL_SIPHON_UUID,"Soul siphon", i, EntityAttributeModifier.Operation.ADDITION));

        }
    }

    private void removeSoulSiphon() {
        EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(SoulsAndSorcery.GENERIC_SOUL_GATHERING);
        if (entityAttributeInstance != null) {
            if (entityAttributeInstance.getModifier(SOUL_SIPHON_UUID) != null) {
                entityAttributeInstance.removeModifier(SOUL_SIPHON_UUID);
            }
        }
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
        cir.getReturnValue().add(SoulsAndSorcery.GENERIC_SOUL_GATHERING);
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
