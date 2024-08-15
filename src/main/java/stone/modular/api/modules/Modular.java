package stone.modular.api.modules;

import net.minecraft.world.item.ItemStack;

import java.util.Set;

/**
 * Some Modular item capable of accepting modules
 * 
 * Each item can only have one of each module, to make it easier to reason about
 * and simplify putting on modules
 */
public interface Modular {

    String MODULE_LIST = "modules";

    /**
     * Adds the given module to the item stack as NBT
     * 
     * @param stack  item of stack is assumed to be Modular
     * @param module the module to add
     */
    void addModule(ItemStack stack, Module module);

    /**
     * Adds multiple modules to the itemstack as NBT
     * 
     * @param stack   item of stack is assumed to be Modular
     * @param modules the modules to add
     */
    void addModules(ItemStack stack, Set<Module> modules);

    Set<Module> getModules(ItemStack stack);

}