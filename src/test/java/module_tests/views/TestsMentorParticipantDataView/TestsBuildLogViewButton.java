package module_tests.views.TestsMentorParticipantDataView;

import com.cygans.database.controllers.LogController;
import com.cygans.views.mentor.participants.MentorParticipantDataView;
import com.cygans.views.participant.logbooks.ParticipantPersonData;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsBuildLogViewButton {

    @Mock
    private LogController logController;

    @Mock
    private VaadinSession vaadinSession;

    private MentorParticipantDataView mentorParticipantDataView;

    /**
     * Проверка configureHV
     */
    @Test
    void testsConfigureHV() throws Exception {
        VaadinSession.setCurrent(vaadinSession);
        ParticipantPersonData participantPersonData = new ParticipantPersonData();
        participantPersonData.setLogBookType("Type");
        participantPersonData.setLogBookId(1L);
        participantPersonData.setDate(LocalDate.now());
        when(logController.getParticipantFullNameByAttribute()).thenReturn("Михаил Иванов");
        mentorParticipantDataView = new MentorParticipantDataView(logController);
        Class c = MentorParticipantDataView.class;
        Method method = c.getDeclaredMethod("buildLogViewButton", ParticipantPersonData.class);
        method.setAccessible(true);
        Button button = (Button) method.invoke(mentorParticipantDataView, participantPersonData);
        assertEquals("Смотреть", button.getText());
        assertEquals("primary", button.getThemeName());
        button.click();
    }


}
