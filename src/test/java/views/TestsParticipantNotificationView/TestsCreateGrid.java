package views.TestsParticipantNotificationView;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.notifications.Notifications;
import com.cygans.views.participant.notifications.ParticipantNotificationView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestsCreateGrid {

    @Mock
    NotificationController notificationController;

    @Mock
    UI ui;

    @InjectMocks
    ParticipantNotificationView participantNotificationView;

    /**
     * Проверка createGrid
     */
    @Test
    void testsCreateGrid() throws Exception {
        Class c = ParticipantNotificationView.class;
        Method method = c.getDeclaredMethod("createGrid");
        method.setAccessible(true);
        method.invoke(participantNotificationView);
        Field field = c.getDeclaredField("grid");
        field.setAccessible(true);
        Grid<Notifications> grid = (Grid<Notifications>) field.get(participantNotificationView);
        assertEquals("column-borders no-border", grid.getThemeName());
        List<Grid.Column<Notifications>> columnList = grid.getColumns();
        Grid.Column<Notifications> f = columnList.get(0);
        Grid.Column<Notifications> s = columnList.get(1);
        Grid.Column<Notifications> t = columnList.get(2);
        assertEquals(3, columnList.size());
        assertEquals("25%", f.getWidth());
        assertEquals(0, f.getFlexGrow());
        assertEquals("30%", s.getWidth());
        assertEquals(0, s.getFlexGrow());
        assertEquals("23%", t.getWidth());
        assertEquals(0, t.getFlexGrow());
    }


}
