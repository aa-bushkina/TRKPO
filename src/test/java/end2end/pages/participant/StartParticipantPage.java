package end2end.pages.participant;

import end2end.pages.registration.LoginPage;
import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Стартовая страница участника
 */
public class StartParticipantPage extends PageBase {
    private static final By DO_LOG_BUTTON = By.xpath(".//vaadin-button[@theme='icon primary']");
    private static final By CHOICE_DATE = By.xpath(".//vaadin-date-picker");
    private static final By CHOICE_TYPE_LOG = By.xpath(".//vaadin-select");
    private static final By NAVBAR = By.xpath(".//*[@slot='navbar']");
    private static final By NOTIFICATIONS_BTN = By.xpath(".//*[@theme='icon'][1]/.");
    private static final By LOGS_BTN = By.xpath(".//*[contains(@theme,'icon')][2]/.");
    private static final By QUESTIONS_BTN = By.xpath(".//*[contains(@theme,'icon')][3]/.");
    private static final By SETTINGS_BTN = By.xpath(".//*[contains(@theme,'icon')][4]/.");
    private static final By LOGOUT_BTN = By.xpath(".//*[contains(@theme,'icon')][5]/.");
    private static final By SPORT_TYPE = By.xpath(".//vaadin-item[2]");
    private static final By EAT_TYPE = By.xpath(".//vaadin-item[3]");
    private static final By EMOTIONAL_TYPE = By.xpath(".//vaadin-item[1]");


    @Override
    protected void checkPage() {
        $(DO_LOG_BUTTON).shouldBe(visible.because("Нет кнопки для создания записи"));
        $(CHOICE_DATE).shouldBe(visible.because("Нет поля для выбора даты"));
        $(CHOICE_TYPE_LOG).shouldBe(visible.because("Нет выбора типа записи"));
        logger.info("Загрузилась стартовая страница участника");
    }

    public LogsParticipantPage goToLogs() {
        logger.info("Кликаем на кнопку журнала");
        $(NAVBAR).$(LOGS_BTN)
                .shouldBe(visible.because("Не отобразилась кнопка журнала"))
                .click();
        return new LogsParticipantPage();
    }

    public QuestionsParticipantPage goToQuestions() {
        logger.info("Кликаем на кнопку вопросов");
        $(NAVBAR).$(QUESTIONS_BTN)
                .shouldBe(visible.because("Не отобразилась кнопка вопросов"))
                .click();
        return new QuestionsParticipantPage();
    }

    public SettingsParticipantPage goToSettings() {
        logger.info("Кликаем на кнопку настроек");
        $(NAVBAR).$(SETTINGS_BTN)
                .shouldBe(visible.because("Не отобразилась кнопка настроек"))
                .click();
        return new SettingsParticipantPage();
    }

    public NotificationsParticipantPage goToNotifications() {
        logger.info("Кликаем на кнопку уведомлений");
        $(NAVBAR).$(NOTIFICATIONS_BTN)
                .shouldBe(visible.because("Не отобразилась кнопка уведомлений"))
                .click();
        return new NotificationsParticipantPage();
    }

    public LoginPage logout() {
        logger.info("Кликаем на кнопку выхода");
        $(NAVBAR).$(LOGOUT_BTN)
                .shouldBe(visible.because("Нет кнопки выхода"))
                .click();
        return new LoginPage();
    }

    public SportLogPage clickAddLogButton() {
        logger.info("Кликаем на кнопку добавления записи");
        $(DO_LOG_BUTTON)
                .shouldBe(visible.because("Нет кнопки добавления записи"))
                .click();
        return new SportLogPage();
    }

    public StartParticipantPage chooseSportType() {
        logger.info("Выбираем тип записи: спортивная активность");
        $(CHOICE_TYPE_LOG)
                .shouldBe(visible.because("Нет кнопки выбора типа записи"))
                .click();
        $(SPORT_TYPE)
                .shouldBe(visible.because("Нет кнопки выбора типа записи: спортивная активность"))
                .click();
        return this;
    }

    public StartParticipantPage chooseEatType() {
        logger.info("Выбираем тип записи: прием пищи");
        $(CHOICE_TYPE_LOG)
                .shouldBe(visible.because("Нет кнопки выбора типа записи"))
                .click();
        $(EAT_TYPE)
                .shouldBe(visible.because("Нет кнопки выбора типа записи: прием пищи"))
                .click();
        return this;
    }

    public StartParticipantPage chooseEmotionalType() {
        logger.info("Выбираем тип записи: эмоциональное состояние");
        $(CHOICE_TYPE_LOG)
                .shouldBe(visible.because("Нет кнопки выбора типа записи"))
                .click();
        $(EMOTIONAL_TYPE)
                .shouldBe(visible.because("Нет кнопки выбора типа записи: эмоциональное состояние"))
                .click();
        return this;
    }
}
