package backTests.TestsPerson;

import com.cygans.database.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetLogin {

    /**
     * Проверяет работы getLogin
     */
    @Test
    public void testGetLogin() {
        Person person = new PersonForTest();
        person.setLogin("john.doe@example.com");
        assertEquals("john.doe@example.com", person.getLogin(), "Верулся неверный логин");
    }

}
