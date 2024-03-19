package end2end.tests;

import com.vaadin.flow.server.VaadinSession;
import end2end.pages.mentor.HistoryParticipantLogsPage;
import end2end.pages.mentor.ParticipantLogDetailsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет, что ментор может просмотреть с главной страницы список записей участника, к которому прикреплен
 */
public class TestEnd2EndMentorLookParticipantLogs extends TestBase {

    private static final String COMMENT = "На улице rain на душе pain";
    private static final String TYPE = "Эмоциональное состояние";
    private static final String DATE_1 = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    private static final String DATE_2 = LocalDate.now().toString();
    private static final int COUNT_LOGS = 1;
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
    public void testEnd2EndMentorLookParticipantLogs() {
        logger.info("Тест проверяет, что ментор может просмотреть с главной страницы список записей участника, к которому прикреплен");

        logger.info("Логинисмя ментором и заходим к закрепленному участнику");
        HistoryParticipantLogsPage historyParticipantLogsPage = getLoginPage().login(LOGIN_MENTOR, PASSWORD)
                .andReturnStartMentorPage()
                .clickParticipant(LOGIN_PARTICIPANT);
        assertEquals(COUNT_LOGS, historyParticipantLogsPage.getCountLogs(), "Неверное количество записей");
        assertEquals(TYPE, historyParticipantLogsPage.getTypeLog(COUNT_LOGS), "Неверный тип записи");
        assertEquals(DATE_1, historyParticipantLogsPage.getDateLog(COUNT_LOGS), "Неверная дата записи");

        logger.info("Заходим в запись и проверяем её");
        ParticipantLogDetailsPage participantLogDetailsPage = historyParticipantLogsPage.clickWatch();
        assertEquals(COMMENT, participantLogDetailsPage.getDescription(), "Неверное описание у записи");
        assertTrue(participantLogDetailsPage.getDate().contains(DATE_2), "Неверная дата записи");

        logger.info("Тест прошел");
    }

    @AfterEach
    public void clear() {
        logoutMentor();
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
