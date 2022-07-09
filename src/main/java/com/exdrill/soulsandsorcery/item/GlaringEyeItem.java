package com.exdrill.soulsandsorcery.item;

public class GlaringEyeItem extends ArtifactItem {
    private final int soulUsage;

    public GlaringEyeItem(int soulUsage, Settings settings) {
        super(soulUsage, settings);
        this.soulUsage = soulUsage;
    }
}
