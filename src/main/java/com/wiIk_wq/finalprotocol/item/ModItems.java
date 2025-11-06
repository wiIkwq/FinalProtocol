package com.wiIk_wq.finalprotocol.item;

import com.wiIk_wq.finalprotocol.FinalProtocol;
import com.wiIk_wq.finalprotocol.block.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FinalProtocol.MOD_ID);

    // ✅ регистрируем item для блока
    public static final RegistryObject<Item> FINAL_PROTOCOL_STORAGE_ITEM =
            ITEMS.register("final_protocol_storage",
                    () -> new BlockItem(ModBlocks.FINAL_PROTOCOL_STORAGE.get(),
                            new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        // Добавляем наш предмет в одну из стандартных вкладок креатива
        eventBus.addListener(ModItems::addCreative);
    }

    private static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(FINAL_PROTOCOL_STORAGE_ITEM);
        }
    }
}