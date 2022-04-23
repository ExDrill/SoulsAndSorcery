package com.exdrill.soulsandsorcery.client.gui;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class SoulBar implements HudRenderCallback {
    private static final Identifier SOUL_ICON = new Identifier(SoulsAndSorcery.MODID, "textures/gui/soul_icon.png");
    private static final Identifier EMPTY_SOUL_ICON = new Identifier(SoulsAndSorcery.MODID, "textures/gui/empty_soul_icon.png");

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
    }
}
