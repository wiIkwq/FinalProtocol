package com.wiIk_wq.finalprotocol.block;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FinalProtocolStorageBlockEntity extends BlockEntity {
    private final CustomEnergyStorage energyStorage = new CustomEnergyStorage(1_000_000, 1000, 1000);
    private final LazyOptional<IEnergyStorage> lazyEnergy = LazyOptional.of(() -> energyStorage);

    public FinalProtocolStorageBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FINAL_PROTOCOL_STORAGE_ENTITY.get(), pos, state);
    }

    // 🔋 Загрузка и сохранение энергии
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("Energy")) {
            energyStorage.deserializeNBT(tag.get("Energy"));
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("Energy", energyStorage.serializeNBT());
        super.saveAdditional(tag);
    }

    // ⚡ Capability (Forge 47+ стиль)
    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable net.minecraft.core.Direction side) {
        if (cap == ForgeCapabilities.ENERGY) {
            return lazyEnergy.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyEnergy.invalidate();
    }

    public int getEnergyStored() {
        return energyStorage.getEnergyStored();
    }

    public int getMaxEnergyStored() {
        return energyStorage.getMaxEnergyStored();
    }

    // 🔋 Класс хранения энергии
    public static class CustomEnergyStorage extends EnergyStorage {
        public CustomEnergyStorage(int capacity, int maxReceive, int maxExtract) {
            super(capacity, maxReceive, maxExtract);
        }

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            int received = super.receiveEnergy(maxReceive, simulate);
            if (received > 0 && !simulate) {
                onEnergyChanged();
            }
            return received;
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            int extracted = super.extractEnergy(maxExtract, simulate);
            if (extracted > 0 && !simulate) {
                onEnergyChanged();
            }
            return extracted;
        }

        private void onEnergyChanged() {
            // позже добавим обновление GUI
        }
    }
}