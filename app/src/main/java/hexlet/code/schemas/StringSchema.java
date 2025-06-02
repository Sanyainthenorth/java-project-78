package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    private final List<Predicate<String>> rules = new ArrayList<>();

    @Override
    public StringSchema required() {
        this.isRequired = true;
        this.rules.clear();
        rules.add(Objects::nonNull);
        rules.add(value -> !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        rules.add(value -> value == null || value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        rules.add(value -> value == null || value.contains(substring));
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

        if (rules.isEmpty()) {
            return true;
        }

        for (Predicate<String> rule : rules) {
            if (!rule.test(stringValue)) {
                return false;
            }
        }

        return true;
    }
}


