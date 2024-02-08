package security.TestsAuthorities;

import com.cygans.security.db.authorities.Authorities;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тест метода getId() в Authorities
 */
public class TestAuthoritiesGetId {

    /**
     * Проверяет вызов метода для объекта с id = null
     */
    @Test
    public void testGetIdNull() {
        Authorities authorities = new Authorities();

        assertNull(authorities.getId(), "Id должен быть null");
    }

    /**
     * Проверяет вызов метода для объекта с установленным id
     */
    @Test
    public void testGetIdNotNull() {
        Long idValue = 1L;
        Authorities authorities = new Authorities();
        authorities.setId(idValue);

        assertEquals(idValue, authorities.getId(), "Id должен совпадать с заданным значением");
    }
}
