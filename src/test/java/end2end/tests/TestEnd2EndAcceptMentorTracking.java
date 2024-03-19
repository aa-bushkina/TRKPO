package end2end.tests;

import end2end.pages.mentor.AddParticipantPage;
import end2end.pages.mentor.StartMentorPage;
import end2end.pages.participant.NotificationsParticipantPage;
import end2end.pages.participant.StartParticipantPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тест проверяет добавление ментором участника в отслеживание и принятие участником запроса
 */
public class TestEnd2EndAcceptMentorTracking extends TestBase {

    @BeforeEach
    public void setUp() {
        logger.info("Регистрируем участника, ментора");
        registerParticipant();
        registerMentor();
    }

    @Test
    public void testEnd2EndAcceptMentorTracking() {
        logger.info("Тест проверяет добавление ментором участника в отслеживание и принятие участником запроса");

        logger.info("Логинимся ментором и переходим на страницу добавления участника");
        AddParticipantPage addParticipantPage = getLoginPage()
                .login(LOGIN_MENTOR, PASSWORD)
                .andReturnStartMentorPage()
                .addParticipant();

        logger.info("Отправляем запрос участнику " + LOGIN_PARTICIPANT);
        addParticipantPage = addParticipantPage.typeLogin(LOGIN_PARTICIPANT).sendRequest();

        logger.info("Выходим и логинимся участником");
        StartParticipantPage startParticipantPage = addParticipantPage.goToStartPage()
                .logout()
                .login(LOGIN_PARTICIPANT, PASSWORD)
                .andReturnStartParticipantPage();

        logger.info("Заходим в уведомления и принимаем запрос");
        NotificationsParticipantPage notificationsParticipantPage = startParticipantPage.goToNotifications()
                .lookNotification()
                .acceptRequest();

        logger.info("Выходим и логинимся ментором");
        StartMentorPage startMentorPage = notificationsParticipantPage.home()
                .logout()
                .login(LOGIN_MENTOR, PASSWORD)
                .andReturnStartMentorPage();

        logger.info("Проверяем, что в списке есть нужный отслеживаемый участник");
        startMentorPage.checkParticipant(FIRSTNAME, LASTNAME, LOGIN_PARTICIPANT);

        logger.info("Тест прошел успешно");
    }

    @AfterEach
    public void logout() {
        logoutMentor();
    }
}
