package security;

import com.cygans.security.SecurityUtils;
import com.vaadin.flow.shared.ApplicationConstants;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestSecurityUtils {

    @Test
    public void testIsFrameworkInternalRequestValidParameterReturnsTrue() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter(ApplicationConstants.REQUEST_TYPE_PARAMETER, "push");
        boolean result = SecurityUtils.isFrameworkInternalRequest(request);

        assertTrue(result);
    }

    @Test
    public void testIsFrameworkInternalRequestNoParameterReturnsFalse() {
        HttpServletRequest request = new MockHttpServletRequest();
        boolean result = SecurityUtils.isFrameworkInternalRequest(request);

        assertFalse(result);
    }

    @Test
    public void testIsFrameworkInternalRequestInvalidParameterValue_ReturnsFalse() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter(ApplicationConstants.REQUEST_TYPE_PARAMETER, "invalid_value");
        boolean result = SecurityUtils.isFrameworkInternalRequest(request);

        assertFalse(result);
    }

//    @Test
//    public void testIsUserLoggedInAuthenticatedUserReturnsTrue() {
//        Authentication authentication = mock(Authentication.class);
//        when(authentication.isAuthenticated()).thenReturn(true);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        boolean result = SecurityUtils.isUserLoggedIn();
//
//        assertTrue(result);
//    }

    @Test
    public void testIsUserLoggedInAnonymousUserReturnsFalse() {
        Authentication authentication = mock(AnonymousAuthenticationToken.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean result = SecurityUtils.isUserLoggedIn();

        assertFalse(result);
    }

    @Test
    public void testIsUserLoggedInNoAuthenticationReturnsFalse() {
        SecurityContextHolder.clearContext();
        boolean result = SecurityUtils.isUserLoggedIn();

        assertFalse(result);
    }
}
