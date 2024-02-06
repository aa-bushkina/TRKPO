package backTests.TestsPerson;

import com.cygans.database.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsSetBirthday {

    /**
     * Проверяет работы метода setBirthday
     */
    @Test
    public void testSetBirthday() {
        Person person = new PersonForTest();
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        person.setBirthday(birthday);
        assertEquals(birthday, person.getBirthday(), "Установилось неверное значение дня рождения");
    }

}
