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
}
