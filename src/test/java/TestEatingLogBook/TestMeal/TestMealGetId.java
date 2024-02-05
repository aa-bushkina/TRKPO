package TestEatingLogBook.TestMeal;

import com.cygans.database.eating_log_book.meal.Meal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тест метода getId() в Meal
 */
public class TestMealGetId {

    /**
     * Проверяет вызов метода для объекта с id = null
     */
    @Test
    public void testGetIdNull() {
        Meal meal = new Meal();

        assertNull(meal.getId(), "Id должен быть null");
    }
}
