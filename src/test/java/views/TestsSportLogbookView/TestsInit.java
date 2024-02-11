package views.TestsSportLogbookView;

import com.cygans.database.controllers.LogController;
import com.cygans.database.controllers.NotificationController;
import com.cygans.views.participant.logbooks.SportLogbookView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
public class TestsInit {

    @Mock
    private LogController logController;

    @Mock
    private NotificationController notificationController;

    @InjectMocks
    private SportLogbookView sportLogbookView;

    /**
     * Проверяет корректность инициализации полей при создании объекта SportLogbookView.
     */
    @Test
    void constructor_checkInitialization() throws Exception {
        Class c = SportLogbookView.class;
        Method method = c.getDeclaredMethod("init");
        method.setAccessible(true);
        method.invoke(sportLogbookView);
        Field intensity = c.getDeclaredField("intensity");
        intensity.setAccessible(true);
        assertNotNull(intensity.get(sportLogbookView));
        Field activity = c.getDeclaredField("activity");
        activity.setAccessible(true);
        assertNotNull(activity.get(sportLogbookView));
        Field comments = c.getDeclaredField("comments");
        comments.setAccessible(true);
        assertNotNull(comments.get(sportLogbookView));
        Field duration = c.getDeclaredField("duration");
        duration.setAccessible(true);
        assertNotNull(duration.get(sportLogbookView));
        Field submitButton = c.getDeclaredField("submitButton");
        submitButton.setAccessible(true);
        assertNotNull(submitButton.get(sportLogbookView));
    }
}
