package TestsSportLogBook.TestIntensityService;

import static org.mockito.Mockito.*;

import com.cygans.database.sport_log_book.intensity.IntensityRepository;
import com.cygans.database.sport_log_book.intensity.IntensityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestsFill {

    private IntensityRepository intensityRepository;

    private IntensityService intensityService;

    @BeforeEach
    void setUp() {
        intensityRepository = mock(IntensityRepository.class);
//        intensityService = new IntensityService();
    }

    /**
     * Проверка метода, когда в БД больше 3 записей
     */
    @Test
    void testFillWhenRepositoryHasMoreThanThreeRecords() {
        when(intensityRepository.count()).thenReturn(4L);
        intensityService.fill();
        verify(intensityRepository, never()).save(any());
    }

}
