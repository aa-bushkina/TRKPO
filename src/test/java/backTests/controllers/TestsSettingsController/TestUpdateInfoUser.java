package backTests.controllers.TestsSettingsController;

import com.cygans.database.controllers.SettingsController;
import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorService;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import com.cygans.security.db.RoleEnum;
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

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestUpdateInfoUser {

    @Mock
    private ParticipantService participantService;

    @Mock
    private MentorService mentorService;

    @InjectMocks
    private SettingsController settingsController;
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

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setId(0L);
        when(loginInfoService.findByLogin("login")).thenReturn(loginInfo);
    }

    @Test
    void testUpdateInfoUserParticipant() {
        RoleEnum role = RoleEnum.PARTICIPANT;
        Long uid = 0L;
        String firstname = "John";
        String lastname = "Doe";
        String login = "login";
        String phone = "+79374657585";
        LocalDate birthday = LocalDate.of(1990, 5, 15);
        String gender = "male";
        Integer height = 180;
        Integer weight = 75;
        Integer breast = 90;
        Integer waist = 80;
        Integer hips = 100;

        when(settingsController.getAuthoritiesParticipant()).thenReturn(new Participant());

        settingsController.updateInfoUser(role, firstname, lastname, login, phone, birthday, gender, height, weight, breast, waist, hips);

        verify(participantService, times(1)).updateParticipantFirstName(uid, firstname);
        verify(participantService, times(1)).updateParticipantLastName(uid, lastname);
        verify(participantService, times(1)).updateParticipantLogin(uid, login);
        verify(participantService, times(1)).updateParticipantPhone(uid, phone);
        verify(participantService, times(1)).updateParticipantBirthday(uid, birthday);
        verify(participantService, times(1)).updateParticipantGender(uid, gender);
        verify(participantService, times(1)).updateParticipantHeight(uid, height);
        verify(participantService, times(1)).updateParticipantWeight(uid, weight);
        verify(participantService, times(1)).updateParticipantBreast(uid, breast);
        verify(participantService, times(1)).updateParticipantWaist(uid, waist);
        verify(participantService, times(1)).updateParticipantHips(uid, hips);
    }

    @Test
    void testUpdateInfoUserMentor() {
        RoleEnum role = RoleEnum.MENTOR;
        Long uid = 0L;
        String firstname = "Jane";
        String lastname = "Smith";
        String login = "login";
        String phone = "+79374657585";
        LocalDate birthday = LocalDate.of(1985, 10, 20);
        String gender = "female";

        when(settingsController.getAuthoritiesMentor()).thenReturn(new Mentor());

        settingsController.updateInfoUser(role, firstname, lastname, login, phone, birthday, gender, null, null, null, null, null);

        verify(mentorService, times(1)).updateFirstname(uid, firstname);
        verify(mentorService, times(1)).updateLastname(uid, lastname);
        verify(mentorService, times(1)).updateLogin(uid, login);
        verify(mentorService, times(1)).updatePhone(uid, phone);
        verify(mentorService, times(1)).updateBirthday(uid, birthday);
        verify(mentorService, times(1)).updateGender(uid, gender);
    }
}

