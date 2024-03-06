package module.backTests.controllers.TestsRegistrationAndLoginController;

import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorService;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import com.cygans.security.db.RoleEnum;
import com.cygans.security.db.authorities.Authorities;
import com.cygans.security.db.authorities.AuthoritiesService;
import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoService;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestRegistrationUser {

    @InjectMocks
    private RegistrationAndLoginController controller;

    @Mock
    private AuthoritiesService authoritiesService;

    @Mock
    private LoginInfoService loginInfoService;

    @Mock
    private MentorService mentorService;

    @Mock
    private ParticipantService participantService;
    VaadinSession vaadinSessionMock;

    @BeforeEach
    public void setUp() {
        vaadinSessionMock = mock(VaadinSession.class);
        VaadinSession.setCurrent(vaadinSessionMock);

        when(vaadinSessionMock.getAttribute("Login")).thenReturn("Login");
        when(vaadinSessionMock.getAttribute("Password")).thenReturn("Password");
        when(vaadinSessionMock.getAttribute("Gender")).thenReturn("Male");
        when(vaadinSessionMock.getAttribute("FirstName")).thenReturn("Firstname");
        when(vaadinSessionMock.getAttribute("LastName")).thenReturn("Lastname");
        when(vaadinSessionMock.getAttribute("Phone")).thenReturn("+794758475632");
        when(vaadinSessionMock.getAttribute("Date")).thenReturn(LocalDate.of(2023, 12, 31));
    }

    @Test
    void testRegistrationUserParticipant() {
        when(vaadinSessionMock.getAttribute("Height")).thenReturn("150");
        when(vaadinSessionMock.getAttribute("Weight")).thenReturn("80");
        when(vaadinSessionMock.getAttribute("Breast")).thenReturn("90");
        when(vaadinSessionMock.getAttribute("Waist")).thenReturn("90");
        when(vaadinSessionMock.getAttribute("Hip")).thenReturn("90");

        RoleEnum role = RoleEnum.PARTICIPANT;

        LoginInfo loginInfoMock = mock(LoginInfo.class);
        when(loginInfoMock.getId()).thenReturn(1L);
        when(loginInfoService.findByLogin(anyString())).thenReturn(loginInfoMock);

        controller.registrationUser(role);

        verify(authoritiesService).saveAuthorities(any(Authorities.class));
        verify(loginInfoService).saveLoginInfo(any(LoginInfo.class));
        verify(participantService).saveParticipant(any(Participant.class));
        verify(mentorService, never()).saveMentor(any());
    }

    @Test
    void testRegistrationUserMentor() {
        RoleEnum role = RoleEnum.MENTOR;

        LoginInfo loginInfoMock = mock(LoginInfo.class);
        when(loginInfoMock.getId()).thenReturn(1L);
        when(loginInfoService.findByLogin(anyString())).thenReturn(loginInfoMock);

        controller.registrationUser(role);

        verify(authoritiesService).saveAuthorities(any(Authorities.class));
        verify(loginInfoService).saveLoginInfo(any(LoginInfo.class));
        verify(mentorService).saveMentor(any(Mentor.class));
        verify(participantService, never()).saveParticipant(any());
    }
}


