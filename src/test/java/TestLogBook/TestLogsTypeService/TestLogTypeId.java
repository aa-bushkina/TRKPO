package TestLogBook.TestLogsTypeService;

import com.cygans.database.log_book.logs_type.LogBookType;
import com.cygans.database.log_book.logs_type.LogsType;
import com.cygans.database.log_book.logs_type.LogsTypeRepository;
import com.cygans.database.log_book.logs_type.LogsTypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет метод logTypeId класса LogType
 */
@ExtendWith(MockitoExtension.class)
public class TestLogTypeId {

    @Mock
    private LogsTypeRepository logsTypeRepository;

    @InjectMocks
    private LogsTypeService logsTypeService;

    /**
     * Тест на успешное получение ID для существующего типа логов
     */
    @Test
    public void testGetLogTypeIdSuccess() {
        String logType = LogBookType.EMOTIONAL.getText();
        LogsType logsType = new LogsType(logType);
        when(logsTypeRepository.findLogsTypeByType(logType)).thenReturn(logsType);

        Long result = logsTypeService.getLogTypeId(logType);

        assertEquals(logsType.getId(), result, "Неверный ID для типа логов");
        verify(logsTypeRepository, times(1)).findLogsTypeByType(logType);
    }

    /**
     * Тест на обработку случая, когда тип логов не найден
     */
    @Test
    public void testGetLogTypeIdTypeNotFound() {
        String logType = "INVALID_TYPE";
        when(logsTypeRepository.findLogsTypeByType(logType)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> logsTypeService.getLogTypeId(logType));
        verify(logsTypeRepository, times(1)).findLogsTypeByType(logType);
    }
}
