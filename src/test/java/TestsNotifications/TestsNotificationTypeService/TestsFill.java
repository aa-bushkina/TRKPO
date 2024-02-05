package TestsNotifications.TestsNotificationTypeService;

import com.cygans.database.notifications.notification_type.NotificationTypeRepository;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestsFill {

    @Mock
    private NotificationTypeRepository repository;

    @InjectMocks
    private NotificationTypeService service;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /**
     * Тест проверяет, что метод fill вызывает save в репозитории семь раз,
     * когда в репозитории нет записей.
     */
    @Test
    public void testFillWhenRepositoryIsEmpty() {
        when(repository.count()).thenReturn(0L);
        service.fill();
        verify(repository, times(7)).save(any());
    }

    /**
     * Тест проверяет, что метод fill выводит сообщение в консоль,
     * когда в репозитории больше семи записей.
     */
    @Test
    public void testFillWhenRepositoryHasMoreThanSevenRecords() {
        when(repository.count()).thenReturn(8L);
        service.fill();
        assertTrue(outContent.toString().contains("Что-то идет не так, почистите таблицу notification_type!!! В ней должно быть только 7 заранее добавленные записи!!!"), "В консоли неверное сообщение");
    }

    /**
     * Тест проверяет, что метод fill не вызывает save,
     * когда в репозитории уже семь записей.
     */
    @Test
    public void testFillWhenRepositoryHasSevenRecords() {
        when(repository.count()).thenReturn(7L);
        service.fill();
        verify(repository, never()).save(any());
        assertEquals("", outContent.toString(), "В консоли что-то вывелось");
    }

}
