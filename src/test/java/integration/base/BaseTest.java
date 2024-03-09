package integration.base;

import com.cygans.Application;
import com.cygans.database.controllers.*;
import com.cygans.database.eating_log_book.EatingLogBookRepository;
import com.cygans.database.eating_log_book.meal.MealService;
import com.cygans.database.emotional_log_book.EmotionalLogBookRepository;
import com.cygans.database.log_book.LogService;
import com.cygans.database.log_book.logs_type.LogsTypeService;
import com.cygans.database.mentor.MentorRepository;
import com.cygans.database.notifications.NotificationsRepository;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantRepository;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentorRepository;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import com.cygans.database.question.QuestionRepository;
import com.cygans.database.sport_log_book.SportLogBookRepository;
import com.cygans.database.sport_log_book.intensity.IntensityService;
import com.cygans.security.db.RoleEnum;
import com.cygans.security.db.authorities.AuthoritiesRepository;
import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoRepository;
import com.cygans.security.db.logInfo.LoginInfoService;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.mail.Part;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Базовый класс интеграционного теста в залогине
 */
@SpringBootTest(classes = Application.class)
@ExtendWith(MockitoExtension.class)
public class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @Autowired
    protected LogController logController;
    @Autowired
    protected RegistrationAndLoginController registrationAndLoginController;
    @Autowired
    protected SettingsController settingsController;
    @Autowired
    protected ParticipantMentorService participantMentorService;
    @Autowired
    protected NotificationController notificationController;
    @Autowired
    protected ParticipantAndMentorController participantAndMentorController;
    @Autowired
    protected QuestionController questionController;

    @Autowired
    protected ParticipantRepository participantRepository;
    @Autowired
    protected MentorRepository mentorRepository;
    @Autowired
    protected AuthoritiesRepository authoritiesRepository;
    @Autowired
    protected LoginInfoRepository loginInfoRepository;
    @Autowired
    protected ParticipantMentorRepository participantMentorRepository;
    @Autowired
    protected QuestionRepository questionRepository;
    @Autowired
    protected NotificationsRepository notificationsRepository;
    @Autowired
    protected EatingLogBookRepository eatingLogBookRepository;
    @Autowired
    protected SportLogBookRepository sportLogBookRepository;
    @Autowired
    protected EmotionalLogBookRepository emotionalLogBookRepository;

    @Autowired
    protected MealService mealService;
    @Autowired
    protected IntensityService intensityService;

    @Mock
    protected LoginInfoService loginInfoService;
    @Mock
    protected LogsTypeService logsTypeService;
    @Mock
    protected ParticipantService participantService;
    protected static final String FIRSTNAME = "Катька";
    protected static final String LASTNAME = "Волосова";
    protected static final String LOGIN_PARTICIPANT = UUID.randomUUID().toString();
    protected static final String LOGIN_MENTOR = UUID.randomUUID().toString();
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

    protected void registerParticipant() {
        logger.info("Мокируем аттрибуты сессии участника");
        VaadinSession vaadinSessionMock = mock(VaadinSession.class);
        VaadinSession.setCurrent(vaadinSessionMock);

        when(VaadinSession.getCurrent().getAttribute("FirstName"))
                .thenReturn(FIRSTNAME);
        when(VaadinSession.getCurrent().getAttribute("LastName"))
                .thenReturn(LASTNAME);
        when(VaadinSession.getCurrent().getAttribute("Login"))
                .thenReturn(LOGIN_PARTICIPANT);
        when(VaadinSession.getCurrent().getAttribute("Password"))
                .thenReturn(PASSWORD);
        when(VaadinSession.getCurrent().getAttribute("Phone"))
                .thenReturn(PHONE);
        when(VaadinSession.getCurrent().getAttribute("Gender"))
                .thenReturn(GENDER);
        when(VaadinSession.getCurrent().getAttribute("Date"))
                .thenReturn(BIRTHDAY);
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
        logger.info("Мокируем аттрибуты сессии ментора");
        VaadinSession vaadinSessionMock = mock(VaadinSession.class);
        VaadinSession.setCurrent(vaadinSessionMock);

        when(VaadinSession.getCurrent().getAttribute("FirstName"))
                .thenReturn(FIRSTNAME);
        when(VaadinSession.getCurrent().getAttribute("LastName"))
                .thenReturn(LASTNAME);
        when(VaadinSession.getCurrent().getAttribute("Login"))
                .thenReturn(LOGIN_MENTOR);
        when(VaadinSession.getCurrent().getAttribute("Password"))
                .thenReturn(PASSWORD);
        when(VaadinSession.getCurrent().getAttribute("Phone"))
                .thenReturn(PHONE);
        when(VaadinSession.getCurrent().getAttribute("Gender"))
                .thenReturn(GENDER);
        when(VaadinSession.getCurrent().getAttribute("Date"))
                .thenReturn(BIRTHDAY);

        logger.info("Вызываем метод регистрации ментора");
        registrationAndLoginController.registrationUser(RoleEnum.MENTOR);
    }

    protected void loginParticipant() {
        when(VaadinSession.getCurrent().getAttribute("Login")).thenReturn(LOGIN_PARTICIPANT);
        registrationAndLoginController.authenticationUser(RoleEnum.PARTICIPANT);
    }

    protected void loginMentor() {
        when(VaadinSession.getCurrent().getAttribute("Login")).thenReturn(LOGIN_MENTOR);
        registrationAndLoginController.authenticationUser(RoleEnum.MENTOR);
    }

    protected void linkParticipantMentor(Long participantId, Long mentorId) {
        logger.info("Связываем ментора и участника");
        participantMentorService.create(participantId, mentorId);
    }

    @AfterEach
    public void tearDown() {
        logger.info("Удаляем из БД участника");
        Long participantId = null;
        if (participantRepository.getParticipantByLogin(LOGIN_PARTICIPANT) != null) {
            participantId = participantRepository.getParticipantByLogin(LOGIN_PARTICIPANT).getId();
            participantRepository.delete(participantRepository.getParticipantByLogin(LOGIN_PARTICIPANT));
        }
        if (loginInfoRepository.findByLogin(LOGIN_PARTICIPANT) != null) {
            loginInfoRepository.delete(loginInfoRepository.findByLogin(LOGIN_PARTICIPANT));
        }
        if (authoritiesRepository.getAuthoritiesByUsername(LOGIN_PARTICIPANT) != null) {
            authoritiesRepository.delete(authoritiesRepository.getAuthoritiesByUsername(LOGIN_PARTICIPANT));
        }

        logger.info("Удаляем из БД ментора");
        if (mentorRepository.getMentorByLogin(LOGIN_MENTOR) != null) {
            mentorRepository.delete(mentorRepository.getMentorByLogin(LOGIN_MENTOR));
        }
        if (loginInfoRepository.findByLogin(LOGIN_MENTOR) != null) {
            loginInfoRepository.delete(loginInfoRepository.findByLogin(LOGIN_MENTOR));
        }
        if (authoritiesRepository.getAuthoritiesByUsername(LOGIN_MENTOR) != null) {
            authoritiesRepository.delete(authoritiesRepository.getAuthoritiesByUsername(LOGIN_MENTOR));
        }

        logger.info("Удаляем из БД пару участник-ментор");
        if (participantId != null && participantMentorRepository.findByParticipantId(participantId) != null) {
            participantMentorRepository.delete(participantMentorRepository.findByParticipantId(participantId));
        }
    }
}
