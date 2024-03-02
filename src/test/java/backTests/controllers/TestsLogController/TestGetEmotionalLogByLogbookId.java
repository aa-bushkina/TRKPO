package backTests.controllers.TestsLogController;

import com.cygans.database.controllers.LogController;
import com.cygans.database.emotional_log_book.EmotionalLogBook;
import com.cygans.database.emotional_log_book.EmotionalLogBookService;
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
class TestGetEmotionalLogByLogbookId {

    @Mock
    private EmotionalLogBookService emotionalLogBookService;

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
    void testGetEmotionalLogByLogbookIdWithValidId() {
        Long logBookId = 1L;
        EmotionalLogBook expectedEmotionalLog = new EmotionalLogBook();
        expectedEmotionalLog.setId(logBookId);
        expectedEmotionalLog.setDescription("Feeling happy");

        when(emotionalLogBookService.findByLogBookId(logBookId)).thenReturn(expectedEmotionalLog);

        EmotionalLogBook result = logController.getEmotionalLogByLogbookId(logBookId);

        assertEquals(expectedEmotionalLog, result);
    }

    @Test
    void testGetEmotionalLogByLogbookIdWithInvalidId() {
        Long logBookId = null;

        EmotionalLogBook result = logController.getEmotionalLogByLogbookId(logBookId);

        assertEquals(null, result);
    }
}
