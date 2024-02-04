package TestsSportLogBook.TestIntensityService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.cygans.database.sport_log_book.intensity.Intensity;
import com.cygans.database.sport_log_book.intensity.IntensityRepository;
import com.cygans.database.sport_log_book.intensity.IntensityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestGetIntensityId {

    private IntensityRepository intensityRepository;

    private IntensityService intensityService;

    @BeforeEach
    void setUp() {
        intensityRepository = mock(IntensityRepository.class);
        intensityService = new IntensityService();
    }

    @Test
    void testGetIntensityId() {
        String intensityType = "LOWER";
        Intensity intensity = new Intensity(intensityType);
        when(intensityRepository.findIntensityByType(intensityType)).thenReturn(intensity);
        Long result = intensityService.getIntensityId(intensityType);
        assertNotNull(result);
        assertEquals(intensity.getId(), result);
    }

}
