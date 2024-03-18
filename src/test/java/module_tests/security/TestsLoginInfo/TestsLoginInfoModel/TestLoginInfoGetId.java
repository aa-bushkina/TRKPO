package module_tests.security.TestsLoginInfo.TestsLoginInfoModel;

import com.cygans.security.db.logInfo.LoginInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тест метода getId() в LoginInfo
 */
public class TestLoginInfoGetId {

    /**
     * Проверяет вызов метода для объекта с id = null
     */
    @Test
    public void testGetIdNull() {
        LoginInfo loginInfo = new LoginInfo();

        assertNull(loginInfo.getId(), "Id должен быть null");
    }

    /**
     * Проверяет вызов метода для объекта с установленным id
     */
    @Test
    public void testGetIdNotNull() {
        Long idValue = 1L;
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setId(idValue);

        assertEquals(idValue, loginInfo.getId(), "Id должен совпадать с заданным значением");
    }
}
