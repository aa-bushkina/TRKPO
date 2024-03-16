package end2end.pages.participant;

import end2end.pages.utils.PageBase;
import end2end.pages.utils.Sex;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.Objects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

/**
 * Страница насктроек участника с возможностью редактирования
 */
public class SettingsActiveParticipantPage extends PageBase {
    private static final By BREAST_FIELD = By.xpath(".//*[text()='Настройки']/..//vaadin-text-field[5]");
    private static final By HEIGHT_FIELD = By.xpath(".//*[text()='Настройки']/..//vaadin-text-field[6]");
    private static final By WAIST_FIELD = By.xpath(".//*[text()='Настройки']/..//vaadin-text-field[7]");
    private static final By WEIGHT_FIELD = By.xpath(".//*[text()='Настройки']/..//vaadin-text-field[8]");
    private static final By HIPS_FIELD = By.xpath(".//*[text()='Настройки']/..//vaadin-text-field[9]");
    private static final By TITLE = By.xpath(".//*[text()='Настройки']");
    private static final By CANCEL_BTN = By.xpath(".//*[text()='Отменить']");
    private static final By SAVE_BTN = By.xpath(".//*[text()='Сохранить']");
    private static final By FIRSTNAME_FIELD = By.xpath(".//*[text()='Настройки']/..//vaadin-text-field[1]");
    private static final By LASTNAME_FIELD = By.xpath(".//*[text()='Настройки']/..//vaadin-text-field[2]");
    private static final By PHONE_FIELD = By.xpath(".//*[text()='Настройки']/..//vaadin-text-field[4]");
    private static final By GENDER_FIELD = By.xpath(".//vaadin-select");
    private static final By BIRTHDAY_FIELD = By.xpath(".//vaadin-date-picker");
    private static final By GENDER_VALUE = By.xpath(".//vaadin-item");
    public static final String INVALID_ATTR = "invalid";

    @Override
    protected void checkPage() {
        $(TITLE).shouldBe(visible.because("Нет заголовка страницы настроек"));
        $(CANCEL_BTN).shouldBe(visible.because("Нет кнопки 'Отменить'"));
        $(SAVE_BTN).shouldBe(visible.because("Нет кнопки 'Сохранить'"));
        logger.info("Загрузилась страница настроек с возможностью редактирования");
    }

    public SettingsParticipantPage saveChanges() {
        logger.info("Кликаем на кнопку 'Сохранить'");
        $(SAVE_BTN).shouldBe(visible.because("Нет кнопки 'Сохранить'")).click();
        return new SettingsParticipantPage();
    }

    public SettingsActiveParticipantPage typeFirstname(String name) {
        logger.info("Вводим имя: " + name);
        executeJavaScript("arguments[0].value = '';", $(FIRSTNAME_FIELD));
        $(FIRSTNAME_FIELD).shouldBe(visible.because("Нет поля ввода имени")).sendKeys(name);
        return this;
    }

    public SettingsActiveParticipantPage typeLastname(String lastname) {
        logger.info("Вводим фамилию: " + lastname);
        executeJavaScript("arguments[0].value = '';", $(LASTNAME_FIELD));
        $(LASTNAME_FIELD).shouldBe(visible.because("Нет поля ввода фамилии")).sendKeys(lastname);
        return this;
    }

    public SettingsActiveParticipantPage typeBirthday(String date) {
        logger.info("Вводим имя: " + date);
        executeJavaScript("arguments[0].value = '';", $(BIRTHDAY_FIELD));
        $(BIRTHDAY_FIELD).shouldBe(visible.because("Нет поля ввода даты")).sendKeys(date);
        $(BIRTHDAY_FIELD).sendKeys(Keys.ENTER);
        return this;
    }

    public SettingsActiveParticipantPage typePhone(String phone) {
        logger.info("Вводим телефон: " + phone);
        executeJavaScript("arguments[0].value = '';", $(PHONE_FIELD));
        $(PHONE_FIELD).shouldBe(visible.because("Нет поля ввода телефона")).sendKeys(phone);
        return this;
    }

    public SettingsActiveParticipantPage chooseGender(Sex sex) {
        logger.info("Выбираем пол: " + sex.name());
        $(GENDER_FIELD).shouldBe(visible.because("Нет поля ввода имени")).click();
        switch (sex) {
            case MEN:
                logger.info("Выбираем пол Муж");
                $$(GENDER_VALUE).get(1).shouldBe(visible.because("Нет варианта Муж")).click();
                break;
            case WOMEN:
                logger.info("Выбираем пол Жен");
                $$(GENDER_VALUE).get(0).shouldBe(visible.because("Нет варианта Жен")).click();
                break;
        }
        return this;
    }

    public SettingsActiveParticipantPage typeBreast(String breast) {
        logger.info("Вводим обхват груди: " + breast);
        executeJavaScript("arguments[0].value = '';", $(BREAST_FIELD));
        $(BREAST_FIELD).shouldBe(visible.because("Нет поля ввода обхвата груди")).sendKeys(breast);
        return this;
    }

    public boolean breastIsInvalid() {
        return Objects.equals( $(BREAST_FIELD).shouldBe(visible.because("Нет поля ввода для обхвата груди"))
                .getAttribute(INVALID_ATTR), "true");
    }


    public SettingsActiveParticipantPage typeHeight(String height) {
        logger.info("Вводим рост: " + height);
        executeJavaScript("arguments[0].value = '';", $(HEIGHT_FIELD));
        $(HEIGHT_FIELD).shouldBe(visible.because("Нет поля ввода роста")).sendKeys(height);
        return this;
    }

    public boolean heightIsInvalid() {
        return Objects.equals($(HEIGHT_FIELD).getAttribute(INVALID_ATTR), "true");
    }

    public SettingsActiveParticipantPage clickHeight() {
        logger.info("Нажимаем на поле роста");
        $(HEIGHT_FIELD).shouldBe(visible.because("Нет поля ввода для роста")).click();
        return new SettingsActiveParticipantPage();
    }

    public SettingsActiveParticipantPage typeWaist(String waist) {
        logger.info("Вводим обхват талии: " + waist);
        executeJavaScript("arguments[0].value = '';", $(WAIST_FIELD));
        $(WAIST_FIELD).shouldBe(visible.because("Нет поля ввода обхвата талии")).sendKeys(waist);
        return this;
    }

    public boolean waistIsInvalid() {
        return Objects.equals($(WAIST_FIELD).shouldBe(visible.because("Нет поля ввода для обхвата талии"))
                .getAttribute(INVALID_ATTR), "true");
    }

    public SettingsActiveParticipantPage typeWeight(String weight) {
        logger.info("Вводим вес: " + weight);
        executeJavaScript("arguments[0].value = '';", $(WEIGHT_FIELD));
        $(WEIGHT_FIELD).shouldBe(visible.because("Нет поля ввода веса")).sendKeys(weight);
        return this;
    }

    public boolean weightIsInvalid() {
        return Objects.equals($(WEIGHT_FIELD).shouldBe(visible.because("Нет поля ввода для веса"))
                .getAttribute(INVALID_ATTR), "true");
    }

    public SettingsActiveParticipantPage clickWeight() {
        logger.info("Нажимаем на поле веса");
        $(WEIGHT_FIELD).shouldBe(visible.because("Нет поля ввода для веса")).click();
        return new SettingsActiveParticipantPage();
    }


    public SettingsActiveParticipantPage typeHips(String hips) {
        logger.info("Вводим обхват бедер: " + hips);
        executeJavaScript("arguments[0].value = '';", $(HIPS_FIELD));
        $(HIPS_FIELD).shouldBe(visible.because("Нет поля ввода обхвата бедер")).sendKeys(hips);
        return this;
    }

    public boolean hipsIsInvalid() {
        return Objects.equals($(HIPS_FIELD).shouldBe(visible.because("Нет поля ввода для обхвата бедер"))
                .getAttribute(INVALID_ATTR), "true");
    }
}
