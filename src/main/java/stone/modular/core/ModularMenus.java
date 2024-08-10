package stone.modular.core;

import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import stone.modular.ModularMod;
import stone.modular.client.gui.menu.ArmorModuleMenu;

public abstract class ModularMenus {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister
        .create(ForgeRegistries.MENU_TYPES, ModularMod.MODID);

    public static final RegistryObject<MenuType<ArmorModuleMenu>> ARMOR_MODULE_MENU = MENU_TYPES
        .register("armor_module_menu",
            () -> new MenuType<ArmorModuleMenu>(ArmorModuleMenu::new, FeatureFlags.VANILLA_SET));
}
