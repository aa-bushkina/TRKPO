package integration;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.controllers.QuestionController;
import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.database.controllers.SettingsController;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.question.Question;
import com.cygans.database.question.question_status.QuestionStatus;
import com.cygans.database.question.question_status.StatusOfQuestion;
import com.cygans.security.db.RoleEnum;
import com.vaadin.flow.server.VaadinSession;
import integration.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет, что после вызова метода контроллера создания ответа на вопрос ментором нотификация может
 * быть получена в списке всех нотификаций прикрепленным к ментору участником, и все поля совпадают с установленными
 */
public class TestAddAnswerToQuestion extends BaseTest {


    private static final String QUESTION = "Что мне съесть?";
    private static final String ANSWER = "Съешь пиццу";
    private static final String ALL_MESSAGE = "Ваш вопрос: Что мне съесть?";
    private Long participantId;
    private Long mentorId;
    private Long typeId;


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
        when(VaadinSession.getCurrent().getAttribute("Login")).thenReturn(LOGIN_PARTICIPANT);
        registrationAndLoginController.authenticationUser(RoleEnum.PARTICIPANT);
        Long logId = questionController.addNewQuestionForNowParticipant(QUESTION);
        notificationController.addNewQuestionNotification(logId, QUESTION);

        logger.info("Достаем нотификацию о нотификации вопроса");
        when(VaadinSession.getCurrent().getAttribute("Login")).thenReturn(LOGIN_MENTOR);
        registrationAndLoginController.authenticationUser(RoleEnum.MENTOR);
        List<Notifications> allNotifications = notificationController.getAllNowMentorNotifications();
        assertEquals(1, allNotifications.size(), "У ментора нет нотификаций");
        Notifications notifications = allNotifications.get(0);

        logger.info("Отвечаем на вопрос ментором");
        questionController.addAnswerToQuestion(logId, notifications.getNotificationId(), ANSWER);

        logger.info("Проверяем состояние вопроса");
        when(VaadinSession.getCurrent().getAttribute("Login")).thenReturn(LOGIN_PARTICIPANT);
        registrationAndLoginController.authenticationUser(RoleEnum.PARTICIPANT);
        List<Question> questions = questionController.getAllQuestionNowParticipant();
        assertEquals(1, questions.size(), "У пользователя нет вопросов");
        Question question = questions.get(0);
        assertAll(
                () -> assertEquals(ANSWER, question.getAnswer(), "Неверный ответ на вопрос"),
                () -> assertEquals(StatusOfQuestion.YES_ANSWER.getText(), questionController.getQuestionStatus(question), "Неверынй статус у вопроса")
        );

        logger.info("Проверяем состояние уведомления");
        when(VaadinSession.getCurrent().getAttribute("Login")).thenReturn(LOGIN_MENTOR);
        registrationAndLoginController.authenticationUser(RoleEnum.MENTOR);
        allNotifications = notificationController.getAllNowMentorNotifications();
        assertEquals(1, allNotifications.size(), "У ментора нет нотификаций");
        Notifications fNotifications = allNotifications.get(0);
        assertTrue(fNotifications.getAllMessage().contains(ALL_MESSAGE), "Нотификация не содержит нужного all_message");
        logger.info("Тест успешно пройден");
    }

}
