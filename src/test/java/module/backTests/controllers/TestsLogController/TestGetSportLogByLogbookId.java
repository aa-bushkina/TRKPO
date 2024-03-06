package module.backTests.controllers.TestsLogController;


import com.cygans.database.controllers.LogController;
import com.cygans.database.sport_log_book.SportLogBook;
import com.cygans.database.sport_log_book.SportLogBookService;
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
class TestGetSportLogByLogbookId {

    @Mock
    private SportLogBookService sportLogBookService;

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
    void testGetSportLogByLogbookIdWithValidId() {
        Long logBookId = 1L;
        SportLogBook expectedSportLog = new SportLogBook();
        expectedSportLog.setId(logBookId);
        expectedSportLog.setActivity("Running");

        when(sportLogBookService.findByLogBookId(logBookId)).thenReturn(expectedSportLog);

        SportLogBook result = logController.getSportLogByLogbookId(logBookId);

        assertEquals(expectedSportLog, result);
    }

    @Test
    void testGetSportLogByLogbookIdWithInvalidId() {
        Long logBookId = null;

        SportLogBook result = logController.getSportLogByLogbookId(logBookId);

        assertEquals(null, result);
    }
}
