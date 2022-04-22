package com.exdrill.soulsandsorcery.mixin;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    private static final Identifier SOUL_ICON = new Identifier(SoulsAndSorcery.MODID, "textures/gui/soul_icon.png");
    private static final Identifier EMPTY_SOUL_ICON = new Identifier(SoulsAndSorcery.MODID, "textures/gui/empty_soul_icon.png");

    @Inject(method = "render", at = @At("HEAD"))
    public void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {

    }

    @Inject(method = "renderStatusBars", at = @At("HEAD"))
    private void renderStatusBars(MatrixStack matrices, CallbackInfo ci) {
        PlayerEntity playerEntity = this.getCameraPlayer();

    }

    public void renderSoulBar(MatrixStack matrices) {
        PlayerEntity playerEntity = this.getCameraPlayer();
        if (playerEntity != null) {

        }
    }

    @Shadow
    private PlayerEntity getCameraPlayer() {
        return null;
    }

}
