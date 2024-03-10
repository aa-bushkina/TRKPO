package end2end.pages.registration;

import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Вторая страница регистрации
 */
public class SecondRegistrationPage extends PageBase {


    public static final By FIRST_NAME_INPUT = By.xpath(".//vaadin-text-field[1]");
    public static final By SECOND_NAME_INPUT = By.xpath(".//vaadin-text-field[2]");
    public static final By LOGIN = By.xpath(".//vaadin-text-field[3]");
    public static final By PASSWORD = By.xpath(".//vaadin-password-field[1]");
    public static final By REPEAT_PASSWORD = By.xpath(".//vaadin-password-field[2]");
    public static final By CONTINUE_BUTTON = By.xpath(".//*[text()='Далее']");

    @Override
    protected void checkPage() {
        $(FIRST_NAME_INPUT).shouldBe(visible.because("Нет поля ввода для имени"));
        $(SECOND_NAME_INPUT).shouldBe(visible.because("Нет поля ввода для фамилии"));
        $(LOGIN).shouldBe(visible.because("Нет поля ввода для логина"));
        $(PASSWORD).shouldBe(visible.because("Нет поля ввода для пароля"));
        $(REPEAT_PASSWORD).shouldBe(visible.because("Нет поля повторного ввода для пароля"));
        $(CONTINUE_BUTTON).shouldBe(visible.because("Нет кнопки 'Далее'"));
        logger.info("Загрузилась вторая страница регистрации");
    }

    public SecondRegistrationPage typeName(String name) {
        logger.info("Вводим имя " + name);
        $(FIRST_NAME_INPUT).shouldBe(visible.because("Нет поля ввода для имени")).sendKeys(name);
        return this;
    }

    public SecondRegistrationPage typeSecondName(String name) {
        logger.info("Вводим фамилию " + name);
        $(SECOND_NAME_INPUT).shouldBe(visible.because("Нет поля ввода для фамилии")).sendKeys(name);
        return this;
    }

    public SecondRegistrationPage typeLogin(String login) {
        logger.info("Вводим логин " + login);
        $(LOGIN).shouldBe(visible.because("Нет поля ввода для логина")).sendKeys(login);
        return this;
    }

    public SecondRegistrationPage typePassword(String pass) {
        logger.info("Вводим пароль " + pass);
        $(PASSWORD).shouldBe(visible.because("Нет поля ввода для пароля")).sendKeys(pass);
        $(REPEAT_PASSWORD).shouldBe(visible.because("Нет поля повторного ввода для пароля")).sendKeys(pass);
        return this;
    }

    public ThirdRegistrationPage doContinue() {
        logger.info("Нажимаем на далее");
        $(CONTINUE_BUTTON).shouldBe(visible.because("Нет кнопки 'Далее'")).click();
        return new ThirdRegistrationPage();
    }

}
