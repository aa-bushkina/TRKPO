package module_tests.views.TestsEmotionalLogbookView;

import com.cygans.database.controllers.LogController;
import com.cygans.database.controllers.NotificationController;
import com.cygans.views.components.Toolbar;
import com.cygans.views.participant.logbooks.EmotionalLogbookView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestsEmotionalLogbookView {

    @Mock
    private LogController logController;

    @Mock
    private NotificationController notificationController;

    @Mock
    private TextArea emotionalText;

    @Mock
    private Button submitButton;

    @Mock
    private UI ui;

    @Mock
    private Notification notification;

    @InjectMocks
    private EmotionalLogbookView emotionalLogbookView;

    @BeforeEach
    void setUp() {
        emotionalLogbookView = new EmotionalLogbookView(logController, notificationController);
    }

    /**
     * Тестирование конструктора
     */
    @Test
    void testConstructor() {
        assertNotNull(emotionalLogbookView);
        assertEquals(2, emotionalLogbookView.getChildren().count());
        assertTrue(emotionalLogbookView.getChildren().anyMatch(component -> component instanceof Toolbar));
        assertTrue(emotionalLogbookView.getChildren().anyMatch(component -> component instanceof HorizontalLayout));
    }

    /**
     * Тестирование создания компонентов
     */
    @Test
    void testCreateFields() throws Exception {
        Class cl = EmotionalLogbookView.class;
        Method method = cl.getDeclaredMethod("createFields");
        method.setAccessible(true);
        Component fields = (Component) method.invoke(emotionalLogbookView);
        assertTrue(fields instanceof HorizontalLayout);
        HorizontalLayout horizontalLayout = (HorizontalLayout) fields;
        assertEquals(1, horizontalLayout.getChildren().count());
        assertTrue(horizontalLayout.getChildren().anyMatch(component -> component instanceof VerticalLayout));
        VerticalLayout verticalLayout = (VerticalLayout) horizontalLayout.getChildren().findFirst().get();
        assertEquals(3, verticalLayout.getChildren().count());
        assertTrue(verticalLayout.getChildren().anyMatch(component -> component instanceof H3));
        assertTrue(verticalLayout.getChildren().anyMatch(component -> component instanceof FormLayout));
        assertTrue(verticalLayout.getChildren().anyMatch(component -> component instanceof Button));
    }

    /**
     * Тестирование отправки данных
     */
    @Test
    void testSubmitData() throws Exception {
        Class cl = EmotionalLogbookView.class;
        Field field = cl.getDeclaredField("submitButton");
        field.setAccessible(true);
        Field field2 = cl.getDeclaredField("emotionalText");
        field2.setAccessible(true);
        TextArea textArea = new TextArea();
        textArea.setValue("Test Emotional Text");
        field2.set(emotionalLogbookView, textArea);
        UI.setCurrent(ui);
        UI.getCurrent().access(() -> {
            try {
                ((Button) field.get(emotionalLogbookView)).click();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        emotionalText.setValue("Test Emotional Text");
        ((Button) field.get(emotionalLogbookView)).click();
        verify(notification, times(0)).addThemeVariants(NotificationVariant.LUMO_ERROR);
        verify(logController, times(1)).saveEmotionalLog("Test Emotional Text");
        verify(notificationController, times(1)).addNewEmotionalLogNotification(0L, "Test Emotional Text");
    }

    /**
     * Тестирование недопустимого ввода данных
     */
    @Test
    void testInvalidData() throws Exception {
        Class cl = EmotionalLogbookView.class;
        Field field = cl.getDeclaredField("submitButton");
        field.setAccessible(true);
        Field field2 = cl.getDeclaredField("emotionalText");
        field2.setAccessible(true);
        TextArea textArea = new TextArea();
        textArea.setValue("Test Emotional Text");
        textArea.setInvalid(true);
        field2.set(emotionalLogbookView, textArea);
        UI.setCurrent(ui);
        UI.getCurrent().access(() -> {
            try {
                ((Button) field.get(emotionalLogbookView)).click();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        emotionalText.setValue("Test Emotional Text");
        ((Button) field.get(emotionalLogbookView)).click();
        verify(logController, times(0)).saveEmotionalLog("Test Emotional Text");
        verify(notificationController, times(0)).addNewEmotionalLogNotification(0L, "Test Emotional Text");
    }

    /**
     * Тестирование пустого ввода данных
     */
    @Test
    void testEmptyData() throws Exception {
        Class cl = EmotionalLogbookView.class;
        Field field = cl.getDeclaredField("submitButton");
        field.setAccessible(true);
        Field field2 = cl.getDeclaredField("emotionalText");
        field2.setAccessible(true);
        TextArea textArea = new TextArea();
        textArea.setValue("");
        textArea.setInvalid(true);
        field2.set(emotionalLogbookView, textArea);
        UI.setCurrent(ui);
        UI.getCurrent().access(() -> {
            try {
                ((Button) field.get(emotionalLogbookView)).click();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        emotionalText.setValue("Test Emotional Text");
        ((Button) field.get(emotionalLogbookView)).click();
        verify(logController, times(0)).saveEmotionalLog("Test Emotional Text");
        verify(notificationController, times(0)).addNewEmotionalLogNotification(0L, "Test Emotional Text");
    }

    /**
     * Тестирование ввода слишком длинных данных
     */
    @Test
    void testLongData() throws Exception {
        Class cl = EmotionalLogbookView.class;
        Field field = cl.getDeclaredField("submitButton");
        field.setAccessible(true);
        Field field2 = cl.getDeclaredField("emotionalText");
        field2.setAccessible(true);
        TextArea textArea = new TextArea();
        textArea.setValue("a".repeat(1001));
        textArea.setInvalid(true);
        field2.set(emotionalLogbookView, textArea);
        UI.setCurrent(ui);
        UI.getCurrent().access(() -> {
            try {
                ((Button) field.get(emotionalLogbookView)).click();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        emotionalText.setValue("Test Emotional Text");
        ((Button) field.get(emotionalLogbookView)).click();
        verify(logController, times(0)).saveEmotionalLog("Test Emotional Text");
        verify(notificationController, times(0)).addNewEmotionalLogNotification(0L, "Test Emotional Text");
    }

}
