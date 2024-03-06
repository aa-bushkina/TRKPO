package module.security.TestsAuthorities.TestsAuthoritiesService;

import com.cygans.security.db.authorities.Authorities;
import com.cygans.security.db.authorities.AuthoritiesRepository;
import com.cygans.security.db.authorities.AuthoritiesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestAuthoritiesServiceSaveAuthority {

    @Mock
    private AuthoritiesRepository authoritiesRepository;

    @InjectMocks
    private AuthoritiesService authoritiesService;

    @Test
    void testSaveAuthorities() {
        Authorities authorities = new Authorities();

        authoritiesService.saveAuthorities(authorities);

        verify(authoritiesRepository).save(authorities);
    }
}


