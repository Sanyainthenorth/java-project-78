package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Базовый класс схемы валидации.
 *
 * @param <T> тип данных, который валидируется схемой
 */
public abstract class BaseSchema<T> {
    protected final Map<String, Predicate<T>> checks = new HashMap<>();

    /**
     * Проверяет валидность переданного значения по правилам схемы.
     *
     * @param value значение для проверки
     * @return {@code true}, если значение валидно, иначе {@code false}
     */
    public boolean isValid(T value) {
        if (value == null) {
            return !checks.containsKey("required");
        }

        return checks.values().stream()
                     .allMatch(check -> check.test(value));
    }

    /**
     * Отмечает схему как обязательную для заполнения.
     *
     * @return текущий объект схемы для поддержки цепочки вызовов
     */
    public BaseSchema<T> required() {
        Predicate<T> requiredCheck = this::isRequiredValid;
        checks.put("required", requiredCheck);
        return this;
    }

    /**
     * Проверка обязательности (по умолчанию — значение не null).
     * Подклассы могут переопределить для более специфичной логики.
     *
     * @param value значение для проверки
     * @return {@code true}, если значение соответствует требованию required
     */
    protected boolean isRequiredValid(T value) {
        return value != null;
    }
}




