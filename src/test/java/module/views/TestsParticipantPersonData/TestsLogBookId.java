package module.views.TestsParticipantPersonData;

import com.cygans.views.participant.logbooks.ParticipantPersonData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsLogBookId {

    private ParticipantPersonData personData;

    @BeforeEach
    public void setUp() {
        personData = new ParticipantPersonData();
    }

    /**
     * Проверка методов get и set для поля logBookId.
     */
    @Test
    public void testGetSetLogBookType() {
        personData.setLogBookId(1L);
        assertEquals(1L, personData.getLogBookId(), "Значение не совпадают");
    }

}
