package end2end.tests;

import end2end.pages.participant.SettingsActiveParticipantPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест проверяет ограничения на поля параметром участника при редактировании личной информации
 */
public class TestEnd2EndCheckRestrictionsSettingsParticipant extends TestBase {

    private static final String NORMAL_HEIGHT = "100";
    private static final String MORE_HEIGHT = "251";
    private static final String LESS_HEIGHT = "66";
    private static final String NORMAL_WEIGHT = "100";
    private static final String MORE_WEIGHT = "601";
    private static final String LESS_WEIGHT = "1";
    private static final String NORMAL_BREAST = "100";
    private static final String MORE_BREAST = "251";
    private static final String LESS_BREAST = "29";
    private static final String NORMAL_HIPS = "100";
    private static final String MORE_HIPS = "251";
    private static final String LESS_HIPS = "29";
    private static final String NORMAL_WAIST = "100";
    private static final String MORE_WAIST = "251";
    private static final String LESS_WAIST = "29";

    @BeforeEach
    public void setUp() {
        logger.info("Регистрируем участника");
        registerParticipant();
    }

    @Test
    public void testEnd2EndCheckRestrictionsSettingsParticipant() {
        logger.info("Тест проверяет ограничения на поля параметром участника при редактировании личной информации");

        logger.info("Логиниися участником, переходим в настройки и нажимаем редактировать");
        SettingsActiveParticipantPage settingsActiveParticipantPage = getLoginPage().login(LOGIN_PARTICIPANT, PASSWORD)
                .andReturnStartParticipantPage()
                .goToSettings()
                .changeInfo();

        logger.info("Проверяем ввод различного роста");
        settingsActiveParticipantPage = settingsActiveParticipantPage.typeHeight(LESS_HEIGHT)
                .clickWeight();
        assertTrue(settingsActiveParticipantPage.heightIsInvalid(),
                "Поле ввода роста не стало невалидным от значение меньше границы");
        settingsActiveParticipantPage = settingsActiveParticipantPage.typeHeight(NORMAL_HEIGHT)
                .clickWeight();
        assertFalse(settingsActiveParticipantPage.heightIsInvalid(),
                "Поле ввода роста не стало валидным");
        settingsActiveParticipantPage = settingsActiveParticipantPage.typeHeight(MORE_HEIGHT)
                .clickWeight();
        assertTrue(settingsActiveParticipantPage.heightIsInvalid(),
                "Поле ввода роста не стало невалидным от значение больше границы");
        settingsActiveParticipantPage = settingsActiveParticipantPage.typeHeight(NORMAL_HEIGHT)
                .clickWeight();
        assertFalse(settingsActiveParticipantPage.heightIsInvalid(),
                "Поле ввода роста не стало валидным");

        logger.info("Проверяем ввод различного обхвата груди");
        settingsActiveParticipantPage = settingsActiveParticipantPage.typeBreast(LESS_BREAST)
                .clickWeight();
        assertTrue(settingsActiveParticipantPage.breastIsInvalid(),
                "Поле ввода обхвата груди не стало невалидным от значение меньше границы");
        settingsActiveParticipantPage = settingsActiveParticipantPage.typeBreast(NORMAL_BREAST)
                .clickWeight();
        assertFalse(settingsActiveParticipantPage.breastIsInvalid(),
                "Поле ввода обхвата груди не стало валидным");
        settingsActiveParticipantPage = settingsActiveParticipantPage.typeBreast(MORE_BREAST)
                .clickWeight();
        assertTrue(settingsActiveParticipantPage.breastIsInvalid(),
                "Поле ввода обхвата груди не стало невалидным от значение больше границы");
        settingsActiveParticipantPage = settingsActiveParticipantPage.typeBreast(NORMAL_BREAST)
                .clickWeight();
        assertFalse(settingsActiveParticipantPage.breastIsInvalid(),
                "Поле ввода обхвата груди не стало валидным");

        logger.info("Проверяем ввод различного обхвата бедер");
        settingsActiveParticipantPage = settingsActiveParticipantPage.typeHips(LESS_HIPS)
                .clickWeight();
        assertTrue(settingsActiveParticipantPage.hipsIsInvalid(),
                "Поле ввода обхвата бедер не стало невалидным от значение меньше границы");
        settingsActiveParticipantPage = settingsActiveParticipantPage.typeHips(NORMAL_HIPS)
                .clickWeight();
        assertFalse(settingsActiveParticipantPage.hipsIsInvalid(),
                "Поле ввода обхвата бедер не стало валидным");
        settingsActiveParticipantPage = settingsActiveParticipantPage.typeHips(MORE_HIPS)
                .clickWeight();
        assertTrue(settingsActiveParticipantPage.hipsIsInvalid(),
                "Поле ввода обхвата бедер не стало невалидным от значение больше границы");
        settingsActiveParticipantPage = settingsActiveParticipantPage.typeHips(NORMAL_HIPS)
                .clickWeight();
        assertFalse(settingsActiveParticipantPage.hipsIsInvalid(),
                "Поле ввода обхвата бедер не стало валидным");

        logger.info("Проверяем ввод различного обхвата талии");
        settingsActiveParticipantPage = settingsActiveParticipantPage.typeWaist(LESS_WAIST)
                .clickWeight();
        assertTrue(settingsActiveParticipantPage.waistIsInvalid(),
                "Поле ввода обхвата талии не стало невалидным от значение меньше границы");
        settingsActiveParticipantPage = settingsActiveParticipantPage.typeWaist(NORMAL_WAIST)
                .clickWeight();
        assertFalse(settingsActiveParticipantPage.waistIsInvalid(),
                "Поле ввода обхвата талии не стало валидным");
        settingsActiveParticipantPage = settingsActiveParticipantPage.typeWaist(MORE_WAIST)
                .clickWeight();
        assertTrue(settingsActiveParticipantPage.waistIsInvalid(),
                "Поле ввода обхвата талии не стало невалидным от значение больше границы");
        settingsActiveParticipantPage = settingsActiveParticipantPage.typeWaist(NORMAL_WAIST)
                .clickWeight();
        assertFalse(settingsActiveParticipantPage.waistIsInvalid(),
                "Поле ввода обхвата талии не стало валидным");

        logger.info("Проверяем ввод различного веса");
        settingsActiveParticipantPage = settingsActiveParticipantPage.typeWeight(LESS_WEIGHT)
                .clickHeight();
        assertTrue(settingsActiveParticipantPage.weightIsInvalid(),
                "Поле ввода веса не стало невалидным от значение меньше границы");
        settingsActiveParticipantPage = settingsActiveParticipantPage.typeWeight(NORMAL_WEIGHT)
                .clickHeight();
        assertFalse(settingsActiveParticipantPage.weightIsInvalid(),
                "Поле ввода веса не стало валидным");
        settingsActiveParticipantPage = settingsActiveParticipantPage.typeWeight(MORE_WEIGHT)
                .clickHeight();
        assertTrue(settingsActiveParticipantPage.weightIsInvalid(),
                "Поле ввода веса не стало невалидным от значение больше границы");
        settingsActiveParticipantPage = settingsActiveParticipantPage.typeWeight(NORMAL_WEIGHT)
                .clickHeight();
        assertFalse(settingsActiveParticipantPage.weightIsInvalid(),
                "Поле ввода веса не стало валидным");

        logger.info("Тест прошел успешно");
    }

    @AfterEach
    public void logout() {
        logoutParticipant();
    }
}
