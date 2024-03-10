package end2end.tests;

import end2end.TestBase;
import end2end.pages.SecondRegistrationPage;
import org.junit.jupiter.api.Test;

/**
 * Тест проверяет регистрацию и залогин под участником
 */
public class RegistrationAndLoginTest extends TestBase {

    @Test
    public void registrationAndLoginTest() {
        logger.info("Тест проверяет регистрацию и залогин под участником");

        logger.info("Переходим к выбору типа юзера и выбираем участника");
        SecondRegistrationPage secondRegistrationPage = getLoginPage().goToRegistration().clickParticipant();

        logger.info("Тест прошел успешно");
    }

}
