package hexlet.code.schemas;

/**
 * Базовый класс схемы валидации.
 *
 * @param <T> тип данных, который валидируется схемой
 */
public abstract class BaseSchema<T> {
    protected boolean isRequired = false;

    /**
     * Проверяет валидность переданного значения по правилам схемы.
     *
     * @param value значение для проверки
     * @return {@code true}, если значение валидно, иначе {@code false}
     */
    public abstract boolean isValid(Object value);

    /**
     * Отмечает схему как обязательную для заполнения.
     *
     * <p>Этот метод может быть переопределён в подклассах для изменения поведения,
     * например, чтобы добавить дополнительные проверки или логику.
     *
     * @return текущий объект схемы для поддержки цепочки вызовов
     */
    public BaseSchema<T> required() {
        this.isRequired = true;
        return this;
    }
}


