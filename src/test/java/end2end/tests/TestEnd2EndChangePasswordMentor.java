package end2end.tests;

import end2end.pages.mentor.SettingsMentorPage;
import end2end.pages.mentor.StartMentorPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тест проверяет смену пароля ментором
 */
public class TestEnd2EndChangePasswordMentor extends TestBase {
    private static final String NEW_PASSWORD = "654321Aa_";

    @BeforeEach
    public void setUp() {
        logger.info("Регистрируем ментора");
        registerMentor();
    }

    @Test
    public void testEnd2EndChangePasswordMentor() {
        logger.info("Тест проверяет смену пароля ментором");

        logger.info("Логинимся ментором и меняем в настройках пароль");
        getLoginPage()
                .login(LOGIN_MENTOR, PASSWORD)
                .andReturnStartMentorPage()
                .goToSettings()
                .changePassword()
                .typeOldPass(PASSWORD)
                .typeNewPass(NEW_PASSWORD)
                .typeRepeatPass(NEW_PASSWORD)
                .clickSave();

        logger.info("Выходим из аккаунта и входим с новым паролем");
        StartMentorPage startMentorPage = new SettingsMentorPage()
                .goToStartPage()
                .logout()
                .login(LOGIN_MENTOR, NEW_PASSWORD)
                .andReturnStartMentorPage();

        logger.info("Тест прошел успешно");
    }

    @AfterEach
    public void logout() {
        logoutMentor();
    }
}
