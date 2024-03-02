package security.TestsLoginInfo.TestsLoginInfoModel;

import com.cygans.security.db.logInfo.LoginInfo;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты для конструкторов класса LoginInfo
 */
public class TestLoginInfoConstructors {

    /**
     * Проверяет конструктор без параметров
     */
    @Test
    public void testDefaultConstructor() {
        LoginInfo loginInfo = new LoginInfo();

        assertNotNull(loginInfo, "Объект не должен быть null");
        assertNull(loginInfo.getId(), "Id должен быть null");
        assertNull(loginInfo.getLogin(), "Логин должен быть null");
        assertNull(loginInfo.getPassword(), "Пароль должен быть null");
    }

    /**
     * Проверяет вызов конструктора с параметрами
     */
    @Test
    public void testConstructorWithParameters() {
        String login = "user@example.com";
        String password = "password";
        Long authoritiesId = 1L;
        Byte enabled = 1;

        LoginInfo loginInfo = new LoginInfo(login, password, authoritiesId, enabled);

        assertNotNull(loginInfo, "Объект не должен быть null");
        assertNull(loginInfo.getId(), "Id должен быть null");
        assertEquals(login, loginInfo.getLogin(), "Логин должен совпадать с переданным значением");
        assertTrue(new BCryptPasswordEncoder().matches(password, loginInfo.getPassword()));
    }
}
