package module_tests.backTests.TestsEatingLogBook.TestEatingLogBookModel;

import com.cygans.database.eating_log_book.EatingLogBook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тесты для метода setDescription() класса EatingLogBook
 */
public class TestEatingLogBookSetDescription {
    /**
     * Проверяет, что метод устанавливает корректное значение description
     */
    @Test
    public void testSetDescription() {
        String description = "Test description";
        EatingLogBook eatingLogBook = new EatingLogBook();
        eatingLogBook.setDescription(description);

        assertEquals(description, eatingLogBook.getDescription(), "Метод setDescription() установил неверное значение");
    }

    /**
     * Проверяет, что метод устанавливает null, если передан null
     */
    @Test
    public void testSetDescriptionWithNull() {
        EatingLogBook eatingLogBook = new EatingLogBook();
        assertThrows(IllegalArgumentException.class, () -> eatingLogBook.setDescription(null),
                "Метод должен бросить исключение для null значений Description");
    }

    /**
     * Проверяет, что метод устанавливает корректное значение description после изменения
     */
    @Test
    public void testSetDescriptionAfterChange() {
        String initialDescription = "Initial description";
        String newDescription = "New description";
        EatingLogBook eatingLogBook = new EatingLogBook();

        eatingLogBook.setDescription(initialDescription);
        assertEquals(initialDescription, eatingLogBook.getDescription(), "Метод setDescription() установил неверное значение при первой установке");

        eatingLogBook.setDescription(newDescription);
        assertEquals(newDescription, eatingLogBook.getDescription(), "Метод setDescription() установил неверное значение после изменения");
    }

    /**
     * Проверяет, что метод кидает исключение при передачи пустой строки
     */
    @Test
    public void testSetEmptyDescription() {
        EatingLogBook eatingLogBook = new EatingLogBook();
        assertThrows(IllegalArgumentException.class, () -> eatingLogBook.setDescription(""),
                "Метод должен бросить исключение для пустых значений Description");
    }
}
