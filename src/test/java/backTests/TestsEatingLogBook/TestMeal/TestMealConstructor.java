package backTests.TestsEatingLogBook.TestMeal;

import com.cygans.database.eating_log_book.meal.Meal;
import com.cygans.database.eating_log_book.meal.MealType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тесты для конструктора класса Meal
 */
public class TestMealConstructor {

    /**
     * Проверяет конструктор без параметров
     */
    @Test
    public void testDefaultConstructor() {
        Meal meal = new Meal();

        assertNotNull(meal, "Объект не должен быть null");
        assertNull(meal.getId(), "Id должен быть null");
        assertNull(meal.getType(), "Тип должен быть null");
    }

    /**
     * Проверяет вызов конструктора с существующим типом
     */
    @Test
    public void testConstructorWithValidType() {
        String validType = MealType.BREAKFAST.getText();

        Meal meal = new Meal(validType);

        assertNotNull(meal, "Объект не должен быть null");
        assertNull(meal.getId(), "Id должен быть null");
        assertNotNull(meal.getType(), "Тип не должен быть null");
        assertEquals(validType, meal.getType(), "Тип должен совпадать с переданным значением");
    }

    /**
     * Проверяет вызов конструктора с типом null
     */
    @Test
    public void testConstructorWithNullType() {
        assertThrows(IllegalArgumentException.class, () -> new Meal(null),
                "Ожидалось исключение IllegalArgumentException при передаче недопустимого типа");
    }

    /**
     * Проверяет вызов конструктора с пустым типом
     */
    @Test
    public void testConstructorWithEmptyType() {
        assertThrows(IllegalArgumentException.class, () -> new Meal(""),
                "Ожидалось исключение IllegalArgumentException при передаче недопустимого типа");
    }
}

