package end2end.tests;

import end2end.pages.participant.RegistrationWithParametersPage;
import end2end.pages.registration.SecondRegistrationPage;
import end2end.pages.registration.ThirdRegistrationPage;
import end2end.pages.utils.Sex;
import org.junit.jupiter.api.Test;

/**
 * Тест проверяет регистрацию и залогин под участником
 */
public class TestEnd2EndRegistrationParticipant extends TestBase {
    private static final String FIRST_NAME = "Эльза";
    private static final String SECOND_NAME = "Белая";
    private static final String PASSWORD = "123456Aa_";
    private static final String PHONE = "+79458697969";
    private static final String DATE = "10.03.1994";
    private static final String HEIGHT = "170";
    public static final String WEIGHT = "150";
    public static final String WIDTH_BREAST = "120";
    public static final String WIDTH_WAIST = "100";
    public static final String WIDTH_HIPS = "120";

    @Test
    public void registrationParticipantTest() {
        logger.info("Тест проверяет регистрацию и залогин под участником");

        logger.info("Переходим к выбору типа юзера и выбираем участника");
        SecondRegistrationPage secondRegistrationPage = getLoginPage().goToRegistration().clickParticipant();

        logger.info("Заполняем информацию на странице регистрации (имя, фамилия, логин и пароль)");
        ThirdRegistrationPage thirdRegistrationPage = secondRegistrationPage.typeName(FIRST_NAME)
                .typeSecondName(SECOND_NAME)
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
                .typeBreast(WIDTH_BREAST)
                .typeHips(WIDTH_HIPS)
                .typeWaist(WIDTH_WAIST)
                .endRegistration();

        logger.info("Тест прошел успешно");
    }
}
