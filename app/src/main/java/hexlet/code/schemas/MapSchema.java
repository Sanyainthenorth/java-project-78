package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema<T> extends BaseSchema<Map<String, T>> {
    private Integer expectedSize = null;
    private Map<String, BaseSchema<T>> shapeSchemas = null;
    private final Map<String, Predicate<Map<String, T>>> checks = new HashMap<>();

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return !isRequired;
        }

        if (!(value instanceof Map)) {
            return false;
        }

        Map<String, T> mapValue = (Map<String, T>) value;

        boolean checksResult = checks.values().stream()
                                     .allMatch(check -> check.test(mapValue));

        if (!checksResult) {
            return false;
        }
        if (shapeSchemas != null) {
            for (Map.Entry<String, BaseSchema<T>> entry : shapeSchemas.entrySet()) {
                Object fieldValue = mapValue.get(entry.getKey());
                BaseSchema<T> schema = entry.getValue();
                if (!schema.isValid(fieldValue)) {
                    return false;
                }
            }
        }

        return true;
    }

    public MapSchema<T> sizeof(int size) {
        Predicate<Map<String, T>> sizeCheck = map -> map.size() == size;
        checks.put("sizeCheck", sizeCheck);
        this.expectedSize = size;
        return this;
    }

    public MapSchema<T> shape(Map<String, BaseSchema<T>> schemas) {
        this.shapeSchemas = schemas;
        return this;
    }
}





