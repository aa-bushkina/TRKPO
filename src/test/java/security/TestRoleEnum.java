package security;


import com.cygans.security.db.RoleEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тесты для RoleEnum enum
 */
public class TestRoleEnum {

    /**
     * Проверяет, что каждый элемент RoleEnum имеет значение
     */
    @Test
    public void testRoleEnumHasValue() {
        for (RoleEnum role : RoleEnum.values()) {
            assertNotNull(role.getValue(), "Значение для " + role + " не должно быть null");
        }
    }

    /**
     * Проверяет, что getValue() возвращает ожидаемое значение для каждого элемента RoleEnum
     */
    @Test
    public void testGetValue() {
        assertEquals("PARTICIPANT", RoleEnum.PARTICIPANT.getValue(),
                "Результат вызова getValue() не совпадает с ожидаемым для PARTICIPANT");
        assertEquals("MENTOR", RoleEnum.MENTOR.getValue(),
                "Результат вызова getValue() не совпадает с ожидаемым для MENTOR");
    }

    /**
     * Проверяет, что значения RoleEnum не являются null
     */
    @Test
    public void testEnumValuesNotNull() {
        for (RoleEnum role : RoleEnum.values()) {
            assertNotNull(role, "Значение Enum " + role + " не должно быть null");
        }
    }

    /**
     * Проверяет работу метода valueOf()
     */
    @Test
    public void testValueOf() {
        assertEquals(RoleEnum.PARTICIPANT, RoleEnum.valueOf("PARTICIPANT"),
                "Результат вызова valueOf() не совпадает с ожидаемым для PARTICIPANT");
        assertEquals(RoleEnum.MENTOR, RoleEnum.valueOf("MENTOR"),
                "Результат вызова valueOf() не совпадает с ожидаемым для MENTOR");
    }

    /**
     * Проверяет создание Enum из недопустимой строки
     */
    @Test
    public void testValueOfWithInvalidString() {
        assertThrows(IllegalArgumentException.class, () -> RoleEnum.valueOf("INVALID_ROLE"));
    }
}
