package TestsSportLogBook.TestsIntensity;

import com.cygans.database.sport_log_book.SportLogBook;
import com.cygans.database.sport_log_book.intensity.Intensity;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSetId {

    /**
     * Проверяем работу setId
     */
    @Test
    public void testSetDuration() {
        Intensity intensity = new Intensity();
        intensity.setId(1L);
        assertEquals(1L, intensity.getId(), "setId установил неверное значение");
    }

}
