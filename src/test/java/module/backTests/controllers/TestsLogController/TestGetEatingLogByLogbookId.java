package module.backTests.controllers.TestsLogController;

import com.cygans.database.controllers.LogController;
import com.cygans.database.eating_log_book.EatingLogBook;
import com.cygans.database.eating_log_book.EatingLogBookService;
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
class TestGetEatingLogByLogbookId {

    @Mock
    private EatingLogBookService eatingLogBookService;

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
    void testGetEatingLogByLogbookIdWithValidId() {
        Long logBookId = 1L;
        EatingLogBook expectedEatingLog = new EatingLogBook();
        expectedEatingLog.setId(logBookId);
        expectedEatingLog.setDescription("Breakfast");

        when(eatingLogBookService.findByLogBookId(logBookId)).thenReturn(expectedEatingLog);

        EatingLogBook result = logController.getEatingLogByLogbookId(logBookId);

        assertEquals(expectedEatingLog, result);
    }

    @Test
    void testGetEatingLogByLogbookIdWithInvalidId() {
        Long logBookId = null;

        EatingLogBook result = logController.getEatingLogByLogbookId(logBookId);

        assertEquals(null, result);
    }
}

