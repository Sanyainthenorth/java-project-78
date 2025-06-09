package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        checks.put("required", str -> str != null && !str.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        checks.put("minLength", str -> str == null || str.isEmpty() || str.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        checks.put("contains", str -> str == null || str.isEmpty() || str.contains(substring));
        return this;
    }
}



