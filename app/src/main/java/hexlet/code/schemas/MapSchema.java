package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema<T> extends BaseSchema<Map<String, T>> {

    public MapSchema<T> sizeof(int size) {
        checks.put("sizeof", map -> map.size() == size);
        return this;
    }

    public MapSchema<T> shape(Map<String, BaseSchema<T>> schemas) {
        checks.put("shape", map -> checkShape(map, schemas));
        return this;
    }

    private boolean checkShape(Map<String, T> map, Map<String, BaseSchema<T>> schemas) {
        for (var entry : schemas.entrySet()) {
            String key = entry.getKey();
            BaseSchema<T> schema = entry.getValue();
            T val = map.get(key);
            if (!schema.isValid(val)) {
                return false;
            }
        }
        return true;
    }
}









