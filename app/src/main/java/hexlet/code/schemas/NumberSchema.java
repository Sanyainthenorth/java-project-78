package hexlet.code.schemas;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {

    private final Map<String, Predicate<Integer>> checks = new HashMap<>();

    @Override
    public boolean isValid(Object value) {
        if (!(value instanceof Integer)) {
            return !isRequired;
        }

        Integer number = (Integer) value;

        if (isRequired && number == null) {
            return false;
        }

        return checks.values().stream()
                     .allMatch(check -> check.test(number));
    }

    public NumberSchema positive() {
        Predicate<Integer> positiveCheck = num -> num > 0;
        checks.put("positiveCheck", positiveCheck);
        return this;
    }

    public NumberSchema range(int min, int max) {
        Predicate<Integer> rangeCheck = num -> num >= min && num <= max;
        checks.put("rangeCheck", rangeCheck);
        return this;
    }
}

