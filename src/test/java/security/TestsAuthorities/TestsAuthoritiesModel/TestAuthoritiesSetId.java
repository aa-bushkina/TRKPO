package security.TestsAuthorities.TestsAuthoritiesModel;

import com.cygans.security.db.authorities.Authorities;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тест метода setId() в Authorities
 */
public class TestAuthoritiesSetId {

    /**
     * Проверяет установку id для объекта Authorities
     */
    @Test
    public void testSetId() {
        Authorities authorities = new Authorities();
        Long idValue = 123L;

        authorities.setId(idValue);

        assertEquals(idValue, authorities.getId(), "Id должен быть установлен");
    }

    /**
     * Проверяет установку id в null для объекта Authorities
     */
    @Test
    public void testSetIdNull() {
        Authorities authorities = new Authorities();

        authorities.setId(null);

        assertNull(authorities.getId(), "Id должен быть установлен в null");
    }
}

