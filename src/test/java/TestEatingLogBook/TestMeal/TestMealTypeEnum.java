package TestEatingLogBook.TestMeal;


import com.cygans.database.eating_log_book.meal.MealType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тесты для MealType enum
 */
public class TestMealTypeEnum {

    /**
     * Проверяет, что каждый тип MealType имеет текст
     */
    @Test
    public void testMealTypeHasText() {
        for (MealType mealType : MealType.values()) {
            assertNotNull(mealType.getText(), "Текст для " + mealType + " не должен быть null");
        }
    }

    /**
     * Проверяет, что getText() возвращает ожидаемый текст для каждого типа MealType
     */
    @Test
    public void testGetText() {
        assertEquals("Завтрак", MealType.BREAKFAST.getText(),
                "Результат вызова getText() не совпадает с ожидаемым для BREAKFAST");
        assertEquals("Обед", MealType.LAUNCH.getText(),
                "Результат вызова getText() не совпадает с ожидаемым для LAUNCH");
        assertEquals("Ужин", MealType.DINNER.getText(),
                "Результат вызова getText() не совпадает с ожидаемым для DINNER");
        assertEquals("Другое", MealType.OTHER.getText(),
                "Результат вызова getText() не совпадает с ожидаемым для OTHER");
    }

    /**
     * Проверяет, что значения MealType не являются null
     */
    @Test
    public void testEnumValuesNotNull() {
        for (MealType mealType : MealType.values()) {
            assertNotNull(mealType, "Значение Enum " + mealType + " не должно быть null");
        }
    }

    /**
     * Проверяет работу метода valueOf()
     */
    @Test
    public void testValueOf() {
        assertEquals(MealType.BREAKFAST, MealType.valueOf("BREAKFAST"),
                "Результат вызова valueOf() не совпадает с ожидаемым для BREAKFAST");
        assertEquals(MealType.LAUNCH, MealType.valueOf("LAUNCH"),
                "Результат вызова valueOf() не совпадает с ожидаемым для LAUNCH");
        assertEquals(MealType.DINNER, MealType.valueOf("DINNER"),
                "Результат вызова valueOf() не совпадает с ожидаемым для DINNER");
        assertEquals(MealType.OTHER, MealType.valueOf("OTHER"),
                "Результат вызова valueOf() не совпадает с ожидаемым для OTHER");
    }

    /**
     * Проверяет создание Enum из недопустимой строки
     */
    @Test
    public void testValueOfWithInvalidString() {
        assertThrows(IllegalArgumentException.class, () -> MealType.valueOf("INVALID_TYPE"));
    }
}
