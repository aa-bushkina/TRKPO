package end2end.tests;

import com.cygans.Application;
import com.cygans.database.controllers.LogController;
import com.cygans.database.controllers.NotificationController;
import com.cygans.database.controllers.QuestionController;
import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.database.emotional_log_book.EmotionalLogBookRepository;
import com.cygans.database.log_book.LogRepository;
import com.cygans.database.mentor.MentorRepository;
import com.cygans.database.notifications.NotificationsRepository;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantRepository;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentorRepository;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import com.cygans.database.question.QuestionRepository;
import com.cygans.security.db.RoleEnum;
import com.cygans.security.db.authorities.AuthoritiesRepository;
import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoRepository;
import com.cygans.security.db.logInfo.LoginInfoService;
import com.vaadin.flow.server.VaadinSession;
import end2end.pages.registration.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;

import static com.codeborne.selenide.Selenide.open;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Application.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestBase {
    protected static final Logger logger = LoggerFactory.getLogger(TestBase.class);

    @Autowired
    protected ParticipantRepository participantRepository;
    @Autowired
    protected ParticipantMentorRepository participantMentorRepository;
    @Autowired
    protected MentorRepository mentorRepository;
    @Autowired
    protected AuthoritiesRepository authoritiesRepository;
    @Autowired
    protected LoginInfoRepository loginInfoRepository;
    @Autowired
    protected EmotionalLogBookRepository emotionalLogBookRepository;
    @Autowired
    protected NotificationsRepository notificationsRepository;
    @Autowired
    protected LogRepository logRepository;
    @Autowired
    protected QuestionRepository questionRepository;
    @Autowired
    protected RegistrationAndLoginController registrationAndLoginController;
    @Autowired
    protected LogController logController;
    @Autowired
    protected NotificationController notificationController;
    @Autowired
    protected ParticipantMentorService participantMentorService;
    @Autowired
    protected QuestionController questionController;
    @Mock
    protected LoginInfoService loginInfoService;
    @Mock
    protected ParticipantService participantService;
    protected static final String FIRSTNAME = "Катька";
    protected static final String LASTNAME = "Волосова";
    protected static final String LOGIN_PARTICIPANT = "elsa_participant";
    protected static final String LOGIN_MENTOR = "elsa_mentor";
    protected static final String LOGIN_SECOND_MENTOR = "elsa_mentor_2";
    protected static final String PHONE = "+79383170126";
    protected static final String GENDER = "Жен";
    protected static final LocalDate BIRTHDAY = LocalDate.now();
    protected static final String HEIGHT = "123";
    protected static final String WEIGHT = "123";
    protected static final String BREAST = "123";
    protected static final String WAIST = "123";
    protected static final String HIPS = "123";
    protected static final String PASSWORD = "Qu_ntum_42";

    @BeforeEach
    public void startDriver() {
        WebDriverManager.chromedriver().setup();
        logger.info("Переходим на страницу логина");
        open("http://localhost:8080/login");
    }

    protected LoginPage getLoginPage() {
        return new LoginPage();
    }

    private void prepareSession() {
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
        when(VaadinSession.getCurrent().getAttribute("FirstName"))
                .thenReturn(FIRSTNAME);
        when(VaadinSession.getCurrent().getAttribute("LastName"))
                .thenReturn(LASTNAME);
        when(VaadinSession.getCurrent().getAttribute("Password"))
                .thenReturn(PASSWORD);
        when(VaadinSession.getCurrent().getAttribute("Phone"))
                .thenReturn(PHONE);
        when(VaadinSession.getCurrent().getAttribute("Gender"))
                .thenReturn(GENDER);
        when(VaadinSession.getCurrent().getAttribute("Date"))
                .thenReturn(BIRTHDAY);
    }

    protected void registerParticipant() {
        prepareSession();
        logger.info("Мокируем аттрибуты сессии участника");
        when(VaadinSession.getCurrent().getAttribute("Login"))
                .thenReturn(LOGIN_PARTICIPANT);
        when(VaadinSession.getCurrent().getAttribute("Height"))
                .thenReturn(HEIGHT);
        when(VaadinSession.getCurrent().getAttribute("Weight"))
                .thenReturn(WEIGHT);
        when(VaadinSession.getCurrent().getAttribute("Breast"))
                .thenReturn(BREAST);
        when(VaadinSession.getCurrent().getAttribute("Waist"))
                .thenReturn(WAIST);
        when(VaadinSession.getCurrent().getAttribute("Hip"))
                .thenReturn(HIPS);

        logger.info("Вызываем метод регистрации участника");
        registrationAndLoginController.registrationUser(RoleEnum.PARTICIPANT);
    }

    protected void registerMentor() {
        prepareSession();
        logger.info("Мокируем аттрибуты сессии ментора");
        when(VaadinSession.getCurrent().getAttribute("Login"))
                .thenReturn(LOGIN_MENTOR);

        logger.info("Вызываем метод регистрации ментора");
        registrationAndLoginController.registrationUser(RoleEnum.MENTOR);
    }

    protected void registerSecondMentor() {
        prepareSession();
        logger.info("Мокируем аттрибуты сессии второго ментора");
        when(VaadinSession.getCurrent().getAttribute("Login"))
                .thenReturn(LOGIN_SECOND_MENTOR);

        logger.info("Вызываем метод регистрации второго ментора");
        registrationAndLoginController.registrationUser(RoleEnum.MENTOR);
    }

    protected void linkParticipantMentor() {
        logger.info("Связываем ментора и участника");
        Long participantId = participantRepository.getParticipantByLogin(LOGIN_PARTICIPANT).getId();
        Long mentorId = mentorRepository.getMentorByLogin(LOGIN_MENTOR).getId();
        participantMentorService.create(participantId, mentorId);
    }

    protected void loginParticipant() {
        when(VaadinSession.getCurrent().getAttribute("Login")).thenReturn(LOGIN_PARTICIPANT);
        registrationAndLoginController.authenticationUser(RoleEnum.PARTICIPANT);
    }

    protected void loginMentor() {
        when(VaadinSession.getCurrent().getAttribute("Login")).thenReturn(LOGIN_MENTOR);
        registrationAndLoginController.authenticationUser(RoleEnum.MENTOR);
    }

    @AfterEach
    public void tearDown() {
        logger.info("Удаляем из БД участника, если создавали");
        Long participantId = null;
        if (participantRepository.getParticipantByLogin(LOGIN_PARTICIPANT) != null) {
            participantId = participantRepository.getParticipantByLogin(LOGIN_PARTICIPANT).getId();
            participantRepository.delete(participantRepository.getParticipantByLogin(LOGIN_PARTICIPANT));
            logger.info("Удалили участника");
        }
        if (loginInfoRepository.findByLogin(LOGIN_PARTICIPANT) != null) {
            loginInfoRepository.delete(loginInfoRepository.findByLogin(LOGIN_PARTICIPANT));
            logger.info("Удалили loginInfo участника");
        }
        if (authoritiesRepository.getAuthoritiesByUsername(LOGIN_PARTICIPANT) != null) {
            authoritiesRepository.delete(authoritiesRepository.getAuthoritiesByUsername(LOGIN_PARTICIPANT));
            logger.info("Удалили authorities участника");
        }

        logger.info("Удаляем из БД ментора, если создавали");
        if (mentorRepository.getMentorByLogin(LOGIN_MENTOR) != null) {
            mentorRepository.delete(mentorRepository.getMentorByLogin(LOGIN_MENTOR));
            logger.info("Удалили ментора");
        }
        if (loginInfoRepository.findByLogin(LOGIN_MENTOR) != null) {
            loginInfoRepository.delete(loginInfoRepository.findByLogin(LOGIN_MENTOR));
            logger.info("Удалили loginInfo ментора");
        }
        if (authoritiesRepository.getAuthoritiesByUsername(LOGIN_MENTOR) != null) {
            authoritiesRepository.delete(authoritiesRepository.getAuthoritiesByUsername(LOGIN_MENTOR));
            logger.info("Удалили authorities ментора");
        }

        logger.info("Удаляем из БД второго ментора, если создавали");
        if (mentorRepository.getMentorByLogin(LOGIN_SECOND_MENTOR) != null) {
            mentorRepository.delete(mentorRepository.getMentorByLogin(LOGIN_SECOND_MENTOR));
            logger.info("Удалили ментора");
        }
        if (loginInfoRepository.findByLogin(LOGIN_SECOND_MENTOR) != null) {
            loginInfoRepository.delete(loginInfoRepository.findByLogin(LOGIN_SECOND_MENTOR));
            logger.info("Удалили loginInfo ментора");
        }
        if (authoritiesRepository.getAuthoritiesByUsername(LOGIN_SECOND_MENTOR) != null) {
            authoritiesRepository.delete(authoritiesRepository.getAuthoritiesByUsername(LOGIN_SECOND_MENTOR));
            logger.info("Удалили authorities ментора");
        }

        logger.info("Удаляем из БД пару участник-ментор, если создавали");
        if (participantId != null && participantMentorRepository.findByParticipantId(participantId) != null) {
            participantMentorRepository.delete(participantMentorRepository.findByParticipantId(participantId));
            logger.info("Удалили связь участника и ментора");
        }
    }
}
