package views.TestsEatingLogbookView;


import com.cygans.database.controllers.LogController;
import com.cygans.database.controllers.NotificationController;
import com.cygans.views.StartView;
import com.cygans.views.participant.logbooks.EatingLogbookView;
import com.cygans.views.util.Filler;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.QueryParameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestsEatingLogBookView {

    @Mock
    private LogController logController;

    @Mock
    private NotificationController notificationController;

    @InjectMocks
    private EatingLogbookView eatingLogbookView;


    /**
     * Тестирование init()
     * */
    @Test
    void testInit() throws Exception {
        Class cl = EatingLogbookView.class;
        Method method = cl.getDeclaredMethod("init");
        method.setAccessible(true);
        method.invoke(eatingLogbookView);
        Field field1 = cl.getDeclaredField("hourPicker");
        field1.setAccessible(true);
        Field field2 = cl.getDeclaredField("minutePicker");
        field2.setAccessible(true);
        Field field3 = cl.getDeclaredField("meal_type");
        field3.setAccessible(true);
        Field field4 = cl.getDeclaredField("description");
        field4.setAccessible(true);
        assertEquals("Часы", ((ComboBox<String>) field1.get(eatingLogbookView)).getLabel());
        assertEquals("Минуты", ((ComboBox<String>) field2.get(eatingLogbookView)).getLabel());
        assertEquals("Описание", ((TextArea) field4.get(eatingLogbookView)).getLabel());
        assertEquals("Приём пищи", ((ComboBox<String>) field3.get(eatingLogbookView)).getLabel());
    }

    /**
     * Тестирование установки видимости кнопки "Очистить" у поля описания
     * */
    @Test
    void testSetClearButtonVisible() throws Exception {
        Class cl = EatingLogbookView.class;
        Method method = cl.getDeclaredMethod("setClearButtonVisible");
        method.setAccessible(true);
        method.invoke(eatingLogbookView);
        Field field1 = cl.getDeclaredField("description");
        field1.setAccessible(true);
        Field field2 = cl.getDeclaredField("meal_type");
        field2.setAccessible(true);
        assertTrue(((TextArea) field1.get(eatingLogbookView)).isClearButtonVisible());
        assertTrue(((ComboBox<String>) field2.get(eatingLogbookView)).isClearButtonVisible());
    }

    /**
     * Тестирование создания компонентов
     * */
    @Test
    void testCreateFields() throws Exception {
        Class cl = EatingLogbookView.class;
        Method method = cl.getDeclaredMethod("createFields");
        method.setAccessible(true);
        method.invoke(eatingLogbookView);
        Field field1 = cl.getDeclaredField("description");
        field1.setAccessible(true);
        assertEquals("80%", ((TextArea) field1.get(eatingLogbookView)).getWidth());
        assertEquals("200px", ((TextArea) field1.get(eatingLogbookView)).getHeight());
        assertEquals(300, ((TextArea) field1.get(eatingLogbookView)).getMaxLength());
    }

}
