package backTests.controllers.TestsSettingsController;

import com.cygans.database.controllers.SettingsController;
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestCheckEqualsPassword {

    @Mock
    private LoginInfoService loginInfoService;

    @InjectMocks
    private SettingsController settingsController;

    @BeforeEach
    void setUp() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "username",
                "password",
                AuthorityUtils.createAuthorityList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void testCheckEqualsPassword_CorrectPassword() {
        String password = "password";

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setPassword(password);

        when(loginInfoService.findByLogin("username")).thenReturn(loginInfo);

        assertTrue(settingsController.checkEqualsPassword(password));
    }

    @Test
    void testCheckEqualsPassword_IncorrectPassword() {
        String password = "password";
        String incorrectPassword = "wrong_password";

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setPassword(password);

        when(loginInfoService.findByLogin("username")).thenReturn(loginInfo);

        assertFalse(settingsController.checkEqualsPassword(incorrectPassword), "Разные пароли были признаны методом одинаковыми");
    }
}
