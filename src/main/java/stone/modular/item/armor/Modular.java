package stone.modular.item.armor;

import net.minecraft.world.item.ItemStack;

import java.util.Set;

public interface Modular {

    String MODULE_LIST = "modules";

    void addModule(ItemStack stack, Module mod);

    void addModules(ItemStack stack, Set<Module> mods);

    Set<Module> getModules(ItemStack stack);

}