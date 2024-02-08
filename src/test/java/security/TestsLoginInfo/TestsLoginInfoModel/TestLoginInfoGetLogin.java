package security.TestsLoginInfo.TestsLoginInfoModel;

import com.cygans.security.db.logInfo.LoginInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLoginInfoGetLogin {

    @Test
    public void testGetLogin() {
        String login = "test@example.com";
        LoginInfo loginInfo = new LoginInfo(login, "password", 1L, (byte) 1);

        assertEquals(login, loginInfo.getLogin(), "Метод getLogin() должен возвращать установленный логин");
    }

    @Test
    public void testGetEmptyLogin() {
        String emptyLogin = "";
        LoginInfo loginInfo = new LoginInfo(emptyLogin, "password", 1L, (byte) 1);

        assertEquals(emptyLogin, loginInfo.getLogin(), "Метод getLogin() должен возвращать пустой логин");
    }

    @Test
    public void testGetNullLogin() {
        LoginInfo loginInfo = new LoginInfo(null, "password", 1L, (byte) 1);

        assertEquals(null, loginInfo.getLogin(), "Метод getLogin() должен возвращать null, если логин установлен в null");
    }
}
