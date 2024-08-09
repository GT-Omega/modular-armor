package stone.modular.core;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import stone.modular.ModularMod;
import stone.modular.item.armor.ModularBoots;
import stone.modular.item.armor.ModularChestplate;
import stone.modular.item.armor.ModularHelmet;
import stone.modular.item.armor.ModularLeggings;
import stone.modular.item.module.attribute.AttributeModule;

public abstract class ModularItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister
        .create(ForgeRegistries.ITEMS, ModularMod.MODID);

    public static RegistryObject<ModularHelmet> MODULAR_HELMET = ITEMS.register("modular_helmet",
        ModularHelmet::new);
    public static RegistryObject<ModularChestplate> MODULAR_CHESTPLATE = ITEMS
        .register("modular_chestplate", ModularChestplate::new);
    public static RegistryObject<ModularLeggings> MODULAR_LEGGINGS = ITEMS.register(
        "modular_leggings",
        ModularLeggings::new);
    public static RegistryObject<ModularBoots> MODULAR_BOOTS = ITEMS.register("modular_boots",
        ModularBoots::new);

    public static RegistryObject<AttributeModule> MODULE_ARMOR = ITEMS.register("module_armor",
        () -> new AttributeModule.Additive(EquipmentSlot.CHEST, Attributes.ARMOR, 10));

    public static void init(IEventBus bus) {
        ITEMS.register(bus);
    };
}
