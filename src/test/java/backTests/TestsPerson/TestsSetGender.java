package backTests.TestsPerson;

import com.cygans.database.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsSetGender {

    /**
     * Проверяет работа setGender
     */
    @Test
    public void testSetGender() {
        Person person = new PersonForTest();
        person.setGender("Male");
        assertEquals("Male", person.getGender(), "Установилось неверное значение");
    }

}
