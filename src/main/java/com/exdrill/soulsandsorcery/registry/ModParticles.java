package com.exdrill.soulsandsorcery.registry;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.outercloud.scribe.Scribe;
import net.minecraft.util.Identifier;

public class ModParticles {

    private static final Identifier PUNCH = new Identifier(SoulsAndSorcery.MODID, "punch");

    public static void register() {
        Scribe.registerParticle(PUNCH);
    }

    public static void registerClient() {
        Scribe.registerDataDrivenClientParticle(PUNCH);
    }
}
