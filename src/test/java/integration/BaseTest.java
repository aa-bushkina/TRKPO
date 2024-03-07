package integration;

import com.cygans.database.log_book.LogService;
import com.cygans.database.log_book.logs_type.LogsTypeService;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoService;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Базовый класс интеграционного теста в залогине
 */
@ExtendWith(MockitoExtension.class)
public class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    @Mock
    protected LogService logService;
    @Mock
    protected LoginInfoService loginInfoService;
    @Mock
    protected LogsTypeService logsTypeService;
    @Mock
    protected ParticipantService participantService;

    @BeforeEach
    public void setUp() {
        logger.info("Мокируем данные для сессии залогина");
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "login",
                "password",
                AuthorityUtils.createAuthorityList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        VaadinSession vaadinSessionMock = mock(VaadinSession.class);
        VaadinSession.setCurrent(vaadinSessionMock);
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setId(1L);
        Participant participant = new Participant();
        when(loginInfoService.findByLogin(any()))
                .thenReturn(loginInfo);
        when(participantService.getParticipantByLoginInfoId(any()))
                .thenReturn(participant);
    }
}
