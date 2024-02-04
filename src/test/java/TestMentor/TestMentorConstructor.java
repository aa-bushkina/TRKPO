package TestMentor;

import com.cygans.database.mentor.Mentor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Проверяет конструктор
 */
public class TestMentorConstructor {
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String LOGIN = "john.doe";
    private static final String PHONE = "+79370904985";
    private static final String GENDER = "Male";
    private static final LocalDate BIRTHDAY = LocalDate.of(1990, 1, 1);
    private static final Long LOGIN_INFO_ID = 1L;

    /**
     * Проверяет конструктор с параметрами
     */
    @Test
    public void testConstructorWithParameters() {
        Mentor mentor = new Mentor(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, LOGIN_INFO_ID);

        assertAll(
                () -> assertEquals(FIRST_NAME, mentor.getFirstName()),
                () -> assertEquals(LAST_NAME, mentor.getLastName()),
                () -> assertEquals(LOGIN, mentor.getLogin()),
                () -> assertEquals(PHONE, mentor.getPhone()),
                () -> assertEquals(GENDER, mentor.getGender()),
                () -> assertEquals(BIRTHDAY, mentor.getBirthday()),
                () -> assertEquals(LOGIN_INFO_ID, mentor.getLoginInfoId())
        );
    }

    /**
     * Проверяет конструктор без параметров
     */
    @Test
    public void testDefaultConstructor() {
        Mentor mentor = new Mentor();

        assertAll(
                () -> assertNotNull(mentor.getId()),
                () -> assertNull(mentor.getFirstName()),
                () -> assertNull(mentor.getLastName()),
                () -> assertNull(mentor.getLogin()),
                () -> assertNull(mentor.getPhone()),
                () -> assertNull(mentor.getGender()),
                () -> assertNull(mentor.getBirthday()),
                () -> assertNull(mentor.getLoginInfoId())
        );
    }

    /**
     * Проверяет конструктор с параметрами null
     */
    @Test
    public void testConstructorWithNullValues() {
        assertThrows(IllegalArgumentException.class, () -> new Mentor(null, null, null, null, null, null, null),
                "Не получили ожидаеме исключение при вызове метода со всеми параметрами null");
    }

    /**
     * Проверяет, что конструктор обрабатывает случай с недостаточным количеством значений
     */
    @Test
    public void testConstructorWithMissingRequiredValues() {
        assertThrows(IllegalArgumentException.class, () -> new Mentor(FIRST_NAME, null, null, null, GENDER, null, LOGIN_INFO_ID),
                "Не получили ожидаеме исключение при вызове метода без всех обязательных параметров");

        assertThrows(IllegalArgumentException.class, () -> new Mentor("", "", "", "", "", BIRTHDAY, LOGIN_INFO_ID),
                "Не получили ожидаеме исключение при вызове метода с пустыми обязательными параметрами");
    }
}
