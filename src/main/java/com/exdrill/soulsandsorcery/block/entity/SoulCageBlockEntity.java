package com.exdrill.soulsandsorcery.block.entity;

import com.exdrill.soulsandsorcery.registry.ModBlockEntityType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class SoulCageBlockEntity extends BlockEntity {

    public SoulCageBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityType.SOUL_CAGE, pos, state);
    }




    int soulsStored = 0;

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("SoulsStored", soulsStored);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.soulsStored = nbt.getInt("SoulsStored");
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    public int getSoulsStored() {
        return soulsStored;
    }

    public void addStoredSouls(int amount) {
        soulsStored = soulsStored + amount;
    }
}
