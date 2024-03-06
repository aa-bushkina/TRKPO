package module.views.TestsStartView;

import com.cygans.views.StartView;
import com.vaadin.flow.component.login.LoginI18n;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestsCreateLoginI18n {

//    @Mock
//    private Filler filler;

    @InjectMocks
    private StartView startView;

    /**
     * Тестирование метода createLoginI18n.
     */
    @Test
    public void testCreateLoginI18n() throws Exception {
        Class loginInfo18 = StartView.class;
        Method method = loginInfo18.getDeclaredMethod("createLoginI18n");
        method.setAccessible(true);
        LoginI18n i18n = (LoginI18n) method.invoke(startView);
        assertEquals("Логин", i18n.getForm().getUsername(), "Неправильное отображение поля для ввода логина");
        assertEquals("Вход", i18n.getForm().getTitle(), "Неправильное отображение заголовка формы");
        assertEquals("Войти", i18n.getForm().getSubmit(), "Неправильное отображение текста кнопки для входа");
        assertEquals("Пароль", i18n.getForm().getPassword(), "Неправильное отображение поля для ввода пароля");
        assertEquals("Неверный логин или пароль", i18n.getErrorMessage().getTitle(), "Неправильное отображение заголовка ошибки");
        assertEquals("Проверьте введенные данные и попробуйте еще раз", i18n.getErrorMessage().getMessage(), "Неправильное отображение сообщения ошибки");
    }

}
