package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    @Override
    protected boolean isValidType(Object value) {
        return value instanceof Integer;
    }

    public NumberSchema positive() {
        checks.put("positiveCheck", num -> num == null || (num > 0));
        return this;
    }

    public NumberSchema range(int min, int max) {
        checks.put("rangeCheck", num -> num == null || (num >= min && num <= max));
        return this;
    }
}