package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    private final List<Predicate<String>> checks = new ArrayList<>();

    @Override
    public StringSchema required() {
        this.isRequired = true;
        checks.add(value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        checks.add(value -> value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        checks.add(value -> value.contains(substring));
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return !isRequired;
        }

        if (!(value instanceof String)) {
            return false;
        }

        String stringValue = (String) value;

        for (Predicate<String> check : checks) {
            if (!check.test(stringValue)) {
                return false;
            }
        }

        return true;
    }
}


