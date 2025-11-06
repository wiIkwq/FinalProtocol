package com.wiIk_wq.finalprotocol.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.wiIk_wq.finalprotocol.menu.FinalProtocolStorageMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class FinalProtocolStorageScreen extends AbstractContainerScreen<FinalProtocolStorageMenu> {

    private static final ResourceLocation CHEST_BG = new ResourceLocation("minecraft", "textures/gui/container/generic_54.png");
    private static final ResourceLocation WIDGETS = new ResourceLocation("minecraft", "textures/gui/widgets.png");

    public FinalProtocolStorageScreen(FinalProtocolStorageMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 210;
        this.imageHeight = 200;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        // 📦 Отрисовка нижней части generic_54.png (инвентарь игрока)
        RenderSystem.setShaderTexture(0, CHEST_BG);
        int chestPartHeight = 96; // нижние три ряда
        guiGraphics.blit(CHEST_BG, x, y + 100, 0, 126, this.imageWidth - 34, chestPartHeight);

        // ⚡ Энергия (слева)
        guiGraphics.fill(x + 15, y + 25, x + 20, y + 100, 0xFF222222);
        guiGraphics.fill(x + 15, y + 70, x + 20, y + 100, 0xFF00FF00);

        // 🔳 9 основных слотов (в линию)
        RenderSystem.setShaderTexture(0, WIDGETS);
        int slotStartX = x + 40;
        int slotStartY = y + 50;
        for (int i = 0; i < 9; i++) {
            guiGraphics.blit(WIDGETS, slotStartX + i * 18, slotStartY, 1, 23, 22, 22, 256, 256);
        }

        // 🔘 Core Input / Output
        int coreY = slotStartY + 40;
        guiGraphics.blit(WIDGETS, x + 185, coreY, 1, 23, 22, 22, 256, 256);
        guiGraphics.blit(WIDGETS, x + 210, coreY, 1, 23, 22, 22, 256, 256);
        guiGraphics.fill(x + 205, coreY + 10, x + 208, coreY + 12, 0xFFFFFFFF);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(font, "Final Protocol Storage", 10, 8, 0xE0E0E0, false);
        guiGraphics.drawString(font, "CH: 01", this.imageWidth - 50, 8, 0x00FF00, false);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}