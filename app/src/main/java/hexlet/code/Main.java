package hexlet.code;
import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Main {
    public static void main(String[] args) {
        // Тестирование StringSchema
        Validator v1 = new Validator();
        StringSchema schema = v1.string();

        System.out.println("String tests:");
        System.out.println(schema.isValid(""));      // true
        System.out.println(schema.isValid(null));    // true

        schema.required();
        System.out.println(schema.isValid(null));    // false
        System.out.println(schema.isValid(""));      // false
        System.out.println(schema.isValid("what does the fox say")); // true
        System.out.println(schema.isValid("hexlet")); // true

        System.out.println(schema.contains("wh").isValid("what does the fox say")); // true
        System.out.println(schema.contains("what").isValid("what does the fox say")); // true
        System.out.println(schema.contains("whatthe").isValid("what does the fox say")); // false
        System.out.println(schema.isValid("what does the fox say")); // false

        StringSchema schema1 = v1.string();
        System.out.println(schema1.minLength(10).minLength(4).isValid("Hexlet")); // true

        // Тестирование NumberSchema
        Validator v2 = new Validator();
        NumberSchema numSchema = v2.number();

        System.out.println("\nNumber tests:");
        System.out.println(numSchema.isValid(5));     // true
        System.out.println(numSchema.isValid(null));  // true
        System.out.println(numSchema.positive().isValid(null)); // true

        numSchema.required();
        System.out.println(numSchema.isValid(null));  // false
        System.out.println(numSchema.isValid(10));    // true
        System.out.println(numSchema.isValid(-10));   // false
        System.out.println(numSchema.isValid(0));     // false

        numSchema.range(5, 10);
        System.out.println(numSchema.isValid(5));     // true
        System.out.println(numSchema.isValid(10));    // true
        System.out.println(numSchema.isValid(4));     // false
        System.out.println(numSchema.isValid(11));    // false
    }
}
