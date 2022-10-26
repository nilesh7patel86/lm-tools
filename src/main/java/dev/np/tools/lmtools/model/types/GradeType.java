package dev.np.tools.lmtools.model.types;

import java.util.HashMap;
import java.util.Map;

public enum GradeType {
    COMMON("Common", 1),
    UNCOMMON("Uncommon", 2),
    RARE("Rare", 3),
    EPIC("Epic", 4),
    LEGENDARY("Legendary", 5),
    MYTHIC("Mythic", 6),
    TEMPERED("Tempered", 7),
    ;

    private final String desc;
    private final int sortOrder;
    private static final Map<String, GradeType> map = new HashMap<>(values().length);

    static {
        for (GradeType g : values()) {
            map.put(g.desc, g);
        }
    }

    public static GradeType of(String name) {
        GradeType result = map.get(name);
        if (result == null)
            throw new IllegalArgumentException("Invalid value: " + name);
        return result;
    }

    GradeType(String desc, int sortOrder) {
        this.desc = desc;
        this.sortOrder = sortOrder;
    }

    public String desc() {
        return desc;
    }

    public int sortOrder() {
        return sortOrder;
    }
}
