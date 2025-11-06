package com.wiIk_wq.finalprotocol;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

/**
 * Main class for the Final Protocol mod.
 * Compatible with Forge 47.4.10 (MC 1.20.1).
 */
@Mod(FinalProtocol.MOD_ID)
public class FinalProtocol {
    public static final String MOD_ID = "finalprotocol"; // ✅ исправлено
    private static final Logger LOGGER = LogUtils.getLogger();

    public FinalProtocol() {
        // Register mod event bus
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // ✅ Future expansion: registration of items, blocks, etc.
        // ModItems.register(modEventBus);
        // ModBlocks.register(modEventBus);

        // Register global event listeners
        MinecraftForge.EVENT_BUS.register(this);

        LOGGER.info("Final Protocol initialized successfully!");
    }
}