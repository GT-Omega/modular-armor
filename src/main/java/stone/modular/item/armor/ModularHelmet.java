package stone.modular.item.armor;

import net.minecraft.world.entity.EquipmentSlot;

public class ModularHelmet extends ModularArmor {

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }
}
