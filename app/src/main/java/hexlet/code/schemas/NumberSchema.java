package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    private boolean mustBePositive = false;
    private Integer rangeMin = null;
    private Integer rangeMax = null;

    @Override
    public boolean isValid(Object value) {
        if (!(value instanceof Integer)) {
            return !isRequired;
        }

        Integer number = (Integer) value;

        if (mustBePositive && number <= 0) {
            return false;
        }

        if (rangeMin != null && number < rangeMin) {
            return false;
        }

        if (rangeMax != null && number > rangeMax) {
            return false;
        }

        return true;
    }

    public NumberSchema positive() {
        this.mustBePositive = true;
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.rangeMin = min;
        this.rangeMax = max;
        return this;
    }
}

