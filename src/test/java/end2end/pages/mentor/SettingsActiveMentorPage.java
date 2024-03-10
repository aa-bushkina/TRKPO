package end2end.pages.mentor;

import end2end.pages.SettingsPageFactory;
import end2end.pages.utils.PageBase;
import end2end.pages.utils.Sex;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

/**
 * Страница настроек ментора с возможностью редактирования
 */
public class SettingsActiveMentorPage extends PageBase {
    private static final By TITLE = By.xpath(".//*[text()='Настройки']");
    private static final By CANCEL_BTN = By.xpath(".//*[text()='Отменить']");
    private static final By SAVE_BTN = By.xpath(".//*[text()='Сохранить']");
    private static final By FIRSTNAME_FIELD = By.xpath(".//*[text()='Настройки']/..//vaadin-text-field[1]");
    private static final By LASTNAME_FIELD = By.xpath(".//*[text()='Настройки']/..//vaadin-text-field[2]");
    private static final By PHONE_FIELD = By.xpath(".//*[text()='Настройки']/..//vaadin-text-field[4]");
    private static final By GENDER_FIELD = By.xpath(".//vaadin-select");
    private static final By BIRTHDAY_FIELD = By.xpath(".//vaadin-date-picker");
    private static final By GENDER_VALUE = By.xpath(".//vaadin-item");

    @Override
    protected void checkPage() {
        $(TITLE).shouldBe(visible.because("Нет заголовка страницы настроек"));
        $(CANCEL_BTN).shouldBe(visible.because("Нет кнопки 'Отменить'"));
        $(SAVE_BTN).shouldBe(visible.because("Нет кнопки 'Сохранить'"));
        logger.info("Загрузилась страница настроек с возможностью редактирования");
    }

    public SettingsPageFactory saveChanges() {
        logger.info("Кликаем на кнопку 'Сохранить'");
        $(SAVE_BTN).shouldBe(visible.because("Нет кнопки 'Сохранить'")).click();
        return new SettingsPageFactory();
    }

    public SettingsActiveMentorPage typeFirstname(String name) {
        logger.info("Вводим имя: " + name);
        executeJavaScript("arguments[0].value = '';", $(FIRSTNAME_FIELD));
        $(FIRSTNAME_FIELD).shouldBe(visible.because("Нет поля ввода имени")).sendKeys(name);
        return this;
    }

    public SettingsActiveMentorPage typeLastname(String lastname) {
        logger.info("Вводим фамилию: " + lastname);
        executeJavaScript("arguments[0].value = '';", $(LASTNAME_FIELD));
        $(LASTNAME_FIELD).shouldBe(visible.because("Нет поля ввода фамилии")).sendKeys(lastname);
        return this;
    }

    public SettingsActiveMentorPage typeDate(String date) {
        logger.info("Вводим имя: " + date);
        executeJavaScript("arguments[0].value = '';", $(BIRTHDAY_FIELD));
        $(BIRTHDAY_FIELD).shouldBe(visible.because("Нет поля ввода даты")).sendKeys(date);
        $(BIRTHDAY_FIELD).sendKeys(Keys.ENTER);
        return this;
    }

    public SettingsActiveMentorPage typePhone(String phone) {
        logger.info("Вводим телефон: " + phone);
        executeJavaScript("arguments[0].value = '';", $(PHONE_FIELD));
        $(PHONE_FIELD).shouldBe(visible.because("Нет поля ввода телефона")).sendKeys(phone);
        return this;
    }

    public SettingsActiveMentorPage chooseGender(Sex sex) {
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
}
