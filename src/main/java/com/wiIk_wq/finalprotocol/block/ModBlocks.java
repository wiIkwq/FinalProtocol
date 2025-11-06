package com.wiIk_wq.finalprotocol.block;

import com.wiIk_wq.finalprotocol.FinalProtocol;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, FinalProtocol.MOD_ID);

    public static final RegistryObject<Block> FINAL_PROTOCOL_STORAGE =
            BLOCKS.register("final_protocol_storage",
                    () -> new FinalProtocolStorageBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.METAL)
                                    .pushReaction(PushReaction.NORMAL) // ✅ заменено METAL → NORMAL
                                    .strength(6.0f, 8.0f)
                                    .sound(SoundType.METAL)
                                    .requiresCorrectToolForDrops()
                    ));
}