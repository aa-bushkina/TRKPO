package end2end.pages.mentor;

import end2end.pages.utils.PageBase;
import org.apache.poi.ss.formula.functions.T;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Страница добавления участников
 */
public class AddParticipantPage extends PageBase {

    private static final By TITLE = By.xpath(".//h2[text()='Добавить участника']");
    private static final By INPUT_LOGIN = By.xpath(".//vaadin-text-field");
    private static final By ADD_PARTICIPANT = By.xpath(".//vaadin-button[@theme='primary']");
    private static final By HOME = By.xpath(".//vaadin-button[@theme='icon tertiary']");

    @Override
    protected void checkPage() {
        $(TITLE).shouldBe(visible.because("Нет заголовка страница"));
        $(INPUT_LOGIN).shouldBe(visible.because("Нет поля ввода логина участника"));
        $(ADD_PARTICIPANT).shouldBe(visible.because("Нет кнопки добавления участника"));
        $(HOME).shouldBe(visible.because("Нет кнопки домой"));
        logger.info("Загрузилась страница добавления участников");
    }

    public AddParticipantPage typeLogin(String login) {
        logger.info("Вводим логин " + login);
        $(INPUT_LOGIN).shouldBe(visible.because("Нет поля ввода логина участника")).sendKeys(login);
        return this;
    }

    public AddParticipantPage sendRequest() {
        logger.info("Нажимаем на кнопку Добавить");
        $(ADD_PARTICIPANT).shouldBe(visible.because("Нет кнопки добавления участника")).click();
        return new AddParticipantPage();
    }

    public StartMentorPage home() {
        logger.info("Возвращаеися на стартовую страницу");
        $(HOME).shouldBe(visible.because("Нет кнопки домой")).click();
        return new StartMentorPage();
    }

    public String getLoginInInput() {
        return $(INPUT_LOGIN).getValue();
    }

}
