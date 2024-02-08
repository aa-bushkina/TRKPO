package security.TestsLoginInfo.TestsLoginInfoService;

import com.cygans.security.db.authorities.AuthoritiesRepository;
import com.cygans.security.db.authorities.AuthoritiesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestAuthoritiesServiceGetRepository {

    @InjectMocks
    private AuthoritiesService service;

    @Test
    public void testGetRepository() {
        AuthoritiesRepository expectedRepo = Mockito.mock(AuthoritiesRepository.class);

        AuthoritiesService service = new AuthoritiesService(expectedRepo);
        AuthoritiesRepository actualRepo = service.getRepository();

        assertEquals(expectedRepo, actualRepo);
    }

    @Test
    public void testGetRepositoryEquals() {
        AuthoritiesRepository repo1 = service.getRepository();
        AuthoritiesRepository repo2 = service.getRepository();

        assertEquals(repo1, repo2);
    }
}

