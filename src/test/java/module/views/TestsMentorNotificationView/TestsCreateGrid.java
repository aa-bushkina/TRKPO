package module.views.TestsMentorNotificationView;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.notifications.Notifications;
import com.cygans.views.mentor.notifications.MentorNotificationView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class TestsCreateGrid {

    @Mock
    NotificationController notificationController;

    @Mock
    ParticipantAndMentorController participantAndMentorController;

    @Mock
    UI ui;

    @Mock
    VaadinSession vaadinSession;

    @InjectMocks
    MentorNotificationView mentorNotificationView;

    /**
     * Проверка createGrid
     */
    @Test
    void testsCreateGrid() throws Exception {
        Class c = MentorNotificationView.class;
        Method method = c.getDeclaredMethod("addFiltersToGrid");
        method.setAccessible(true);
        method.invoke(mentorNotificationView);
        method = c.getDeclaredMethod("createGrid");
        method.setAccessible(true);
        method.invoke(mentorNotificationView);
        Field field = c.getDeclaredField("grid");
        field.setAccessible(true);
        Grid<Notifications> grid = (Grid<Notifications>) field.get(mentorNotificationView);
        assertEquals("column-borders no-border", grid.getThemeName());
        assertEquals("100%", grid.getHeight());
        assertTrue(grid.isAllRowsVisible());
        List<Grid.Column<Notifications>> columnList = grid.getColumns();
        Grid.Column<Notifications> f = columnList.get(0);
        Grid.Column<Notifications> s = columnList.get(1);
        Grid.Column<Notifications> t = columnList.get(2);
        Grid.Column<Notifications> th = columnList.get(3);
        Grid.Column<Notifications> fi = columnList.get(4);
        assertEquals(5, columnList.size());
        assertEquals("18%", f.getWidth());
        assertEquals(0, f.getFlexGrow());
        assertEquals("18%", s.getWidth());
        assertEquals(0, s.getFlexGrow());
        assertEquals("18%", t.getWidth());
        assertEquals(0, t.getFlexGrow());
        assertEquals("25%", th.getWidth());
        assertEquals(0, fi.getFlexGrow());
        assertEquals("15%", fi.getWidth());
    }


}