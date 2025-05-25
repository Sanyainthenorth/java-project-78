package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    private boolean mustBePositive = false;
    private Integer rangeMin = null;
    private Integer rangeMax = null;

    @Override
    public boolean isValid(Integer value) {
        if (value == null) {
            return !isRequired;
        }

        if (mustBePositive && value <= 0) {
            return false;
        }

        if (rangeMin != null && value < rangeMin) {
            return false;
        }

        if (rangeMax != null && value > rangeMax) {
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

