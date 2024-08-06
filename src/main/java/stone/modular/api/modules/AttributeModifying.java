package stone.modular.api.modules;

public interface AttributeModifying {

    /**
     * Enum representing the priority that AttributeModification should take
     * 
     * note for tomorrow, maybe split this to a standalone enum to use for all my
     * priority needs
     */
    public static enum ModificationPriority implements Comparable<ModificationPriority> {
        // order of this matters, it's what determines the sorting of these for
        // Comparable
        FIRST, ADDITIVE, MULTIPLICATIVE, POST_MULTIPLICATIVE, FINAL;
    }
}
