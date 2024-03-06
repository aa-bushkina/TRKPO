package module.backTests.TestsEatingLogBook.TestEatingLogBookModel;

import com.cygans.database.eating_log_book.EatingLogBook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для метода getMealId() класса EatingLogBook
 */
public class TestEatingLogBookGetMealId {
    /**
     * Проверяет, что метод возвращает корректное значение mealId для объекта с установленным значением
     */
    @Test
    public void testGetMealIdWithSetValue() {
        long mealId = 1L;
        EatingLogBook eatingLogBook = new EatingLogBook();
        eatingLogBook.setMealId(mealId);

        assertEquals(mealId, eatingLogBook.getMealId(), "Метод getMealId() вернул неверное значение");
    }

    /**
     * Проверяет, что метод возвращает 0 для объекта с неустановленным значением mealId
     */
    @Test
    public void testGetMealIdWithUnsetValue() {
        EatingLogBook eatingLogBook = new EatingLogBook();

        assertEquals(0L, eatingLogBook.getMealId(), "Метод getMealId() вернул неверное значение");
    }

    /**
     * Проверяет, что метод возвращает корректное значение mealId после изменения значения
     */
    @Test
    public void testGetMealIdAfterSetValue() {
        EatingLogBook eatingLogBook = new EatingLogBook();
        long newMealId = 2L;
        eatingLogBook.setMealId(newMealId);

        assertEquals(newMealId, eatingLogBook.getMealId(), "Метод getMealId() вернул неверное значение после изменения");
    }
}
