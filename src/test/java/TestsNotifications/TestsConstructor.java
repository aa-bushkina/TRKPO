package TestsNotifications;

import com.cygans.database.notifications.Notifications;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TestsConstructor {

    private static final Long PARTICIPANT_ID = 1L;
    private static final Long MENTOR_ID = 2L;
    private static final Long NOTIFICATION_TYPE_ID = 3L;
    private static final Long NOTIFICATION_STATUS_ID = 4L;

    /**
     * Проверяет работу конструктора с параметрами
     */
    @Test
    public void testConstructorWithParameters() {
        Notifications notifications = new Notifications(PARTICIPANT_ID, MENTOR_ID, NOTIFICATION_TYPE_ID, NOTIFICATION_STATUS_ID);
        assertAll(
                () -> assertEquals(PARTICIPANT_ID, notifications.getParticipantId(), "ParticipantId не совпадает"),
                () -> assertEquals(MENTOR_ID, notifications.getMentorId(), "MentorId не совпадает"),
                () -> assertEquals(NOTIFICATION_TYPE_ID, notifications.getNotificationTypeId(), "NotificationTypeId не совпадает"),
                () -> assertEquals(NOTIFICATION_STATUS_ID, notifications.getNotificationStatusId(), "NotificationStatusId не совпадает"),
                () -> assertTrue(LocalDateTime.now().toString().contains(notifications.getDate().toString()), "Date не совпадает"),
                () -> assertNull(notifications.getReplyMessage(), "ReplyMessage не пустой"),
                () -> assertEquals(0, notifications.getNotificationId(), "NotificationId неверный"),
                () -> assertNull(notifications.getAllMessage(), "ФддMessage не пустой"),
                () -> assertNull(notifications.getLogBookId(), "LogBookId не пустой"),
                () -> assertNull(notifications.getQuestionId(), "QuestionId не пустой"),
                () -> assertEquals(LocalDate.now(), notifications.getDateNoTime(), "DateNoTime неверный")
        );
    }

    /**
     * Проверяет работу конструктора без параметров
     */
    @Test
    public void testConstructorWithoutParameters() {
        Notifications notifications = new Notifications();
        assertAll(
                () -> assertNull(notifications.getParticipantId(), "ParticipantId не пустой"),
                () -> assertNull(notifications.getMentorId(), "MentorId не пустой"),
                () -> assertNull(notifications.getNotificationTypeId(), "NotificationTypeId не пустой"),
                () -> assertNull(notifications.getNotificationStatusId(), "NotificationStatusId не пустой"),
                () -> assertNull(notifications.getDate(), "Date не пустой"),
                () -> assertNull(notifications.getReplyMessage(), "ReplyMessage не пустой"),
                () -> assertEquals(0, notifications.getNotificationId(), "NotificationId не совпадает"),
                () -> assertNull(notifications.getAllMessage(), "AllMessage не пустой"),
                () -> assertNull(notifications.getLogBookId(), "LogBookId не пустой"),
                () -> assertNull(notifications.getQuestionId(), "QuestionId не пустой"),
                () -> assertThrows(NullPointerException.class, notifications::getDateNoTime, "Неверный тип ошиби при вызове getDateNoTime")
        );
    }

    /**
     * Проверяет работу конструктора с параметрами c participantId null
     */
    @Test
    public void testConstructorWithParticipantIdNullValues() {
        assertThrows(IllegalArgumentException.class, () -> new Notifications(null, MENTOR_ID, NOTIFICATION_TYPE_ID, NOTIFICATION_STATUS_ID),
                "Не получили ожидаеме исключение при вызове метода с participantId null");
    }

    /**
     * Проверяет работу конструктора с параметрами c mentorId null
     */
    @Test
    public void testConstructorWithMentorIdNullValues() {
        assertThrows(IllegalArgumentException.class, () -> new Notifications(PARTICIPANT_ID, null, NOTIFICATION_TYPE_ID, NOTIFICATION_STATUS_ID),
                "Не получили ожидаеме исключение при вызове метода с mentorId null");
    }

    /**
     * Проверяет работу конструктора с параметрами c notificationTypeId null
     */
    @Test
    public void testConstructorWithNotificationTypeIdNullValues() {
        assertThrows(IllegalArgumentException.class, () -> new Notifications(PARTICIPANT_ID, MENTOR_ID, null, NOTIFICATION_STATUS_ID),
                "Не получили ожидаеме исключение при вызове метода с notificationTypeId null");
    }

    /**
     * Проверяет работу конструктора с параметрами c notificationStatusId null
     */
    @Test
    public void testConstructorWithNotificationStatusIdNullValues() {
        assertThrows(IllegalArgumentException.class, () -> new Notifications(PARTICIPANT_ID, MENTOR_ID, NOTIFICATION_TYPE_ID, null),
                "Не получили ожидаеме исключение при вызове метода с notificationStatusId null");
    }

}
