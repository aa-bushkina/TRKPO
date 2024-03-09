package integration;

import com.cygans.Application;
import com.cygans.database.controllers.NotificationController;
import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.database.controllers.SettingsController;
import com.cygans.database.notifications.Notifications;
import com.cygans.security.db.RoleEnum;
import com.vaadin.flow.server.VaadinSession;
import integration.base.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет, что после вызова метода контроллера создания нотификации о добавлении
 * записи о приеме пищи участником нотификация может быть получена в списке всех нотификаций
 * прикрепленным к участнику ментором, и все поля совпадают с установленными
 */
@SpringBootTest(classes = Application.class)
public class TestIntAddEatingNotification extends BaseTest {
    private static final LocalTime TIME = LocalTime.of(10, 0);
    private static final String DESCRIPTION = "Мне было очень грустно и я съела весь Буше";
    private static final String MEAL_TYPE = "Завтрак";
    private static final LocalDate DATE = LocalDate.now();
    private Long participantId;
    private Long mentorId;
    private Long notificationId;

    @BeforeEach
    public void setUp() {
        logger.info("Создаем участника, ментора и связываем их");
        registerParticipant();
        participantId = settingsController.getAuthoritiesParticipant().getId();
        registerMentor();
        mentorId = settingsController.getAuthoritiesMentor().getId();
        linkParticipantMentor(participantId, mentorId);
    }

    @Test
    public void testIntAddEatingNotification() {
        logger.info("Тест проверяет, что после вызова метода контроллера создания нотификации о добавлении " +
                "записи о приеме пищи участником нотификация может быть получена в списке всех нотификаций " +
                "прикрепленным к участнику ментором, и все поля совпадают с установленными");

        logger.info("Вызываем метод сохранения нотификации о приеме пищи");
        loginParticipant();
        notificationController.addNewEatingLogNotification(1L, TIME, DESCRIPTION, MEAL_TYPE);

        logger.info("Получаем все нотификации ментора и проверяем, что среди них есть добавленная нотификация");
        loginMentor();
        List<Notifications> allNotifications = notificationController.getAllNowMentorNotifications();
        assertEquals(1, allNotifications.size(), "У пользователя нет нотификаций");

        logger.info("Проверяем, что текст нотификации содержит все переданные значения");
        Notifications notification = allNotifications.get(0);
        notificationId = notification.getNotificationId();
        assertAll(
                () -> assertTrue(notification.getAllMessage().contains(TIME.toString()),
                        "В нотификации нет значения time"),
                () -> assertTrue(notification.getAllMessage().contains(DESCRIPTION),
                        "В нотификации нет значения description"),
                () -> assertTrue(notification.getAllMessage().contains(MEAL_TYPE),
                        "В нотификации нет значения meal_type"),
                () -> assertEquals(DATE.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), notification.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        "Не совпадает значение date"),
                () -> assertEquals(participantId, notification.getParticipantId(),
                        "Не совпадает значение participantId"),
                () -> assertEquals(mentorId, notification.getMentorId(),
                        "Не совпадает значение mentorId"),
                () -> assertNull(notification.getReplyMessage(),
                        "Поле replyMessage не пустое")
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
