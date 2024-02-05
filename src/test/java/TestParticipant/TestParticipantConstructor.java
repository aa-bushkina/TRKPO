package TestParticipant;

import com.cygans.database.participant.Participant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static TestParticipant.Variables.BIRTHDAY;
import static TestParticipant.Variables.BREAST;
import static TestParticipant.Variables.FIRST_NAME;
import static TestParticipant.Variables.GENDER;
import static TestParticipant.Variables.HEIGHT;
import static TestParticipant.Variables.HIPS;
import static TestParticipant.Variables.LAST_NAME;
import static TestParticipant.Variables.LOGIN;
import static TestParticipant.Variables.LOGIN_INFO_ID;
import static TestParticipant.Variables.PHONE;
import static TestParticipant.Variables.WAIST;
import static TestParticipant.Variables.WEIGHT;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Проверяет конструктор
 */
public class TestParticipantConstructor {

    /**
     * Проверяет конструктор с параметрами
     */
    @Test
    public void testConstructorWithParameters() {
        Participant participant = new Participant(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT,
                WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID);

        assertAll(
                () -> assertEquals(FIRST_NAME, participant.getFirstName()),
                () -> assertEquals(LAST_NAME, participant.getLastName()),
                () -> assertEquals(LOGIN, participant.getLogin()),
                () -> assertEquals(PHONE, participant.getPhone()),
                () -> assertEquals(GENDER, participant.getGender()),
                () -> assertEquals(BIRTHDAY, participant.getBirthday()),
                () -> assertEquals(HEIGHT, participant.getHeight()),
                () -> assertEquals(WEIGHT, participant.getWeight()),
                () -> assertEquals(HIPS, participant.getHips()),
                () -> assertEquals(WAIST, participant.getWaist()),
                () -> assertEquals(BREAST, participant.getBreast()),
                () -> assertEquals(LOGIN_INFO_ID, participant.getLoginInfoId())
        );
    }

    /**
     * Проверяет конструктор без параметров
     */
    @Test
    public void testDefaultConstructor() {
        Participant participant = new Participant();

        assertAll(
                () -> assertNull(participant.getFirstName()),
                () -> assertNull(participant.getLastName()),
                () -> assertNull(participant.getLogin()),
                () -> assertNull(participant.getPhone()),
                () -> assertNull(participant.getGender()),
                () -> assertNull(participant.getBirthday()),
                () -> assertNull(participant.getHeight()),
                () -> assertNull(participant.getWeight()),
                () -> assertNull(participant.getHips()),
                () -> assertNull(participant.getWaist()),
                () -> assertNull(participant.getBreast()),
                () -> assertNull(participant.getLoginInfoId())
        );
    }

    /**
     * Проверяет конструктор с параметрами null
     */
    @Test
    public void testConstructorWithNullValues() {
        assertThrows(IllegalArgumentException.class, () -> new Participant(null, null, null, null, null, null, null, null, null, null, null, null),
                "Не получили ожидаеме исключение при вызове метода со всеми параметрами null");
    }

    @ParameterizedTest(name = "[firstName: {0}, lastName: {1}, login: {2}, phone: {3}, gender: {4}, birthday: {5}" +
            ", height: {6}, weight: {7}, hips: {8}, waist: {9}, breast: {10}, loginInfoId: {11}")
    @MethodSource("provideInvalidParams")
    public void testConstructorWithMissingRequiredValues(String firstName, String lastName, String login,
                                                         String phone, String gender, LocalDate birthday,
                                                         Integer height, Integer weight, Integer hips,
                                                         Integer waist, Integer breast, Long loginInfoId) {
        assertThrows(IllegalArgumentException.class, () -> new Participant(firstName, lastName, login, phone, gender,
                        birthday, height, weight, hips, waist, breast, loginInfoId),
                "Не получили ожидаеме исключение при вызове метода без всех обязательных параметров");
    }

    private static Stream<Arguments> provideInvalidParams() {
        return Stream.of(
                Arguments.of(null, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT, WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID),
                Arguments.of(FIRST_NAME, null, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT, WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID),
                Arguments.of(FIRST_NAME, LAST_NAME, null, PHONE, GENDER, BIRTHDAY, HEIGHT, WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID),
                Arguments.of(FIRST_NAME, LAST_NAME, LOGIN, null, GENDER, BIRTHDAY, HEIGHT, WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID),
                Arguments.of(FIRST_NAME, LAST_NAME, LOGIN, PHONE, null, BIRTHDAY, HEIGHT, WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID),
                Arguments.of(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, null, HEIGHT, WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID),
                Arguments.of(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, null, WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID),
                Arguments.of(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT, null, HIPS, WAIST, BREAST, LOGIN_INFO_ID),
                Arguments.of(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT, WEIGHT, null, WAIST, BREAST, LOGIN_INFO_ID),
                Arguments.of(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT, WEIGHT, HIPS, null, BREAST, LOGIN_INFO_ID),
                Arguments.of(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT, WEIGHT, HIPS, WAIST, null, LOGIN_INFO_ID),
                Arguments.of(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT, WEIGHT, HIPS, WAIST, BREAST, null)
        );
    }
}
