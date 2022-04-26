package com.exdrill.soulsandsorcery.mixin.client;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.access.LivingEntityAccess;
import com.exdrill.soulsandsorcery.item.SoulWeaponItem;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {

    private static final Identifier SOUL_BAR = new Identifier(SoulsAndSorcery.MODID, "textures/gui/soul_bar.png");
    private static final Identifier LARGE_SOUL_BAR = new Identifier(SoulsAndSorcery.MODID, "textures/gui/large_soul_bar.png");

    @Shadow
    private int scaledHeight;
    @Shadow
    private int scaledWidth;
    @Final
    @Shadow
    private MinecraftClient client;


    @Inject(method = "render", at = @At("HEAD"))
    private void renderSouls(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        int i = this.scaledWidth / 2 - 180;
        renderSoulBar(matrices, i);
    }



    public void renderSoulBar(MatrixStack matrices, int x) {
        if (this.client.player != null && !this.client.player.isCreative()) {
            if (((LivingEntityAccess)this.client.player).canSoulHarvest()) {
                PlayerEntity player = this.client.player;
                this.client.getProfiler().push("soulBar");
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderTexture(0, LARGE_SOUL_BAR);
                float f = ((LivingEntityAccess) player).getSouls();
                int j = (int) ((int) f * 2.5);
                int k = this.scaledHeight - 20;
                drawTexture(matrices, x, k, 0, 0, 50, 18, 50, 36);

                if (f > 0) {
                    drawTexture(matrices, x , k, 0, 18, j, 18, 50, 36);
                }
                this.client.getProfiler().pop();
            }
        }
    }

    @Shadow
    private PlayerEntity getCameraPlayer() {
        return null;
    }


}
