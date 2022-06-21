package com.exdrill.soulsandsorcery.block.entity;

import com.exdrill.soulsandsorcery.SoulsAndSorcery;
import com.exdrill.soulsandsorcery.registry.ModBlockEntityType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class SoulCageBlockEntity extends BlockEntity {

    public int soulsStored;
    public SoulCageBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityType.SOUL_CAGE, pos, state);
    }


    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.putInt("SoulsStored", soulsStored);
        this.markDirty();
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        soulsStored = nbt.getInt("SoulsStored");
    }


    public static void tick(World world, BlockPos pos, SoulCageBlockEntity entity) {
        if (entity.soulsStored == 20) {
            Box box = new Box(pos).expand(10);
            world.getNonSpectatingEntities(PlayerEntity.class, box).forEach(player -> player.addStatusEffect(new StatusEffectInstance(SoulsAndSorcery.ALLEVIATING, 200, 0, true,true)));
        }
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
