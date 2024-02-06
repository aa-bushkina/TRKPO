package backTests.TestsPerson;

import com.cygans.database.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetGender {

    /**
     * Проверка работы getGender
     */
    @Test
    public void testGetGender() {
        Person person = new PersonForTest();
        person.setGender("Male");
        assertEquals("Male", person.getGender(), "Вернулось неверное значнение gender");
    }

}
