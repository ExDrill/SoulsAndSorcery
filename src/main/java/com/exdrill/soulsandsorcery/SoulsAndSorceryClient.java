package com.exdrill.soulsandsorcery;

import com.exdrill.soulsandsorcery.registry.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.DrawableHelper;

public class SoulsAndSorceryClient extends DrawableHelper implements ClientModInitializer {



    @Override
    public void onInitializeClient() {
        ModBlocks.registerClient();
    }
}
