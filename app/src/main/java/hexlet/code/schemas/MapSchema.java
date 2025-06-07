package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema<T> extends BaseSchema<Map<String, T>> {

    @Override
    protected boolean isValidType(Object value) {
        return value instanceof Map;
    }

    public MapSchema<T> sizeof(int size) {
        checks.put("sizeCheck", map -> map != null && map.size() == size);
        return this;
    }

    public MapSchema<T> shape(Map<String, BaseSchema<T>> schemas) {
        checks.put("shapeCheck", map -> {
            if (map == null) {
                return false;
            }
            for (var entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<T> schema = entry.getValue();
                T val = map.get(key);
                if (!schema.isValid(val)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}







