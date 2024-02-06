package TestLogBook.TestLogService;

import com.cygans.database.log_book.Log;
import com.cygans.database.log_book.LogRepository;
import com.cygans.database.log_book.LogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет метод findLogBooksBetweenDate класса LogType
 */
@ExtendWith(MockitoExtension.class)
public class TestLogFindLogBooksBetweenDate {
    @Mock
    private LogRepository repository;

    @InjectMocks
    private LogService service;

    @Test
    public void testFindLogBooksBetweenDateSuccess() {
        LocalDate start = LocalDate.of(2022, 1, 1);
        LocalDate end = LocalDate.of(2022, 12, 31);
        Long participantId = 1L;

        List<Log> fakeLogs = new ArrayList<>();
        fakeLogs.add(new Log());
        when(repository.findByDateBetweenAndParticipantId(start, end, participantId)).thenReturn(fakeLogs);

        List<Log> result = service.findLogBooksBetweenDate(start, end, participantId);

        assertEquals(fakeLogs, result, "Unexpected logs returned");
    }

    @Test
    public void testFindLogBooksBetweenDateEmptyResult() {
        LocalDate start = LocalDate.of(2022, 1, 1);
        LocalDate end = LocalDate.of(2022, 12, 31);
        Long participantId = 1L;

        when(repository.findByDateBetweenAndParticipantId(start, end, participantId)).thenReturn(null);

        List<Log> result = service.findLogBooksBetweenDate(start, end, participantId);

        assertNull(result, "Expected null for no logs");
    }

    @Test
    public void testFindLogBooksBetweenDateSortedResult() {
        LocalDate start = LocalDate.of(2022, 1, 1);
        LocalDate end = LocalDate.of(2022, 12, 31);
        Long participantId = 1L;

        List<Log> fakeLogs = new ArrayList<>();
        fakeLogs.add(new Log());
        when(repository.findByDateBetweenAndParticipantId(start, end, participantId)).thenReturn(fakeLogs);

        List<Log> result = service.findLogBooksBetweenDate(start, end, participantId);

        Collections.sort(fakeLogs);

        assertEquals(fakeLogs, result, "Logs should be sorted");
    }

    @Test
    public void testFindLogBooksBetweenDateNullResult() {
        LocalDate start = LocalDate.of(2022, 1, 1);
        LocalDate end = LocalDate.of(2022, 12, 31);
        Long participantId = 1L;

        when(repository.findByDateBetweenAndParticipantId(start, end, participantId)).thenReturn(null);

        List<Log> result = service.findLogBooksBetweenDate(start, end, participantId);

        assertNull(result, "Should return null for no logs");
    }
}
