package com.exdrill.soulsandsorcery;

import com.exdrill.soulsandsorcery.registry.ModBlocks;
import net.fabricmc.api.ClientModInitializer;

public class SoulsAndSorceryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModBlocks.registerClient();
    }
}
