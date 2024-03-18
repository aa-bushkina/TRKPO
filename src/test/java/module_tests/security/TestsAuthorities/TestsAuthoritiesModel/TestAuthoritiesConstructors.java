package module_tests.security.TestsAuthorities.TestsAuthoritiesModel;

import com.cygans.security.db.authorities.Authorities;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тесты для конструкторов класса Authorities
 */
public class TestAuthoritiesConstructors {

    /**
     * Проверяет конструктор без параметров
     */
    @Test
    public void testDefaultConstructor() {
        Authorities authorities = new Authorities();

        assertNotNull(authorities, "Объект не должен быть null");
        assertNull(authorities.getId(), "Id должен быть null");
        assertNull(authorities.getUsername(), "Имя пользователя должно быть null");
        assertNull(authorities.getAuthority(), "Роль должна быть null");
    }

    /**
     * Проверяет вызов конструктора с параметрами
     */
    @Test
    public void testConstructorWithParameters() {
        String username = "user";
        String authority = "ROLE_USER";

        Authorities authorities = new Authorities(username, authority);

        assertNotNull(authorities, "Объект не должен быть null");
        assertNull(authorities.getId(), "Id должен быть null");
        assertEquals(username, authorities.getUsername(), "Имя пользователя должно совпадать с переданным значением");
        assertEquals(authority, authorities.getAuthority(), "Роль должна совпадать с переданным значением");
    }
}
