package end2end.tests;

import end2end.pages.participant.RegistrationWithParametersPage;
import end2end.pages.registration.SecondRegistrationPage;
import end2end.pages.registration.ThirdRegistrationPage;
import end2end.pages.utils.Sex;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест проверяет ограничения на поля параметров тела при регистрации участника
 */
public class TestEnd2EndCheckRestrictionsRegistrationParticipant extends TestBase {

    private static final String NORMAL_HEIGHT = "100";
    private static final String MORE_HEIGHT = "251";
    private static final String LESS_HEIGHT = "66";
    private static final String NORMAL_WEIGHT = "100";
    private static final String MORE_WEIGHT = "601";
    private static final String LESS_WEIGHT = "1";
    private static final String NORMAL_BREAST = "100";
    private static final String MORE_BREAST = "251";
    private static final String LESS_BREAST =  "29";
    private static final String NORMAL_HIPS = "100";
    private static final String MORE_HIPS = "251";
    private static final String LESS_HIPS = "29";
    private static final String NORMAL_WAIST = "100";
    private static final String MORE_WAIST = "251";
    private static final String LESS_WAIST = "29";
    private static final String DATE = "10.03.1994";

    @Test
    public void testEnd2EndCheckRestrictionsRegistrationParticipant() {
        logger.info("Тест проверяет ограничения на поля параметров тела при регистрации участника");

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

        logger.info("Проверяем ввод различного роста");
        registrationWithParametersPage = registrationWithParametersPage.typeHeight(LESS_HEIGHT)
                .clickWeight();
        assertTrue(registrationWithParametersPage.heightIsInvalid(),
                "Поле ввода роста не стало невалидным от значение меньше границы");
        refresh();
        registrationWithParametersPage = registrationWithParametersPage.typeHeight(NORMAL_HEIGHT)
                .clickWeight();
        assertFalse(registrationWithParametersPage.heightIsInvalid(),
                "Поле ввода роста не стало валидным");
        refresh();
        registrationWithParametersPage = registrationWithParametersPage.typeHeight(MORE_HEIGHT)
                .clickWeight();
        assertTrue(registrationWithParametersPage.heightIsInvalid(),
                "Поле ввода роста не стало невалидным от значение больше границы");
        refresh();
        registrationWithParametersPage = registrationWithParametersPage.typeHeight(NORMAL_HEIGHT)
                .clickWeight();
        assertFalse(registrationWithParametersPage.heightIsInvalid(),
                "Поле ввода роста не стало валидным");
        refresh();

        logger.info("Проверяем ввод различного обхвата груди");
        registrationWithParametersPage = registrationWithParametersPage.typeBreast(LESS_BREAST)
                .clickWeight();
        assertTrue(registrationWithParametersPage.breastIsInvalid(),
                "Поле ввода обхвата груди не стало невалидным от значение меньше границы");
        refresh();
        registrationWithParametersPage = registrationWithParametersPage.typeBreast(NORMAL_BREAST)
                .clickWeight();
        assertFalse(registrationWithParametersPage.breastIsInvalid(),
                "Поле ввода обхвата груди не стало валидным");
        refresh();
        registrationWithParametersPage = registrationWithParametersPage.typeBreast(MORE_BREAST)
                .clickWeight();
        assertTrue(registrationWithParametersPage.breastIsInvalid(),
                "Поле ввода обхвата груди не стало невалидным от значение больше границы");
        refresh();
        registrationWithParametersPage = registrationWithParametersPage.typeBreast(NORMAL_BREAST)
                .clickWeight();
        assertFalse(registrationWithParametersPage.breastIsInvalid(),
                "Поле ввода обхвата груди не стало валидным");
        refresh();

        logger.info("Проверяем ввод различного обхвата бедер");
        registrationWithParametersPage = registrationWithParametersPage.typeHips(LESS_HIPS)
                .clickWeight();
        assertTrue(registrationWithParametersPage.hipsIsInvalid(),
                "Поле ввода обхвата бедер не стало невалидным от значение меньше границы");
        refresh();
        registrationWithParametersPage = registrationWithParametersPage.typeHips(NORMAL_HIPS)
                .clickWeight();
        assertFalse(registrationWithParametersPage.hipsIsInvalid(),
                "Поле ввода обхвата бедер не стало валидным");
        refresh();
        registrationWithParametersPage = registrationWithParametersPage.typeHips(MORE_HIPS)
                .clickWeight();
        assertTrue(registrationWithParametersPage.hipsIsInvalid(),
                "Поле ввода обхвата бедер не стало невалидным от значение больше границы");
        refresh();
        registrationWithParametersPage = registrationWithParametersPage.typeHips(NORMAL_HIPS)
                .clickWeight();
        assertFalse(registrationWithParametersPage.hipsIsInvalid(),
                "Поле ввода обхвата бедер не стало валидным");
        refresh();

        logger.info("Проверяем ввод различного обхвата талии");
        registrationWithParametersPage = registrationWithParametersPage.typeWaist(LESS_WAIST)
                .clickWeight();
        assertTrue(registrationWithParametersPage.waistIsInvalid(),
                "Поле ввода обхвата талии не стало невалидным от значение меньше границы");
        refresh();
        registrationWithParametersPage = registrationWithParametersPage.typeWaist(NORMAL_WAIST)
                .clickWeight();
        assertFalse(registrationWithParametersPage.waistIsInvalid(),
                "Поле ввода обхвата талии не стало валидным");
        refresh();
        registrationWithParametersPage = registrationWithParametersPage.typeWaist(MORE_WAIST)
                .clickWeight();
        assertTrue(registrationWithParametersPage.waistIsInvalid(),
                "Поле ввода обхвата талии не стало невалидным от значение больше границы");
        refresh();
        registrationWithParametersPage = registrationWithParametersPage.typeWaist(NORMAL_WAIST)
                .clickWeight();
        assertFalse(registrationWithParametersPage.waistIsInvalid(),
                "Поле ввода обхвата талии не стало валидным");
        refresh();

        logger.info("Проверяем ввод различного веса");
        registrationWithParametersPage = registrationWithParametersPage.typeWeight(LESS_WEIGHT)
                .clickHeight();
        assertTrue(registrationWithParametersPage.weightIsInvalid(),
                "Поле ввода веса не стало невалидным от значение меньше границы");
        refresh();
        registrationWithParametersPage = registrationWithParametersPage.typeWeight(NORMAL_WEIGHT)
                .clickHeight();
        assertFalse(registrationWithParametersPage.weightIsInvalid(),
                "Поле ввода веса не стало валидным");
        refresh();
        registrationWithParametersPage = registrationWithParametersPage.typeWeight(MORE_WEIGHT)
                .clickHeight();
        assertTrue(registrationWithParametersPage.weightIsInvalid(),
                "Поле ввода веса не стало невалидным от значение больше границы");
        refresh();
        registrationWithParametersPage = registrationWithParametersPage.typeWeight(NORMAL_WEIGHT)
                .clickHeight();
        assertFalse(registrationWithParametersPage.weightIsInvalid(),
                "Поле ввода веса не стало валидным");
        refresh();

        logger.info("Тест прошел успешно");
    }

}
