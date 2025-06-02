package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    private final Map<String, Predicate<String>> checks = new HashMap<>();

    @Override
    public StringSchema required() {
        this.isRequired = true;
        return this;
    }

    public StringSchema minLength(int length) {
        Predicate<String> minLengthCheck = str -> str.length() >= length;
        checks.put("minLength", minLengthCheck);
        return this;
    }

    public StringSchema contains(String substring) {
        Predicate<String> containsCheck = str -> str.contains(substring);
        checks.put("contains", containsCheck);
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (!(value instanceof String)) {
            return !isRequired;
        }

        String stringValue = (String) value;

        if (isRequired && stringValue.isEmpty()) {
            return false;
        }

        return checks.values().stream()
                     .allMatch(check -> check.test(stringValue));
    }
}


