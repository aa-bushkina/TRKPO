package TestsSportLogBook.TestIntensityService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.cygans.database.sport_log_book.intensity.Intensity;
import com.cygans.database.sport_log_book.intensity.IntensityRepository;
import com.cygans.database.sport_log_book.intensity.IntensityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGetIntensityId {

    private static final String INTENSITY_TYPE = "LOWER";
    private static final long INTENSITY_ID = 1L;

    private IntensityRepository intensityRepository;

    private IntensityService intensityService;

    @BeforeEach
    void setUp() {
        intensityRepository = mock(IntensityRepository.class);
        intensityService = new IntensityService();
    }

    /**
     * Проверка getIntensityId с существующей интенсивностью
     */
    @Test
    void testGetIntensityId() {
        Intensity intensity = new Intensity(INTENSITY_TYPE);
        intensity.setId(INTENSITY_ID);

        when(intensityRepository.findIntensityByType(INTENSITY_TYPE)).thenReturn(intensity);

        Long result = intensityService.getIntensityId(INTENSITY_TYPE);
        assertNotNull(result, "Вернулся пустой объект");
        assertEquals(intensity.getId(), result, "У объекта неверное id");
    }

}
