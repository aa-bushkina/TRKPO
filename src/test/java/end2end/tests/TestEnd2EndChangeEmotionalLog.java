package end2end.tests;

import com.vaadin.flow.server.VaadinSession;
import end2end.pages.mentor.NotificationsMentorPage;
import end2end.pages.mentor.OneNotificationMentorPage;
import end2end.pages.participant.LogParticipantPage;
import end2end.pages.participant.StartParticipantPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет, что можно изменить созданную запись об эмоциональном состоянии
 * и она изменится у участника в журнале записей и у ментора в нотификации
 */
public class TestEnd2EndChangeEmotionalLog extends TestBase {

    private static final String COMMENT = "На улице rain на душе pain";
    private static final String NEW_COMMENT = ", но зато поел пельмени, стало весело";
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
    public void testEnd2EndChangeEmotionalLog() {
        logger.info("Тест проверяет, что можно изменить созданную запись об эмоциональном состоянии" +
                " и она изменится у участника в журнале записей и у ментора в нотификации");

        logger.info("Логинимся участником");
        StartParticipantPage startParticipantPage = getLoginPage()
                .login(LOGIN_PARTICIPANT, PASSWORD)
                .andReturnStartParticipantPage();

        logger.info("Редактируем запись об эмоциональном состоянии");
        LogParticipantPage logParticipantPage = startParticipantPage.goToLogs()
                .lookLog()
                .clickChange()
                .addText(NEW_COMMENT)
                .clickSave();
        assertEquals(COMMENT + NEW_COMMENT, logParticipantPage.getDescription(), "Описание не изменилось");

        logger.info("Выходим, логинимся ментором и заходим в нотификации");
        NotificationsMentorPage notificationsMentorPage = logParticipantPage.home()
                .logout()
                .login(LOGIN_MENTOR, PASSWORD)
                .andReturnStartMentorPage()
                .goToNotifications();

        logger.info("Проверяем, что в нотификации текст изменился");
        OneNotificationMentorPage oneNotificationMentorPage = notificationsMentorPage.lookNotification();
        assertTrue(oneNotificationMentorPage.getMessage().contains(COMMENT + NEW_COMMENT), "Тест не изменился в уведомлении");

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
