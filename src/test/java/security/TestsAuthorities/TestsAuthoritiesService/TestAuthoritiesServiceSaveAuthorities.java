package security.TestsAuthorities.TestsAuthoritiesService;

import com.cygans.security.db.authorities.Authorities;
import com.cygans.security.db.authorities.AuthoritiesRepository;
import com.cygans.security.db.authorities.AuthoritiesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestAuthoritiesServiceSaveAuthorities {

    @Mock
    private AuthoritiesRepository repository;

    @InjectMocks
    private AuthoritiesService service;


    @Test
    public void testSaveAuthorities() {
        Authorities authorities = new Authorities("user123", "ROLE_USER");

        service.saveAuthorities(authorities);

        verify(repository).save(authorities);
    }

    @Test
    public void testSaveAuthoritiesTwice() {
        Authorities authorities = new Authorities("user123", "ROLE_USER");

        service.saveAuthorities(authorities);
        service.saveAuthorities(authorities);

        verify(repository, times(2)).save(authorities);
    }
}

