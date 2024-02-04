package TestsPerson;

import com.cygans.database.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetLastName {

    /**
     * Проверяет работы getLastName
     */
    @Test
    public void testGetLastName() {
        Person person = new PersonForTest();
        person.setLastName("Doe");
        assertEquals("Doe", person.getLastName(), "Вернулось неверное значение фамилии");
    }

}
