package stone.modular;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import stone.modular.core.Proxy;

@Mod(ModularMod.MODID)
public class ModularMod {
    public static final String MODID = "modular_armor";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public ModularMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        switch (FMLEnvironment.dist) {
        case CLIENT:
            new Proxy.Client().init(bus);
            break;
        case DEDICATED_SERVER:
            new Proxy.Server().init(bus);
            break;
        default:
            break;
        }
    }
}
