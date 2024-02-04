package TestParticipant;

import com.cygans.database.mentor.Mentor;
import com.cygans.database.participant.Participant;
import org.junit.Test;

import java.time.LocalDate;

import static TestParticipant.Variables.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Провеяет метод toString()
 */
public class TestParticipantToString {
    private static final String HEIGHT_FIELD = "height=";
    private static final String WEIGHT_FIELD = "weight=";
    private static final String HIPS_FILED = "hips=";
    private static final String WAIST_FIELD = "waist=";
    private static final String BREAST_FIELD = "breast=";
    private static final String ID_FIELD = "id=";
    private static final String FIRST_NAME_FIELD = "firstname=";
    private static final String LAST_NAME_FIELD = "lastname=";
    private static final String LOGIN_FIELD = "login=";
    private static final String PHONE_FIELD = "phone=";
    private static final String GENDER_FIELD = "gender=";
    private static final String BIRTHDAY_FIELD = "birthday=";
    private static final String LOGIN_INFO_ID_FIELD = "loginInfoId=";

    private static final String NULL_STRING = "null";

    /**
     * Проверяет метод toString() на наличие обязательных полей
     */
    @Test
    public void testToStringWithRequiredFields() {
        Participant participant = new Participant(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT,
          WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID);

        String toStringResult = participant.toString();
        assertAll(
              () -> assertTrue(toStringResult.contains(HEIGHT_FIELD)),
              () -> assertTrue(toStringResult.contains(WEIGHT_FIELD)),
              () -> assertTrue(toStringResult.contains(HIPS_FILED)),
              () -> assertTrue(toStringResult.contains(WAIST_FIELD)),
              () -> assertTrue(toStringResult.contains(BREAST_FIELD)),
              () -> assertTrue(toStringResult.contains(ID_FIELD)),
              () -> assertTrue(toStringResult.contains(FIRST_NAME_FIELD)),
              () -> assertTrue(toStringResult.contains(LAST_NAME_FIELD)),
              () -> assertTrue(toStringResult.contains(LOGIN_FIELD)),
              () -> assertTrue(toStringResult.contains(PHONE_FIELD)),
              () -> assertTrue(toStringResult.contains(GENDER_FIELD)),
              () -> assertTrue(toStringResult.contains(BIRTHDAY_FIELD)),
              () -> assertTrue(toStringResult.contains(LOGIN_INFO_ID_FIELD))
        );
    }

    /**
     * Провеяет метод toString() с валидными значениями полей
     */
    @Test
    public void testToString() {
      Participant participant = new Participant(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT,
        WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID);

        String toStringResult = participant.toString();
        assertAll(
          () -> assertTrue(toStringResult.contains(HEIGHT_FIELD + HEIGHT)),
          () -> assertTrue(toStringResult.contains(WEIGHT_FIELD + WEIGHT)),
          () -> assertTrue(toStringResult.contains(HIPS_FILED + HIPS)),
          () -> assertTrue(toStringResult.contains(WAIST_FIELD + WAIST)),
          () -> assertTrue(toStringResult.contains(BREAST_FIELD + BREAST)),
          () -> assertTrue(toStringResult.contains(ID_FIELD + 0)),
          () -> assertTrue(toStringResult.contains(FIRST_NAME_FIELD + FIRST_NAME)),
          () -> assertTrue(toStringResult.contains(LAST_NAME_FIELD + LAST_NAME)),
          () -> assertTrue(toStringResult.contains(LOGIN_FIELD + LOGIN)),
          () -> assertTrue(toStringResult.contains(PHONE_FIELD + PHONE)),
          () -> assertTrue(toStringResult.contains(GENDER_FIELD + GENDER)),
          () -> assertTrue(toStringResult.contains(BIRTHDAY_FIELD + BIRTHDAY)),
          () -> assertTrue(toStringResult.contains(LOGIN_INFO_ID_FIELD + LOGIN_INFO_ID))
        );
    }

    /**
     * Проверяет метод toString() после изменений полей объекта
     */
    @Test
    public void testToStringAfterModifications() {
        Participant participant = new Participant(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, HEIGHT,
          WEIGHT, HIPS, WAIST, BREAST, LOGIN_INFO_ID);
        String toStringResult = participant.toString();
        assertAll(
          () -> assertTrue(toStringResult.contains(HEIGHT_FIELD + HEIGHT)),
          () -> assertTrue(toStringResult.contains(WEIGHT_FIELD + WEIGHT)),
          () -> assertTrue(toStringResult.contains(HIPS_FILED + HIPS)),
          () -> assertTrue(toStringResult.contains(WAIST_FIELD + WAIST)),
          () -> assertTrue(toStringResult.contains(BREAST_FIELD + BREAST)),
          () -> assertTrue(toStringResult.contains(ID_FIELD + 0)),
          () -> assertTrue(toStringResult.contains(FIRST_NAME_FIELD + FIRST_NAME)),
          () -> assertTrue(toStringResult.contains(LAST_NAME_FIELD + LAST_NAME)),
          () -> assertTrue(toStringResult.contains(LOGIN_FIELD + LOGIN)),
          () -> assertTrue(toStringResult.contains(PHONE_FIELD + PHONE)),
          () -> assertTrue(toStringResult.contains(GENDER_FIELD + GENDER)),
          () -> assertTrue(toStringResult.contains(BIRTHDAY_FIELD + BIRTHDAY)),
          () -> assertTrue(toStringResult.contains(LOGIN_INFO_ID_FIELD + LOGIN_INFO_ID))
        );

        participant.setFirstName(FIRST_NAME_UPDATED);
        participant.setLastName(LAST_NAME_UPDATED);
        participant.setLogin(LOGIN_UPDATED);
        participant.setPhone(PHONE_UPDATED);
        participant.setGender(GENDER_UPDATED);
        participant.setBirthday(BIRTHDAY_UPDATED);
        participant.setBreast(BREAST_UPDATED);
        participant.setHeight(HEIGHT_UPDATED);
        participant.setHips(HIPS_UPDATED);
        participant.setWaist(WAIST_UPDATED);
        participant.setWeight(WEIGHT_UPDATED);

        String toStringResultUpdated = participant.toString();
        assertAll(
          () -> assertTrue(toStringResultUpdated.contains(HEIGHT_FIELD + HEIGHT_UPDATED)),
          () -> assertTrue(toStringResultUpdated.contains(WEIGHT_FIELD + WEIGHT_UPDATED)),
          () -> assertTrue(toStringResultUpdated.contains(HIPS_FILED + HIPS_UPDATED)),
          () -> assertTrue(toStringResultUpdated.contains(WAIST_FIELD + WAIST_UPDATED)),
          () -> assertTrue(toStringResultUpdated.contains(BREAST_FIELD + BREAST_UPDATED)),
          () -> assertTrue(toStringResultUpdated.contains(ID_FIELD + 0)),
          () -> assertTrue(toStringResultUpdated.contains(FIRST_NAME_FIELD + FIRST_NAME_UPDATED)),
          () -> assertTrue(toStringResultUpdated.contains(LAST_NAME_FIELD + LAST_NAME_UPDATED)),
          () -> assertTrue(toStringResultUpdated.contains(LOGIN_FIELD + LOGIN_UPDATED)),
          () -> assertTrue(toStringResultUpdated.contains(PHONE_FIELD + PHONE_UPDATED)),
          () -> assertTrue(toStringResultUpdated.contains(GENDER_FIELD + GENDER_UPDATED)),
          () -> assertTrue(toStringResultUpdated.contains(BIRTHDAY_FIELD + BIRTHDAY_UPDATED)),
          () -> assertTrue(toStringResultUpdated.contains(LOGIN_INFO_ID_FIELD + LOGIN_INFO_ID))
        );
    }

    /**
     * Проверяет метод toString() с пустыми значениями полей
     */
    @Test
    public void testToStringWithEmptyFields() {
        Participant participant = new Participant();

        String toStringResult = participant.toString();
        assertAll(
          () -> assertTrue(toStringResult.contains(HEIGHT_FIELD + NULL_STRING)),
          () -> assertTrue(toStringResult.contains(WEIGHT_FIELD + NULL_STRING)),
          () -> assertTrue(toStringResult.contains(HIPS_FILED + NULL_STRING)),
          () -> assertTrue(toStringResult.contains(WAIST_FIELD + NULL_STRING)),
          () -> assertTrue(toStringResult.contains(BREAST_FIELD + NULL_STRING)),
          () -> assertTrue(toStringResult.contains(ID_FIELD + 0)),
          () -> assertTrue(toStringResult.contains(FIRST_NAME_FIELD + NULL_STRING)),
          () -> assertTrue(toStringResult.contains(LAST_NAME_FIELD + NULL_STRING)),
          () -> assertTrue(toStringResult.contains(LOGIN_FIELD + NULL_STRING)),
          () -> assertTrue(toStringResult.contains(PHONE_FIELD + NULL_STRING)),
          () -> assertTrue(toStringResult.contains(GENDER_FIELD + NULL_STRING)),
          () -> assertTrue(toStringResult.contains(BIRTHDAY_FIELD + NULL_STRING)),
          () -> assertTrue(toStringResult.contains(LOGIN_INFO_ID_FIELD + NULL_STRING))
        );
    }
}
