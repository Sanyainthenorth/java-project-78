package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema<T> extends BaseSchema<Map<String, T>> {
    private final List<Predicate<Map<String, T>>> checks = new ArrayList<>();
    private Map<String, BaseSchema<T>> shapeSchemas;

    @Override
    public MapSchema<T> required() {
        this.isRequired = true;
        checks.add(value -> value != null);
        return this;
    }

    public MapSchema<T> sizeof(int size) {
        checks.add(value -> value == null || value.size() == size);
        return this;
    }

    public void shape(Map<String, BaseSchema<T>> schemas) {
        this.shapeSchemas = schemas;
        checks.add(map -> {
            if (map == null) return true;
            for (Map.Entry<String, BaseSchema<T>> entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<T> schema = entry.getValue();
                Object fieldValue = map.get(key);
                if (!schema.isValid(fieldValue)) {
                    return false;
                }
            }
            return true;
        });
    }

    @Override
    public boolean isValid(Object value) {
        if (!(value instanceof Map)) {
            return !isRequired;
        }

        @SuppressWarnings("unchecked")
        Map<String, T> mapValue = (Map<String, T>) value;

        for (Predicate<Map<String, T>> check : checks) {
            if (!check.test(mapValue)) {
                return false;
            }
        }

        return true;
    }
}





