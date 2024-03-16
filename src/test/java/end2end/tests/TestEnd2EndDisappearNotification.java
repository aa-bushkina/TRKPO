package end2end.tests;

import com.vaadin.flow.server.VaadinSession;
import end2end.pages.mentor.NotificationsMentorPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет, что нотификации о записях у ментора исчезают только после ответа на них
 */
public class TestEnd2EndDisappearNotification extends TestBase {

    private static final int COUNT_NOTIFICATIONS = 1;
    private static final String COMMENT = "На улице rain на душе pain";
    private static final String ANSWER = "У меня тоже";
    private Long logId;
    private Long notificationId;

    @BeforeEach
    public void setUp() {
        logger.info("Регистрируем участника, ментора и связываем их");
        registerParticipant();
        registerMentor();
        linkParticipantMentor();

        logger.info("Создаем запись об эмоциональном состоянии и уведомление");
        when(VaadinSession.getCurrent().getAttribute("date")).thenReturn(LocalDate.now());
        loginParticipant();
        logId = logController.saveEmotionalLog(COMMENT);
        notificationController.addNewEmotionalLogNotification(logId, COMMENT);
        loginMentor();
        notificationId = notificationController.getAllNowMentorNotifications()
                .get(0)
                .getNotificationId();
    }

    @Test
    public void testEnd2EndDisappearNotification() {
        logger.info("Тест проверяет, что нотификации о записях у ментора исчезают только после ответа на них");

        logger.info("Логинимся ментором и заходим в оповещения");
        NotificationsMentorPage notificationsMentorPage = getLoginPage().login(LOGIN_MENTOR, PASSWORD)
                .andReturnStartMentorPage()
                .goToNotifications();
        assertEquals(COUNT_NOTIFICATIONS, notificationsMentorPage.getCountNotifications(), "Неверное количество оповещений");

        logger.info("Зайдем и выйдем из оповещения");
        notificationsMentorPage = notificationsMentorPage.lookNotification().clickBack();
        assertEquals(COUNT_NOTIFICATIONS, notificationsMentorPage.getCountNotifications(), "Неверное количество оповещений");

        logger.info("Зайдем и ответим на оповещение");
        notificationsMentorPage = notificationsMentorPage.lookNotification()
                .answerOnNotification(ANSWER)
                .sendAnswer();
        assertEquals(0, notificationsMentorPage.getCountNotifications(), "Оповещение не исчезло");

        logger.info("Тест прошел успешно");
    }

    @AfterEach
    public void clear() {
        logger.info("Удаляем запись");
        if (emotionalLogBookRepository.findByLogBookId(logId) != null) {
            emotionalLogBookRepository.delete(emotionalLogBookRepository.findByLogBookId(logId));
        }
        if (logRepository.findById(logId).isPresent()) {
            logRepository.delete(logRepository.findById(logId).get());
        }
        logger.info("Удаляем нотификацию");
        if (notificationsRepository.getNotificationById(notificationId) != null) {
            notificationsRepository.delete(notificationsRepository.getNotificationById(notificationId));
        }
    }

}
