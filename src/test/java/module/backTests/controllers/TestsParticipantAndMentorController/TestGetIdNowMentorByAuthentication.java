package module.backTests.controllers.TestsParticipantAndMentorController;

import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorService;
import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestGetIdNowMentorByAuthentication {

    @InjectMocks
    private ParticipantAndMentorController controller;

    @Mock
    private MentorService mentorService;

    @Mock
    private LoginInfoService loginInfoService;

    @BeforeEach
    public void setUp() {
        LoginInfo loginInfoMock = mock(LoginInfo.class);
        Authentication authenticationMock = new UsernamePasswordAuthenticationToken("username", "password");

        when(loginInfoService.findByLogin(anyString())).thenReturn(loginInfoMock);
        when(mentorService.getMentorByLoginInfoId(anyLong())).thenReturn(new Mentor());

        SecurityContextHolder.getContext().setAuthentication(authenticationMock);
    }

    @Test
    void testGetIdNowMentorByAuthentication() {
        Mentor mentor = controller.getIdNowMentorByAuthentication();

        verify(mentorService).getMentorByLoginInfoId(anyLong());
        assertEquals(Mentor.class, mentor.getClass());
    }
}
