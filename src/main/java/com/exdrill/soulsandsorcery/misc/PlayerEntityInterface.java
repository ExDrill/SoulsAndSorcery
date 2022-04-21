package com.exdrill.soulsandsorcery.misc;

public interface PlayerEntityInterface {
    void addSouls(int soulCount);

    int getSouls();

    boolean canSoulHarvest();

    void setSoulHarvester(boolean soulHarvester);

}
