package module_tests.security.TestsLoginInfo.TestsLoginInfoService;

import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoRepository;
import com.cygans.security.db.logInfo.LoginInfoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestLoginInfoServiceFindByLogin {

    @Mock
    private LoginInfoRepository mockRepository;

    @InjectMocks
    private LoginInfoService service;

    @Test
    public void testFindByLoginFound() {
        String login = "test@example.com";
        LoginInfo expectedLoginInfo = new LoginInfo(login, "password", 1L, (byte) 1);
        when(mockRepository.findByLogin(login)).thenReturn(expectedLoginInfo);

        LoginInfo actualLoginInfo = service.findByLogin(login);

        assertEquals(expectedLoginInfo, actualLoginInfo);
    }

    @Test
    public void testFindByLoginNotFound() {
        String login = "nonexistent@example.com";
        when(mockRepository.findByLogin(login)).thenReturn(null);

        LoginInfo actualLoginInfo = service.findByLogin(login);

        assertNull(actualLoginInfo);
    }
}
