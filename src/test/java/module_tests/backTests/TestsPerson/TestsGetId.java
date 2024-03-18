package module_tests.backTests.TestsPerson;

import com.cygans.database.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsGetId {

    /**
     * Проверяет работы getId
     */
    @Test
    public void testGetId() {
        Person person = new PersonForTest();
        assertEquals(0L, person.getId(), "Вернулся неверный id");
    }

}
