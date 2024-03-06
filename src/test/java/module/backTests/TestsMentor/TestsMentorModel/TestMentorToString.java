package module.backTests.TestsMentor.TestsMentorModel;

import com.cygans.database.mentor.Mentor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Провеяет метод toString()
 */
public class TestMentorToString {
    private static final String FIRST_NAME = "John";
    private static final String FIRST_NAME_UPDATED = "John2";
    private static final String LAST_NAME = "Doe";
    private static final String LAST_NAME_UPDATED = "Doe2";
    private static final String LOGIN = "john.doe";
    private static final String LOGIN_UPDATED = "john.doe2";
    private static final String PHONE = "+79370904985";
    private static final String PHONE_UPDATED = "+79370904982";
    private static final String GENDER = "Male";
    private static final String GENDER_UPDATED = "Female";
    private static final LocalDate BIRTHDAY = LocalDate.of(1990, 1, 1);
    private static final LocalDate BIRTHDAY_UPDATED = LocalDate.of(1992, 2, 2);
    private static final Long LOGIN_INFO_ID = 1L;

    /**
     * Проверяет метод toString() на наличие обязательных полей
     */
    @Test
    public void testToStringWithRequiredFields() {
        Mentor mentor = new Mentor(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, LOGIN_INFO_ID);

        String toStringResult = mentor.toString();
        assertAll(
                () -> assertTrue(toStringResult.contains("id=")),
                () -> assertTrue(toStringResult.contains("firstName=")),
                () -> assertTrue(toStringResult.contains("lastName=")),
                () -> assertTrue(toStringResult.contains("login=")),
                () -> assertTrue(toStringResult.contains("phone=")),
                () -> assertTrue(toStringResult.contains("gender=")),
                () -> assertTrue(toStringResult.contains("birthday=")),
                () -> assertTrue(toStringResult.contains("loginInfoId="))
        );
    }

    /**
     * Провеяет метод toString() с валидными значениями полей
     */
    @Test
    public void testToString() {
        Mentor mentor = new Mentor(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, LOGIN_INFO_ID);

        String toStringResult = mentor.toString();
        assertAll(
                () -> assertTrue(toStringResult.contains("id=0")),
                () -> assertTrue(toStringResult.contains("firstName=John")),
                () -> assertTrue(toStringResult.contains("lastName=Doe")),
                () -> assertTrue(toStringResult.contains("login=john.doe")),
                () -> assertTrue(toStringResult.contains("phone=+79370904985")),
                () -> assertTrue(toStringResult.contains("gender=Male")),
                () -> assertTrue(toStringResult.contains("birthday=1990-01-01")),
                () -> assertTrue(toStringResult.contains("loginInfoId=1"))
        );
    }

    /**
     * Проверяет метод toString() после изменений полей объекта
     */
    @Test
    public void testToStringAfterModifications() {
        Mentor mentor = new Mentor(FIRST_NAME, LAST_NAME, LOGIN, PHONE, GENDER, BIRTHDAY, LOGIN_INFO_ID);
        String toStringResult = mentor.toString();
        assertAll(
                () -> assertTrue(toStringResult.contains("id=0")),
                () -> assertTrue(toStringResult.contains("firstName=John")),
                () -> assertTrue(toStringResult.contains("lastName=Doe")),
                () -> assertTrue(toStringResult.contains("login=john.doe")),
                () -> assertTrue(toStringResult.contains("phone=+79370904985")),
                () -> assertTrue(toStringResult.contains("gender=Male")),
                () -> assertTrue(toStringResult.contains("birthday=1990-01-01")),
                () -> assertTrue(toStringResult.contains("loginInfoId=1"))
        );

        mentor.setFirstName(FIRST_NAME_UPDATED);
        mentor.setLastName(LAST_NAME_UPDATED);
        mentor.setLogin(LOGIN_UPDATED);
        mentor.setPhone(PHONE_UPDATED);
        mentor.setGender(GENDER_UPDATED);
        mentor.setBirthday(BIRTHDAY_UPDATED);

        String toStringResultUpdated = mentor.toString();
        assertAll(
                () -> assertTrue(toStringResultUpdated.contains("id=0")),
                () -> assertTrue(toStringResultUpdated.contains("firstName=John2")),
                () -> assertTrue(toStringResultUpdated.contains("lastName=Doe2")),
                () -> assertTrue(toStringResultUpdated.contains("login=john.doe2")),
                () -> assertTrue(toStringResultUpdated.contains("phone=+79370904982")),
                () -> assertTrue(toStringResultUpdated.contains("gender=Female")),
                () -> assertTrue(toStringResultUpdated.contains("birthday=1992-02-02")),
                () -> assertTrue(toStringResultUpdated.contains("loginInfoId=1"))
        );
    }

    /**
     * Проверяет метод toString() с пустыми значениями полей
     */
    @Test
    public void testToStringWithEmptyFields() {
        Mentor mentor = new Mentor();

        String toStringResult = mentor.toString();
        assertAll(
                () -> assertTrue(toStringResult.contains("id=0")),
                () -> assertTrue(toStringResult.contains("firstName=null")),
                () -> assertTrue(toStringResult.contains("lastName=null")),
                () -> assertTrue(toStringResult.contains("login=null")),
                () -> assertTrue(toStringResult.contains("phone=null")),
                () -> assertTrue(toStringResult.contains("gender=null")),
                () -> assertTrue(toStringResult.contains("birthday=null")),
                () -> assertTrue(toStringResult.contains("loginInfoId=null"))
        );
    }
}
