package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

    @Test
    public void testStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));

        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("hexlet"));

        schema.minLength(5);
        assertFalse(schema.isValid("hex"));
        assertTrue(schema.isValid("hexlet"));

        schema.contains("hex");
        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid("let"));
    }

    @Test
    public void testNumberSchema() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));

        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));

        schema.positive();
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(10));

        schema.range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    public void testMapSchema() {
        Validator v = new Validator();
        MapSchema<String> schema = v.map();
        assertTrue(schema.isValid(null));
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

        schema.sizeof(2);
        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
        data.put("key3", "value3");
        assertFalse(schema.isValid(data));
    }

    @Test
    public void testShapeValidation() {
        Validator v = new Validator();
        MapSchema<String> schema = v.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1)); // true

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2)); // false

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3)); // false
    }

    @Test
    void testEmptySchema() {
        Validator v = new Validator();
        MapSchema<String> schema = v.map();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
        assertTrue(schema.isValid(Map.of("key", "value")));
    }

    @Test
    public void testNullIsValidWhenNotRequiredEvenWithOtherChecks() {
        Validator v = new Validator();
        StringSchema schema = v.string().minLength(10).contains("abc");

        assertTrue(schema.isValid(null), "null должен быть валиден, если required не установлен");
    }

    @Test
    public void testNullIsValidWhenNotRequiredEvenWithOtherChecksNumber() {
        Validator v = new Validator();
        NumberSchema schema = v.number().positive().range(1, 100);

        assertTrue(schema.isValid(null), "null должен быть валиден, если required не установлен");
    }

    @Test
    public void testNullIsValidWhenNotRequiredEvenWithOtherChecksMap() {
        Validator v = new Validator();
        MapSchema<String> schema = v.map().sizeof(2);

        assertTrue(schema.isValid(null), "null должен быть валиден, если required не установлен");
    }

    @Test
    public void testNullIsInvalidWhenRequiredWithOtherChecks() {
        Validator v = new Validator();
        StringSchema schema = v.string().required().minLength(5);

        assertFalse(schema.isValid(null), "null не должен быть валиден, если required установлен");
    }

    @Test
    public void testEmptyStringWithMinLength() {
        Validator v = new Validator();
        StringSchema schema = v.string().minLength(1);

        assertTrue(schema.isValid(""), "пустая строка валидна, если required не установлен");

        schema.required();
        assertFalse(schema.isValid(""), "пустая строка невалидна, если required установлен");
    }

    @Test
    public void testShapeAllowsNullValuesWhenNotRequired() {
        Validator v = new Validator();
        MapSchema<String> schema = v.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string());  // не required
        schemas.put("lastName", v.string().minLength(2)); // не required

        schema.shape(schemas);

        Map<String, String> human = new HashMap<>();
        human.put("firstName", null);
        human.put("lastName", null);

        assertTrue(schema.isValid(human), "null значения должны быть валидны, если required не установлен");
    }
}
