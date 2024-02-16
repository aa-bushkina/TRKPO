package backTests.controllers.TestsQuestionController;

import com.cygans.database.controllers.QuestionController;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.question.Question;
import com.cygans.database.question.QuestionService;
import com.cygans.database.question.question_status.QuestionStatusService;
import com.cygans.database.question.question_status.StatusOfQuestion;
import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestAddNewQuestionForNowParticipant {

    @InjectMocks
    private QuestionController questionController;

    @Mock
    private QuestionService questionService;
    @Mock
    private ParticipantService participantService;

    @Mock
    private QuestionStatusService questionStatusService;

    @Mock
    private LoginInfoService loginInfoService;

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
    void addNewQuestionForNowParticipantTest() {
        String questionText = "Test question";
        Long participantId = 1L;
        Long questionId = 0L;
        Long loginInfoId = 0L;
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setId(loginInfoId);
        Participant participant = new Participant();
        participant.setId(participantId);

        when(loginInfoService.findByLogin(any())).thenReturn(loginInfo);
        when(participantService.getParticipantByLoginInfoId(loginInfoId)).thenReturn(participant);

        when(questionStatusService.geQuestionStatusId(StatusOfQuestion.NO_ANSWER)).thenReturn(1L);
        doNothing().when(questionService).saveQuestion(any());

        Long result = questionController.addNewQuestionForNowParticipant(questionText);

        assertEquals(questionId, result);
        verify(questionService).saveQuestion(any(Question.class));
    }
}
