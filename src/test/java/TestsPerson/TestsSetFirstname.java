package TestsPerson;

import com.cygans.database.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsSetFirstname {

    /**
     * Проверяет работы setFirstName
     */
    @Test
    public void testSetFirstName() {
        Person person = new PersonForTest();
        person.setFirstName("John");
        assertEquals("John", person.getFirstName(), "Установилось неверное значение имени");
    }

}
