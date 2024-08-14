package stone.modular.api.modules;

import java.util.HashMap;
import java.util.Map;

public class ModuleRegistry {
    // extremely janky, but I just want it to work rn
    // TODO replace with a forge registry, maybe even a datapack registry
    public static final Map<String, Module> toModule = new HashMap<>();
    public static final Map<Module, String> toKey = new HashMap<>();
}
