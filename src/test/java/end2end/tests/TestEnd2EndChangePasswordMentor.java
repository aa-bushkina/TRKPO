package end2end.tests;

import end2end.pages.mentor.SettingsMentorPage;
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
    public void registrationParticipantTest() {
        logger.info("Тест проверяет смену пароля ментором");

        logger.info("Логинимся ментором и меняем в настройках пароль");
        getLoginPage()
                .login(LOGIN_MENTOR, PASSWORD)
                .goToSettings()
                .andReturnMentorSettingsPage()
                .changePassword()
                .typeOldPass(PASSWORD)
                .typeNewPass(NEW_PASSWORD)
                .typeRepeatPass(NEW_PASSWORD)
                .clickSave();

        logger.info("Выходим из аккаунта и входим с новым паролем");
        new SettingsMentorPage()
                .goToStartPage()
                .logout()
                .login(LOGIN_MENTOR, NEW_PASSWORD);

        logger.info("Тест прошел успешно");
    }
}
