package backTests.TestsMentor.TestsMentorModel;

import com.cygans.database.mentor.Mentor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

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

    private static Stream<Arguments> provideParams() {
        return Stream.of(
                Arguments.of(FIRST_NAME, null, LOGIN, "", null, null, LOGIN_INFO_ID),
                Arguments.of("", "", LOGIN, null, "", BIRTHDAY, LOGIN_INFO_ID),
                Arguments.of("", null, "", PHONE, null, null, null),
                Arguments.of("", LAST_NAME, null, "", GENDER, BIRTHDAY, LOGIN_INFO_ID),
                Arguments.of(null, "", "", "", "", BIRTHDAY, null),
                Arguments.of(null, LAST_NAME, LOGIN, PHONE, GENDER, null, LOGIN_INFO_ID),
                Arguments.of(null, "", "", "", "", BIRTHDAY, null),
                Arguments.of(null, null, null, null, null, null, LOGIN_INFO_ID),
                Arguments.of(null, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, null),
                Arguments.of(null, "", "", "", "", null, LOGIN_INFO_ID),
                Arguments.of(FIRST_NAME, LAST_NAME, "", null, GENDER, null, null),//
                Arguments.of(FIRST_NAME, "", null, PHONE, "", BIRTHDAY, LOGIN_INFO_ID),
                Arguments.of(FIRST_NAME, null, LOGIN, "", null, null, null),
                Arguments.of(FIRST_NAME, LAST_NAME, "", null, GENDER, BIRTHDAY, LOGIN_INFO_ID),
                Arguments.of(FIRST_NAME, "", null, PHONE, "", null, null),
                Arguments.of(FIRST_NAME, null, LOGIN, "", null, BIRTHDAY, LOGIN_INFO_ID),
                Arguments.of("", "", LOGIN, null, "", BIRTHDAY, null),
                Arguments.of("", null, "", PHONE, null, null, LOGIN_INFO_ID),
                Arguments.of("", LAST_NAME, null, "", GENDER, BIRTHDAY, null),
                Arguments.of("", "", LOGIN, null, "", null, LOGIN_INFO_ID),
                Arguments.of("", null, "", PHONE, null, BIRTHDAY, null),
                Arguments.of("", LAST_NAME, null, "", GENDER, null, LOGIN_INFO_ID)
        );
    }

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
    @ParameterizedTest(name = "[firstName: {0}, lastName: {1}, login: {2},phone: {3}, gender: {4}, birthday: {5}, loginInfoId: {6}")
    @MethodSource("provideParams")
    public void testConstructorWithMissingRequiredValues(String firstName,
                                                         String lastName,
                                                         String login,
                                                         String phone,
                                                         String gender,
                                                         LocalDate birthday,
                                                         Long loginInfoId) {
        assertThrows(IllegalArgumentException.class, () -> new Mentor(firstName, lastName, login, phone, gender, birthday, loginInfoId),
                "Не получили ожидаеме исключение при вызове метода без всех обязательных параметров");
    }
}
