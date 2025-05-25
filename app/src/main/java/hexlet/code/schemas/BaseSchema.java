package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;

    public abstract boolean isValid(T value);

    public BaseSchema<T> required() {
        this.isRequired = true;
        return this;
    }
}

