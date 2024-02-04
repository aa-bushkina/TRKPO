package TestsPerson;

import com.cygans.database.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsSetLogin {

    /**
     * Проверяет работы setLogin
     */
    @Test
    public void testSetLogin() {
        Person person = new PersonForTest();
        person.setLogin("john.doe@example.com");
        assertEquals("john.doe@example.com", person.getLogin(), "Установился неверный логин");
    }

}
