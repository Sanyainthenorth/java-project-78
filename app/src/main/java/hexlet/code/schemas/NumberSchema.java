package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema positive() {
        checks.put("positive", num -> num > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        checks.put("range", num -> num >= min && num <= max);
        return this;
    }
}

