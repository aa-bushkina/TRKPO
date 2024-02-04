package TestsIntensityType;

import com.cygans.database.sport_log_book.intensity.IntensityType;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestsIntensityType {

    /**
     * Проверяет, что каждый тип IntensityType имеет текст
     */
    @Test
    public void testIntensityTypeHasText() {
        for (IntensityType IntensityType : IntensityType.values()) {
            assertNotNull(IntensityType.getText(), "Текст для " + IntensityType + " не должен быть null");
        }
    }

    /**
     * Проверяет, что getText() возвращает ожидаемый текст для каждого типа IntensityType
     */
    @Test
    public void testGetText() {
        assertEquals("Высокая", IntensityType.HIGH.getText(),
                "Результат вызова getText() не совпадает с ожидаемым для HIGH");
        assertEquals("Низкая", IntensityType.LOWER.getText(),
                "Результат вызова getText() не совпадает с ожидаемым для LOWER");
        assertEquals("Средняя", IntensityType.MIDDLE.getText(),
                "Результат вызова getText() не совпадает с ожидаемым для MIDDLE");
    }

    /**
     * Проверяет, что значения IntensityType не являются null
     */
    @Test
    public void testEnumValuesNotNull() {
        for (IntensityType IntensityType : IntensityType.values()) {
            assertNotNull(IntensityType, "Значение Enum " + IntensityType + " не должно быть null");
        }
    }

    /**
     * Проверяет, что toString() возвращает ожидаемые значения
     */
    @Test
    public void testToString() {
        assertEquals("HIGH", IntensityType.HIGH.toString(),
                "Результат вызова toString() не совпадает с ожидаемым для HIGH");
        assertEquals("LOWER", IntensityType.LOWER.toString(),
                "Результат вызова toString() не совпадает с ожидаемым для LOWER");
        assertEquals("MIDDLE", IntensityType.MIDDLE.toString(),
                "Результат вызова toString() не совпадает с ожидаемым для MIDDLE");
    }

    /**
     * Проверяет сравнение элементов enum
     */
    @Test
    public void testEnumEquality() {
        assertEquals(IntensityType.MIDDLE, IntensityType.MIDDLE,
                "Сравнение двух элементов MIDDLE показало, что они не равны");
        assertEquals(IntensityType.LOWER, IntensityType.LOWER,
                "Сравнение двух элементов LOWER показало, что они не равны");
        assertEquals(IntensityType.HIGH, IntensityType.HIGH,
                "Сравнение двух элементов типа HIGH показало, что они не равны");
    }

    /**
     * Проверяет работу метода valueOf()
     */
    @Test
    public void testValueOf() {
        assertEquals(IntensityType.HIGH, IntensityType.valueOf("HIGH"),
                "Результат вызова valueOf() не совпадает с ожидаемым для HIGH");
        assertEquals(IntensityType.LOWER, IntensityType.valueOf("LOWER"),
                "Результат вызова valueOf() не совпадает с ожидаемым для LOWER");
        assertEquals(IntensityType.MIDDLE, IntensityType.valueOf("MIDDLE"),
                "Результат вызова valueOf() не совпадает с ожидаемым для MIDDLE");
    }

    /**
     * Проверяет создание Enum из недопустимой строки
     */
    @Test
    public void testValueOfWithInvalidString() {
        assertThrows(IllegalArgumentException.class, () -> IntensityType.valueOf("INVALID_TYPE"));
    }

}
