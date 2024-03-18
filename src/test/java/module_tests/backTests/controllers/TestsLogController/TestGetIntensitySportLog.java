package module_tests.backTests.controllers.TestsLogController;


import com.cygans.database.controllers.LogController;
import com.cygans.database.sport_log_book.intensity.Intensity;
import com.cygans.database.sport_log_book.intensity.IntensityService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestGetIntensitySportLog {

    @Mock
    private IntensityService intensityService;

    @InjectMocks
    private LogController logController;

    @BeforeEach
    public void setUp() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "login",
                "password",
                AuthorityUtils.createAuthorityList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void testGetIntensitySportLogWithValidIntensityId() {
        Long intensityId = 1L;
        Intensity expectedIntensity = new Intensity();
        expectedIntensity.setId(intensityId);
        expectedIntensity.setType("High");

        when(intensityService.getIntensityType(intensityId)).thenReturn(expectedIntensity);

        Intensity result = logController.getIntensitySportLog(intensityId);

        assertEquals(expectedIntensity, result);
    }

    @Test
    void testGetIntensitySportLogWithInvalidIntensityId() {
        Long intensityId = null;

        Intensity result = logController.getIntensitySportLog(intensityId);

        assertEquals(null, result);
    }
}
