package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    private Integer minLength = null;
    private String substring = null;

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;

    }
    public StringSchema contains(String str) {
        this.substring = str;
        return this;
    }
    @Override
    protected boolean validateValue(String value) {
        if (isRequired && value.isEmpty()) {
            return false;
        }
        if (minLength != null && value.length() < minLength) {
            return false;
        }
        if (substring != null && !value.contains(substring)) {
            return false;
        }
        return true;
    }
}
