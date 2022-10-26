package dev.np.tools.lmtools.model.types;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum ValueType {
    NUMBER("N"), PERCENTAGE("P");

    private final String desc;
    private static final Map<String, ValueType> map = new HashMap<>(values().length);

    static {
        Arrays.stream(values()).forEach(g -> map.put(g.desc, g));
    }

    public static ValueType of(String name) {
        ValueType result = map.get(name);
        if (result == null)
            throw new IllegalArgumentException("Invalid value");
        return result;
    }

    ValueType(String desc) {
        this.desc = desc;
    }

    public String desc() {
        return desc;
    }
}
