package backTests.TestsSportLogBook.TestsIntensityModel;

import com.cygans.database.sport_log_book.intensity.Intensity;
import com.cygans.database.sport_log_book.intensity.IntensityType;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestsSetType {

    /**
     * Проверяет вызов метода для объекта с status = null
     */
    @Test
    public void testGetStatusNull() {
        Intensity intensity = new Intensity();
        assertNull(intensity.getType(), "Тип должен быть null");
        String validType = IntensityType.HIGH.getText();
        intensity.setType(validType);
        assertEquals(validType, intensity.getType(), "Установилось неверное значение");
    }

    /**
     * Проверяет вызов метода для объекта с существующим status
     */
    @Test
    public void testGetStatusNotNull() {
        String validType = IntensityType.HIGH.getText();
        Intensity intensity = new Intensity(validType);
        assertEquals(validType, intensity.getType(), "Изначально неверное значение");
        String validType2 = IntensityType.HIGH.getText();
        intensity.setType(validType2);
        assertEquals(validType2, intensity.getType(), "Установилось неверное значение");
    }

}
