package TestsNotifications.TestsStatusOfNotificationEnum;

import com.cygans.database.notifications.notification_status.StatusOfNotification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestsStatusOfNotification {

    /**
     * Проверяет, что каждый тип StatusOfNotification имеет текст
     */
    @Test
    public void testStatusOfNotificationHasText() {
        for (StatusOfNotification StatusOfNotification : StatusOfNotification.values()) {
            assertNotNull(StatusOfNotification.getValue(), "Текст для " + StatusOfNotification + " не должен быть null");
        }
    }

    /**
     * Проверяет, что getText() возвращает ожидаемый текст для каждого типа StatusOfNotification
     */
    @Test
    public void testGetText() {
        assertEquals("Новый ответ", StatusOfNotification.ANSWERED_NOT_SEEN.getValue(),
                "Результат вызова getText() не совпадает с ожидаемым для ANSWERED_NOT_SEEN");
        assertEquals("Нет ответа", StatusOfNotification.NO_ANSWER.getValue(),
                "Результат вызова getText() не совпадает с ожидаемым для NO_ANSWER");
        assertEquals("Ответ просмотрен", StatusOfNotification.ANSWERED_SEEN.getValue(),
                "Результат вызова getText() не совпадает с ожидаемым для ANSWERED_SEEN");
    }

    /**
     * Проверяет, что значения StatusOfNotification не являются null
     */
    @Test
    public void testEnumValuesNotNull() {
        for (StatusOfNotification StatusOfNotification : StatusOfNotification.values()) {
            assertNotNull(StatusOfNotification, "Значение Enum " + StatusOfNotification + " не должно быть null");
        }
    }

    /**
     * Проверяет, что toString() возвращает ожидаемые значения
     */
    @Test
    public void testToString() {
        assertEquals("ANSWERED_NOT_SEEN", StatusOfNotification.ANSWERED_NOT_SEEN.toString(),
                "Результат вызова toString() не совпадает с ожидаемым для EMOTIONAL");
        assertEquals("ANSWERED_SEEN", StatusOfNotification.ANSWERED_SEEN.toString(),
                "Результат вызова toString() не совпадает с ожидаемым для SPORT");
        assertEquals("NO_ANSWER", StatusOfNotification.NO_ANSWER.toString(),
                "Результат вызова toString() не совпадает с ожидаемым для EATING");
    }

    /**
     * Проверяет сравнение элементов enum
     */
    @Test
    public void testEnumEquality() {
        assertEquals(StatusOfNotification.ANSWERED_NOT_SEEN, StatusOfNotification.ANSWERED_NOT_SEEN,
                "Сравнение двух элементов ANSWERED_NOT_SEEN показало, что они не равны");
        assertEquals(StatusOfNotification.ANSWERED_SEEN, StatusOfNotification.ANSWERED_SEEN,
                "Сравнение двух элементов ANSWERED_SEEN показало, что они не равны");
        assertEquals(StatusOfNotification.NO_ANSWER, StatusOfNotification.NO_ANSWER,
                "Сравнение двух элементов типа NO_ANSWER показало, что они не равны");
    }

    /**
     * Проверяет работу метода valueOf()
     */
    @Test
    public void testValueOf() {
        assertEquals(StatusOfNotification.ANSWERED_NOT_SEEN, StatusOfNotification.valueOf("ANSWERED_NOT_SEEN"),
                "Результат вызова valueOf() не совпадает с ожидаемым для ANSWERED_NOT_SEEN");
        assertEquals(StatusOfNotification.ANSWERED_SEEN, StatusOfNotification.valueOf("ANSWERED_SEEN"),
                "Результат вызова valueOf() не совпадает с ожидаемым для ANSWERED_SEEN");
        assertEquals(StatusOfNotification.NO_ANSWER, StatusOfNotification.valueOf("NO_ANSWER"),
                "Результат вызова valueOf() не совпадает с ожидаемым для NO_ANSWER");
    }

    /**
     * Проверяет создание Enum из недопустимой строки
     */
    @Test
    public void testValueOfWithInvalidString() {
        assertThrows(IllegalArgumentException.class, () -> StatusOfNotification.valueOf("INVALID_TYPE"));
    }

}
