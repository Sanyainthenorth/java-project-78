package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    private int minLength = -1;
    private String mustContain;

    @Override
    public StringSchema required() {
        this.isRequired = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        this.mustContain = substring;
        return this;
    }

    @Override
    public boolean isValid(String value) {
        if (value == null) {
            return !isRequired;
        }

        if (isRequired && value.isEmpty()) {
            return false;
        }

        if (minLength >= 0 && value.length() < minLength) {
            return false;
        }

        if (mustContain != null && !value.contains(mustContain)) {
            return false;
        }

        return true;
    }
}


