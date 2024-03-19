package end2end.tests;

import end2end.pages.participant.SettingsParticipantPage;
import end2end.pages.utils.Sex;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест проверяет регистрацию и залогин под участником
 */
public class TestEnd2EndSettingsParticipant extends TestBase {
    protected static final String NEW_FIRSTNAME = "Мария";
    protected static final String NEW_LASTNAME = "Кротова";
    protected static final String NEW_PHONE = "+79383177777";
    protected static final String NEW_BIRTHDAY = "10.03.1992";
    protected static final String NEW_HEIGHT = "100";
    protected static final String NEW_WEIGHT = "101";
    protected static final String NEW_BREAST = "102";
    protected static final String NEW_WAIST = "103";
    protected static final String NEW_HIPS = "104";

    @BeforeEach
    public void setUp() {
        logger.info("Регистрируем участника");
        registerParticipant();
    }

    @Test
    public void testEnd2EndSettingsParticipant() {
        logger.info("Тест проверяет регистрацию и залогин под участником");

        logger.info("Логинимся участником и меняем в настройках пароль");
        SettingsParticipantPage settingsParticipantPage = getLoginPage()
                .login(LOGIN_PARTICIPANT, PASSWORD)
                .andReturnStartParticipantPage()
                .goToSettings()
                .changeInfo()
                .typeBreast(NEW_BREAST)
                .typeHeight(NEW_HEIGHT)
                .typeWaist(NEW_WAIST)
                .typeWeight(NEW_WEIGHT)
                .typeHips(NEW_HIPS)
                .typeFirstname(NEW_FIRSTNAME)
                .typeLastname(NEW_LASTNAME)
                .typeBirthday(NEW_BIRTHDAY)
                .chooseGender(Sex.MEN)
                .typePhone(NEW_PHONE)
                .saveChanges();

        logger.info("Перезаходим в настройки и проверяем значения полей");
        SettingsParticipantPage participantSettingsPage = settingsParticipantPage
                .goToStartPage()
                .goToSettings();

        assertAll(
                () -> assertEquals(NEW_FIRSTNAME, participantSettingsPage.getFirstname(),
                        "Не совпадает ожидаемое значение имени с установленным"),
                () -> assertEquals(NEW_LASTNAME, participantSettingsPage.getLastname(),
                        "Не совпадает ожидаемое значение фамилии с установленным"),
                () -> assertEquals(NEW_BIRTHDAY, participantSettingsPage.getBirthday(),
                        "Не совпадает ожидаемое значение даты рождения с установленным"),
                () -> assertEquals(NEW_PHONE, participantSettingsPage.getPhone(),
                        "Не совпадает ожидаемое значение телефона с установленным"),
                () -> assertEquals("2", participantSettingsPage.getGender(),
                        "Не совпадает ожидаемое значение имени с установленным"),
                () -> assertEquals(NEW_BREAST, participantSettingsPage.getBreast(),
                        "Не совпадает ожидаемое значение обхвата груди с установленным"),
                () -> assertEquals(NEW_WAIST, participantSettingsPage.getWaist(),
                        "Не совпадает ожидаемое значение обхвата талии с установленным"),
                () -> assertEquals(NEW_HIPS, participantSettingsPage.getHips(),
                        "Не совпадает ожидаемое значение обхвата бедер с установленным"),
                () -> assertEquals(NEW_HEIGHT, participantSettingsPage.getHeight(),
                        "Не совпадает ожидаемое значение роста с установленным"),
                () -> assertEquals(NEW_WEIGHT, participantSettingsPage.getWeight(),
                        "Не совпадает ожидаемое значение веса с установленным")
        );

        logger.info("Тест прошел успешно");
    }

    @AfterEach
    public void logout() {
        logoutParticipant();
    }
}
