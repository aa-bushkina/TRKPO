package module_tests.backTests.TestsNotifications.TestsNotificationStatusService;

import com.cygans.database.notifications.notification_status.NotificationStatusRepository;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsFill {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @Mock
    private NotificationStatusRepository repository;
    @InjectMocks
    private NotificationStatusService service;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /**
     * Тест проверяет, что метод fill вызывает save в репозитории три раза,
     * когда в репозитории нет записей.
     */
    @Test
    public void testFillWhenRepositoryIsEmpty() {
        when(repository.count()).thenReturn(0L);
        service.fill();
        verify(repository, times(3)).save(any());
    }

    /**
     * Тест проверяет, что метод fill выводит сообщение в консоль,
     * когда в репозитории больше трех записей.
     */
    @Test
    public void testFillWhenRepositoryHasMoreThanThreeRecords() {
        when(repository.count()).thenReturn(4L);
        service.fill();
        assertTrue(outContent.toString().contains("Что-то идет не так, почистите таблицу notification_status!!! В ней должно быть только 3 заранее добавленные записи!!!"), "Неверное сообщение в консоли");
    }

    /**
     * Тест проверяет, что метод fill не вызывает save,
     * когда в репозитории уже три записи.
     */
    @Test
    public void testFillWhenRepositoryHasThreeRecords() {
        when(repository.count()).thenReturn(3L);
        service.fill();
        verify(repository, never()).save(any());
        assertEquals("", outContent.toString(), "В консоль что-то вывелось");
    }

}
