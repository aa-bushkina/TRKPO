package backTests.TestsPerson;

import com.cygans.database.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetFirstName {

    /**
     * Проверяет работы getFirstName
     */
    @Test
    public void testGetFirstName() {
        Person person = new PersonForTest();
        person.setFirstName("John");
        assertEquals("John", person.getFirstName(), "Вернулось неверное значение имени");
    }

}
