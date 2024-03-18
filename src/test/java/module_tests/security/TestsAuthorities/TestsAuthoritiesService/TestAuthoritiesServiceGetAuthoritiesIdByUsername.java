package module_tests.security.TestsAuthorities.TestsAuthoritiesService;


import com.cygans.security.db.authorities.Authorities;
import com.cygans.security.db.authorities.AuthoritiesRepository;
import com.cygans.security.db.authorities.AuthoritiesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestAuthoritiesServiceGetAuthoritiesIdByUsername {

    @Mock
    private AuthoritiesRepository repository;

    @InjectMocks
    private AuthoritiesService service;


    @Test
    public void testGetAuthoritiesIdByUsername() {
        String username = "user123";
        Long expectedId = 123L;
        Authorities authorities = new Authorities(username, "ROLE_USER");
        authorities.setId(expectedId);
        when(repository.getAuthoritiesByUsername(username)).thenReturn(authorities);

        Long id = service.getAuthoritiesIdByUsername(username);

        assertEquals(expectedId, id);
    }


    @Test
    public void testGetAuthoritiesIdByUsernameEmptyUsername() {
        String username = "";
        assertThrows(NullPointerException.class, () -> {
            service.getAuthoritiesIdByUsername(username);
        });
    }
}

