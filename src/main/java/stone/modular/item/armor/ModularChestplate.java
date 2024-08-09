package stone.modular.item.armor;

import net.minecraft.world.entity.EquipmentSlot;

public class ModularChestplate extends ModularArmor {

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.CHEST;
    }

}
