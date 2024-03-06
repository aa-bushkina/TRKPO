package module.backTests.TestsLog.TestLogService;

import com.cygans.database.log_book.Log;
import com.cygans.database.log_book.LogRepository;
import com.cygans.database.log_book.LogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет метод findLogBooksByParticipantId класса LogType
 */
@ExtendWith(MockitoExtension.class)
public class TestLogFindLogBooksByParticipantId {
    @Mock
    private LogRepository repository;

    @InjectMocks
    private LogService service;

    /**
     * Тест на успешное получение списка Log по идентификатору участника
     */
    @Test
    public void testFindLogsByParticipantIdSuccess() {
        Long participantId = 1L;

        LocalDate currentDate = LocalDate.now();
        Log log1 = new Log();
        log1.setDate(currentDate.minusDays(1));
        Log log2 = new Log();
        log2.setDate(currentDate);

        List<Log> expectedLogs = Arrays.asList(log1, log2);
        when(repository.findByParticipantId(participantId)).thenReturn(expectedLogs);
        List<Log> result = service.findLogBooksByParticipantId(participantId);

        assertEquals(expectedLogs, result, "Возвращенный список Log не соответствует ожидаемому списку Log");
        verify(repository, times(1)).findByParticipantId(participantId);
    }

    /**
     * Тест на поиск Log по идентификатору участника, когда записей отсутствуют
     */
    @Test
    public void testFindLogsByParticipantIdEmptyResult() {
        Long participantId = 1L;
        when(repository.findByParticipantId(participantId)).thenReturn(Collections.emptyList());

        List<Log> result = service.findLogBooksByParticipantId(participantId);

        assertTrue(result.isEmpty(), "Список Log не должен содержать элементов");
        verify(repository, times(1)).findByParticipantId(participantId);
    }
}
