package hexlet.code;
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;

public class Main {
    public static void main(String[] args) {
        var v = new Validator();

        var schema = v.string();

        // Пока не вызван метод required(), null и пустая строка считаются валидным
        schema.isValid(""); // true
        schema.isValid(null); // true

        schema.required();

        schema.isValid(null); // false
        schema.isValid(""); // false
        schema.isValid("what does the fox say"); // true
        schema.isValid("hexlet"); // true

        schema.contains("wh").isValid("what does the fox say"); // true
        schema.contains("what").isValid("what does the fox say"); // true
        schema.contains("whatthe").isValid("what does the fox say"); // false

        schema.isValid("what does the fox say"); // false
// Здесь уже false, так как добавлена еще одна проверка contains("whatthe")

// Если один валидатор вызывался несколько раз
// то последний имеет приоритет (перетирает предыдущий)
        var schema1 = v.string();
        schema1.minLength(10).minLength(4).isValid("Hexlet"); // true
    }
}
