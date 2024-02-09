package backTests.TestsNotifications.TetsNotificationController;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.mentor.MentorService;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentor;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import com.cygans.security.db.logInfo.LoginInfoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsGetMentorIdByParticipant {


    @Mock
    private NotificationsService notificationsService;

    @Mock
    private NotificationTypeService notificationTypeService;

    @Mock
    private NotificationStatusService notificationStatusService;

    @Mock
    private ParticipantService participantService;

    @Mock
    private LoginInfoService loginInfoService;

    @Mock
    private ParticipantMentorService participantMentorService;

    @Mock
    private MentorService mentorService;

    @InjectMocks
    private NotificationController controller;

    /**
     * Тест проверяет, что метод getMentorIdByParticipant возвращает правильный идентификатор ментора.
     */
    @Test
    public void testGetMentorIdByParticipant() throws Exception {
        Long participantId = 1L;
        Long mentorId = 2L;
        ParticipantMentor participantMentor = new ParticipantMentor();
        participantMentor.setMentorId(mentorId);
        when(participantMentorService.getMentorParticipantByParticipantId(participantId)).thenReturn(participantMentor);
        Method privateMethod = NotificationController.class.getDeclaredMethod("getMentorIdByParticipant", Long.class);
        privateMethod.setAccessible(true);
        Long actualMentorId = (Long) privateMethod.invoke(controller, participantId);
        assertEquals(mentorId, actualMentorId);
        verify(participantMentorService, times(1)).getMentorParticipantByParticipantId(participantId);
    }

}
