package TestsLogbook;

import com.cygans.database.Logbook;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetId {

    private static class TestLogbook extends Logbook {
    }


    /**
     * Проверяет работу getId
     */
    @Test
    public void testGetId() {
        Logbook logbook = new TestLogbook();
        logbook.setId(1L);
        assertEquals(1L, logbook.getId(), "Вернулось неверное значение");
    }

    /**
     * Проверяет работу getId после изменения
     */
    @Test
    public void testGetIdAfterChange() {
        Logbook logbook = new TestLogbook();
        logbook.setId(1L);
        assertEquals(1L, logbook.getId(), "Вернулось неверное значение до изменения");
        logbook.setId(2L);
        assertEquals(2L, logbook.getId(), "Вернулось неверное значение после изменения");
    }

}
