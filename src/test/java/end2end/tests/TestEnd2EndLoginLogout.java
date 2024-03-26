package end2end.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тест проверяет вход/выход с портала участника/ментора
 */
public class TestEnd2EndLoginLogout extends TestBase {

    @BeforeEach
    public void setUp() {
        logger.info("Регистрируем участника и ментора");
        registerParticipant();
        registerMentor();
    }

    @Test
    public void testEnd2EndLoginLogout() {
        logger.info("Тест проверяет вход/выход с портала участника/ментора");

        logger.info("Логинимся участником и выходим из аккаунта");
        getLoginPage()
                .login(LOGIN_PARTICIPANT, PASSWORD)
                .andReturnStartParticipantPage()
                .logout();

        logger.info("Логинимся ментором и выходим из аккаунта");
        getLoginPage()
                .login(LOGIN_MENTOR, PASSWORD)
                .andReturnStartMentorPage()
                .logout();

        logger.info("Тест прошел успешно");
    }
}
