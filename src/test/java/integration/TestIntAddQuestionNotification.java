package integration;

import com.cygans.Application;
import com.cygans.database.controllers.NotificationController;
import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.database.controllers.SettingsController;
import com.cygans.database.notifications.Notifications;
import com.cygans.security.db.RoleEnum;
import com.vaadin.flow.server.VaadinSession;
import integration.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет, что после вызова метода контроллера создания нотификации о добавлении вопроса
 * участником нотификация может быть получена в списке всех нотификаций прикрепленным к участнику
 * ментором, и все поля совпадают с установленными
 */
@SpringBootTest(classes = Application.class)
public class TestIntAddQuestionNotification extends BaseTest {
    private static final Long PARTICIPANT_ID = 1L;
    private static final Long MENTOR_ID = 2L;
    private static final String QUESTION = "Надоело писать тесты, как перестать заедать этот стресс";
    private static final LocalDate DATE = LocalDate.now();
    private Long participantId;
    private Long mentorId;

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
    public void testIntAddQuestionNotification() {
        logger.info("Тест проверяет, что после вызова метода контроллера создания нотификации о добавлении " +
                "записи об эмоциональном состоянии участником нотификация может быть получена в списке всех нотификаций " +
                "прикрепленным к участнику ментором, и все поля совпадают с установленными");

        logger.info("Вызываем метод сохранения нотификации о вопросе");
        loginParticipant();
        notificationController.addNewQuestionNotification(1L, QUESTION);

        logger.info("Получаем все нотификации ментора и проверяем, что среди них есть добавленная нотификация");
        loginMentor();
        List<Notifications> allNotifications = notificationController.getAllNowMentorNotifications();
        assertEquals(1, allNotifications.size(), "У пользователя нет нотификаций");

        logger.info("Проверяем, что текст нотификации содержит все переданные значения");
        Notifications notification = allNotifications.get(0);
        assertAll(
                () -> assertTrue(notification.getAllMessage().contains(QUESTION),
                        "В нотификации нет значения question"),
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
}
