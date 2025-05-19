package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;
    public BaseSchema<T> required() {
        this.isRequired = true;
        return this;
    }
    public boolean isValid(T value) {
        if (!isRequired && value == null) {
            return true;
        }
        if (isRequired && value == null) {
            return false;
        }
        return validateValue(value);
    }
    protected abstract boolean validateValue(T value);
}
