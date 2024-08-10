package stone.modular.client.gui.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import stone.modular.core.ModularMenus;

public class ArmorModuleMenu extends AbstractContainerMenu {

    public ArmorModuleMenu(int pContainerId, Inventory pPlayerInventory) {
        super(ModularMenus.ARMOR_MODULE_MENU.get(), pContainerId);

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlot(new Slot(pPlayerInventory, j + i * 9 + 9, 108 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlot(new Slot(pPlayerInventory, k, 108 + k * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }

}
