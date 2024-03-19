package end2end.tests;

import end2end.pages.mentor.StartMentorPage;
import end2end.pages.participant.QuestionsParticipantPage;
import end2end.pages.registration.SecondRegistrationPage;
import end2end.pages.registration.ThirdRegistrationPage;
import end2end.pages.utils.Sex;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * Тест проверяет регистрацию и залогин под ментором
 */
public class TestEnd2EndRegistrationMentor extends TestBase {

    private static final String FIRST_NAME = "Эльза";
    private static final String SECOND_NAME = "Белая";
    private static final String PASSWORD = "123456Aa_";
    private static final String PHONE = "+79458697969";
    private static final String DATE = "10.03.1994";

    @Test
    public void testEnd2EndRegistrationMentor() {
        logger.info("Тест проверяет регистрацию и залогин под ментором");

        logger.info("Переходим к выбору типа юзера и выбираем ментора");
        SecondRegistrationPage secondRegistrationPage = getLoginPage().goToRegistration().clickMentor();

        logger.info("Заполняем информацию на странице регистрации (имя, фамилия, логин и пароль)");
        ThirdRegistrationPage thirdRegistrationPage = secondRegistrationPage.typeName(FIRST_NAME)
                .typeSecondName(SECOND_NAME)
                .typeLogin(LOGIN_MENTOR)
                .typePassword(PASSWORD)
                .doContinue();

        logger.info("Заполняем следующую страницу регистрации (пол, дата рождения и номер телефона)");
        thirdRegistrationPage.choiceSex(Sex.WOMEN)
                .typePhone(PHONE)
                .choiceDate(DATE)
                .endRegistration();

        logger.info("Тест прошел успешно");
    }

    @AfterEach
    public void logout() {
        logoutMentor();
    }
}
