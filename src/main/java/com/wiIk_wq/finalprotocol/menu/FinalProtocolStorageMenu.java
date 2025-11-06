package com.wiIk_wq.finalprotocol.menu;

import com.wiIk_wq.finalprotocol.FinalProtocol;
import com.wiIk_wq.finalprotocol.block.FinalProtocolStorageBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FinalProtocolStorageMenu extends AbstractContainerMenu {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, FinalProtocol.MOD_ID);

    public static final RegistryObject<MenuType<FinalProtocolStorageMenu>> FINAL_PROTOCOL_STORAGE_MENU =
            MENUS.register("final_protocol_storage_menu",
                    () -> IForgeMenuType.create(FinalProtocolStorageMenu::new));

    private final FinalProtocolStorageBlockEntity blockEntity;
    private final Level level;

    public FinalProtocolStorageMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv, inv.player.level(), buf.readBlockPos());
    }

    public FinalProtocolStorageMenu(int id, Inventory inv, Level level, BlockPos pos) {
        super(FINAL_PROTOCOL_STORAGE_MENU.get(), id);
        this.level = level;
        this.blockEntity = (FinalProtocolStorageBlockEntity) level.getBlockEntity(pos);

        if (blockEntity != null) {
            blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                // 9 основных слотов — в линию
                for (int i = 0; i < 9; i++) {
                    this.addSlot(new SlotItemHandler(handler, i, 44 + i * 18, 35));
                }
                // 2 Core-слота справа
                this.addSlot(new SlotItemHandler(handler, 9, 200, 35));  // input
                this.addSlot(new SlotItemHandler(handler, 10, 220, 35)); // output
            });
        }

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    private void addPlayerInventory(Inventory playerInventory) {
        int startY = 84;
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9,
                        8 + col * 18, startY + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        int y = 142;
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, y));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}