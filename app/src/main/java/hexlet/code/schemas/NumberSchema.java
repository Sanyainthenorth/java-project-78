package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {

    private final List<Predicate<Integer>> checks = new ArrayList<>();

    @Override
    public NumberSchema required() {
        this.isRequired = true;
        checks.add(value -> value != null);
        return this;
    }

    public NumberSchema positive() {
        checks.add(value -> value == null || value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        checks.add(value -> value == null || (value >= min && value <= max));
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (!(value instanceof Integer)) {
            return !isRequired;
        }

        Integer intValue = (Integer) value;

        for (Predicate<Integer> check : checks) {
            if (!check.test(intValue)) {
                return false;
            }
        }

        return true;
    }
}

