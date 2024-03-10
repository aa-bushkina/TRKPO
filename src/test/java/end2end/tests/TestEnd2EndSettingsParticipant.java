package end2end.tests;

import end2end.pages.SettingsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тест проверяет регистрацию и залогин под участником
 */
public class TestEnd2EndSettingsParticipant extends TestBase {
    @BeforeEach
    public void setUp() {
        logger.info("Регистрируем участника");
        registerParticipant();
    }

    @Test
    public void registrationParticipantTest() {
        logger.info("Тест проверяет регистрацию и залогин под участником");

        logger.info("Логинимся участником и меняем в настройках пароль");
        SettingsPage settingsPage = getLoginPage()
                .login(LOGIN_PARTICIPANT, PASSWORD)
                .goToSettings()
                .changeInfo()
                .saveChanges();

        logger.info("Перезаходим в настройки и проверяем значения полей");
        settingsPage
                .goToStartPage()
                .goToSettings();

        logger.info("Тест прошел успешно");
    }
}
