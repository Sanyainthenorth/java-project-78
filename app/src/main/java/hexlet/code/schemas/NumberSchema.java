package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean mustBePositive = false;
    private Integer minRange = null;
    private Integer maxRange = null;


    public NumberSchema positive() {
        this.mustBePositive = true;
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.minRange = min;
        this.maxRange = max;
        return this;
    }
    @Override
    protected boolean validateValue(Integer value) {
        if (mustBePositive && value <= 0) {
            return false;
        }
        if (minRange != null && value < minRange) {
            return false;
        }
        if (maxRange != null && value > maxRange) {
            return false;
        }
        return true;
    }
}
