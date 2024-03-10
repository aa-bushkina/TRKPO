package end2end.tests;

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
    private static final String LOGIN = "elsa";
    private static final String PASSWORD = "123456Aa_";
    private static final String PHONE = "+79458697969";
    private static final String DATE = "10.03.1994";

    @Test
    public void registrationParticipantTest() {
        logger.info("Тест проверяет регистрацию и залогин под ментором");

        logger.info("Переходим к выбору типа юзера и выбираем ментора");
        SecondRegistrationPage secondRegistrationPage = getLoginPage().goToRegistration().clickMentor();

        logger.info("Заполняем информацию на странице регистрации (имя, фамилия, логин и пароль)");
        ThirdRegistrationPage thirdRegistrationPage = secondRegistrationPage.typeName(FIRST_NAME)
                .typeSecondName(SECOND_NAME)
                .typeLogin(LOGIN)
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
    public void deleteUser() {
        logger.info("Удаляем записи о пользователе в БД");
        if (mentorRepository.getMentorByLogin(LOGIN) != null) {
            mentorRepository.delete(mentorRepository.getMentorByLogin(LOGIN));
        }
        if (loginInfoRepository.findByLogin(LOGIN) != null) {
            loginInfoRepository.delete(loginInfoRepository.findByLogin(LOGIN));
        }
        if (authoritiesRepository.getAuthoritiesByUsername(LOGIN) != null) {
            authoritiesRepository.delete(authoritiesRepository.getAuthoritiesByUsername(LOGIN));
        }
    }

}
