package backTests.controllers.TestsRegistrationAndLoginController;

import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.security.db.RoleEnum;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class TestRegistrationUser {

    @InjectMocks
    private RegistrationAndLoginController controller;

    @Mock
    private SecurityContext securityContextMock;

    @BeforeEach
    public void setUp() {
        SecurityContextHolder.setContext(securityContextMock);

        VaadinSession vaadinSessionMock = mock(VaadinSession.class);
        VaadinSession.setCurrent(vaadinSessionMock);
    }


    @Test
    void testAuthenticationUser() {
        RoleEnum role = RoleEnum.PARTICIPANT;

        controller.authenticationUser(role);

        verify(securityContextMock).setAuthentication(any(Authentication.class));
    }
}


