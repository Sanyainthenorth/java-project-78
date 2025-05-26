package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema<T> extends BaseSchema<Map<String, T>> {
    private Integer expectedSize = null;
    private Map<String, BaseSchema<T>> shapeSchemas;

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return !isRequired;
        }

        if (!(value instanceof Map)) {
            return false;
        }

        Map<?, ?> mapValue = (Map<?, ?>) value;

        if (expectedSize != null && mapValue.size() != expectedSize) {
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
        this.expectedSize = size;
        return this;
    }

    public void shape(Map<String, BaseSchema<T>> schemas) {
        this.shapeSchemas = schemas;
    }
}





