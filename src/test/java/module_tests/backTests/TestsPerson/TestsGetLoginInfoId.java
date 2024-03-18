package module_tests.backTests.TestsPerson;

import com.cygans.database.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class TestsGetLoginInfoId {

    @Test
    public void testGetLoginInfoId() {
        Person person = new PersonForTest();
        assertNull(person.getLoginInfoId(), "Вернулось неверное значение loginInfoId");
    }

}
