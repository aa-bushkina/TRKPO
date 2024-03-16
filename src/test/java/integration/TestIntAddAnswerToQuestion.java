package integration;

import com.cygans.database.notifications.Notifications;
import com.cygans.database.question.Question;
import com.cygans.database.question.question_status.StatusOfQuestion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест проверяет, что после вызова метода контроллера создания ответа на вопрос ментором нотификация может
 * быть получена в списке всех нотификаций прикрепленным к ментору участником, и все поля совпадают с установленными
 */
public class TestIntAddAnswerToQuestion extends TestIntBase {
    private static final String QUESTION = "Что мне съесть?";
    private static final String ANSWER = "Съешь пиццу";
    private static final String ALL_MESSAGE = "Ваш вопрос: Что мне съесть?";
    private Long participantId;
    private Long mentorId;
    private Long questionId;
    private Long notificationId;

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
    public void testAddAnswerToQuestion() {
        logger.info("Тест проверяет, что после вызова метода контроллера создания ответа на вопрос ментором " +
                "нотификация может быть получена в списке всех нотификаций прикрепленным к ментору участником," +
                " и все поля совпадают с установленными");

        logger.info("Создаем вопрос и нотификацию о нем");
        loginParticipant();
        questionId = questionController.addNewQuestionForNowParticipant(QUESTION);
        notificationController.addNewQuestionNotification(questionId, QUESTION);

        logger.info("Достаем нотификацию о вопросе");
        loginMentor();
        List<Notifications> allNotifications = notificationController.getAllNowMentorNotifications();
        assertEquals(1, allNotifications.size(), "У ментора нет нотификаций");
        Notifications notifications = allNotifications.get(0);
        notificationId = notifications.getNotificationId();

        logger.info("Отвечаем на вопрос ментором");
        questionController.addAnswerToQuestion(questionId, notifications.getNotificationId(), ANSWER);

        logger.info("Проверяем состояние вопроса");
        loginParticipant();
        List<Question> questions = questionController.getAllQuestionNowParticipant();
        assertEquals(1, questions.size(), "У пользователя нет вопросов");
        Question question = questions.get(0);
        assertAll(
                () -> assertEquals(ANSWER, question.getAnswer(), "Неверный ответ на вопрос"),
                () -> assertEquals(StatusOfQuestion.YES_ANSWER.getText(), questionController.getQuestionStatus(question), "Неверынй статус у вопроса")
        );

        logger.info("Проверяем состояние уведомления");
        loginMentor();
        allNotifications = notificationController.getAllNowMentorNotifications();
        assertEquals(1, allNotifications.size(), "У ментора нет нотификаций");
        Notifications fNotifications = allNotifications.get(0);
        assertTrue(fNotifications.getAllMessage().contains(ALL_MESSAGE), "Нотификация не содержит нужного all_message");

        logger.info("Тест успешно пройден");
    }

    @AfterEach
    public void clear() {
        logger.info("Удаляем вопрос и уведолмения о нем");
        if (questionRepository.getQuestionById(questionId) != null) {
            questionRepository.delete(questionRepository.getQuestionById(questionId));
        }
        if (notificationsRepository.getNotificationById(notificationId) != null) {
            notificationsRepository.delete(notificationsRepository.getNotificationById(notificationId));
        }
    }
}
