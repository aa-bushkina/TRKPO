package backTests.controllers.TestsLogController;


import com.cygans.database.controllers.LogController;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestGetAnswerForLog {

    @Mock
    private NotificationsService notificationsService;

    @InjectMocks
    private LogController logController;

    @BeforeEach
    void setUp() {
        Notifications notification = new Notifications();
        notification.setReplyMessage("This is a reply message.");

        when(notificationsService.getNotificationByLogBookId(1L)).thenReturn(notification);
    }

    @Test
    void testGetAnswerForLog() {
        String expectedReply = "This is a reply message.";

        String result = logController.getAnswerForLog(1L);

        assertEquals(expectedReply, result);
    }
}
