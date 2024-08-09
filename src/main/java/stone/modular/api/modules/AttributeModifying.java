package stone.modular.api.modules;

import net.minecraft.world.entity.ai.attributes.Attribute;

public interface AttributeModifying extends Comparable<AttributeModifying>, Module {

    /**
     * @return the attribute being modified
     */
    Attribute getAttribute();

    ModificationPriority getPriority();
    
    double modifyAttribute(double old);

    public default int compareTo(AttributeModifying other) {
        return this.getPriority().compareTo(other.getPriority());
    }

    /**
     * Enum representing the priority that AttributeModification should take
     * 
     * This enum is NOT exhaustive, add in additional levels as needed
     */
    public static enum ModificationPriority implements Comparable<ModificationPriority> {
        // order of this matters, it's what determines the sorting of these for
        // Comparable
        /**
         * For modifying the base attribute value
         */
        GOOD_ADDITIVE,
        /**
         * For buffing the attribute's value after its been added too
         */
        MULTIPLICATIVE,
        /**
         * For modifying the attributes value after its been buffed
         */
        BAD_ADDITIVE;
    }
}
