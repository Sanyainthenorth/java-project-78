package hexlet.code.schemas;

public class StringSchema {
    private boolean isRequired = false;
    private int minLength = 0;
    private String substring = null;
    public StringSchema required() {
        this.isRequired = true;
        return this;
    }
    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;

    }
    public StringSchema contains(String str) {
        this.substring = str;
        return this;
    }
    public boolean isValid(String value) {
        if (isRequired && (value == null || value.isEmpty())) {
            return false;
        }

        if (minLength > 0 && value.length() < minLength) {
            return false;
        }

        if (substring != null && !value.contains(substring)) {
            return false;
        }

        return true;
    }
}
