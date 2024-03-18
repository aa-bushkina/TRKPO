package module_tests.security.TestsAuthorities.TestsAuthoritiesModel;


import com.cygans.security.db.authorities.Authorities;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тест метода getAuthority() в Authorities
 */
public class TestAuthoritiesGetAuthority {
    /**
     * Проверяет вызов метода для объекта с authority = null
     */
    @Test
    public void testGetAuthorityNull() {
        Authorities authorities = new Authorities();

        assertNull(authorities.getAuthority(), "Роль должна быть null");
    }

    /**
     * Проверяет вызов метода для объекта с непустым значением authority
     */
    @Test
    public void testGetAuthorityNotNull() {
        String authorityValue = "ROLE_USER";
        Authorities authorities = new Authorities();
        authorities.setAuthority(authorityValue);

        assertEquals(authorityValue, authorities.getAuthority(), "Роль должна совпадать с заданным значением");
    }

    /**
     * Проверяет вызов метода для объекта с пустым значением authority
     */
    @Test
    public void testGetAuthorityEmpty() {
        Authorities authorities = new Authorities();
        authorities.setAuthority("");

        assertEquals("", authorities.getAuthority(), "Роль должна быть пустой строкой");
    }
}

