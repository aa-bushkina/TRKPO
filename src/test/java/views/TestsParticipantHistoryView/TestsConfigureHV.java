package views.TestsParticipantHistoryView;


import com.cygans.database.controllers.LogController;
import com.cygans.views.participant.history.ParticipantHistoryView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsConfigureHV {

    @Mock
    private LogController logController;

    @InjectMocks
    private ParticipantHistoryView participantHistoryView;

    /**
     * Проверка метода configureHV
     */
    @Test
    void testConfigureHV() throws Exception {
        when(logController.getAllLogsTypes()).thenReturn(List.of("Type1", "Type2"));
        Class cl = ParticipantHistoryView.class;
        Method method = cl.getDeclaredMethod("configureHV");
        method.setAccessible(true);
        method.invoke(participantHistoryView);
        verify(logController, times(2)).getAllLogsTypes();
        Field field = cl.getDeclaredField("logsTypeFilter");
        field.setAccessible(true);
        assertNotNull(field.get(participantHistoryView));
    }

}
