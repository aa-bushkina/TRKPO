package module_tests.backTests.TestsSportLogBook.TestIntensityService;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TestsGetIntensityId {

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
     * Проверка getIntensityId с существующей записью
     */
    @Test
    void testGetIntensityId() {
        Intensity intensity = new Intensity(INTENSITY_TYPE);
        intensity.setId(INTENSITY_ID);
        Mockito.when(intensityRepository.findIntensityByType(INTENSITY_TYPE)).thenReturn(intensity);
        Long result = intensityService.getIntensityId(INTENSITY_TYPE);
        assertNotNull(result, "Вернулся пустой объект");
        assertEquals(intensity.getId(), result, "У объекта неверное id");
    }

    /**
     * Проверка getIntensityId с несуществующей записью
     */
    @Test
    void testGetIntensityIdNull() {
        Mockito.when(intensityRepository.findIntensityByType(INTENSITY_TYPE)).thenReturn(null);
        assertThrows(NullPointerException.class,
                () -> intensityService.getIntensityId(INTENSITY_TYPE),
                "Исключение не выбросилось");
    }

}
