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
    private static final By CHOICE_TYPE_LOG = By.xpath(".//vaadin-combo-box");
    private static final By NAVBAR = By.xpath(".//*[@slot='navbar']");
    private static final By NOTIFICATIONS_BTN = By.xpath(".//*[@theme='icon'][1]/.");
    private static final By LOGS_BTN = By.xpath(".//*[contains(@theme,'icon')][2]/.");
    private static final By QUESTIONS_BTN = By.xpath(".//*[contains(@theme,'icon')][3]/.");
    private static final By SETTINGS_BTN = By.xpath(".//*[contains(@theme,'icon')][4]/.");
    private static final By LOGOUT_BTN = By.xpath(".//*[contains(@theme,'icon')][5]/.");


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
}
