package TestEatingLogBook;

import com.cygans.database.eating_log_book.EatingLogBook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тесты для метода setMealId() класса EatingLogBook
 */
public class TestEatingLogBookSetMealId {
    /**
     * Проверяет, что метод корректно устанавливает значение mealId
     */
    @Test
    public void testSetMealIdWithValidValue() {
        EatingLogBook eatingLogBook = new EatingLogBook();
        long mealId = 1L;

        eatingLogBook.setMealId(mealId);

        assertEquals(mealId, eatingLogBook.getMealId(), "Метод setMealId() не установил правильное значение");
    }

    /**
     * Проверяет, что метод корректно устанавливает значение mealId после изменения
     */
    @Test
    public void testSetMealIdAfterChange() {
        EatingLogBook eatingLogBook = new EatingLogBook();
        long initialMealId = 2L;
        long newMealId = 3L;

        eatingLogBook.setMealId(initialMealId);
        assertEquals(initialMealId, eatingLogBook.getMealId(), "Метод setMealId() не установил правильное значение после первой установки значения");

        eatingLogBook.setMealId(newMealId);
        assertEquals(newMealId, eatingLogBook.getMealId(), "Метод setMealId() не установил правильное значение после изменения");
    }

    /**
     * Проверяет, что получим исключение при установке отрицательного значения mealId
     */
    @Test
    public void testSetMealIdWithNegativeValue() {
        EatingLogBook eatingLogBook = new EatingLogBook();
        assertThrows(IllegalArgumentException.class, () -> eatingLogBook.setMealId(-1),
                "Метод должен бросить исключение для отрицательных значений MealId");
    }

    /**
     * Проверяет, что метод корректно устанавливает значение mealId при максимальном значении
     */
    @Test
    public void testSetMealIdWithMaxValue() {
        EatingLogBook eatingLogBook = new EatingLogBook();
        long maxMealId = Long.MAX_VALUE;

        eatingLogBook.setMealId(maxMealId);

        assertEquals(maxMealId, eatingLogBook.getMealId(), "Метод setMealId() не установил правильное максимальное значение");
    }
}
