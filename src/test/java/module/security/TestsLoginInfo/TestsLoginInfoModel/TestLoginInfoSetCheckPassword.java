package module.security.TestsLoginInfo.TestsLoginInfoModel;

import com.cygans.security.db.logInfo.LoginInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLoginInfoSetCheckPassword {

    @Test
    public void testCheckCorrectPassword() {
        String password = "password";
        LoginInfo loginInfo = new LoginInfo("test@example.com", password, 1L, (byte) 1);

        assertTrue(loginInfo.checkPassword(password), "Метод checkPassword() должен вернуть true для правильного пароля");
    }

    @Test
    public void testCheckIncorrectPassword() {
        String correctPassword = "correctPassword";
        String wrongPassword = "wrongPassword";
        LoginInfo loginInfo = new LoginInfo("test@example.com", correctPassword, 1L, (byte) 1);

        assertFalse(loginInfo.checkPassword(wrongPassword), "Метод checkPassword() должен вернуть false для неправильного пароля");
    }

    @Test
    public void testCheckEmptyPassword() {
        String correctPassword = "correctPassword";
        LoginInfo loginInfo = new LoginInfo("test@example.com", correctPassword, 1L, (byte) 1);

        assertFalse(loginInfo.checkPassword(""), "Метод checkPassword() должен вернуть false для пустого пароля");
    }
}
