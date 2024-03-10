package end2end.tests;

import com.cygans.Application;
import com.cygans.database.mentor.MentorRepository;
import com.cygans.database.participant.ParticipantRepository;
import com.cygans.security.db.authorities.AuthoritiesRepository;
import com.cygans.security.db.logInfo.LoginInfoRepository;
import end2end.pages.registration.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.codeborne.selenide.Selenide.open;

@SpringBootTest(classes = Application.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestBase {

    protected static final Logger logger = LoggerFactory.getLogger(TestBase.class);

    @Autowired
    protected ParticipantRepository participantRepository;
    @Autowired
    protected MentorRepository mentorRepository;
    @Autowired
    protected AuthoritiesRepository authoritiesRepository;
    @Autowired
    protected LoginInfoRepository loginInfoRepository;

    @BeforeEach
    public void startDriver() {
        WebDriverManager.chromedriver().setup();
        logger.info("Переходим на страницу логина");
        open("http://localhost:8080/login");
    }

    protected LoginPage getLoginPage() {
        return new LoginPage();
    }

}
