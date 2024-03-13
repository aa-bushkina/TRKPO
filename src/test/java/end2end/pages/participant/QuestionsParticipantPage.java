package end2end.pages.participant;

import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Страница уведомлений участника
 */
public class QuestionsParticipantPage extends PageBase {
    private static final By TITLE = By.xpath(".//*[text()='Вопросы']");
    private static final By TABLE = By.xpath(".//vaadin-grid");
    private static final By HOME_BTN = By.xpath(".//*[@icon='vaadin:home']/..");
    private static final By INPUT_AREA = By.xpath(".//vaadin-text-area");
    private static final By ASK_BTN = By.xpath(".//*[text()='Задать']");
    private static final By NOTIFICATION = By.xpath(".//*[@id='vaadin-notification-card']");

    @Override
    protected void checkPage() {
        $(TITLE).shouldBe(visible.because("Нет заголовка страницы уведомлений"));
        $(TABLE).shouldBe(visible.because("Нет таблицы уведомлений"));
        $(INPUT_AREA).shouldBe(visible.because("Нет поля ввода вопроса"));
        $(ASK_BTN).shouldBe(visible.because("Нет кнопки 'Задать'"));
        logger.info("Загрузилась страница уведомлений");
    }

    public StartParticipantPage goToStartPage() {
        logger.info("Кликаем на кнопку домой");
        $(HOME_BTN).parent()
                .shouldBe(visible.because("Нет кнопки домой"))
                .click();
        return new StartParticipantPage();
    }

    public QuestionsParticipantPage typeQuestion(String text) {
        logger.info("Вводим вопрос");
        $(INPUT_AREA)
                .shouldBe(visible.because("Нет поля ввода вопроса"))
                .sendKeys(text);
        return this;
    }

    public QuestionsParticipantPage clickSend() {
        logger.info("Кликаем на кнопку отправку вопроса");
        $(ASK_BTN)
                .shouldBe(visible.because("Нет кнопки отправки вопроса"))
                .click();
        return this;
    }

    public String getNotificationText() {
        logger.info("Получаем текст нотификации");
        return $(NOTIFICATION)
                .shouldBe(visible.because("Нет нотификации"), Duration.ofSeconds(5))
                .text();
    }
}
