package TestsPerson;

import com.cygans.database.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsSetLastName {

    /**
     * Проверяет работы setLastName
     */
    @Test
    public void testSetLastName() {
        Person person = new PersonForTest();
        person.setLastName("Doe");
        assertEquals("Doe", person.getLastName(), "Установилась неверная фамилия");
    }

}
