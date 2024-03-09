package integration;

import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import integration.base.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест проверяет, что при удалении ментором участника из отслеживания у участника создается нотификация
 */
public class TestAddDeleteParticipantNotificationNowMentor extends BaseTest {

    private static final String SHORT_MESSAGE = "Катька Волосова удалил тебя из отслеживания";
    private static final String ALL_MESSAGE = "Ментор Катька Волосова удалил тебя из отслеживания.\n" +
            "\n" +
            "Если тебе неизвестны причины такого решения - обратись в поддержку, чтобы они объяснили причину и помогли с подбором нового ментора.\n";
    private static final LocalDate DATE = LocalDate.now();
    private Long participantId;
    private Long mentorId;
    private Long statusId;
    private Long typeId;
    private Long notificationId;

    @BeforeEach
    public void setUp() {
        logger.info("Создаем участника, ментора и связываем их");
        registerParticipant();
        participantId = settingsController.getAuthoritiesParticipant().getId();
        registerMentor();
        mentorId = settingsController.getAuthoritiesMentor().getId();
        linkParticipantMentor(participantId, mentorId);
        statusId = notificationController.getNotificationStatusId(StatusOfNotification.ANSWERED_NOT_SEEN);
        typeId = notificationController.getNotificationTypeId(TypeOfNotification.DELETE_REQUEST);
    }

    @Test
    public void testAddDeleteParticipantNotificationNowMentor() {
        logger.info("Тест проверяет, что при удалении ментором участника из отслеживания у участника создается нотификация");

        logger.info("Удаляем участника ментором");
        loginMentor();
        participantAndMentorController.deleteParticipantFromMentor(participantAndMentorController.getParticipantByLogin(LOGIN_PARTICIPANT));
        notificationController.addDeleteParticipantNotificationNowMentor(participantAndMentorController.getParticipantByLogin(LOGIN_PARTICIPANT));

        logger.info("Проверяем уведомления у участника");
        loginParticipant();
        List<Notifications> allNotifications = notificationController.getNotificationWithAnswerNotSeenParticipant(true, null);
        assertEquals(1, allNotifications.size(), "У участника нет нотификаций");
        Notifications notification = allNotifications.get(0);
        notificationId = notification.getNotificationId();
        assertAll(
                () -> assertTrue(notification.getAllMessage().contains(ALL_MESSAGE), "Уведомление не содержит нужного основного текста"),
                () -> assertEquals(DATE.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), notification.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), "Не совпадает значение date"),
                () -> assertEquals(participantId, notification.getParticipantId(), "Не совпадает значение participantId"),
                () -> assertEquals(mentorId, notification.getMentorId(), "Не совпадает значение mentorId"),
                () -> assertEquals(SHORT_MESSAGE, notification.getShortMessage(), "Не совпадает значение shortMessage"),
                () -> assertEquals(statusId, notification.getNotificationStatusId(), "Не совпадает значение statusId"),
                () -> assertEquals(typeId, notification.getNotificationTypeId(), "Не совпадает значение typeId"),
                () -> assertNull(notification.getReplyMessage(), "Поле replyMessage не пустое"),
                () -> assertNull(notification.getLogBookId(), "Поле logBookId не пустое"),
                () -> assertNull(notification.getQuestionId(), "Поле questionId не пустое")
        );
        logger.info("Тест успешно пройден");
    }

    @AfterEach
    public void clear() {
        logger.info("Удаляем нотификацию");
        if (notificationsRepository.getNotificationById(notificationId) != null) {
            notificationsRepository.delete(notificationsRepository.getNotificationById(notificationId));
        }
    }

}
