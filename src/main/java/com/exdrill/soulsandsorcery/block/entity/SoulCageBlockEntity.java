package com.exdrill.soulsandsorcery.block.entity;

import com.exdrill.soulsandsorcery.registry.ModBlockEntityType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class SoulCageBlockEntity extends BlockEntity {

    public int soulsStored;
    public SoulCageBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityType.SOUL_CAGE, pos, state);
    }


    @Override
    public void writeNbt(NbtCompound nbt) {

        nbt.putInt("SoulsStored", soulsStored);
        System.out.println("Writing Souls Stored: "+ soulsStored);
        System.out.println("-------------------------------");
        this.markDirty();
        super.writeNbt(nbt);

    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        System.out.println("Reading Souls Stored: "+ soulsStored);
        System.out.println("-------------------------------");
        soulsStored = nbt.getInt("SoulsStored");
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
