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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет, что при вызове метода контроллера отправки запроса ментором на отслеживание участника –
 * в списке уведомлений участника появляется уведомление
 */
@SpringBootTest(classes = Application.class)
public class TestAddRequestToParticipantNotificationNowMentor extends BaseTest {

    private static final String SHORT_MESSAGE = "Катька Волосова хочет стать твоим ментором";
    private static final String ALL_MESSAGE = "Ментор Катька Волосова хочет стать твоим ментором.\n" +
            "\n" +
            "Напоминание: Принимая запрос, ты соглашаешься с тем, что твой ментор будет видеть все твои записи и вопросы\n";
    private static final LocalDate DATE = LocalDate.now();
    private static final LocalTime TIME = LocalTime.now();
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
        statusId = notificationController.getNotificationStatusId(StatusOfNotification.ANSWERED_NOT_SEEN);
        typeId = notificationController.getNotificationTypeId(TypeOfNotification.ADD_REQUEST);
    }


    @Test
    public void testAddRequestToParticipantNotificationNowMentor() {
        logger.info("Тест проверяет, что при вызове метода контроллера отправки запроса ментором на отслеживание " +
                "участника – в списке уведомлений участника появляется уведомление");

        logger.info("Вызываем метод создания запроса на отслеживание участника ментором");
        when(VaadinSession.getCurrent().getAttribute("Login")).thenReturn(LOGIN_MENTOR);
        registrationAndLoginController.authenticationUser(RoleEnum.MENTOR);
        notificationController.addRequestToParticipantNotificationNowMentor(participantAndMentorController.getParticipantByLogin(LOGIN_PARTICIPANT));

        logger.info("Получаем все нотификации пользователя");
        when(VaadinSession.getCurrent().getAttribute("Login"))
                .thenReturn(LOGIN_PARTICIPANT);
        registrationAndLoginController.authenticationUser(RoleEnum.PARTICIPANT);
        List<Notifications> allNotifications = notificationController.getNotificationWithAnswerNotSeenParticipant(true, null);
        assertEquals(1, allNotifications.size(), "У пользователя нет нотификаций");

        logger.info("Проверяем, что текст нотификации содержит все нужные значения");
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
