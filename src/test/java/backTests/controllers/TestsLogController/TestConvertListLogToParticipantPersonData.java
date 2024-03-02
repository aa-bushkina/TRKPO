package backTests.controllers.TestsLogController;

import com.cygans.database.controllers.LogController;
import com.cygans.database.log_book.Log;
import com.cygans.database.log_book.LogService;
import com.cygans.database.log_book.logs_type.LogsTypeService;
import com.cygans.views.participant.logbooks.ParticipantPersonData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestConvertListLogToParticipantPersonData {

    @Mock
    private LogService logService;

    @Mock
    private LogsTypeService logsTypeService;

    @InjectMocks
    private LogController logController;

    @Test
    void testConvertListLogToParticipantPersonData() {
        List<Log> logBook = new ArrayList<>();

        Log log1 = new Log(1L, LocalDate.of(2023, 6, 12), 1L);
        Log log2 = new Log(2L, LocalDate.of(2023, 5, 10), 2L);
        log1.setId(1L);
        log2.setId(2L);
        logBook.add(log1);
        logBook.add(log2);

        when(logsTypeService.getLogTypeById(1L)).thenReturn("Type1");
        when(logsTypeService.getLogTypeById(2L)).thenReturn("Type2");

        List<ParticipantPersonData> result = logController.convertListLogToParticipantPersonData(logBook);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getLogBookId());
        assertEquals(2L, result.get(1).getLogBookId());
        assertEquals(LocalDate.of(2023, 6, 12), result.get(0).getDate());
        assertEquals(LocalDate.of(2023, 5, 10), result.get(1).getDate());
        assertEquals("Type1", result.get(0).getLogBookType());
        assertEquals("Type2", result.get(1).getLogBookType());
    }

    @Test
    void testConvertEmptyListLogToParticipantPersonData() {
        List<Log> logBook = new ArrayList<>();

        List<ParticipantPersonData> result = logController.convertListLogToParticipantPersonData(logBook);

        assertEquals(0, result.size());
    }
}
