package module.security.TestsAuthorities.TestsAuthoritiesModel;

import com.cygans.security.db.authorities.Authorities;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тест метода setAuthority() в Authorities
 */
public class TestAuthoritiesSetAuthority {

    /**
     * Проверяет установку authority для объекта Authorities
     */
    @Test
    public void testSetAuthority() {
        Authorities authorities = new Authorities();
        String authorityValue = "ROLE_USER";

        authorities.setAuthority(authorityValue);

        assertEquals(authorityValue, authorities.getAuthority(), "Роль должна быть установлена");
    }

    /**
     * Проверяет установку authority в null для объекта Authorities
     */
    @Test
    public void testSetAuthorityNull() {
        Authorities authorities = new Authorities();

        authorities.setAuthority(null);

        assertNull(authorities.getAuthority(), "Роль должна быть установлена в null");
    }
}

