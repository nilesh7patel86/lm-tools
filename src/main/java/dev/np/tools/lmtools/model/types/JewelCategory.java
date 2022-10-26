package dev.np.tools.lmtools.model.types;

import java.util.HashMap;
import java.util.Map;

public enum JewelCategory {
    MONSTER("Monster"),
    STANDARD("Standard"),
    ASSAULT("Assault"),
    DRAGON("Dragon"),
    FEUDAL("Feudal"),
    BUNDLE("Bundle"),
    TURF("Turf"),
    ROYALE("Royale"),
    ;

    private final String desc;
    private static final Map<String, JewelCategory> map = new HashMap<>(values().length);
    static {
        for (JewelCategory g : values()) {
            map.put(g.desc, g);
        }
    }

    public static JewelCategory of(String name) {
        JewelCategory result = map.get(name);
        if (result == null)
            throw new IllegalArgumentException("Invalid value");
        return result;
    }
    JewelCategory(String desc) {
        this.desc = desc;
    }

    public String desc() {
        return desc;
    }
}
