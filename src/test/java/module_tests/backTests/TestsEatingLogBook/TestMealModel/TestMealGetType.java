package module_tests.backTests.TestsEatingLogBook.TestMealModel;


import com.cygans.database.eating_log_book.meal.Meal;
import com.cygans.database.eating_log_book.meal.MealType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тест метода getType() в Meal
 */
public class TestMealGetType {

    /**
     * Проверяет вызов метода для объекта с type = null
     */
    @Test
    public void testGetTypeNull() {
        Meal meal = new Meal();

        assertNull(meal.getType(), "Тип должен быть null");
    }

    /**
     * Проверяет вызов метода для объекта с существующим type
     */
    @Test
    public void testGetTypeNotNull() {
        String validType = MealType.BREAKFAST.getText();
        Meal meal = new Meal(validType);

        assertEquals(validType, meal.getType(), "Тип не соответствует ожидаемому значению");
    }
}
