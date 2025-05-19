package hexlet.code;

import hexlet.code.schemas.NumberSchema;
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
    public void testNumberSchema() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        // Без required null валиден
        assertTrue(schema.isValid(null));

        // Простое число валидно
        assertTrue(schema.isValid(5));

        // После required null невалиден
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));

        // Проверка positive
        schema.positive();
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(10));

        // Проверка range
        schema.range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
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
