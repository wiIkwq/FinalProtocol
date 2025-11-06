package com.wiIk_wq.finalprotocol;

import com.mojang.logging.LogUtils;
import com.wiIk_wq.finalprotocol.block.ModBlocks;
import com.wiIk_wq.finalprotocol.block.ModBlockEntities;
import com.wiIk_wq.finalprotocol.item.ModItems;
import com.wiIk_wq.finalprotocol.menu.FinalProtocolStorageMenu;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(FinalProtocol.MOD_ID)
public class FinalProtocol {
    public static final String MOD_ID = "finalprotocol";
    private static final Logger LOGGER = LogUtils.getLogger();

    public FinalProtocol() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // ✅ Register everything
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModItems.register(modEventBus);
        FinalProtocolStorageMenu.MENUS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("Final Protocol initialized successfully!");
    }
}