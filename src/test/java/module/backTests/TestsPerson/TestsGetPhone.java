package module.backTests.TestsPerson;

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
        person.setPhone("+79563787635");
        assertEquals("+79563787635", person.getPhone(), "Верунлся неверный номер телефона");
    }

}
