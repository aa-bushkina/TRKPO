package TestsSportLogBook.TestsIntensity;

import com.cygans.database.sport_log_book.intensity.Intensity;
import com.cygans.database.sport_log_book.intensity.IntensityType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class TestsGetId {

    /**
     * Проверяет работу getId
     */
    @Test
    public void testGetId() {
        Intensity intensity = new Intensity(IntensityType.HIGH.getText());
        assertNull(intensity.getId(), "ID не null");
    }

}
