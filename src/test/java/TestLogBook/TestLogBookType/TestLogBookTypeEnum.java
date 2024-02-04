package TestLogBook.TestLogBookType;


import com.cygans.database.log_book.logs_type.LogBookType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тесты для LogBookType enum
 */
public class TestLogBookTypeEnum {
    /**
     * Проверяет, что каждый тип LogBookType имеет текст
     */
    @Test
    public void testLogBookTypeHasText() {
        for (LogBookType logBookType : LogBookType.values()) {
            assertNotNull(logBookType.getText(), "Текст для " + logBookType + " не должен быть null");
        }
    }

    /**
     * Проверяет, что getText() возвращает ожидаемый текст для каждого типа LogBookType
     */
    @Test
    public void testGetText() {
        assertEquals("Эмоциональное состояние", LogBookType.EMOTIONAL.getText(),
                "Результат вызова getText() не совпадает с ожидаемым для EMOTIONAL");
        assertEquals("Спортивная активность", LogBookType.SPORT.getText(),
                "Результат вызова getText() не совпадает с ожидаемым для SPORT");
        assertEquals("Приём пищи", LogBookType.EATING.getText(),
                "Результат вызова getText() не совпадает с ожидаемым для EATING");
    }

    /**
     * Проверяет, что значения LogBookType не являются null
     */
    @Test
    public void testEnumValuesNotNull() {
        for (LogBookType logBookType : LogBookType.values()) {
            assertNotNull(logBookType, "Значение Enum " + logBookType + " не должно быть null");
        }
    }

    /**
     * Проверяет, что toString() возвращает ожидаемые значения
     */
    @Test
    public void testToString() {
        assertEquals("EMOTIONAL", LogBookType.EMOTIONAL.toString(),
                "Результат вызова toString() не совпадает с ожидаемым для EMOTIONAL");
        assertEquals("SPORT", LogBookType.SPORT.toString(),
                "Результат вызова toString() не совпадает с ожидаемым для SPORT");
        assertEquals("EATING", LogBookType.EATING.toString(),
                "Результат вызова toString() не совпадает с ожидаемым для EATING");
    }

    /**
     * Проверяет сравнение элементов enum
     */
    @Test
    public void testEnumEquality() {
        assertEquals(LogBookType.EMOTIONAL, LogBookType.EMOTIONAL,
                "Сравнение двух элементов типа EMOTIONAL показало, что они не равны");
        assertEquals(LogBookType.SPORT, LogBookType.SPORT,
                "Сравнение двух элементов типа SPORT показало, что они не равны");
        assertEquals(LogBookType.EATING, LogBookType.EATING,
                "Сравнение двух элементов типа EATING показало, что они не равны");
    }

    /**
     * Проверяет работу метода valueOf()
     */
    @Test
    public void testValueOf() {
        assertEquals(LogBookType.EMOTIONAL, LogBookType.valueOf("EMOTIONAL"),
                "Результат вызова valueOf() не совпадает с ожидаемым для EMOTIONAL");
        assertEquals(LogBookType.SPORT, LogBookType.valueOf("SPORT"),
                "Результат вызова valueOf() не совпадает с ожидаемым для SPORT");
        assertEquals(LogBookType.EATING, LogBookType.valueOf("EATING"),
                "Результат вызова valueOf() не совпадает с ожидаемым для EATING");
    }

    /**
     * Проверяет создание Enum из недопустимой строки
     */
    @Test
    public void testValueOfWithInvalidString() {
        assertThrows(IllegalArgumentException.class, () -> LogBookType.valueOf("INVALID_TYPE"));
    }
}
