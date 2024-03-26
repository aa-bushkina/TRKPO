package end2end.tests;

import com.vaadin.flow.server.VaadinSession;
import end2end.pages.mentor.NotificationsMentorPage;
import end2end.pages.participant.OneNotificationParticipantPage;
import end2end.pages.participant.QuestionsParticipantPage;
import end2end.pages.participant.StartParticipantPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет, что ментор может ответить на вопрос прикрепленного участника
 * и участник увидит ответ в нотификации и на странице вопросов
 */
public class TestEnd2EndMentorAnswerQuestion extends TestBase {

    private static final String QUESTION = "Мне грустно после тренировки, это нормально?";
    private static final String ANSWER = "Да, съешь пельмени, будет лучше";
    private static final String QUESTION_STATUS = "Ответ получен";
    private Long questionId;
    private Long notificationId;

    @BeforeEach
    public void setUp() {
        logger.info("Регистрируем участника, ментора и связываем их");
        registerParticipant();
        registerMentor();
        linkParticipantMentor();

        logger.info("Создаем вопрос и нотификацию о нем");
        when(VaadinSession.getCurrent().getAttribute("date")).thenReturn(LocalDate.now());
        loginParticipant();
        questionId = questionController.addNewQuestionForNowParticipant(QUESTION);
        notificationController.addNewQuestionNotification(questionId, QUESTION);
        loginMentor();
        notificationId = notificationController.getAllNowMentorNotifications().get(0).getNotificationId();
    }

    @Test
    public void testEnd2EndMentorAnswerQuestion() {
        logger.info("Тест проверяет, что ментор может ответить на вопрос прикрепленного участника " +
                "и участник увидит ответ в нотификации и на странице вопросов");

        logger.info("Логинимся ментором и переходим в уведомления");
        NotificationsMentorPage notificationsMentorPage = getLoginPage().login(LOGIN_MENTOR, PASSWORD)
                .andReturnStartMentorPage()
                .goToNotifications();

        logger.info("Пеерходим в уведомление и отвечаем на него");
        notificationsMentorPage = notificationsMentorPage.lookNotification()
                .answerOnNotification(ANSWER)
                .sendAnswer();

        logger.info("Логинимся участником");
        StartParticipantPage startParticipantPage = notificationsMentorPage.goToStartPage()
                .logout()
                .login(LOGIN_PARTICIPANT, PASSWORD)
                .andReturnStartParticipantPage();

        logger.info("Проверяем ответ в уведомлении");
        OneNotificationParticipantPage oneNotificationParticipantPage = startParticipantPage.goToNotifications().lookNotification();
        String answer = oneNotificationParticipantPage.getAnswer();
        assertEquals(ANSWER, answer, "Неверный ответ отображается");

        logger.info("Проверим ответ в списке вопросов во вкладке вопросы");
        QuestionsParticipantPage questionsParticipantPage = oneNotificationParticipantPage.back()
                .home()
                .goToQuestions();
        assertEquals(QUESTION_STATUS, questionsParticipantPage.getQuestionStatus(), "Неверный статус у вопроса");
        answer = questionsParticipantPage.clickWatch().getAnswer();
        assertEquals(ANSWER, answer, "Неверный ответ отображается");

        logger.info("Тест прошел успешно");
    }

    @AfterEach
    public void clear() {
        logger.info("Удаляем вопрос и уведомления о нем");
        if (questionRepository.getQuestionById(questionId) != null) {
            questionRepository.delete(questionRepository.getQuestionById(questionId));
        }
        if (notificationsRepository.getNotificationById(notificationId) != null) {
            notificationsRepository.delete(notificationsRepository.getNotificationById(notificationId));
        }
    }

}
