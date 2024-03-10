package end2end.pages;

import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ChangePasswordPage extends PageBase {

    private static final By TITLE = By.xpath(".//*[text()='Изменить пароль']");
    private static final By SAVE_BTN = By.xpath(".//*[text()='Сохранить']");
    private static final By CANCEL_BTN = By.xpath(".//*[text()='Отменить']");
    public static final By OLD_PASS_FIELD = By.xpath(".//vaadin-password-field[1]");
    public static final By NEW_PASS_FIELD = By.xpath(".//vaadin-password-field[2]");
    public static final By REPEAT_PASS_FIELD = By.xpath(".//vaadin-password-field[3]");

    @Override
    protected void checkPage() {
        $(TITLE).shouldBe(visible.because("Нет заголовка страницы настроек"));
        $(SAVE_BTN).shouldBe(visible.because("Нет кнопки 'Сохранить'"));
        $(CANCEL_BTN).shouldBe(visible.because("Нет кнопки 'Отменить'"));
        $(OLD_PASS_FIELD).shouldBe(visible.because("Нет поля ввода старого пароля"));
        $(NEW_PASS_FIELD).shouldBe(visible.because("Нет поля ввода нового пароля"));
        $(REPEAT_PASS_FIELD).shouldBe(visible.because("Нет поля ввода повтора пароля"));
        logger.info("Загрузилась страница смены пароля");
    }

    public ChangePasswordPage typeOldPass(String pass) {
        logger.info("Вводим текущий пароль: " + pass);
        $(OLD_PASS_FIELD).shouldBe(visible.because("Нет поля ввода старого пароля")).sendKeys(pass);
        return this;
    }

    public ChangePasswordPage typeNewPass(String pass) {
        logger.info("Вводим новый пароль: " + pass);
        $(NEW_PASS_FIELD).shouldBe(visible.because("Нет поля ввода нового пароля")).sendKeys(pass);
        return this;
    }

    public ChangePasswordPage typeRepeatPass(String pass) {
        logger.info("Вводим повторяемый пароль: " + pass);
        $(REPEAT_PASS_FIELD).shouldBe(visible.because("Нет поля ввода повтора пароля")).sendKeys(pass);
        return this;
    }

    public void clickSave() {
        logger.info("Кликаем на кнопку 'Сохранить'");
        $(SAVE_BTN).shouldBe(visible.because("Нет поля ввода повтора пароля")).click();
    }
}
