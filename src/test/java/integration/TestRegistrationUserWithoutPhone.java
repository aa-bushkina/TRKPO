package integration;

import com.cygans.Application;
import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.database.participant.ParticipantRepository;
import com.cygans.security.db.RoleEnum;
import com.cygans.security.db.authorities.AuthoritiesRepository;
import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoRepository;
import com.cygans.security.db.logInfo.LoginInfoService;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет, что при вызове метода контролера создания пользователя
 * с пустым значением поля телефона не создается запись в БД
 */
@SpringBootTest(classes = Application.class)
public class TestRegistrationUserWithoutPhone {

    private static final Logger logger = LoggerFactory.getLogger(TestIntRegisterParticipant.class);
    private static final String FIRSTNAME = "Катька";
    private static final String LASTNAME = "Волосова";
    private static final String LOGIN = "sdj";
    private static final String GENDER = "Жен";
    private static final LocalDate BIRTHDAY = LocalDate.now();
    private static final String HEIGHT = "123";
    private static final String WEIGHT = "123";
    private static final String BREAST = "123";
    private static final String PASSWORD = "Qu_ntum_42";
    private static final String WAIST = "123";
    private static final String HIPS = "123";
    private LoginInfo loginInfo;

    @Autowired
    private RegistrationAndLoginController registrationAndLoginController;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private LoginInfoRepository loginInfoRepository;
    @Autowired
    private AuthoritiesRepository authoritiesRepository;
    @Mock
    private LoginInfoService loginInfoService;

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
                .thenReturn(null);
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
        loginInfo = new LoginInfo();
        loginInfo.setId(1L);
        when(loginInfoService.findByLogin(any()))
                .thenReturn(loginInfo);
    }

    @Test
    public void testRegistrationUserWithoutPhone() {
        logger.info("Тест проверяет, что при вызове метода контролера создания пользователя " +
                "с пустым значением поля телефона не создается запись в БД");

        logger.info("Вызываем метод регистрации участника");
        assertThrows(IllegalArgumentException.class,
                () -> registrationAndLoginController.registrationUser(RoleEnum.PARTICIPANT),
                "Удалось зарегестрировать участника без телефона");

        logger.info("Проверяем, что в БД не создались записи после регистрации в таблице Participant");
        assertNull(participantRepository.getParticipantByLogin(LOGIN), "Успешно создался участник в Participant");

        logger.info("Тест успешно пройден");
    }

    @AfterEach
    public void tearDown() {
        logger.info("Чистим БД");
        if (participantRepository.getParticipantByLogin(LOGIN) != null) {
            participantRepository.delete(participantRepository.getParticipantByLogin(LOGIN));
        }
        if (loginInfoRepository.findByLogin(LOGIN) != null) {
            loginInfoRepository.delete(loginInfoRepository.findByLogin(LOGIN));
        }
        if (authoritiesRepository.getAuthoritiesByUsername(LOGIN) != null) {
            authoritiesRepository.delete(authoritiesRepository.getAuthoritiesByUsername(LOGIN));
        }
    }

}
