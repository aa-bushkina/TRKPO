package TestLogBook.TestLogsTypeService;

import com.cygans.database.log_book.logs_type.LogsTypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест проверяет метод getAllLogsTypes класса LogType
 */
@ExtendWith(MockitoExtension.class)
public class TestLogTypeGetAllLogsTypes {
    @InjectMocks
    private LogsTypeService logsTypeService;

    /**
     * Тест на успешное получение всех типов логов с использованием мокирования репозитория
     */
    @Test
    public void testGetAllLogsTypesSuccessWithMockingRepository() {
        List<String> result = logsTypeService.getAllLogsTypes();
        List<String> expectedTypes = List.of("Эмоциональное состояние", "Спортивная активность", "Приём пищи");

        assertEquals(expectedTypes, result, "Неверный список типов логов");
    }
}
