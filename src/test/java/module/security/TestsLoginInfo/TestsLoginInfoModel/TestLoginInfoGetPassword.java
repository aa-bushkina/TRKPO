package module.security.TestsLoginInfo.TestsLoginInfoModel;

import com.cygans.security.db.logInfo.LoginInfo;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLoginInfoGetPassword {

    @Test
    public void testGetPassword() {
        String password = "password";
        LoginInfo loginInfo = new LoginInfo("test@example.com", password, 1L, (byte) 1);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = loginInfo.getPassword();

        assertTrue(encoder.matches(password, encodedPassword), "Метод getPassword() должен возвращать верный пароль");
    }

    @Test
    public void testGetEncryptedPassword() {
        String password = "password";
        LoginInfo loginInfo = new LoginInfo("test@example.com", password, 1L, (byte) 1);

        assertTrue(new BCryptPasswordEncoder().matches(password, loginInfo.getPassword()), "Метод getPassword() должен возвращать зашифрованный пароль");
    }

    @Test
    public void testGetEmptyPassword() {
        String emptyPassword = "";
        LoginInfo loginInfo = new LoginInfo("test@example.com", emptyPassword, 1L, (byte) 1);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String encodedPassword = loginInfo.getPassword();

        assertTrue(encoder.matches(emptyPassword, encodedPassword), "Метод getPassword() должен возвращать пустой пароль");
    }
}
