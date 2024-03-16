package end2end.pages.mentor;

import end2end.pages.utils.PageBase;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Страница истории записей участника
 */
public class HistoryParticipantLogsPage extends PageBase {

    private static final By TITLE = By.xpath(".//vaadin-horizontal-layout//h3");
    private static final By DOWNLOAD_BUTTON = By.xpath("//vaadin-button[@theme='icon']");
    private static final By GRID = By.xpath(".//vaadin-grid");
    private static final By WATCH_BUTTON = By.xpath(".//*[text()='Смотреть']");
    private static final By TYPE_LOG = By.xpath(".//*[@slot='vaadin-grid-cell-content-2']");
    private static final By DATE_LOG = By.xpath(".//*[@slot='vaadin-grid-cell-content-1']");

    @Override
    protected void checkPage() {
        $(TITLE).shouldBe(visible.because("Нет заголовка страницы"));
        $(DOWNLOAD_BUTTON).shouldBe(visible.because("Нет кнопки скачать историю записей"));
        $(GRID).shouldBe(visible.because("Нет таблицы записей"));
        logger.info("Страница истории записей участника загрузилась");
    }

    public int getCountLogs() {
        return $$(WATCH_BUTTON).size();
    }

    public String getTypeLog(int number) {
        return $$(TYPE_LOG).get(number - 1).getText();
    }

    public String getDateLog(int number) {
        return $$(DATE_LOG).get(number - 1).getText();
    }

    public ParticipantLogDetailsPage clickWatch() {
        logger.info("Нажимаем на Смотреть единственной записи");
        $(WATCH_BUTTON).shouldBe(visible.because("Нет кнопки Смотреть")).click();
        return new ParticipantLogDetailsPage();
    }

}
