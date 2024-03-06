package module.backTests.controllers.TestsQuestionController;


import com.cygans.database.controllers.QuestionController;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import com.cygans.database.question.Question;
import com.cygans.database.question.QuestionService;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestAddAnswerToQuestion {

    @InjectMocks
    private QuestionController questionController;

    @Mock
    private QuestionService questionService;

    @Mock
    private NotificationsService notificationsService;

    @Mock
    private NotificationTypeService notificationTypeService;

    @BeforeEach
    public void setUp() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "login",
                "password",
                AuthorityUtils.createAuthorityList());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        VaadinSession vaadinSessionMock = mock(VaadinSession.class);
        VaadinSession.setCurrent(vaadinSessionMock);
    }


    @Test
    void addAnswerToQuestionTest() {
        Long questionId = 1L;
        Long notificationId = 2L;
        String answer = "Test answer";
        String questionText = "Test question";
        LocalDate currentDate = LocalDate.now();
        String expectedMessage = "Ваш вопрос: " + questionText + "\nДата: " + currentDate;

        Question question = new Question();
        question.setQuestion(questionText);
        question.setDate(currentDate);
        when(questionService.getQuestionById(questionId)).thenReturn(question);

        questionController.addAnswerToQuestion(questionId, notificationId, answer);

        verify(questionService).addAnswer(questionId, answer);
        verify(notificationsService).updateNotificationType(notificationId, notificationTypeService.getNotificationTypeId(TypeOfNotification.ANSWER_ON_QUESTION));
        verify(notificationsService).updateNotificationAllMessage(notificationId, expectedMessage);
    }
}
