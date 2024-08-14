package stone.modular.item.armor;

import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import stone.modular.ModularMod;
import stone.modular.api.modules.AttributeModifying;
import stone.modular.api.modules.Module;
import stone.modular.api.modules.ModuleRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class ModularArmor extends Item implements Equipable {

    public static final String MODULE_LIST = "modules";
    @Override
    public int getEnchantmentLevel(ItemStack stack, Enchantment enchantment) {
        // prevent the player from taking off the super good armor,
        // the developer knows best!
        if (enchantment == Enchantments.BINDING_CURSE)
        {
            return 1;
        } else
        {
            return super.getEnchantmentLevel(stack, enchantment);
        }
    }

    public ModularArmor() {
        // TODO make dropped items invincible like the nether star
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        return false;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_NETHERITE;
    }

    @Override
    public Entity createEntity(Level level, Entity location, ItemStack stack) {
        ModularMod.LOGGER.info("creating Entity");
        // Vec3 pos = location.position();
        // Vec3 mov = location.getDeltaMovement();
        // ItemEntity entity = new ItemEntity(level, pos.x, pos.y, pos.z, stack, mov.x,
        // mov.y, mov.z);
        // entity.setUnlimitedLifetime();
        // entity.setInvulnerable(true);
        if (location instanceof ItemEntity item)
        {
            item.setInvulnerable(true);
            item.setUnlimitedLifetime();
        }
        return null;
    }

    // TODO try not to do so much switching between NBT and Java objects, probably
    // has to be done in the GUI or something

    public void addModule(ItemStack stack, Module mod) {
        addModules(stack, Set.of(mod));
    }

    public void addModules(ItemStack stack, Set<Module> mods) {
        CompoundTag tag = stack.getOrCreateTag();
        ListTag list = tag.getList(MODULE_LIST, Tag.TAG_STRING);
        final Set<Module> currentMods = getModules(stack);
        for (Module mod : mods)
        {
            if (!currentMods.contains(mod))
            {
                list.add(StringTag.valueOf(ModuleRegistry.toKey.get(mod)));
            }
        }
        tag.put(MODULE_LIST, list);
        stack.setTag(tag);
        refreshEffects(stack);
    }

    public void refreshEffects(ItemStack stack) {
        Set<Module> mods = getModules(stack);
        
        // split this into methods definitely, maybe a registry for module types to allow arbitrary modules to be registered? idk
        Map<Attribute, NavigableSet<AttributeModifying>> attributeModules = new HashMap<>();
        Object2DoubleMap<Attribute> attributes = new Object2DoubleOpenHashMap<>();
        mods
            .stream()
            .filter(mod -> mod instanceof AttributeModifying)
            .map(mod -> (AttributeModifying) mod)
            .forEach(mod -> attributeModules
                .computeIfAbsent(mod.getAttribute(),
                    $ -> new TreeSet<>())
                .add((AttributeModifying) mod));

        for (var entry : attributeModules.entrySet())
        {
            // does the player just use the defaults always?
            // what if we want this to affect other mod's attribute buffs?
            double value = entry.getKey().getDefaultValue();
            for (var mod : entry.getValue())
            {
                value = mod.modifyAttribute(value);
            }
            // deterministic yet random uuid
            Random rand = new Random((long) entry.getKey().getDescriptionId().hashCode());
            UUID uuid = new UUID(rand.nextLong(), rand.nextLong());
            // maybe use Minecraft's default attribute modifiers instead?
            // would play nicer, but has less modifier types (but how many do we really
            // need?)
            // also how do you remove attribute modifiers??
            stack
                .addAttributeModifier(entry.getKey(),
                    new AttributeModifier(uuid, "modular_attribute_modifier", value,
                        AttributeModifier.Operation.ADDITION),
                    getEquipmentSlot());
        }
    }

    public Set<Module> getModules(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        return tag.getList(MODULE_LIST, Tag.TAG_STRING)
            .stream()
            .map(string -> ModuleRegistry.toModule.get(string.getAsString()))
            .collect(Collectors.toSet());
    }
}
