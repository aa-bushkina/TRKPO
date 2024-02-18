package views.TestsParticipantQuestionsView;

import com.cygans.database.controllers.LogController;
import com.cygans.database.controllers.NotificationController;
import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.controllers.QuestionController;
import com.cygans.database.question.Question;
import com.cygans.views.mentor.participants.MentorParticipantDataView;
import com.cygans.views.participant.logbooks.ParticipantPersonData;
import com.cygans.views.participant.questions.ParticipantQuestionsView;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.select.Select;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsConfigureHV {

    @Mock
    private QuestionController questionController;

    @Mock
    private ParticipantAndMentorController participantAndMentorController;

    @Mock
    private NotificationController notificationController;

    private ParticipantQuestionsView participantQuestionsView;

    /**
     * Проверка configureHV
     */
    @Test
    void testsConfigureHV() throws Exception {
        participantQuestionsView = new ParticipantQuestionsView(questionController, participantAndMentorController, notificationController);
        Class c = ParticipantQuestionsView.class;
        Method method = c.getDeclaredMethod("configureHV");
        method.setAccessible(true);
        method.invoke(participantQuestionsView);
        Field field = c.getDeclaredField("historyList");
        field.setAccessible(true);
        Grid<Question> arrayList = (Grid<Question>) field.get(participantQuestionsView);
        assertEquals("100%", arrayList.getHeight());
        assertEquals("100%", arrayList.getWidth());
        assertTrue(arrayList.isAllRowsVisible());
        assertEquals(1, arrayList.getHeaderRows().size());
        assertEquals(8, arrayList.getColumns().size());
    }

}
