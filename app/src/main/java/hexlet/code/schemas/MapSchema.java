package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>>{
    private Integer requiredSize = null;
    public MapSchema sizeof(int size) {
        this.requiredSize = size;
        return this;

    }
    @Override
    protected boolean validateValue(Map<?, ?> value) {
        if (requiredSize != null && value.size() != requiredSize) {
            return false;
        }

        return true;
    }

}
