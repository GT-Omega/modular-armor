package stone.modular.api.modules;

import net.minecraft.world.entity.EquipmentSlot;

/**
 * A Module to be put into modular armor
 */
public interface Module {
    EquipmentSlot getSlot();
}
