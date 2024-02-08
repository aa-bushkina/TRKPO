package security.TestsAuthorities;


import com.cygans.security.db.authorities.Authorities;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тест метода setUsername() в Authorities
 */
public class TestAuthoritiesSetUsername {

    /**
     * Проверяет установку имени пользователя для объекта Authorities
     */
    @Test
    public void testSetUsername() {
        Authorities authorities = new Authorities();
        String usernameValue = "user123";

        authorities.setUsername(usernameValue);

        assertEquals(usernameValue, authorities.getUsername(), "Имя пользователя должно быть установлено");
    }

    /**
     * Проверяет установку имени пользователя в null для объекта Authorities
     */
    @Test
    public void testSetUsernameNull() {
        Authorities authorities = new Authorities();

        authorities.setUsername(null);

        assertNull(authorities.getUsername(), "Имя пользователя должно быть установлено в null");
    }
}
