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
    private static final By QUESTION_DATE = By.xpath(".//*[@slot='vaadin-grid-cell-content-1']");
    private static final By QUESTION_TEXT = By.xpath(".//*[@slot='vaadin-grid-cell-content-2']");
    private static final By QUESTION_STATUS = By.xpath(".//*[@slot='vaadin-grid-cell-content-3']");

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

    public String getQuestionText() {
        logger.info("Получаем текст вопроса из списка вопросов");
        return $(QUESTION_TEXT)
                .shouldBe(visible.because("Нет текста вопроса в списке вопросов"))
                .text();
    }

    public String getQuestionDate() {
        logger.info("Получаем дату вопроса из списка вопросов");
        return $(QUESTION_DATE)
                .shouldBe(visible.because("Нет даты вопроса в списке вопросов"))
                .text();
    }

    public String getQuestionStatus() {
        logger.info("Получаем статус вопроса из списка вопросов");
        return $(QUESTION_STATUS)
                .shouldBe(visible.because("Нет статуса вопроса в списке вопросов"))
                .text();
    }
}
