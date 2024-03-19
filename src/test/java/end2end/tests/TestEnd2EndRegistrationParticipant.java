package end2end.tests;

import end2end.pages.mentor.StartMentorPage;
import end2end.pages.participant.RegistrationWithParametersPage;
import end2end.pages.participant.StartParticipantPage;
import end2end.pages.registration.SecondRegistrationPage;
import end2end.pages.registration.ThirdRegistrationPage;
import end2end.pages.utils.Sex;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * Тест проверяет регистрацию и залогин под участником
 */
public class TestEnd2EndRegistrationParticipant extends TestBase {
    private static final String DATE = "10.03.1994";

    @Test
    public void testEnd2EndRegistrationParticipant() {
        logger.info("Тест проверяет регистрацию и залогин под участником");

        logger.info("Переходим к выбору типа юзера и выбираем участника");
        SecondRegistrationPage secondRegistrationPage = getLoginPage().goToRegistration().clickParticipant();

        logger.info("Заполняем информацию на странице регистрации (имя, фамилия, логин и пароль)");
        ThirdRegistrationPage thirdRegistrationPage = secondRegistrationPage.typeName(FIRSTNAME)
                .typeSecondName(LASTNAME)
                .typeLogin(LOGIN_PARTICIPANT)
                .typePassword(PASSWORD)
                .doContinue();

        logger.info("Заполняем следующую страницу регистрации (пол, дата рождения и номер телефона)");
        RegistrationWithParametersPage registrationWithParametersPage = thirdRegistrationPage.choiceSex(Sex.WOMEN)
                .typePhone(PHONE)
                .choiceDate(DATE)
                .doContinue();

        logger.info("Вводим параметры участника");
        registrationWithParametersPage.typeHeight(HEIGHT)
                .typeWeight(WEIGHT)
                .typeBreast(BREAST)
                .typeHips(HIPS)
                .typeWaist(WAIST)
                .endRegistration();

        logger.info("Тест прошел успешно");
    }

    @AfterEach
    public void logout() {
        logoutParticipant();
    }
}
