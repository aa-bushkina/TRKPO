package end2end.pages.participant;

import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

/**
 * История записей участника
 */
public class LogsParticipantPage extends PageBase {
    private static final By TITLE = By.xpath(".//*[text()='История записей']");
    private static final By TABLE = By.xpath(".//vaadin-grid");
    private static final By HOME_BTN = By.xpath(".//*[@icon='vaadin:home']/..");
    private static final By LOG_DATE = By.xpath(".//*[@slot='vaadin-grid-cell-content-1']");
    private static final By LOG_TYPE = By.xpath(".//*[@slot='vaadin-grid-cell-content-2']");
    private static final By FILTER_TYPE = By.xpath(".//vaadin-select");
    private static final By ALL_TYPE = By.xpath(".//vaadin-item[1]");
    private static final By EMOTIONAL_TYPE = By.xpath(".//vaadin-item[2]");
    private static final By SPORT_TYPE = By.xpath(".//vaadin-item[3]");
    private static final By EAT_TYPE = By.xpath(".//vaadin-item[4]");
    private static final By FILTER_DATE = By.xpath(".//vaadin-date-picker");
    private static final By WATCH_BTN = By.xpath(".//*[text()='Смотреть']");

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

    public LogsParticipantPage chooseAllType() {
        logger.info("Выбираем типа записей: все");
        $(FILTER_TYPE)
                .shouldBe(visible.because("Нет кнопки выбора типа записей"))
                .click();
        $(ALL_TYPE)
                .shouldBe(visible.because("Нет кнопки выбора типа записей: все"))
                .click();
        return this;
    }

    public LogsParticipantPage chooseEmotionalType() {
        logger.info("Выбираем тип записей: эмоциональное состояние");
        $(FILTER_TYPE)
                .shouldBe(visible.because("Нет кнопки выбора типа записей"))
                .click();
        $(EMOTIONAL_TYPE)
                .shouldBe(visible.because("Нет кнопки выбора типа записей: эмоциональное состояние"))
                .click();
        return this;
    }

    public LogsParticipantPage chooseSportType() {
        logger.info("Выбираем тип записей: спортивная активность");
        $(FILTER_TYPE)
                .shouldBe(visible.because("Нет кнопки выбора типа записей"))
                .click();
        $(SPORT_TYPE)
                .shouldBe(visible.because("Нет кнопки выбора типа записей: спортивная активность"))
                .click();
        return this;
    }

    public LogsParticipantPage chooseEatType() {
        logger.info("Выбираем тип записей: прием пищи");
        $(FILTER_TYPE)
                .shouldBe(visible.because("Нет кнопки выбора типа записей"))
                .click();
        $(EAT_TYPE)
                .shouldBe(visible.because("Нет кнопки выбора типа записей: прием пищи"))
                .click();
        return this;
    }

    public LogsParticipantPage chooseDate(String date) {
        logger.info("Выбираем дату записей: " + date);
        executeJavaScript("arguments[0].value = '';", $(FILTER_DATE));
        $(FILTER_DATE).shouldBe(visible.because("Нет кнопки выбора даты записей")).sendKeys(date);
        $(FILTER_DATE).sendKeys(Keys.ENTER);
        return this;
    }

    public int getLogsCount() {
        logger.info("Получаем количество записей");
        return $$(WATCH_BTN).filter(visible).size();
    }
}
