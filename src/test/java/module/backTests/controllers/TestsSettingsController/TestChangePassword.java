package module.backTests.controllers.TestsSettingsController;


import com.cygans.database.controllers.SettingsController;
import com.cygans.security.db.RoleEnum;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestChangePassword {

    @Mock
    private LoginInfoService loginInfoService;

    @InjectMocks
    private SettingsController settingsController;

    @BeforeEach
    void setUp() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "username",
                "old_password",
                AuthorityUtils.createAuthorityList(RoleEnum.PARTICIPANT.getValue()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void testChangePasswordSuccess() {
        String newPassword = "new_password";
        RoleEnum role = RoleEnum.MENTOR;

        LoginInfo loginInfo = new LoginInfo("username", "old_password", 0L, (byte) 1);

        when(loginInfoService.findByLogin("username")).thenReturn(loginInfo);

        settingsController.changePassword(newPassword, role);

        verify(loginInfoService, times(1)).updateUserPassword("username", newPassword);
        verify(loginInfoService, times(1)).findByLogin("username");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertTrue(authentication.isAuthenticated());
        assertEquals(newPassword, authentication.getCredentials());
        assertTrue(authentication.getAuthorities().contains(AuthorityUtils.createAuthorityList(role.getValue()).iterator().next()));
    }
}
