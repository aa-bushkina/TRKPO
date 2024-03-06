package module.security.TestsLoginInfo.TestsLoginInfoService;

import com.cygans.security.db.logInfo.LoginInfoRepository;
import com.cygans.security.db.logInfo.LoginInfoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestLoginInfoServiceGetRepository {

    @Mock
    private LoginInfoRepository mockRepository;

    @InjectMocks
    private LoginInfoService service;

    @Test
    public void testGetRepository() {
        // Arrange
        LoginInfoService loginInfoService = new LoginInfoService(mockRepository);

        // Act
        LoginInfoRepository actualRepo = loginInfoService.getRepository();

        // Assert
        assertEquals(mockRepository, actualRepo);
    }

    @Test
    public void testGetRepositoryEquals() {
        // Act
        LoginInfoRepository repo1 = service.getRepository();
        LoginInfoRepository repo2 = service.getRepository();

        // Assert
        assertEquals(repo1, repo2);
    }
}
