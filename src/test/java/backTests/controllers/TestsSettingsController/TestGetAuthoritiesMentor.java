package backTests.controllers.TestsSettingsController;


import com.cygans.database.controllers.SettingsController;
import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestGetAuthoritiesMentor {

    @Mock
    private LoginInfoService loginInfoService;

    @Mock
    private MentorService mentorService;

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
    void testGetAuthoritiesMentorSuccess() {
        Long loginInfoId = 1L;
        Mentor mentor = new Mentor();
        mentor.setId(123L);

        when(mentorService.getMentorByLoginInfoId(loginInfoId)).thenReturn(mentor);

        Mentor result = settingsController.getAuthoritiesMentor();

        assertEquals(mentor, result);
        verify(mentorService, times(1)).getMentorByLoginInfoId(loginInfoId);
    }

    @Test
    void testGetAuthoritiesMentorNullMentor() {
        Long loginInfoId = 1L;

        when(mentorService.getMentorByLoginInfoId(loginInfoId)).thenReturn(null);

        Mentor result = settingsController.getAuthoritiesMentor();

        assertNull(result);
        verify(mentorService, times(1)).getMentorByLoginInfoId(loginInfoId);
    }
}
