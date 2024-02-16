package backTests.controllers.TestsParticipantAndMentorController;

import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
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
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestGetNowParticipantByAuthentication {

    @InjectMocks
    private ParticipantAndMentorController controller;

    @Mock
    private LoginInfoService loginInfoService;

    @Mock
    private UserDetailsService userDetailsService;
    @Mock
    private ParticipantService participantService;

    @BeforeEach
    public void setUp() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "username",
                "password",
                AuthorityUtils.createAuthorityList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void testGetNowParticipantByAuthentication() {
        Long loginInfoId = 123L;
        Long participantId = 456L;
        Participant participant = new Participant();
        participant.setId(participantId);

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setId(123L);
        when(loginInfoService.findByLogin("username")).thenReturn(loginInfo);
        when(participantService.getParticipantByLoginInfoId(loginInfoId)).thenReturn(participant);

        Participant result = controller.getNowParticipantByAuthentication();

        verify(loginInfoService).findByLogin("username");
        assertEquals(participant, result);
    }
}
