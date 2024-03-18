package module_tests.backTests.controllers.TestsLogController;

import com.cygans.database.controllers.LogController;
import com.cygans.database.log_book.logs_type.LogBookType;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestGetLogTypeIdByName {

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
    void testGetLogTypeIdByNameWithValidType() {
        LogBookType typeNameEmotional = LogBookType.EMOTIONAL;
        LogBookType typeNameSport = LogBookType.SPORT;
        LogBookType typeNameEating = LogBookType.EATING;

        when(logsTypeService.getLogTypeId(typeNameEmotional.getText())).thenReturn(0L);
        when(logsTypeService.getLogTypeId(typeNameSport.getText())).thenReturn(1L);
        when(logsTypeService.getLogTypeId(typeNameEating.getText())).thenReturn(2L);

        Long resultEmotional = logController.getLogTypeIdByName(typeNameEmotional);
        assertEquals(0L, resultEmotional, "Не совпадает id типа " + typeNameEmotional);

        Long resultSport = logController.getLogTypeIdByName(typeNameSport);
        assertEquals(1L, resultSport, "Не совпадает id типа " + typeNameSport);

        Long resultEating = logController.getLogTypeIdByName(typeNameEating);
        assertEquals(2L, resultEating, "Не совпадает id типа " + typeNameEating);
    }
}
