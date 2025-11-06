package com.wiIk_wq.finalprotocol.block;

import com.wiIk_wq.finalprotocol.FinalProtocol;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, FinalProtocol.MOD_ID);

    public static final RegistryObject<BlockEntityType<FinalProtocolStorageBlockEntity>> FINAL_PROTOCOL_STORAGE_ENTITY =
            BLOCK_ENTITIES.register("final_protocol_storage_entity",
                    () -> BlockEntityType.Builder.of(
                            FinalProtocolStorageBlockEntity::new,
                            ModBlocks.FINAL_PROTOCOL_STORAGE.get()
                    ).build(null));
}