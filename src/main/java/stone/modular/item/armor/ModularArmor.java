package stone.modular.item.armor;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import stone.modular.ModularMod;

public abstract class ModularArmor extends Item implements Equipable {

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
}
