package integration;

import com.cygans.Application;
import com.cygans.database.controllers.SettingsController;
import com.cygans.security.db.RoleEnum;
import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoRepository;
import integration.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест проверяет, что при вызове метода контролера изменения пароля – пароль изменяется в БД
 */
@SpringBootTest(classes = Application.class)
public class TestIntSettingsPassword extends BaseTest {
    private static final String NEW_PASSWORD = "Qu_ntum_55";

    @Autowired
    private SettingsController settingsController;

    @Autowired
    private LoginInfoRepository loginInfoRepository;

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
        LoginInfo loginInfo = loginInfoRepository.findByLogin(LOGIN);
        assertTrue(new BCryptPasswordEncoder().matches(NEW_PASSWORD, loginInfo.getPassword()),
                "Не совпадает значение password");

        logger.info("Тест успешно пройден");
    }
}
