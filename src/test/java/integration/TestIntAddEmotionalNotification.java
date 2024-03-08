package integration;

import com.cygans.Application;
import com.cygans.database.controllers.NotificationController;
import com.cygans.database.notifications.Notifications;
import integration.base.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест проверяет, что после вызова метода контроллера создания нотификации о добавлении
 * записи об эмоциональном состоянии участником нотификация может быть получена в списке всех нотификаций
 * прикрепленным к участнику ментором, и все поля совпадают с установленными
 */
@SpringBootTest(classes = Application.class)
public class TestIntAddEmotionalNotification extends BaseTest {
    private static final Long PARTICIPANT_ID = 1L;
    private static final Long MENTOR_ID = 2L;
    private static final String COMMENTS = "Я плакала сильно, поэтому хотела есть, только чикенбургерами спасаюсь";

    @Autowired
    private NotificationController notificationController;

    @BeforeEach
    public void setUp() {
        logger.info("Мокируем связь участника и ментора");
        //TODO
    }

    @Test
    public void testIntAddSportLog() {
        logger.info("Тест проверяет, что после вызова метода контроллера создания нотификации о добавлении " +
                "записи об эмоциональном состоянии участником нотификация может быть получена в списке всех нотификаций " +
                "прикрепленным к участнику ментором, и все поля совпадают с установленными");

        logger.info("Вызываем метод сохранения нотификации об эмоциональном состоянии");
        notificationController.addNewEmotionalLogNotification(1L, COMMENTS);

        logger.info("Получаем все нотификации ментора и проверяем, что среди них есть добавленная нотификация");
        List<Notifications> allNotifications = notificationController.getAllNowMentorNotifications();
        assertEquals(1, allNotifications.size(), "У пользователя нет нотификаций");

        logger.info("Проверяем, что текст нотификации содержит все переданные значения");
        Notifications notification = allNotifications.get(0);
        assertAll(
                () -> assertTrue(notification.getAllMessage().contains(COMMENTS),
                        "В нотификации нет значения comments"),
                () -> assertEquals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        notification.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        "Не совпадает значение date"),
                () -> assertEquals(PARTICIPANT_ID, notification.getParticipantId(),
                        "Не совпадает значение participantId"),
                () -> assertEquals(MENTOR_ID, notification.getMentorId(),
                        "Не совпадает значение mentorId"),
                () -> assertTrue(notification.getReplyMessage().isEmpty(),
                        "Поле replyMessage не пустое")
        );

        logger.info("Тест успешно пройден");
    }
}
