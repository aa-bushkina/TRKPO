package module.backTests.controllers.TestsRegistrationAndLoginController;

import com.cygans.database.controllers.RegistrationAndLoginController;
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestCheckPresentLogin {

    @Mock
    private LoginInfoService loginInfoService;

    @InjectMocks
    private RegistrationAndLoginController controller;

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
    void testCheckPresentLoginExists() {
        String existingLogin = "existingLogin";
        when(loginInfoService.findByLogin(existingLogin)).thenReturn(new LoginInfo());

        boolean result = controller.checkPresentLogin(existingLogin);

        assertTrue(result);
    }

    @Test
    void testCheckPresentLoginDoesNotExist() {
        String nonExistingLogin = "nonExistingLogin";
        when(loginInfoService.findByLogin(nonExistingLogin)).thenReturn(null);

        boolean result = controller.checkPresentLogin(nonExistingLogin);

        assertFalse(result);
    }
}

