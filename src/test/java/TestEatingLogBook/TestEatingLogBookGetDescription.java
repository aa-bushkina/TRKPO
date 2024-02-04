package TestEatingLogBook;

import com.cygans.database.eating_log_book.EatingLogBook;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для метода getDescription() класса EatingLogBook
 */
public class TestEatingLogBookGetDescription {
    /**
     * Проверяет, что метод возвращает корректное значение description
     */
    @Test
    public void testGetDescription() {
        String description = "Test description";
        EatingLogBook eatingLogBook = new EatingLogBook();
        eatingLogBook.setDescription(description);

        assertEquals(description, eatingLogBook.getDescription(), "Метод getDescription() возвращает неверное значение");
    }

    /**
     * Проверяет, что метод возвращает null, если description установлен в null
     */
    @Test
    public void testGetDescriptionWithNull() {
        EatingLogBook eatingLogBook = new EatingLogBook();
        eatingLogBook.setDescription(null);

        assertEquals(null, eatingLogBook.getDescription(), "Метод getDescription() возвращает неверное значение");
    }

    /**
     * Проверяет, что метод возвращает корректное значение description после изменения
     */
    @Test
    public void testGetDescriptionAfterChange() {
        String initialDescription = "Initial description";
        String newDescription = "New description";
        EatingLogBook eatingLogBook = new EatingLogBook();
        eatingLogBook.setDescription(initialDescription);

        eatingLogBook.setDescription(newDescription);

        assertEquals(newDescription, eatingLogBook.getDescription(), "Метод getDescription() возвращает неверное значение после изменения");
    }
}
