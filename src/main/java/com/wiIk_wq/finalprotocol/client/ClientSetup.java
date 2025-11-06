package com.wiIk_wq.finalprotocol.client;

import com.wiIk_wq.finalprotocol.menu.FinalProtocolStorageMenu;
import com.wiIk_wq.finalprotocol.client.FinalProtocolStorageScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraft.client.gui.screens.MenuScreens;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(FinalProtocolStorageMenu.FINAL_PROTOCOL_STORAGE_MENU.get(),
                    FinalProtocolStorageScreen::new);
        });
    }
}