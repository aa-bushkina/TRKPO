package integration;

import com.cygans.security.db.RoleEnum;
import com.cygans.security.db.logInfo.LoginInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест проверяет, что при вызове метода контролера изменения пароля – пароль изменяется в БД
 */
public class TestIntSettingsPassword extends TestIntBase {
    private static final String NEW_PASSWORD = "Qu_ntum_55";

    @BeforeEach
    public void setUp() {
        logger.info("Создаем участника");
        registerParticipant();
    }

    @Test
    public void testIntSettingsPassword() {
        logger.info("Тест проверяет, что при вызове метода контролера изменения пароля – пароль изменяется в БД");

        logger.info("Вызываем метод обновления пароля");
        settingsController.changePassword(NEW_PASSWORD, RoleEnum.PARTICIPANT);

        logger.info("Проверяем, что в БД изменился пароль участника в таблице Participant");
        LoginInfo loginInfo = loginInfoRepository.findByLogin(LOGIN_PARTICIPANT);
        assertTrue(new BCryptPasswordEncoder().matches(NEW_PASSWORD, loginInfo.getPassword()),
                "Не совпадает значение password");

        logger.info("Тест успешно пройден");
    }
}
