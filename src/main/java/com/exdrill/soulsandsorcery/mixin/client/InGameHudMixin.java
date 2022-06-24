package com.exdrill.soulsandsorcery.mixin.client;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.access.SoulComponents;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {

    private static final Identifier SOUL_SPHERE = new Identifier(SoulsAndSorcery.MODID, "textures/gui/soul_sphere.png");

    @Shadow
    private int scaledHeight;
    @Shadow
    private int scaledWidth;
    @Final
    @Shadow
    private MinecraftClient client;


    @Shadow public abstract TextRenderer getTextRenderer();

    @Inject(method = "render", at = @At("HEAD"))
    private void renderSouls(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        if (!this.client.options.hudHidden) {
            int i = this.scaledWidth / 2 - 180;
            renderSoulBar(matrices, i);
        }

    }

    public void renderSoulBar(MatrixStack matrices, int x) {
        if (this.client.player != null && !this.client.player.isCreative() && !this.client.player.isSpectator()) {

            if (((SoulComponents)this.client.player).getSouls() > 0) {

                PlayerEntity player = this.client.player;

                int soulAmount = ((SoulComponents) player).getSouls();
                int activeHeight = (int) (soulAmount * 0.95);
                int height = this.scaledHeight - 20;

                this.client.getProfiler().push("soulSphere");
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderTexture(0, SOUL_SPHERE);

                drawTexture(matrices, x, height - 1, 0, 0, 19, 19, 19, 38);

                if (soulAmount > 0) {
                    drawTexture(matrices, x, height - 1, 0, 19, 19, activeHeight, 19, 38);
                }
                this.client.getProfiler().pop();

                // Soul Text
                this.client.getProfiler().push("soulText");
                int displayX = x + 7;
                int displayY = height + 5;
                String string = String.valueOf(soulAmount);


                
                if (soulAmount > 9) {
                    this.client.textRenderer.draw(matrices, string, x + 5, displayY, 0xFFFFFF);
                } else {
                    this.client.textRenderer.draw(matrices, string, x + 7, displayY, 0xFFFFFF);
                }

                this.client.getProfiler().pop();
            }
        }
    }


}
