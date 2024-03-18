package module_tests.security.TestsLoginInfo.TestsLoginInfoService;


import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoRepository;
import com.cygans.security.db.logInfo.LoginInfoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestLoginInfoServiceSaveLoginInfo {

    @Mock
    private LoginInfoRepository mockRepository;

    @InjectMocks
    private LoginInfoService service;

    @Test
    public void testSaveLoginInfo() {
        LoginInfo loginInfo = new LoginInfo("test@example.com", "password", 1L, (byte) 1);
        service.saveLoginInfo(loginInfo);

        verify(mockRepository, times(1)).save(loginInfo);
    }
}

