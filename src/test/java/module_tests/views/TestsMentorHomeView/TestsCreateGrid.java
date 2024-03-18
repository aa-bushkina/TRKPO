package module_tests.views.TestsMentorHomeView;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.mentor.Mentor;
import com.cygans.database.participant.Participant;
import com.cygans.views.mentor.MentorHomeView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsCreateGrid {

    @Mock
    private NotificationController notificationController;

    @Mock
    private ParticipantAndMentorController participantAndMentorController;

    @Mock
    private VaadinSession vaadinSession;

    private MentorHomeView mentorHomeView;

    /**
     * Проверка метода createGrid
     */
    @Test
    void createGrid() throws Exception {
        Participant participant = new Participant();
        List<Participant> list = new ArrayList<>();
        list.add(participant);
        VaadinSession.setCurrent(vaadinSession);
        Mentor mentor = new Mentor();
        when(participantAndMentorController.getIdNowMentorByAuthentication()).thenReturn(mentor);
        when(vaadinSession.getAttribute("Error")).thenReturn(null);
        when(participantAndMentorController.getParticipantsByMentor(mentor)).thenReturn(list);
        mentorHomeView = new MentorHomeView(notificationController, participantAndMentorController);
        Class cl = MentorHomeView.class;
        Method method = cl.getDeclaredMethod("createGrid");
        method.setAccessible(true);
        method.invoke(mentorHomeView);

        Field dataProvider = cl.getDeclaredField("dataProvider");
        dataProvider.setAccessible(true);
        ListDataProvider listDataProvider = (ListDataProvider) dataProvider.get(mentorHomeView);
        assertEquals(1, listDataProvider.getItems().size());
        assertEquals(list.get(0), listDataProvider.getItems().stream().toList().get(0));

        Field grid = cl.getDeclaredField("grid");
        grid.setAccessible(true);
        Grid grid1 = (Grid) grid.get(mentorHomeView);
        assertEquals(listDataProvider, grid1.getDataProvider());
        assertEquals(4, grid1.getColumns().size());
        assertEquals("column-borders no-border", grid1.getThemeName());
        assertEquals("100%", grid1.getHeight());
        assertEquals(2, grid1.getHeaderRows().size());
        assertEquals(4, ((HeaderRow) grid1.getHeaderRows().get(1)).getCells().size());
        assertTrue(grid1.isAllRowsVisible());
    }

}
