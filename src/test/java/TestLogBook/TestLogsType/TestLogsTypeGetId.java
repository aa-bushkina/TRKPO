package TestLogBook.TestLogsType;

import com.cygans.database.log_book.logs_type.LogsType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тест метода getId() в LogsType
 */
public class TestLogsTypeGetId {

    /**
     * Проверяет вызов метода для объекта с id = null
     */
    @Test
    public void testGetIdNull() {
        LogsType logsType = new LogsType();

        assertNull(logsType.getId(), "Id должен быть null");
    }
}
