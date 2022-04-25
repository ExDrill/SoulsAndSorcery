package com.exdrill.soulsandsorcery.access;

public interface LivingEntityAccess {
    void addSouls(int soulCount);

    int getSouls();

    boolean canSoulHarvest();

    void setSoulHarvester(boolean soulHarvester);

    void setSouls(int soulCount);

}
