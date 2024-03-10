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
    private static final By NAVBAR_BUTTONS = By.xpath(".//*[contains(@theme,'icon')]");

    @Override
    protected void checkPage() {
        $(TITLE).shouldBe(visible.because("Нет заголовка страницы моих записей"));
        $(ADD_PARTICIPANT_BTN).shouldBe(visible.because("Нет кнопки дабовления участника"));
        $(TABLE).shouldBe(visible.because("Нет таблицы участников"));
        logger.info("Загрузилась стартовая страница ментора");
    }

    public SettingsMentorPage goToSettings() {
        logger.info("Кликаем на кнопку настроек");
        $(NAVBAR).$$(NAVBAR_BUTTONS)
                .get(2)
                .shouldBe(visible.because("Не отобразилась кнопка настроек"))
                .click();
        return new SettingsMentorPage();
    }

    public NotificationsMentorPage goToNotifications() {
        logger.info("Кликаем на кнопку уведомлений");
        $(NAVBAR).$$(NAVBAR_BUTTONS)
                .get(1)
                .shouldBe(visible.because("Не отобразилась кнопка уведомлений"))
                .click();
        return new NotificationsMentorPage();
    }

    public LoginPage logout() {
        logger.info("Кликаем на кнопку выхода");
        $(NAVBAR).$$(NAVBAR_BUTTONS)
                .get(3)
                .shouldBe(visible.because("Нет кнопки выхода"))
                .click();
        return new LoginPage();
    }
}
