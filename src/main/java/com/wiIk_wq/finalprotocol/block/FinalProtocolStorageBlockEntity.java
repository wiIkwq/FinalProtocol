package com.wiIk_wq.finalprotocol.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FinalProtocolStorageBlockEntity extends BlockEntity {
    public FinalProtocolStorageBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FINAL_PROTOCOL_STORAGE_ENTITY.get(), pos, state);
    }
}