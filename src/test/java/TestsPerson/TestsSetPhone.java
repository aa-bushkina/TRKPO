package TestsPerson;

import com.cygans.database.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsSetPhone {

    /**
     * Проверка работы setPhone
     */
    @Test
    public void testSetPhone() {
        Person person = new PersonForTest();
        person.setPhone("89767856547");
        assertEquals("89767856547", person.getPhone(), "Установился неверный телефон");
    }

}