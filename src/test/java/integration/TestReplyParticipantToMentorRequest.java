package integration;

import com.cygans.Application;
import com.cygans.database.controllers.NotificationController;
import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.database.controllers.SettingsController;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import com.cygans.security.db.RoleEnum;
import com.vaadin.flow.server.VaadinSession;
import integration.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет, что при вызове метода контролера принятия участником запроса ментора
 * на отслеживание создается нотификация для ментора и связь участника и ментора в БД
 */
@SpringBootTest(classes = Application.class)
public class TestReplyParticipantToMentorRequest extends BaseTest {

    private static String ANSWER = "Катька Волосова принял запрос на менторство.";
    private Long participantId;
    private Long mentorId;
    private Long statusId;
    private Long typeId;

    @Autowired
    private NotificationController notificationController;
    @Autowired
    private SettingsController settingsController;
    @Autowired
    private RegistrationAndLoginController registrationAndLoginController;
    @Autowired
    private ParticipantAndMentorController participantAndMentorController;

    @BeforeEach
    public void setUp() {
        logger.info("Создаем участника и ментора");
        registerParticipant();
        participantId = settingsController.getAuthoritiesParticipant().getId();
        registerMentor();
        mentorId = settingsController.getAuthoritiesMentor().getId();

        logger.info("Отправляем запрос на отслеживание");
        when(VaadinSession.getCurrent().getAttribute("Login")).thenReturn(LOGIN_MENTOR);
        registrationAndLoginController.authenticationUser(RoleEnum.MENTOR);
        notificationController.addRequestToParticipantNotificationNowMentor(participantAndMentorController.getParticipantByLogin(LOGIN_PARTICIPANT));

        statusId = notificationController.getNotificationStatusId(StatusOfNotification.ANSWERED_NOT_SEEN);
        typeId = notificationController.getNotificationTypeId(TypeOfNotification.ADD_REQUEST);
    }

    @Test
    public void testReplyParticipantToMentorRequest() {
        logger.info("Тест проверяет, что при вызове метода контролера принятия участником запроса ментора на " +
                "отслеживание создается нотификация для ментора и связь участника и ментора в БД");


        logger.info("Получаем участником уведомление о запросе не отслеживание");
        when(VaadinSession.getCurrent().getAttribute("Login"))
                .thenReturn(LOGIN_PARTICIPANT);
        registrationAndLoginController.authenticationUser(RoleEnum.PARTICIPANT);
        List<Notifications> allNotifications = notificationController.getNotificationWithAnswerNotSeenParticipant(true, null);
        assertEquals(1, allNotifications.size(), "У пользователя нет нотификаций");
        Notifications notification = allNotifications.get(0);

        logger.info("Вызваем метод для принятия запрос на отслеживание");
        notificationController.replyParticipantToMentorRequest(notification);

        logger.info("Проверяем, что создалась пара");
        Long id = participantAndMentorController.getMentorIdOfParticipant(participantAndMentorController.getParticipantByLogin(LOGIN_PARTICIPANT));
        assertEquals(mentorId, id, "Ментор не прикрепился к участнику");

        logger.info("Проверяем, что у нотификации появился ответ и изменился статус c типом");
        allNotifications = notificationController.getNotificationWithAnswerNotSeenParticipant(true, null);
        assertEquals(1, allNotifications.size(), "У пользователя нет нотификаций");
        Notifications finalNotification = allNotifications.get(0);
        assertAll(
                () -> assertEquals(ANSWER, finalNotification.getReplyMessage(), "У нотификации неверный ответ"),
                () -> assertEquals(statusId, finalNotification.getNotificationStatusId(), "У нотификации неверный statusId"),
                () -> assertEquals(typeId, finalNotification.getNotificationTypeId(), "У нотификации неверный typeId")

        );
        logger.info("Тест успешно пройден");
    }

}
