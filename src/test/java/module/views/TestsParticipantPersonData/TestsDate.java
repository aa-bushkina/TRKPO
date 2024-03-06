package module.views.TestsParticipantPersonData;

import com.cygans.views.participant.logbooks.ParticipantPersonData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsDate {

    private ParticipantPersonData personData;

    @BeforeEach
    public void setUp() {
        personData = new ParticipantPersonData();
    }

    /**
     * Проверка методов get и set для поля date.
     */
    @Test
    public void testGetSetDate() {
        LocalDate date = LocalDate.of(2024, 2, 10);
        personData.setDate(date);
        assertEquals(date, personData.getDate());
    }

}
