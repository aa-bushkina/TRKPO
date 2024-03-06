package module.backTests.controllers.TestsNotificationController;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorService;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsAddAnswerToParticipantLogNotification {

    @Mock
    private NotificationsService notificationsService;

    @Mock
    private NotificationTypeService notificationTypeService;

    @Mock
    private NotificationStatusService notificationStatusService;

    @Mock
    private ParticipantService participantService;

    @Mock
    private MentorService mentorService;

    @InjectMocks
    private NotificationController controller;

    /**
     * Тест проверяет, что метод addAnswerToParticipantLogNotification правильно создает и сохраняет уведомление.
     */
    @Test
    public void testAddAnswerToParticipantLogNotification() {
        Long participantId = 1L;
        Long mentorId = 2L;
        Long logBookId = 3L;
        String replyMsg = "Ответ на запись";
        Notifications notifications = new Notifications();
        notifications.setParticipantId(participantId);
        notifications.setMentorId(mentorId);
        notifications.setLogBookId(logBookId);
        Participant participant = new Participant();
        participant.setId(participantId);
        participant.setFirstName("Петр");
        participant.setLastName("Петров");
        Mentor mentor = new Mentor();
        mentor.setId(mentorId);
        mentor.setFirstName("Иван");
        mentor.setLastName("Иванов");
        when(participantService.getParticipantById(participantId)).thenReturn(participant);
        when(mentorService.getMentorById(mentorId)).thenReturn(mentor);
        when(notificationTypeService.getNotificationTypeId(TypeOfNotification.ANSWER_ON_LOG)).thenReturn(4L);
        when(notificationStatusService.getNotificationStatusId(StatusOfNotification.ANSWERED_NOT_SEEN)).thenReturn(5L);
        controller.addAnswerToParticipantLogNotification(notifications, replyMsg);
        verify(notificationsService, times(1)).saveNotification(any(Notifications.class));
        verify(notificationsService, times(1)).updateNotificationLogId(notifications.getNotificationId(), null);
    }

}
