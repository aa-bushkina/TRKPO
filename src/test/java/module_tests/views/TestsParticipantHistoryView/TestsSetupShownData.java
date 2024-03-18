package module_tests.views.TestsParticipantHistoryView;

import com.cygans.database.controllers.LogController;
import com.cygans.database.log_book.Log;
import com.cygans.views.participant.history.ParticipantHistoryView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsSetupShownData {

    @Mock
    private LogController logController;

    @InjectMocks
    private ParticipantHistoryView participantHistoryView;


    /**
     * Проверка метода setupShownData
     */
    @Test
    void testSetupShownData() throws Exception {
        List<Log> mockLogs = new ArrayList<>();
        when(logController.getAllNowParticipantLogs(true)).thenReturn(mockLogs);
        Class cl = ParticipantHistoryView.class;
        Method method = cl.getDeclaredMethod("setupShownData");
        method.setAccessible(true);
        method.invoke(participantHistoryView);
        verify(logController, times(2)).getAllNowParticipantLogs(true);
        Field field = cl.getDeclaredField("historyDataShown");
        field.setAccessible(true);
        assertEquals(mockLogs.size(), ((ArrayList<?>) field.get(participantHistoryView)).size());
    }

}
