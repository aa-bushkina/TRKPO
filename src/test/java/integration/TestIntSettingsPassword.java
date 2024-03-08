package integration;

import com.cygans.Application;
import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.database.controllers.SettingsController;
import com.cygans.security.db.RoleEnum;
import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoRepository;
import com.vaadin.flow.server.VaadinSession;
import integration.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет, что при вызове метода контролера изменения пароля – пароль изменяется в БД
 */
@SpringBootTest(classes = Application.class)
public class TestIntSettingsPassword extends BaseTest {
    private static final String FIRSTNAME = "Катька";
    private static final String LASTNAME = "Волосова";
    private static final String LOGIN = "sdkkj";
    private static final String PHONE = "+79383170126";
    private static final String GENDER = "Жен";
    private static final LocalDate BIRTHDAY = LocalDate.now();
    private static final String HEIGHT = "123";
    private static final String WEIGHT = "123";
    private static final String BREAST = "123";
    private static final String WAIST = "123";
    private static final String HIPS = "123";
    private static final String PASSWORD = "Qu_ntum_42";
    private static final String NEW_PASSWORD = "Qu_ntum_55";

    @Autowired
    private RegistrationAndLoginController registrationAndLoginController;

    @Autowired
    private SettingsController settingsController;

    @Autowired
    private LoginInfoRepository loginInfoRepository;

    @BeforeEach
    public void setUp() {
        VaadinSession vaadinSessionMock = mock(VaadinSession.class);
        VaadinSession.setCurrent(vaadinSessionMock);

        when(VaadinSession.getCurrent().getAttribute("FirstName"))
                .thenReturn(FIRSTNAME);
        when(VaadinSession.getCurrent().getAttribute("LastName"))
                .thenReturn(LASTNAME);
        when(VaadinSession.getCurrent().getAttribute("Login"))
                .thenReturn(LOGIN);
        when(VaadinSession.getCurrent().getAttribute("Password"))
                .thenReturn(PASSWORD);
        when(VaadinSession.getCurrent().getAttribute("Phone"))
                .thenReturn(PHONE);
        when(VaadinSession.getCurrent().getAttribute("Gender"))
                .thenReturn(GENDER);
        when(VaadinSession.getCurrent().getAttribute("Date"))
                .thenReturn(BIRTHDAY);
        when(VaadinSession.getCurrent().getAttribute("Height"))
                .thenReturn(HEIGHT);
        when(VaadinSession.getCurrent().getAttribute("Weight"))
                .thenReturn(WEIGHT);
        when(VaadinSession.getCurrent().getAttribute("Breast"))
                .thenReturn(BREAST);
        when(VaadinSession.getCurrent().getAttribute("Waist"))
                .thenReturn(WAIST);
        when(VaadinSession.getCurrent().getAttribute("Hip"))
                .thenReturn(HIPS);
    }

    @Test
    public void testIntSettingsPassword() {
        logger.info("Тест проверяет, что при вызове метода контролера изменения пароля – пароль изменяется в БД");

        logger.info("Вызываем метод регистрации участника");
        registrationAndLoginController.registrationUser(RoleEnum.PARTICIPANT);

        logger.info("Вызываем метод обновления пароля");
        settingsController.changePassword(NEW_PASSWORD, RoleEnum.PARTICIPANT);

        logger.info("Проверяем, что в БД изменился пароль участника в таблице Participant");
        LoginInfo loginInfo = loginInfoRepository.findByLogin(LOGIN);
        assertTrue(new BCryptPasswordEncoder().matches(NEW_PASSWORD, loginInfo.getPassword()),
                "Не совпадает значение password");

        logger.info("Тест успешно пройден");
    }
}
