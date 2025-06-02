package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    private final List<Predicate<String>> checks = new ArrayList<>();
    private Integer minLength = null;
    private final List<String> containsList = new ArrayList<>();

    @Override
    public StringSchema required() {
        this.isRequired = true;
        checks.add(value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        checks.add(value -> value == null || value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        containsList.add(substring);
        checks.add(value -> {
            if (value == null) return true;
            for (String s : containsList) {
                if (!value.contains(s)) return false;
            }
            return true;
        });
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


