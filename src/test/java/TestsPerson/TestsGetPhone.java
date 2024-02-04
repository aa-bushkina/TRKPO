package TestsPerson;

import com.cygans.database.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetPhone {

    /**
     * Проверяет работы getPhone
     */
    @Test
    public void testGetPhone() {
        Person person = new PersonForTest();
        person.setPhone("89563787635");
        assertEquals("89563787635", person.getPhone(), "Верунлся неверный номер телефона");
    }

}
