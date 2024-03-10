package end2end.tests;

import org.junit.jupiter.api.Test;

/**
 * Тест проверяет смену пароля ментором
 */
public class TestEnd2EndChangePasswordMentor extends TestBase {
    private static final String NEW_PASSWORD = "654321Aa_";

    @Test
    public void registrationParticipantTest() {
        logger.info("Тест проверяет смену пароля ментором");
        registerMentor();

        logger.info("Переходим к выбору типа юзера и выбираем ментора");
        getLoginPage()
                .login(LOGIN_MENTOR, PASSWORD)
                .goToSettings();

        logger.info("Тест прошел успешно");
    }
}
