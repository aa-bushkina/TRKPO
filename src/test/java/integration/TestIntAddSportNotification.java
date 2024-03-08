package integration;

import com.cygans.Application;
import com.cygans.database.controllers.NotificationController;
import com.cygans.database.notifications.Notifications;
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
 * записи о спорте участником нотификация может быть получена в списке всех нотификаций
 * прикрепленным к участнику ментором, и все поля совпадают с установленными
 */
@SpringBootTest(classes = Application.class)
public class TestIntAddSportNotification extends BaseTest {
    private static final Long PARTICIPANT_ID = 1L;
    private static final Long MENTOR_ID = 2L;
    private static final String INTENSITY = "Низкая";
    private static final String DURATION = "25";
    private static final String ACTIVITY = "Утренняя зарядка";
    private static final String COMMENTS = "Простые упражнения";

    @Autowired
    private NotificationController notificationController;

    @BeforeEach
    public void setUp() {
        logger.info("Мокируем связь участника и ментора");
        //TODO
    }

    @Test
    public void testIntAddSportNotification() {
        logger.info("Тест проверяет, что после вызова метода контроллера создания нотификации о добавлении " +
                "записи о спорте участником нотификация может быть получена в списке всех нотификаций " +
                "прикрепленным к участнику ментором, и все поля совпадают с установленными");

        logger.info("Вызываем метод сохранения нотификации о спорте");
        notificationController.addNewSportLogNotification(1L, INTENSITY, DURATION, ACTIVITY, COMMENTS);

        logger.info("Получаем все нотификации ментора и проверяем, что среди них есть добавленная нотификация");
        List<Notifications> allNotifications = notificationController.getAllNowMentorNotifications();
        assertEquals(1, allNotifications.size(), "У пользователя нет нотификаций");

        logger.info("Проверяем, что текст нотификации содержит все переданные значения");
        Notifications notification = allNotifications.get(0);
        assertAll(
                () -> assertTrue(notification.getAllMessage().contains(INTENSITY),
                        "В нотификации нет значения intensity"),
                () -> assertTrue(notification.getAllMessage().contains(DURATION),
                        "В нотификации нет значения duration"),
                () -> assertTrue(notification.getAllMessage().contains(ACTIVITY),
                        "В нотификации нет значения activity"),
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

    @AfterEach
    public void tearDown() {
        logger.info("Удаляем из БД добавленные записи");
        //TODO
    }
}
