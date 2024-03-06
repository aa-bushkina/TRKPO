package module.views.TestsSportLogbookView;

import com.cygans.database.controllers.LogController;
import com.cygans.database.controllers.NotificationController;
import com.cygans.views.participant.logbooks.SportLogbookView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class TestsCreateFields {

    @Mock
    private LogController logController;

    @Mock
    private NotificationController notificationController;

    @InjectMocks
    private SportLogbookView sportLogbookView;

    /**
     * Проверяет, что форма создается без ошибок.
     */
    @Test
    void createFields_checkFormLayoutNotNull() throws Exception {
        Class c = SportLogbookView.class;
        Method method = c.getDeclaredMethod("createFields");
        method.setAccessible(true);
        HorizontalLayout returnComponent = (HorizontalLayout) method.invoke(sportLogbookView);
        assertNotNull(returnComponent);

        Field comments = c.getDeclaredField("comments");
        comments.setAccessible(true);
        assertNotNull(comments.get(sportLogbookView));
        assertEquals("80%", ((TextArea) comments.get(sportLogbookView)).getStyle().get("width"));
        assertEquals("200px", ((TextArea) comments.get(sportLogbookView)).getStyle().get("height"));
        assertEquals(300, ((TextArea) comments.get(sportLogbookView)).getMaxLength());

        Field activity = c.getDeclaredField("activity");
        activity.setAccessible(true);
        assertNotNull(activity.get(sportLogbookView));
        assertEquals(50, ((TextField) activity.get(sportLogbookView)).getMaxLength());

        Field duration = c.getDeclaredField("duration");
        duration.setAccessible(true);
        assertNotNull(duration.get(sportLogbookView));
        assertEquals(4, ((TextField) duration.get(sportLogbookView)).getMaxLength());

        Field submitButton = c.getDeclaredField("submitButton");
        submitButton.setAccessible(true);
        assertNotNull(submitButton.get(sportLogbookView));
        assertEquals("12%", ((Button) submitButton.get(sportLogbookView)).getStyle().get("width"));
        assertEquals("primary", ((Button) submitButton.get(sportLogbookView)).getThemeName());
    }

}
