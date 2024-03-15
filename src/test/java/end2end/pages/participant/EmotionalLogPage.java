package end2end.pages.participant;

import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Страница создания записи о эмоциях
 */
public class EmotionalLogPage extends PageBase {
    private static final By TITLE = By.xpath(".//*[text()='Эмоциональное состояние']");
    private static final By COMMENT = By.xpath(".//vaadin-text-area");
    private static final By SEND_BTN = By.xpath(".//*[text()='Отправить']");

    protected void checkPage() {
        $(TITLE).shouldBe(visible.because("Нет заголовка страницы записи об эмоциональной активности"));
        $(COMMENT).shouldBe(visible.because("Нет поля комментария"));
        $(SEND_BTN).shouldBe(visible.because("Нет кнопки 'Отправить'"));
        logger.info("Загрузилась страница добавления записи об эмоциональной активности");
    }

    public EmotionalLogPage typeComment(String text) {
        logger.info("Вводим комментарий");
        $(COMMENT)
                .shouldBe(visible.because("Нет поля ввода комментария"))
                .sendKeys(text);
        return this;
    }

    public SendedLogPage clickSend() {
        logger.info("Кликаем на кнопку отправку записи");
        $(SEND_BTN)
                .shouldBe(visible.because("Нет кнопки отправки записи"))
                .click();
        return new SendedLogPage();
    }
}
