package TestsSportLogBook.TestsIntensity;

import com.cygans.database.sport_log_book.intensity.Intensity;
import com.cygans.database.sport_log_book.intensity.IntensityType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestsConstructor {

    /**
     * Проверяет работу конструктора с параметрами
     */
    @Test
    public void testConstructorWithParameters() {
        Intensity intensity = new Intensity(IntensityType.HIGH.getText());
        assertAll(
                () -> assertNull(intensity.getId(), "ID не null"),
                () -> assertEquals(IntensityType.HIGH.getText(), intensity.getType(), "Type не совпадает")
        );
    }

    /**
     * Проверяет конструктор без параметров
     */
    @Test
    public void testDefaultConstructor() {
        Intensity intensity = new Intensity();
        assertAll(
                () -> assertNull(intensity.getId(), "ID не null"),
                () -> assertNull(intensity.getType(), "Type не null")
        );
    }

    /**
     * Проверяет вызов конструктора с типом null
     */
    @Test
    public void testConstructorWithNullType() {
        assertThrows(IllegalArgumentException.class, () -> new Intensity(null),
                "Ожидалось исключение IllegalArgumentException при передаче недопустимого типа");
    }

    /**
     * Проверяет вызов конструктора с пустым типом
     */
    @Test
    public void testConstructorWithEmptyType() {
        assertThrows(IllegalArgumentException.class, () -> new Intensity(""),
                "Ожидалось исключение IllegalArgumentException при передаче недопустимого типа");
    }

}
