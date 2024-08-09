package stone.modular.client.gui;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ArmorModuleScreen extends Screen {

    public ArmorModuleScreen() {
        super(Component.literal("Module Screen Title"));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

}
