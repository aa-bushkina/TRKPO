package end2end.pages.registration;

import end2end.pages.utils.PageBase;
import end2end.pages.StartPageFactory;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Начальная страница логина
 */
public class LoginPage extends PageBase {
    private static final By REGISTRATION_BUTTON = By.xpath(".//*[text()='Регистрация']");
    private static final By LOGIN_FIELD = By.xpath(".//vaadin-text-field");
    private static final By PASSWORD_FIELD = By.xpath(".//vaadin-password-field");
    private static final By LOGIN_BTN = By.xpath(".//*[text()='Войти']");

    @Override
    protected void checkPage() {
        $(REGISTRATION_BUTTON).shouldBe(visible.because("Нет кнопки регистрации"));
        logger.info("Загрузилась страница логина");
    }

    public FirstRegistrationPage goToRegistration() {
        logger.info("Нажимаем на 'Регистрация'");
        $(REGISTRATION_BUTTON).shouldBe(visible.because("Нет кнопки регистрации")).click();
        return new FirstRegistrationPage();
    }

    public StartPageFactory login(String login, String password) {
        logger.info("Логинимся с логином: " + login + " и паролем: " + password);
        $(LOGIN_FIELD).shouldBe(visible.because("Нет поля ввода логина")).sendKeys(login);
        $(PASSWORD_FIELD).shouldBe(visible.because("Нет поля ввода пароля")).sendKeys(password);
        $(LOGIN_BTN).shouldBe(visible.because("Нет кнопки входа")).click();
        return new StartPageFactory();
    }

}
