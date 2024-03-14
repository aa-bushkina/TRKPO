package end2end.pages.participant;

import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * История записей участника
 */
public class LogsParticipantPage extends PageBase {
    private static final By TITLE = By.xpath(".//*[text()='История записей']");
    private static final By TABLE = By.xpath(".//vaadin-grid");
    private static final By HOME_BTN = By.xpath(".//*[@icon='vaadin:home']/..");

    private static final By LOG_DATE = By.xpath(".//*[@slot='vaadin-grid-cell-content-1']");
    private static final By LOG_TYPE = By.xpath(".//*[@slot='vaadin-grid-cell-content-2']");

    @Override
    protected void checkPage() {
        $(TITLE).shouldBe(visible.because("Нет заголовка страницы истории записей"));
        $(TABLE).shouldBe(visible.because("Нет таблицы уведомлений"));
        logger.info("Загрузилась страница истории записей");
    }

    public StartParticipantPage goToStartPage() {
        logger.info("Кликаем на кнопку домой");
        $(HOME_BTN).parent()
                .shouldBe(visible.because("Нет кнопки домой"))
                .click();
        return new StartParticipantPage();
    }

    public String getLogType() {
        logger.info("Получаем тип записи из списка записей");
        return $(LOG_TYPE)
                .shouldBe(visible.because("Нет типа записи в списке записей"))
                .text();
    }

    public String getLogDate() {
        logger.info("Получаем дату записи из списка записей");
        return $(LOG_DATE)
                .shouldBe(visible.because("Нет даты записи в списке записей"))
                .text();
    }
}
