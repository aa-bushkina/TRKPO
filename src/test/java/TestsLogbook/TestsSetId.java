package TestsLogbook;

import com.cygans.database.Logbook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestsSetId {

    /**
     * Проверка установки значения
     */
    @Test
    public void testSetId() {
        Logbook logbook1 = new TestLogbook();
        logbook1.setId(1L);
        assertEquals(1L, logbook1.getId(), "Установилось неверное значение");
    }

    /**
     * Проверка изменения значения
     */
    @Test
    public void testSetIdChange() {
        Logbook logbook1 = new TestLogbook();
        logbook1.setId(1L);
        assertEquals(1L, logbook1.getId(), "Установилось неверное значение до изменения");
        logbook1.setId(2L);
        assertEquals(2L, logbook1.getId(), "Установилось неверное значение после изменения");
    }

    /**
     * Проверка сравнения объектов после setId одним значением
     */
    @Test
    public void testCompareAfterSet() {
        Logbook logbook1 = new TestLogbook();
        logbook1.setId(1L);
        Logbook logbook2 = new TestLogbook();
        logbook2.setId(1L);
        assertEquals(logbook1.getId(), logbook2.getId(), "Объекты ID не равны");
    }

    /**
     * Проверка сравнения объектов после setId разными значениями
     */
    @Test
    public void testCompareAfterDifferentSet() {
        Logbook logbook1 = new TestLogbook();
        logbook1.setId(1L);
        Logbook logbook2 = new TestLogbook();
        logbook2.setId(2L);
        assertNotEquals(logbook1.getId(), logbook2.getId(), "Объекты ID равны");
    }

    private static class TestLogbook extends Logbook {
    }

}
