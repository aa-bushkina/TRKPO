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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет, что при вызове метода контроллера отклонения запроса на менторство участником не создается
 * запись о связи участника и ментора в БД
 */
@SpringBootTest(classes = Application.class)
public class TestAddDeclineToMentorNotificationNowParticipant extends BaseTest {

    private static String ALL_MESSAGE = "Участник Катька Волосова отказал в отслеживании.\n\n" +
            "Если вы считаете, что отказ ошибочен, или у вас возникли проблемы с участником, то обратитетсь в поддержку";
    private static String SHORT_MESSAGE = "Катька Волосова отказал в отслеживании";
    private static String ANSWER = "Катька Волосова принял запрос на менторство.";
    private static final LocalDate DATE = LocalDate.now();
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

        statusId = notificationController.getNotificationStatusId(StatusOfNotification.NO_ANSWER);
        typeId = notificationController.getNotificationTypeId(TypeOfNotification.DECLINE_MENTOR);
    }

    @Test
    public void testAddDeclineToMentorNotificationNowParticipant() {
        logger.info("Тест проверяет, что при вызове метода контроллера отклонения запроса на менторство " +
                "участником не создается запись о связи участника и ментора в БД");

        logger.info("Вызваем метод для отклонения запроса на отслеживание");
        when(VaadinSession.getCurrent().getAttribute("Login")).thenReturn(LOGIN_PARTICIPANT);
        registrationAndLoginController.authenticationUser(RoleEnum.PARTICIPANT);
        notificationController.addDeclineToMentorNotificationNowParticipant(mentorId);

        logger.info("Проверяем, что не создалась пара");
        Long id = participantAndMentorController.getMentorIdOfParticipant(participantAndMentorController.getParticipantByLogin(LOGIN_PARTICIPANT));
        assertNull(id, "Ментор прикрепился к участнику");

        logger.info("Проверяем, что появилась нотификация отклонения запроса");
        when(VaadinSession.getCurrent().getAttribute("Login")).thenReturn(LOGIN_MENTOR);
        registrationAndLoginController.authenticationUser(RoleEnum.MENTOR);
        List<Notifications> allNotifications = notificationController.getAllNowMentorNotifications();
        assertEquals(1, allNotifications.size(), "У ментора нет нотификаций");
        Notifications notification = allNotifications.get(0);
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


}
