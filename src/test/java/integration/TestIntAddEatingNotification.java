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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест проверяет, что после вызова метода контроллера создания нотификации о добавлении
 * записи о приеме пищи участником нотификация может быть получена в списке всех нотификаций
 * прикрепленным к участнику ментором, и все поля совпадают с установленными
 */
@SpringBootTest(classes = Application.class)
public class TestIntAddEatingNotification extends BaseTest {
    private static final Long PARTICIPANT_ID = 1L;
    private static final Long MENTOR_ID = 2L;
    private static final LocalTime TIME = LocalTime.of(10, 00);
    private static final String DESCRIPTION = "Мне было очень грустно и я съела весь Буше";
    private static final String MEAL_TYPE = "Завтрак";

    @Autowired
    private NotificationController notificationController;

    @BeforeEach
    public void setUp() {
        logger.info("Мокируем связь участника и ментора");
        //TODO
    }

    @Test
    public void testIntAddEatingNotification() {
        logger.info("Тест проверяет, что после вызова метода контроллера создания нотификации о добавлении " +
                "записи о приеме пищи участником нотификация может быть получена в списке всех нотификаций " +
                "прикрепленным к участнику ментором, и все поля совпадают с установленными");

        logger.info("Вызываем метод сохранения нотификации о приеме пищи");
        notificationController.addNewEatingLogNotification(1L, TIME, DESCRIPTION, MEAL_TYPE);

        logger.info("Получаем все нотификации ментора и проверяем, что среди них есть добавленная нотификация");
        List<Notifications> allNotifications = notificationController.getAllNowMentorNotifications();
        assertEquals(1, allNotifications.size(), "У пользователя нет нотификаций");

        logger.info("Проверяем, что текст нотификации содержит все переданные значения");
        Notifications notification = allNotifications.get(0);
        assertAll(
                () -> assertTrue(notification.getAllMessage().contains(TIME.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))),
                        "В нотификации нет значения time"),
                () -> assertTrue(notification.getAllMessage().contains(DESCRIPTION),
                        "В нотификации нет значения description"),
                () -> assertTrue(notification.getAllMessage().contains(MEAL_TYPE),
                        "В нотификации нет значения meal_type"),
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
