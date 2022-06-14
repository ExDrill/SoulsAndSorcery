package com.exdrill.soulsandsorcery.mixin.client;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @Shadow @Nullable private ClientWorld world;

    @Inject(method = "processWorldEvent", at = @At("HEAD"))
    public void onProcessWorldEvent(int eventId, BlockPos pos, int data, CallbackInfo ci) {
        if (world != null) {
            switch (eventId) {
                case 98761234:
                    this.world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
                    this.world.addParticle(ParticleTypes.SOUL, pos.getX(), (pos.getY() + 1.0D), pos.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

}
