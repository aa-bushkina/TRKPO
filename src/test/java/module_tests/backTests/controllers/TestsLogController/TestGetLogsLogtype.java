package module_tests.backTests.controllers.TestsLogController;


import com.cygans.database.controllers.LogController;
import com.cygans.database.log_book.Log;
import com.cygans.database.log_book.logs_type.LogsTypeService;
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
class TestGetLogsLogtype {

    @Mock
    private LogsTypeService logsTypeService;

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
    void testGetLogsLogtypeWithValidLog() {
        Log log = new Log(1L, LocalDate.of(2023, 6, 12), 1L);
        log.setId(1L);

        String expectedLogType = "Type1";

        when(logsTypeService.getLogTypeById(log.getLogTypeId())).thenReturn(expectedLogType);

        String result = logController.getLogsLogtype(log);

        assertEquals(expectedLogType, result, "Не совпадает полученный список типов логов с ожидаемым");
    }

    @Test
    void testGetLogsLogtypeWithInvalidLog() {
        Log log = new Log(1L, LocalDate.of(2023, 6, 12), 1L);
        log.setId(1L);

        String result = logController.getLogsLogtype(log);

        assertEquals(null, result, "Не совпадает полученный список типов логов с ожидаемым");
    }
}
