package end2end;

import com.cygans.Application;
import end2end.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static com.codeborne.selenide.Selenide.open;

@SpringBootTest(classes = Application.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestBase {

    protected static final Logger logger = LoggerFactory.getLogger(TestBase.class);

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
