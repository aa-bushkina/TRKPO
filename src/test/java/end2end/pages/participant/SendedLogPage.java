package end2end.pages.participant;

import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Страница успещной отправки записи
 */
public class SendedLogPage extends PageBase {
    private static final By TITLE = By.xpath(".//*[text()='Запись отправлена!']");
    private static final By OK_BTN = By.xpath(".//*[text()='Ок']");

    protected void checkPage() {
        $(TITLE).shouldBe(visible.because("Нет заголовка страницы успешного добавления записи"));
        $(OK_BTN).shouldBe(visible.because("Нет кнопки 'Ок'"));
        logger.info("Загрузилась страница успешного добавления записи");
    }

    public StartParticipantPage clickOk() {
        logger.info("Кликаем на кнопку Ок");
        $(OK_BTN)
                .shouldBe(visible.because("Нет кнопки Ок"))
                .click();
        return new StartParticipantPage();
    }
}
