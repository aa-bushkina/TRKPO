package TestsSportLogBook.TestIntensityService;

import com.cygans.database.sport_log_book.intensity.Intensity;
import com.cygans.database.sport_log_book.intensity.IntensityRepository;
import com.cygans.database.sport_log_book.intensity.IntensityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TestsGetIntensityType {

    private static final String INTENSITY_TYPE = "LOWER";
    private static final long INTENSITY_ID = 1L;

    @Mock
    private IntensityRepository intensityRepository;
    @InjectMocks
    private IntensityService intensityService;

    @BeforeEach
    void setUp() {
        intensityService = new IntensityService(intensityRepository);
    }

    /**
     * Проверка getIntensityType с существующей записью
     */
    @Test
    void testGetIntensityType() {
        Intensity intensity = new Intensity(INTENSITY_TYPE);
        intensity.setId(INTENSITY_ID);
        Mockito.when(intensityRepository.findIntensityById(INTENSITY_ID)).thenReturn(intensity);
        Intensity intensity1 = intensityService.getIntensityType(INTENSITY_ID);
        assertNotNull(intensity1, "Вернулся пустой объект");
        assertEquals(intensity, intensity1, "Вернулся неверный объект");
    }

    /**
     * Проверка getIntensityType с несуществующей записью
     */
    @Test
    void testGetIntensityTypeNull() {
        Mockito.when(intensityRepository.findIntensityById(INTENSITY_ID)).thenReturn(null);
        Intensity intensity1 = intensityService.getIntensityType(INTENSITY_ID);
        assertNull(intensity1, "Вернулся не пустой объект");
    }

}
