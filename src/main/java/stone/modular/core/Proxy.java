package stone.modular.core;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import org.lwjgl.glfw.GLFW;

import stone.modular.ModularMod;
import stone.modular.client.gui.ArmorModuleScreen;

public interface Proxy {
    public void init(IEventBus bus);
    public class Client extends Server {

        public static final KeyMapping ARMOR_GUI_MAPPING = new KeyMapping(
            "key.modular_armor.armor_gui",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, KeyMapping.CATEGORY_MISC);
        
        @Override
        public void init(IEventBus bus) {
            super.init(bus);
            bus.addListener((RegisterKeyMappingsEvent event) ->
            {
                event.register(ARMOR_GUI_MAPPING);
            });

            MinecraftForge.EVENT_BUS.addListener((ClientTickEvent event) ->
            {
                if (event.phase == TickEvent.Phase.END)
                {
                        while (ARMOR_GUI_MAPPING.consumeClick())
                        {
                            ModularMod.LOGGER.info("pressing keybind!");
                            Minecraft.getInstance().setScreen(new ArmorModuleScreen());
                        }
                }
            });
        }
    }

    public class Server implements Proxy {

        @Override
        public void init(IEventBus bus) {
            ModularItems.init(bus);
        }

    }
}
