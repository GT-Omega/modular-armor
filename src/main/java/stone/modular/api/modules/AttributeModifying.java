package stone.modular.api.modules;

import net.minecraft.world.entity.ai.attributes.Attribute;

/**
 * A Module that modifys one of Minecraft's attributes
 * 
 * 
 */
public interface AttributeModifying extends Comparable<AttributeModifying>, Module {

    /**
     * @return the attribute to be modified
     */
    Attribute getAttribute();

    /**
     * Defines the order that modules should be calculated in. Note that within a
     * priority the order is not defined, so ensure that all the modules are
     * commutative
     * 
     * @return the priority for order of operations
     */
    ModificationPriority getPriority();
    
    /**
     * Give the old attribute's value, modify it however you please and return the
     * new value
     * 
     * @param old the current value of the attribute
     * @return the new value of the attribute
     */
    double modifyAttribute(double current);

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
