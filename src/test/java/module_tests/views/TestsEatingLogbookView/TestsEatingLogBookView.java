package module_tests.views.TestsEatingLogbookView;


import com.cygans.database.controllers.LogController;
import com.cygans.database.controllers.NotificationController;
import com.cygans.views.participant.logbooks.EatingLogbookView;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.TextArea;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
     */


    /**
     * Тестирование установки видимости кнопки "Очистить" у поля описания
     */


    /**
     * Тестирование создания компонентов
     */
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
