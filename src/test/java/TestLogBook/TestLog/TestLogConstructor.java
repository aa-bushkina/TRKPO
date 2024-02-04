package TestLogBook.TestLog;


import com.cygans.database.log_book.Log;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тесты для конструкторов класса Log
 */
public class TestLogConstructor {
    /**
     * Проверяет конструктор по умолчанию
     */
    @Test
    public void testDefaultConstructor() {
        Log log = new Log();

        assertAll(
                () -> assertNull(log.getParticipantId(), "participantId должен быть null"),
                () -> assertNull(log.getDate(), "Дата должна быть null"),
                () -> assertEquals(0, log.getLogTypeId(), "logTypeId по умолчанию должен быть 0"),
                () -> assertNotNull(log.getId(), "Id не должен быть null после создания")
        );
    }

    /**
     * Проверяет конструктор с параметрами
     */
    @Test
    public void testConstructorWithParameters() {
        Long participantId = 1L;
        LocalDate date = LocalDate.now();
        long logTypeId = 2L;

        Log log = new Log(participantId, date, logTypeId);

        assertAll(
                () -> assertEquals(participantId, log.getParticipantId(), "Неверное значение participantId"),
                () -> assertEquals(date, log.getDate(), "Неверное значение даты"),
                () -> assertEquals(logTypeId, log.getLogTypeId(), "Неверное значение logTypeId"),
                () -> assertNotNull(log.getId(), "Id не должен быть null после создания")
        );
    }

    /**
     * Проверяет конструктор с параметрами, где participantId равен null
     */
    @Test
    public void testConstructorWithNullParticipantId() {
        LocalDate date = LocalDate.now();
        long logTypeId = 2L;

        Log log = new Log(null, date, logTypeId);

        assertAll(
                () -> assertNull(log.getParticipantId(), "participantId должен быть null"),
                () -> assertEquals(date, log.getDate(), "Неверное значение даты"),
                () -> assertEquals(logTypeId, log.getLogTypeId(), "Неверное значение logTypeId"),
                () -> assertNotNull(log.getId(), "Id не должен быть null после создания")
        );
    }
}