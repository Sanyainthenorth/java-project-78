package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class ValidatorTest {
    @Test
    public void testStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        // По умолчанию null и пустая строка валидны
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));

        // После required() null и пустая строка невалидны
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("hexlet"));

        // Проверка minLength
        schema.minLength(5);
        assertFalse(schema.isValid("hex"));
        assertTrue(schema.isValid("hexlet"));

        // Проверка contains
        schema.contains("hex");
        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid("let"));
    }

    @Test
    public void testMultipleValidators() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        // Проверка перетирания предыдущих ограничений
        schema.minLength(10).minLength(4);
        assertTrue(schema.isValid("Hexlet"));

        // Проверка приоритета последнего contains
        schema.contains("wh").contains("whatthe");
        assertFalse(schema.isValid("what does the fox say"));
    }
}
