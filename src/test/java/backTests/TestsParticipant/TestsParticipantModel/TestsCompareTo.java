package backTests.TestsParticipant.TestsParticipantModel;

import com.cygans.database.participant.Participant;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты для метода compareTo() класса Participant
 */
public class TestsCompareTo {
    public static final LocalDate EQUAL_BIRTHDAY = LocalDate.of(2000, 1, 1);
    public static final LocalDate EARLIER_BIRTHDAY = LocalDate.of(2000, 1, 1);
    public static final LocalDate LATER_BIRTHDAY = LocalDate.of(2005, 1, 1);

    public static final Participant PARTICIPANT_EQUAL_BIRTHDAYS = new Participant(
            "John", "Doe", "johndoe", "+79384857683", "Male", EQUAL_BIRTHDAY, 180, 70, 90, 80, 95, 1L);

    public static final Participant PARTICIPANT_EARLIER_BIRTHDAY = new Participant(
            "John", "Doe", "johndoe", "+79384857681", "Male", EARLIER_BIRTHDAY, 180, 70, 90, 80, 95, 1L);

    public static final Participant PARTICIPANT_LATER_BIRTHDAY = new Participant(
            "Jane", "Doe", "janedoe", "+79384857685", "Female", LATER_BIRTHDAY, 170, 60, 85, 75, 90, 2L);

    /**
     * Проверяет, что метод возвращает 0 для участников с одинаковой датой рождения
     */
    @Test
    public void testCompareToEqualBirthdays() {
        assertEquals(0, PARTICIPANT_EQUAL_BIRTHDAYS.compareTo(PARTICIPANT_EQUAL_BIRTHDAYS),
                "Метод compareTo() не возвращает 0 для участников с одинаковой датой рождения");
    }

    /**
     * Проверяет, что метод возвращает отрицательное значение для участника с более ранней датой рождения
     */
    @Test
    public void testCompareToEarlierBirthday() {
        assertTrue(PARTICIPANT_EARLIER_BIRTHDAY.compareTo(PARTICIPANT_LATER_BIRTHDAY) < 0,
                "Метод compareTo() не возвращает отрицательное значение для участника с более ранней датой рождения");
    }

    /**
     * Проверяет, что метод возвращает положительное значение для участника с более поздней датой рождения
     */
    @Test
    public void testCompareToLaterBirthday() {
        assertTrue(PARTICIPANT_LATER_BIRTHDAY.compareTo(PARTICIPANT_EARLIER_BIRTHDAY) > 0,
                "Метод compareTo() не возвращает положительное значение для участника с более поздней датой рождения");
    }
}
