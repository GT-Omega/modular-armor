package stone.modular.item.module.attribute;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;

import stone.modular.api.modules.AttributeModifying;

public abstract class AttributeModule extends Item implements AttributeModifying {

    private final EquipmentSlot slot;
    private final Attribute attribute;


    public AttributeModule(EquipmentSlot slot, Attribute attribute) {
        super(new Item.Properties().stacksTo(1));
        this.slot = slot;
        this.attribute = attribute;
    }

    @Override
    public EquipmentSlot getSlot() {
        return this.slot;
    }

    @Override
    public Attribute getAttribute() {
        return this.attribute;
    }



    public static class Additive extends AttributeModule {

        private final double increase;

        public Additive(EquipmentSlot slot, Attribute attribute,
            double increase) {
            super(slot, attribute);
            this.increase = increase;
        }

        @Override
        public ModificationPriority getPriority() {
            return ModificationPriority.GOOD_ADDITIVE;
        }

        @Override
        public double modifyAttribute(double old) {
            return old + increase;
        }

    }

}
