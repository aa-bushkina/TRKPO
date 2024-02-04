package LogBook.LogsType;

import com.cygans.database.log_book.logs_type.LogBookType;
import com.cygans.database.log_book.logs_type.LogsType;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тесты для конструктора класса LogsType
 */
public class TestLogsTypeConstructor {
    /**
     * Проверяет конструктор без параметров
     */
    @Test
    public void testDefaultConstructor() {
        LogsType logsType = new LogsType();

        assertNotNull(logsType, "Объект не должен быть null");
        assertNull(logsType.getId(), "Id должен быть null");
        assertNull(logsType.getType(), "Тип должен быть null");
    }

    /**
     * Проверяет вызов конструктора с существующим типом
     */
    @Test
    public void testConstructorWithValidType() {
        String validType = LogBookType.EMOTIONAL.getText();

        LogsType logsType = new LogsType(validType);

        assertNotNull(logsType, "Объект не должен быть null");
        assertNull(logsType.getId(), "Id должен быть null");
        assertNotNull(logsType.getType(), "Тип не должен быть null");
    }

    /**
     * Проверяет вызов конструктора с типом null
     */
    @Test
    public void testConstructorWithNullType() {
        assertThrows(IllegalArgumentException.class, () -> new LogsType(null),
                "Ожидалось исключение IllegalArgumentException при передаче недопустимого типа");
    }

    /**
     * Проверяет вызов конструктора с пустым типом
     */
    @Test
    public void testConstructorWithEmptyType() {
        assertThrows(IllegalArgumentException.class, () -> new LogsType(""),
                "Ожидалось исключение IllegalArgumentException при передаче недопустимого типа");
    }
}
