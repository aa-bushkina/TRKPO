package end2end.pages.participant;

import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Страница просмотра деталей вопроса
 */
public class QuestionParticipantPage extends PageBase {

    private static final By BACK_BUTTON = By.xpath(".//*[text()='Назад']");
    private static final By QUESTION = By.xpath(".//vaadin-text-area[1]");
    private static final By ANSWER = By.xpath(".//vaadin-text-area[2]");
    private static final By TITLE = By.xpath(".//vaadin-vertical-layout//h3");

    @Override
    protected void checkPage() {
        $(BACK_BUTTON).shouldBe(visible.because("Нет кнопки Назад"));
        $(QUESTION).shouldBe(visible.because("Нет текста вопроса"));
        $(TITLE).shouldBe(visible.because("Нет заголовка страницы"));
        logger.info("Загрузилась страница просмотра деталей вопроса");
    }

    public String getAnswer() {
        return $(ANSWER).shouldBe(visible.because("Нет ответа")).getValue();
    }

}
