package TestsTypeOfNotification;

import com.cygans.database.notifications.notification_type.TypeOfNotification;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestsTypeOfNotification {

    /**
     * Проверяет, что каждый тип TypeOfNotification имеет текст
     */
    @Test
    public void testTypeOfNotificationHasText() {
        for (TypeOfNotification TypeOfNotification : TypeOfNotification.values()) {
            assertNotNull(TypeOfNotification.getValue(), "Текст для " + TypeOfNotification + " не должен быть null");
        }
    }

    /**
     * Проверяет, что getText() возвращает ожидаемый текст для каждого типа TypeOfNotification
     */
    @Test
    public void testGetText() {
        assertEquals("Ответ ментора на вопрос", TypeOfNotification.ANSWER_ON_QUESTION.getValue(),
                "Результат вызова getText() не совпадает с ожидаемым для ANSWER_ON_QUESTION");
        assertEquals("Ответ ментора на запись", TypeOfNotification.ANSWER_ON_LOG.getValue(),
                "Результат вызова getText() не совпадает с ожидаемым для ANSWER_ON_LOG");
        assertEquals("Вопрос", TypeOfNotification.QUESTION.getValue(),
                "Результат вызова getText() не совпадает с ожидаемым для QUESTION");
        assertEquals("Добавление в отслеживание", TypeOfNotification.ADD_REQUEST.getValue(),
                "Результат вызова getText() не совпадает с ожидаемым для ADD_REQUEST");
        assertEquals("Отказ в отслеживании", TypeOfNotification.DECLINE_MENTOR.getValue(),
                "Результат вызова getText() не совпадает с ожидаемым для DECLINE_MENTOR");
        assertEquals("Новая запись участника", TypeOfNotification. NEW_LOG.getValue(),
                "Результат вызова getText() не совпадает с ожидаемым для NEW_LOG");
        assertEquals("Удаление из отслеживания", TypeOfNotification.DELETE_REQUEST.getValue(),
                "Результат вызова getText() не совпадает с ожидаемым для DELETE_REQUEST");
    }

    /**
     * Проверяет, что значения TypeOfNotification не являются null
     */
    @Test
    public void testEnumValuesNotNull() {
        for (TypeOfNotification TypeOfNotification : TypeOfNotification.values()) {
            assertNotNull(TypeOfNotification, "Значение Enum " + TypeOfNotification + " не должно быть null");
        }
    }

    /**
     * Проверяет, что toString() возвращает ожидаемые значения
     */
    @Test
    public void testToString() {
        assertEquals("ANSWER_ON_QUESTION", TypeOfNotification.ANSWER_ON_QUESTION.toString(),
                "Результат вызова toString() не совпадает с ожидаемым для ANSWER_ON_QUESTION");
        assertEquals("ANSWER_ON_LOG", TypeOfNotification.ANSWER_ON_LOG.toString(),
                "Результат вызова toString() не совпадает с ожидаемым для ANSWER_ON_LOG");
        assertEquals("QUESTION", TypeOfNotification.QUESTION.toString(),
                "Результат вызова toString() не совпадает с ожидаемым для QUESTION");
        assertEquals("ADD_REQUEST", TypeOfNotification.ADD_REQUEST.toString(),
                "Результат вызова toString() не совпадает с ожидаемым для ADD_REQUEST");
        assertEquals("DECLINE_MENTOR", TypeOfNotification.DECLINE_MENTOR.toString(),
                "Результат вызова toString() не совпадает с ожидаемым для DECLINE_MENTOR");
        assertEquals("NEW_LOG", TypeOfNotification.NEW_LOG.toString(),
                "Результат вызова toString() не совпадает с ожидаемым для NEW_LOG");
        assertEquals("DELETE_REQUEST", TypeOfNotification.DELETE_REQUEST.toString(),
                "Результат вызова toString() не совпадает с ожидаемым для DELETE_REQUEST");
    }

    /**
     * Проверяет сравнение элементов enum
     */
    @Test
    public void testEnumEquality() {
        assertEquals(TypeOfNotification.ANSWER_ON_QUESTION, TypeOfNotification.ANSWER_ON_QUESTION,
                "Сравнение двух элементов ANSWER_ON_QUESTION показало, что они не равны");
        assertEquals(TypeOfNotification.ANSWER_ON_LOG, TypeOfNotification.ANSWER_ON_LOG,
                "Сравнение двух элементов ANSWER_ON_LOG показало, что они не равны");
        assertEquals(TypeOfNotification.QUESTION, TypeOfNotification.QUESTION,
                "Сравнение двух элементов типа QUESTION показало, что они не равны");
        assertEquals(TypeOfNotification.ADD_REQUEST, TypeOfNotification.ADD_REQUEST,
                "Сравнение двух элементов типа ADD_REQUEST показало, что они не равны");
        assertEquals(TypeOfNotification.DECLINE_MENTOR, TypeOfNotification.DECLINE_MENTOR,
                "Сравнение двух элементов типа DECLINE_MENTOR показало, что они не равны");
        assertEquals(TypeOfNotification.NEW_LOG, TypeOfNotification.NEW_LOG,
                "Сравнение двух элементов типа NEW_LOG показало, что они не равны");
        assertEquals(TypeOfNotification.DELETE_REQUEST, TypeOfNotification.DELETE_REQUEST,
                "Сравнение двух элементов типа DELETE_REQUEST показало, что они не равны");
    }

    /**
     * Проверяет работу метода valueOf()
     */
    @Test
    public void testValueOf() {
        assertEquals(TypeOfNotification.ANSWER_ON_QUESTION, TypeOfNotification.valueOf("ANSWER_ON_QUESTION"),
                "Результат вызова valueOf() не совпадает с ожидаемым для ANSWER_ON_QUESTION");
        assertEquals(TypeOfNotification.ANSWER_ON_LOG, TypeOfNotification.valueOf("ANSWER_ON_LOG"),
                "Результат вызова valueOf() не совпадает с ожидаемым для ANSWER_ON_LOG");
        assertEquals(TypeOfNotification.QUESTION, TypeOfNotification.valueOf("QUESTION"),
                "Результат вызова valueOf() не совпадает с ожидаемым для QUESTION");
        assertEquals(TypeOfNotification.ADD_REQUEST, TypeOfNotification.valueOf("ADD_REQUEST"),
                "Результат вызова valueOf() не совпадает с ожидаемым для ADD_REQUEST");
        assertEquals(TypeOfNotification.DECLINE_MENTOR, TypeOfNotification.valueOf("DECLINE_MENTOR"),
                "Результат вызова valueOf() не совпадает с ожидаемым для DECLINE_MENTOR");
        assertEquals(TypeOfNotification.NEW_LOG, TypeOfNotification.valueOf("NEW_LOG"),
                "Результат вызова valueOf() не совпадает с ожидаемым для NEW_LOG");
        assertEquals(TypeOfNotification.DELETE_REQUEST, TypeOfNotification.valueOf("DELETE_REQUEST"),
                "Результат вызова valueOf() не совпадает с ожидаемым для DELETE_REQUEST");
    }

    /**
     * Проверяет создание Enum из недопустимой строки
     */
    @Test
    public void testValueOfWithInvalidString() {
        assertThrows(IllegalArgumentException.class, () -> TypeOfNotification.valueOf("INVALID_TYPE"));
    }
    
}
