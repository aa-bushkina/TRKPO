package TestsPerson;

import com.cygans.database.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetBirthday {

    /**
     * Проверка работы getBirthday
     */
    @Test
    public void testGetBirthday() {
        Person person = new PersonForTest();
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        person.setBirthday(birthday);
        assertEquals(birthday, person.getBirthday(), "Вернулось неверное значения дня рождения");
    }

}
