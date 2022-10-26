package dev.np.tools.lmtools.model.types;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum GearType {
    MAIN_HAND("Main-hand"),
    HELMET("Helmet"),
    ARMOR("Armor"),
    LEGS("Legs"),
    OFF_HAND("Off-hand"),
    ACCESSORY("Accessory");

    private final String desc;

    static Map<String, GearType> map = new HashMap<>();

    static {
        Arrays.stream(values()).forEach(gearType -> map.put(gearType.desc, gearType));
    }

    public static GearType of(String name) {
        GearType result = map.get(name);
        if (result == null)
            throw new IllegalArgumentException("Invalid value");
        return result;
    }

    GearType(String desc) {
        this.desc = desc;
    }

    public String desc() {
        return desc;
    }
}
