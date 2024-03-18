package module_tests.backTests.TestsLog.TestLogsType;

import com.cygans.database.log_book.logs_type.LogBookType;
import com.cygans.database.log_book.logs_type.LogsType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тест метода getType() в LogsType
 */
public class TestLogsTypeGetType {
    /**
     * Проверяет вызов метода для объекта с type = null
     */
    @Test
    public void testGetTypeNull() {
        LogsType logsType = new LogsType();

        assertNull(logsType.getType(), "Тип должен быть null");
    }

    /**
     * Проверяет вызов метода для объекта с существующим type
     */
    @Test
    public void testGetTypeNotNull() {
        String validType = LogBookType.EMOTIONAL.getText();
        LogsType logsType = new LogsType(validType);

        assertEquals(validType, logsType.getType(), "Тип не соответствует ожидаемому значению");
    }
}
