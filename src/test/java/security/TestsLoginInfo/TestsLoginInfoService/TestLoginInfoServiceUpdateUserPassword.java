package security.TestsLoginInfo.TestsLoginInfoService;


import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoRepository;
import com.cygans.security.db.logInfo.LoginInfoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestLoginInfoServiceUpdateUserPassword {

    @Mock
    private LoginInfoRepository mockRepository;

    @InjectMocks
    private LoginInfoService service;

    @Test
    public void testUpdateUserPassword() {
        String login = "test@example.com";
        String newPassword = "newPassword";
        LoginInfo loginInfo = new LoginInfo(login, "oldPassword", 1L, (byte) 1);
        when(mockRepository.findByLogin(login)).thenReturn(loginInfo);

        service.updateUserPassword(login, newPassword);

        verify(mockRepository, times(1)).findByLogin(login);
        verify(mockRepository, times(1)).save(loginInfo);
        assertTrue(new BCryptPasswordEncoder().matches(newPassword, loginInfo.getPassword()), "Пароли не совпали");

    }


    @Test
    public void testUpdateUserPasswordWithNullLoginInfo() {
        String login = "nonexistent@example.com";
        String newPassword = "newPassword";
        when(mockRepository.findByLogin(login)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> service.updateUserPassword(login, newPassword));

        verify(mockRepository, times(1)).findByLogin(login);
        verify(mockRepository, never()).save(any(LoginInfo.class));
    }
}
