package module.backTests.controllers.TestsLogController;

import com.cygans.database.controllers.LogController;
import com.cygans.database.emotional_log_book.EmotionalLogBook;
import com.cygans.database.emotional_log_book.EmotionalLogBookService;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestUpdateEmotionalLog {

    @Mock
    private EmotionalLogBookService emotionalLogBookService;

    @Mock
    private NotificationsService notificationsService;

    @InjectMocks
    private LogController logController;

    @BeforeEach
    void setUp() {
        Notifications notification = new Notifications();
        notification.setAllMessage("Содержание: Previous message");
        when(notificationsService.getNotificationByLogBookId(0L)).thenReturn(notification);
    }

    @Test
    void testUpdateEmotionalLogWithNonNullDescription() {
        String emotionalDescText = "This is an emotional description.";
        Long logBookId = 0L;

        when(emotionalLogBookService.findByLogBookId(logBookId)).thenReturn(new EmotionalLogBook());

        logController.updateEmotionalLog(logBookId, emotionalDescText);

        verify(emotionalLogBookService, times(1)).updateEmotionalDescription(0L, emotionalDescText);
        verify(notificationsService, times(1)).updateNotificationAllMessage(anyLong(), anyString());
    }

    @Test
    void testUpdateEmotionalLogWithNullDescription() {
        String emotionalDescText = null;
        Long logBookId = 0L;

        when(emotionalLogBookService.findByLogBookId(logBookId)).thenReturn(new EmotionalLogBook());

        logController.updateEmotionalLog(logBookId, emotionalDescText);

        verify(emotionalLogBookService, never()).updateEmotionalDescription(anyLong(), anyString());
        verify(notificationsService, times(1)).updateNotificationAllMessage(anyLong(), anyString());
    }
}
