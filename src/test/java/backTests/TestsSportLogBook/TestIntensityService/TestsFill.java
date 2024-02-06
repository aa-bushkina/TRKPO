package backTests.TestsSportLogBook.TestIntensityService;

import com.cygans.database.sport_log_book.intensity.IntensityRepository;
import com.cygans.database.sport_log_book.intensity.IntensityService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsFill {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @Mock
    private IntensityRepository intensityRepository;
    @InjectMocks
    private IntensityService intensityService;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /**
     * Тест проверяет, что метод fill() вызывает save() три раза, когда репозиторий пуст.
     */
    @Test
    public void testFillWhenRepositoryIsEmpty() {
        when(intensityRepository.count()).thenReturn(0L);
        intensityService.fill();
        verify(intensityRepository, times(3)).save(any());
    }

    /**
     * Тест проверяет, что при наличии более чем трех записей в репозитории выводится сообщение об ошибке в консоль.
     */
    @Test
    public void testFillWhenRepositoryHasMoreThanThreeRecords() {
        when(intensityRepository.count()).thenReturn(4L);
        intensityService.fill();
        assertTrue(outContent.toString().contains("Что-то идет не так, почистите таблицу intensity!!! В ней должно быть только 3 заранее добавленные записи!!!"), "В консоли неверное сообщение");
    }

    /**
     * Тест проверяет, что метод fill() не вызывает save(), когда в репозитории уже три записи.
     */
    @Test
    public void testFillWhenRepositoryHasThreeRecords() {
        when(intensityRepository.count()).thenReturn(3L);

        intensityService.fill();

        verify(intensityRepository, never()).save(any());
    }
}

