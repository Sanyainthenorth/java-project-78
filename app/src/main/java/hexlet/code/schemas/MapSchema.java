package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    private Integer expectedSize = null;
    private Map<String, BaseSchema<?>> shapeSchemas;

    @Override
    public boolean isValid(Map<?, ?> value) {
        if (value == null) {
            return !isRequired;
        }

        if (isRequired && !(value instanceof Map)) {
            return false;
        }

        if (expectedSize != null && value.size() != expectedSize) {
            return false;
        }

        if (shapeSchemas != null) {
            for (Map.Entry<String, BaseSchema<?>> entry : shapeSchemas.entrySet()) {
                Object fieldValue = value.get(entry.getKey());
                BaseSchema schema = entry.getValue();
                if (!schema.isValid(fieldValue)) {
                    return false;
                }
            }
        }

        return true;
    }

    public MapSchema sizeof(int size) {
        this.expectedSize = size;
        return this;
    }

    public void shape(Map<String, BaseSchema<?>> schemas) {
        this.shapeSchemas = schemas;
    }
}



