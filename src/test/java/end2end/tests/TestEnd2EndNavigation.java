package end2end.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тест проверяет навигацию по разделам участника/ментора
 */
public class TestEnd2EndNavigation extends TestBase {

    @BeforeEach
    public void setUp() {
        logger.info("Регистрируем участника и ментора");
        registerParticipant();
        registerMentor();
    }

    @Test
    public void testEnd2EndNavigation() {
        logger.info("Тест проверяет навигацию по разделам участника/ментора");

        logger.info("Логинимся участником и выходим из аккаунта");
        getLoginPage()
                .login(LOGIN_PARTICIPANT, PASSWORD)
                .andReturnStartParticipantPage()
                .goToNotifications()
                .goToStartPage()
                .goToSettings()
                .goToStartPage()
                .logout();

        logger.info("Логинимся ментором и выходим из аккаунта");
        getLoginPage()
                .login(LOGIN_MENTOR, PASSWORD)
                .andReturnStartMentorPage()
                .goToNotifications()
                .goToStartPage()
                .goToSettings()
                .goToStartPage()
                .logout();

        logger.info("Тест прошел успешно");
    }
}
