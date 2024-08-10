package stone.modular.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import stone.modular.client.gui.menu.ArmorModuleMenu;

public class ArmorModuleScreen extends AbstractContainerScreen<ArmorModuleMenu> {


    public ArmorModuleScreen(ArmorModuleMenu pMenu, Inventory pPlayerInventory) {
        super(pMenu, pPlayerInventory, Component.literal("Modules"));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX,
        int pMouseY) {

    }

}
