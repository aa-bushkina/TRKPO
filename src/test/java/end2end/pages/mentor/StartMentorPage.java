package end2end.pages.mentor;

import end2end.pages.registration.LoginPage;
import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Стартовая страница ментора
 */
public class StartMentorPage extends PageBase {
    private static final By TITLE = By.xpath(".//h3[text()='Мои участники']");
    private static final By TABLE = By.xpath(".//vaadin-grid[@theme='column-borders no-border']");
    private static final By ADD_PARTICIPANT_BTN = By.xpath(".//h3[text()='Мои участники']/..//vaadin-button[@theme='icon']");
    private static final By NAVBAR = By.xpath(".//*[@slot='navbar']");
    private static final By NOTIFICATIONS_BTN = By.xpath(".//*[@theme='icon'][1]/.");
    private static final By SETTINGS_BTN = By.xpath(".//*[contains(@theme,'icon')][2]/.");
    private static final By LOGOUT_BTN = By.xpath(".//*[contains(@theme,'icon')][3]/.");
    private static final String NAME_PARTICIPANT = ".//vaadin-grid-cell-content[@slot='vaadin-grid-cell-content-1' and text()='%s']";
    private static final String LASTNAME_PARTICIPANT = ".//vaadin-grid-cell-content[@slot='vaadin-grid-cell-content-2' and text()='%s']";
    private static final String LOGIN_PARTICIPANT = ".//vaadin-grid-cell-content[@slot='vaadin-grid-cell-content-3' and text()='%s']";

    @Override
    protected void checkPage() {
        $(TITLE).shouldBe(visible.because("Нет заголовка страницы моих записей"));
        $(ADD_PARTICIPANT_BTN).shouldBe(visible.because("Нет кнопки дабовления участника"));
        $(TABLE).shouldBe(visible.because("Нет таблицы участников"));
        logger.info("Загрузилась стартовая страница ментора");
    }

    public SettingsMentorPage goToSettings() {
        logger.info("Кликаем на кнопку настроек");
        $(NAVBAR).$(SETTINGS_BTN)
                .shouldBe(visible.because("Не отобразилась кнопка настроек"))
                .click();
        return new SettingsMentorPage();
    }

    public NotificationsMentorPage goToNotifications() {
        logger.info("Кликаем на кнопку уведомлений");
        $(NAVBAR).$(NOTIFICATIONS_BTN)
                .shouldBe(visible.because("Не отобразилась кнопка уведомлений"))
                .click();
        return new NotificationsMentorPage();
    }

    public LoginPage logout() {
        logger.info("Кликаем на кнопку выхода");
        $(NAVBAR).$(LOGOUT_BTN)
                .shouldBe(visible.because("Нет кнопки выхода"))
                .click();
        return new LoginPage();
    }

    public AddParticipantPage addParticipant() {
        logger.info("Нажимаем на кнопку добавления участника");
        $(ADD_PARTICIPANT_BTN).shouldBe(visible.because("Нет кнопки дабовления участника")).click();
        return new AddParticipantPage();
    }

    public StartMentorPage checkParticipant(String name, String lastName, String login) {
        logger.info("Проверяем, что есть участник " + name + " " + lastName + " " + login);
        $(By.xpath(String.format(NAME_PARTICIPANT, name))).shouldBe(visible.because("Нет участника с именем " + name));
        $(By.xpath(String.format(LASTNAME_PARTICIPANT, lastName))).shouldBe(visible.because("Нет участника с фамилией " + lastName));
        $(By.xpath(String.format(LOGIN_PARTICIPANT, login))).shouldBe(visible.because("Нет участника с логином " + login));
        return this;
    }

    public HistoryParticipantLogsPage clickParticipant(String login) {
        logger.info("Открываем историю записей у " + login);
        $(By.xpath(String.format(LOGIN_PARTICIPANT, login))).shouldBe(visible.because("Нет участника с логином " + login)).click();
        return new HistoryParticipantLogsPage();
    }

}
