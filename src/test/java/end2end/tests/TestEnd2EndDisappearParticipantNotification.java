package end2end.tests;

import com.vaadin.flow.server.VaadinSession;
import end2end.pages.mentor.NotificationsMentorPage;
import end2end.pages.participant.NotificationsParticipantPage;
import end2end.pages.participant.StartParticipantPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет, что нотификации об ответах ментора у участника исчезают после открытия нотификации
 */
public class TestEnd2EndDisappearParticipantNotification extends TestBase {

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
    public void testEnd2EndDisappearParticipantNotification() {
        logger.info("Тест проверяет, что нотификации об ответах ментора у участника исчезают после открытия нотификации");

        logger.info("Логинимся ментором и отвечаем на оповещение");
        NotificationsMentorPage notificationsMentorPage = getLoginPage().login(LOGIN_MENTOR, PASSWORD)
                .andReturnStartMentorPage()
                .goToNotifications()
                .lookNotification()
                .answerOnNotification(ANSWER)
                .sendAnswer();

        logger.info("Логинимся участником");
        StartParticipantPage startParticipantPage = notificationsMentorPage.goToStartPage()
                .logout()
                .login(LOGIN_PARTICIPANT, PASSWORD)
                .andReturnStartParticipantPage();

        logger.info("Переходим в оповещения, открываем и закрываем необходимое");
        NotificationsParticipantPage notificationsParticipantPage = startParticipantPage.goToNotifications();
        assertEquals(COUNT_NOTIFICATIONS, notificationsParticipantPage.getCountNotifications(), "Неверное количество оповещений");
        notificationsParticipantPage = notificationsParticipantPage.lookNotification().back();
        assertEquals(0, notificationsParticipantPage.getCountNotifications(), "Оповещение не исчезло");

        logger.info("Выходим из аккаунта");
        notificationsParticipantPage.goToStartPage().logout();

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
