package TestsIntensity;

import com.cygans.database.sport_log_book.intensity.Intensity;
import com.cygans.database.sport_log_book.intensity.IntensityType;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetType {


    /**
     * Проверяет вызов метода для объекта с status = null
     */
    @Test
    public void testGetStatusNull() {
        Intensity intensity = new Intensity();
        assertNull(intensity.getType(), "Тип должен быть null");
    }

    /**
     * Проверяет вызов метода для объекта с существующим status
     */
    @Test
    public void testGetStatusNotNull() {
        String validType = IntensityType.HIGH.getText();
        Intensity notificationStatus = new Intensity(validType);
        assertEquals(validType, notificationStatus.getType(), "Тип не соответствует ожидаемому значению");
    }

}
