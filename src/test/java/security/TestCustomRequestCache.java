package security;

import com.cygans.security.CustomRequestCache;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class TestCustomRequestCache {

    @Test
    void saveRequestInternalRequestNotSaved() {
        HttpSessionRequestCache baseRequestCache = Mockito.mock(HttpSessionRequestCache.class);
        CustomRequestCache customRequestCache = new CustomRequestCache();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("User-Agent", "Some User Agent");
        request.addHeader("X-Requested-With", "XMLHttpRequest");
        HttpServletResponse response = new MockHttpServletResponse();

        customRequestCache.saveRequest(request, response);

        verify(baseRequestCache, never()).saveRequest(any(), any());
    }


    @Test
    void saveRequestExternalRequestSave() {
        CustomRequestCache customRequestCache = Mockito.spy(new CustomRequestCache());
        HttpServletRequest request = new MockHttpServletRequest();
        ((MockHttpServletRequest) request).addHeader("User-Agent", "Some User Agent");
        HttpServletResponse response = new MockHttpServletResponse();

        customRequestCache.saveRequest(request, response);

        verify(customRequestCache, times(1)).saveRequest(any(), any());
    }
}
