package end2end.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Начальная страница логина
 */
public class LoginPage extends PageBase {

    private static final By REGISTRATION_BUTTON = By.xpath(".//*[text()='Регистрация']");

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

}
