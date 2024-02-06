package backTests.TestsLog.TestLogsTypeService;

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
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет метод getLogTypeById класса LogType
 */
@ExtendWith(MockitoExtension.class)
public class TestLogTypeGetLogTypeById {

    @Mock
    private LogsTypeRepository logsTypeRepository;

    @InjectMocks
    private LogsTypeService logsTypeService;

    /**
     * Тест на успешное получение типа логов по ID
     */
    @Test
    public void testGetLogTypeByIdSuccess() {
        Long id = 1L;
        LogsType logsType = new LogsType(LogBookType.EMOTIONAL.getText());
        when(logsTypeRepository.findLogsTypeById(id)).thenReturn(logsType);

        String result = logsTypeService.getLogTypeById(id);

        assertEquals(logsType.getType(), result, "Неверный тип логов по ID");
        verify(logsTypeRepository, times(1)).findLogsTypeById(id);
    }

    /**
     * Тест на успешное получение типа логов по ID с использованием ArgumentMatchers
     */
    @Test
    public void testGetLogTypeByIdSuccessWithMatchers() {
        Long id = 3L;
        LogsType logsType = new LogsType(LogBookType.SPORT.getText());
        when(logsTypeRepository.findLogsTypeById(anyLong())).thenReturn(logsType);

        String result = logsTypeService.getLogTypeById(id);

        assertEquals(logsType.getType(), result, "Неверный тип логов по ID с использованием ArgumentMatchers");
        verify(logsTypeRepository, times(1)).findLogsTypeById(id);
    }
}
