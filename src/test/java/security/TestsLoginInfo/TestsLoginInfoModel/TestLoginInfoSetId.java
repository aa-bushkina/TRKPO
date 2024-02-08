package security.TestsLoginInfo.TestsLoginInfoModel;

import com.cygans.security.db.logInfo.LoginInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestLoginInfoSetId {

    @Test
    public void testSetIdToNull() {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setId(null);

        assertNull(loginInfo.getId(), "Id должен быть null");
    }

    @Test
    public void testSetIdToZero() {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setId(0L);

        assertEquals(0L, loginInfo.getId(), "Id должен быть установлен корректно");
    }

    @Test
    public void testSetIdToPositiveValue() {
        LoginInfo loginInfo = new LoginInfo();
        Long idValue = 1L;
        loginInfo.setId(idValue);

        assertEquals(idValue, loginInfo.getId(), "Id должен быть установлен корректно");
    }
}
