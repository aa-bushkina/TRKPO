package integration;

import com.cygans.Application;
import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantRepository;
import com.cygans.security.db.RoleEnum;
import com.cygans.security.db.authorities.Authorities;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет, что при вызове метода регистрации в контроллере с валидными полями
 * для роли участник юзер сохраняется в БД
 */
@SpringBootTest(classes = Application.class)
public class TestIntRegisterParticipant {
    private static final Logger logger = LoggerFactory.getLogger(TestIntRegisterParticipant.class);
    private static final String FIRSTNAME = "Катька";
    private static final String LASTNAME = "Волосова";
    private static final String LOGIN = "sdj";
    private static final String PHONE = "+79383170126";
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
    private RegistrationAndLoginController controller;

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Autowired
    private LoginInfoRepository loginInfoRepository;

    @Autowired
    private ParticipantRepository participantRepository;
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
        loginInfo = new LoginInfo();
        loginInfo.setId(1L);
        when(loginInfoService.findByLogin(any()))
                .thenReturn(loginInfo);
    }

    @Test
    public void testIntRegisterParticipant() {
        logger.info("Тест проверяет, что при вызове метода регистрации в контроллере с валидными полями " +
                "для роли участник юзер сохраняется в БД");

        logger.info("Вызываем метод регистрации участника");
        controller.registrationUser(RoleEnum.PARTICIPANT);

        logger.info("Проверяем, что в БД создались записи после регистрации в таблице Participant");
        Participant participant = participantRepository.getParticipantByLogin(LOGIN);
        assertAll(
                () -> assertEquals(HEIGHT, participant.getHeight().toString(),
                        "Значение height не совпадает с установленным"),
                () -> assertEquals(BREAST, participant.getBreast().toString(),
                        "Значение breast не совпадает с установленным"),
                () -> assertEquals(HIPS, participant.getHips().toString(),
                        "Значение hips не совпадает с установленным"),
                () -> assertEquals(WAIST, participant.getWaist().toString(),
                        "Значение waist не совпадает с установленным"),
                () -> assertEquals(WEIGHT, participant.getWeight().toString(),
                        "Значение weight не совпадает с установленным"),
                () -> assertEquals(FIRSTNAME, participant.getFirstName(),
                        "Значение firstname не совпадает с установленным"),
                () -> assertEquals(LASTNAME, participant.getLastName(),
                        "Значение lastname не совпадает с установленным"),
                () -> assertEquals(BIRTHDAY, participant.getBirthday(),
                        "Значение birthday не совпадает с установленным"),
                () -> assertEquals(GENDER, participant.getGender(),
                        "Значение gender не совпадает с установленным"),
                () -> assertEquals(PHONE, participant.getPhone(),
                        "Значение phone не совпадает с установленным")
        );

        logger.info("Проверяем, что в БД создались записи после регистрации в таблице Authorities");
        Authorities authorities = authoritiesRepository.getAuthoritiesByUsername(LOGIN);
        assertAll(
                () -> assertEquals(LOGIN, authorities.getUsername(),
                        "Не совпадает значение username"),
                () -> assertEquals("PARTICIPANT", authorities.getAuthority(),
                        "Не совпадает значение authority")
        );

        logger.info("Проверяем, что в БД создались записи после регистрации в таблице LoginInfo");
        LoginInfo loginInfo = loginInfoRepository.findByLogin(LOGIN);
        assertAll(
                () -> assertEquals(LOGIN, loginInfo.getLogin(),
                        "Не совпадает значение login"),
                () -> assertTrue(new BCryptPasswordEncoder().matches(PASSWORD, loginInfo.getPassword()),
                        "Не совпадает значение password")
        );

        logger.info("Тест успешно пройден");
    }

    @AfterEach
    public void tearDown() {
        logger.info("Удаляем из БД добавленные записи");
        //TODO
    }
}
