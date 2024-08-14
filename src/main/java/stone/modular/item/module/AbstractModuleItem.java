package stone.modular.item.module;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import stone.modular.api.modules.Module;
import stone.modular.item.armor.ModularArmor;

public abstract class AbstractModuleItem extends Item implements Module {

    public AbstractModuleItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player,
        InteractionHand usedHand) {
        ItemStack armorStack = player.getItemBySlot(getSlot());
        if (armorStack.getItem() instanceof ModularArmor armor)
        {
            ItemStack stack = player.getItemInHand(usedHand);
            armor.addModule(armorStack, (Module) stack.getItem());
            stack.shrink(1);
            return InteractionResultHolder.success(stack);
        } else
        {
            return super.use(level, player, usedHand);
        }
    }

}
