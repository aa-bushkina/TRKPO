package views.TestsMentorParticipantDataView;

import com.cygans.database.controllers.LogController;
import com.cygans.database.log_book.Log;
import com.cygans.views.mentor.participants.MentorParticipantDataView;
import com.cygans.views.participant.logbooks.ParticipantPersonData;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsSetupShownData {

    @Mock
    private LogController logController;

    private MentorParticipantDataView mentorParticipantDataView;

    /**
     * Проверка setupShownData
     */
    @Test
    void testsSetupShownData() throws Exception {
        Log log = new Log();
        List<Log> logs = new ArrayList<>();
        ParticipantPersonData participantPersonData = new ParticipantPersonData();
        List<ParticipantPersonData> participantPersonDataList = new ArrayList<>();
        participantPersonDataList.add(participantPersonData);
        logs.add(log);
        when(logController.getParticipantFullNameByAttribute()).thenReturn("Михаил Иванов");
        when(logController.getAllNowParticipantLogs(false)).thenReturn(logs);
        when(logController.convertListLogToParticipantPersonData(any())).thenReturn(participantPersonDataList);
        mentorParticipantDataView = new MentorParticipantDataView(logController);
        Class c = MentorParticipantDataView.class;
        Method method = c.getDeclaredMethod("setupShownData");
        method.setAccessible(true);
        method.invoke(mentorParticipantDataView);
        Field field = c.getDeclaredField("historyDataShown");
        field.setAccessible(true);
        ArrayList<ParticipantPersonData> arrayList = (ArrayList<ParticipantPersonData>) field.get(mentorParticipantDataView);
        assertEquals(2, arrayList.size());
    }
}
