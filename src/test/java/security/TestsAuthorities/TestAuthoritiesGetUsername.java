package security.TestsAuthorities;

import com.cygans.security.db.authorities.Authorities;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тест метода getUsername() в Authorities
 */
public class TestAuthoritiesGetUsername {

    /**
     * Проверяет вызов метода для объекта с установленным именем пользователя
     */
    @Test
    public void testGetUsernameNotNull() {
        String usernameValue = "user123";
        Authorities authorities = new Authorities();
        authorities.setUsername(usernameValue);

        assertEquals(usernameValue, authorities.getUsername(), "Имя пользователя должно совпадать с заданным значением");
    }

    /**
     * Проверяет вызов метода для объекта с null именем пользователя
     */
    @Test
    public void testGetUsernameNull() {
        Authorities authorities = new Authorities();

        assertNull(authorities.getUsername(), "Имя пользователя должно быть null");
    }
}

