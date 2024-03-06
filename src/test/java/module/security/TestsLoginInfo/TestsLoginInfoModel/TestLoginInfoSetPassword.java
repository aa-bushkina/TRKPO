package module.security.TestsLoginInfo.TestsLoginInfoModel;

import com.cygans.security.db.logInfo.LoginInfo;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLoginInfoSetPassword {

    @Test
    public void testSetPassword() {
        String newPassword = "newPassword";
        LoginInfo loginInfo = new LoginInfo("test@example.com", "oldPassword", 1L, (byte) 1);

        loginInfo.setPassword(newPassword);

        assertTrue(new BCryptPasswordEncoder().matches(newPassword, loginInfo.getPassword()), "Метод setPassword() должен устанавливать правильный пароль");
    }

    @Test
    public void testSetEmptyPassword() {
        String newPassword = "";
        LoginInfo loginInfo = new LoginInfo("test@example.com", "oldPassword", 1L, (byte) 1);

        loginInfo.setPassword(newPassword);

        assertTrue(new BCryptPasswordEncoder().matches(newPassword, loginInfo.getPassword()), "Метод setPassword() должен корректно обрабатывать пустой пароль");
    }
}
