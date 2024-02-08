package views.TestsParticipantPersonData;

import com.cygans.views.participant.logbooks.ParticipantPersonData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsLogBookType {

    private ParticipantPersonData personData;

    @BeforeEach
    public void setUp() {
        personData = new ParticipantPersonData();
    }

    /**
     * Проверка методов get и set для поля logBookType.
     */
    @Test
    public void testGetSetLogBookType() {
        personData.setLogBookType("Test Logbook");
        assertEquals("Test Logbook", personData.getLogBookType(), "Значение не совпадают");
    }

}
