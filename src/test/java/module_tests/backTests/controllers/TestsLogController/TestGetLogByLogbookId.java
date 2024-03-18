package module_tests.backTests.controllers.TestsLogController;


import com.cygans.database.controllers.LogController;
import com.cygans.database.log_book.Log;
import com.cygans.database.log_book.LogService;
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

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestGetLogByLogbookId {

    @Mock
    private LogService logService;

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
    void testGetLogByLogbookIdWithValidId() {
        Long logBookId = 1L;
        Log expectedLog = new Log();
        expectedLog.setId(logBookId);
        expectedLog.setDate(LocalDate.of(2023, 6, 12));

        when(logService.findLogBooksById(logBookId)).thenReturn(expectedLog);

        Log result = logController.getLogByLogbookId(logBookId);

        assertEquals(expectedLog, result);
    }

    @Test
    void testGetLogByLogbookIdWithInvalidId() {
        Long logBookId = null;

        Log result = logController.getLogByLogbookId(logBookId);

        assertEquals(null, result);
    }
}
