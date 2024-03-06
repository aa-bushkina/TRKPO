package module.backTests.controllers.TestsSettingsController;

import com.cygans.database.controllers.SettingsController;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestGetAuthoritiesParticipant {

    @Mock
    private LoginInfoService loginInfoService;

    @Mock
    private ParticipantService participantService;

    @InjectMocks
    private SettingsController settingsController;

    @BeforeEach
    void setUp() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "login",
                "password",
                AuthorityUtils.createAuthorityList());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        VaadinSession vaadinSessionMock = mock(VaadinSession.class);
        VaadinSession.setCurrent(vaadinSessionMock);

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setId(1L);
        when(loginInfoService.findByLogin("login")).thenReturn(loginInfo);
    }

    @Test
    void testGetAuthoritiesParticipantSuccess() {
        Long loginInfoId = 1L;
        Participant participant = new Participant();
        participant.setId(123L);

        when(participantService.getParticipantByLoginInfoId(loginInfoId)).thenReturn(participant);

        Participant result = settingsController.getAuthoritiesParticipant();

        assertEquals(participant, result);
        verify(participantService, times(1)).getParticipantByLoginInfoId(loginInfoId);
    }

    @Test
    void testGetAuthoritiesParticipantNullParticipant() {
        Long loginInfoId = 1L;

        when(participantService.getParticipantByLoginInfoId(loginInfoId)).thenReturn(null);

        Participant result = settingsController.getAuthoritiesParticipant();

        assertNull(result);
        verify(participantService, times(1)).getParticipantByLoginInfoId(loginInfoId);
    }
}
