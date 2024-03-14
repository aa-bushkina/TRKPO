package end2end.pages.participant;

import end2end.pages.ChangePasswordPage;
import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Страница настроек участника
 */
public class SettingsParticipantPage extends PageBase {
    private static final By TITLE = By.xpath(".//*[text()='Настройки']");
    private static final By CHANGE_PASS_BTN = By.xpath(".//*[text()='Изменить пароль']");
    private static final By CHANGE_INFO_BTN = By.xpath(".//*[text()='Редактировать']");
    private static final By HOME_BTN = By.xpath(".//*[@icon='vaadin:home']/..");
    private static final By BREAST_FIELD = By.xpath(".//*[text()='Настройки']/..//vaadin-text-field[5]");
    private static final By HEIGHT_FIELD = By.xpath(".//*[text()='Настройки']/..//vaadin-text-field[6]");
    private static final By WAIST_FIELD = By.xpath(".//*[text()='Настройки']/..//vaadin-text-field[7]");
    private static final By WEIGHT_FIELD = By.xpath(".//*[text()='Настройки']/..//vaadin-text-field[8]");
    private static final By HIPS_FIELD = By.xpath(".//*[text()='Настройки']/..//vaadin-text-field[9]");
    private static final By FIRSTNAME_FIELD = By.xpath(".//*[text()='Настройки']/..//vaadin-text-field[1]");
    private static final By LASTNAME_FIELD = By.xpath(".//*[text()='Настройки']/..//vaadin-text-field[2]");
    private static final By PHONE_FIELD = By.xpath(".//*[text()='Настройки']/..//vaadin-text-field[4]");
    private static final By GENDER_FIELD = By.xpath(".//vaadin-select");
    private static final By BIRTHDAY_FIELD = By.xpath(".//vaadin-date-picker");

    protected void checkPage() {
        $(TITLE).shouldBe(visible.because("Нет заголовка страницы настроек"));
        $(CHANGE_PASS_BTN).shouldBe(visible.because("Нет кнопки 'Изменить пароль'"));
        $(CHANGE_INFO_BTN).shouldBe(visible.because("Нет кнопки 'Редактировать'"));
        logger.info("Загрузилась страница настроек");
    }

    public ChangePasswordPage changePassword() {
        logger.info("Кликаем на 'Изменить пароль'");
        $(CHANGE_PASS_BTN).shouldBe(visible.because("Нет кнопки 'Изменить пароль'")).click();
        return new ChangePasswordPage();
    }

    public SettingsActiveParticipantPage changeInfo() {
        logger.info("Кликаем на 'Редактировать'");
        $(CHANGE_INFO_BTN).shouldBe(visible.because("Нет кнопки 'Редактировать'")).click();
        return new SettingsActiveParticipantPage();
    }

    public StartParticipantPage goToStartPage() {
        logger.info("Кликаем на кнопку домой");
        $(HOME_BTN).parent()
                .shouldBe(visible.because("Нет кнопки домой"))
                .click();
        return new StartParticipantPage();
    }

    public String getFirstname() {
        logger.info("Получаем имя");
        return $(FIRSTNAME_FIELD).shouldBe(visible.because("Нет поля ввода имени")).getValue();
    }

    public String getLastname() {
        logger.info("Получаем фамилию");
        return $(LASTNAME_FIELD).shouldBe(visible.because("Нет поля ввода фамилии")).getValue();
    }

    public String getBirthday() {
        logger.info("Получаем дату рождения");
        String date = $(BIRTHDAY_FIELD).shouldBe(visible.because("Нет поля ввода даты")).getValue();
        return  LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public String getPhone() {
        logger.info("Получаем телефон");
        return $(PHONE_FIELD).shouldBe(visible.because("Нет поля ввода телефона")).getValue();
    }

    public String getGender() {
        logger.info("Получаем пол");
        return $(GENDER_FIELD).shouldBe(visible.because("Нет поля ввода имени")).getValue();
    }

    public String getBreast() {
        logger.info("Получаем обхват груди");
        return $(BREAST_FIELD).shouldBe(visible.because("Нет поля ввода обхвата груди")).getValue();
    }

    public String getHeight() {
        logger.info("Получаем рост");
        return $(HEIGHT_FIELD).shouldBe(visible.because("Нет поля ввода роста")).getValue();
    }

    public String getWaist() {
        logger.info("Получаем обхват талии");
        return $(WAIST_FIELD).shouldBe(visible.because("Нет поля ввода обхвата талии")).getValue();
    }

    public String getWeight() {
        logger.info("Получаем вес");
        return $(WEIGHT_FIELD).shouldBe(visible.because("Нет поля ввода веса")).getValue();
    }

    public String getHips() {
        logger.info("Получаем обхват бедер");
        return $(HIPS_FIELD).shouldBe(visible.because("Нет поля ввода обхвата бедер")).getValue();
    }
}
