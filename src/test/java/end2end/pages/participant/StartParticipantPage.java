package end2end.pages.participant;

import end2end.pages.SettingsPageFactory;
import end2end.pages.IStartPage;
import end2end.pages.registration.LoginPage;
import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Стартовая страница участника
 */
public class StartParticipantPage extends PageBase implements IStartPage {
    private static final By DO_LOG_BUTTON = By.xpath(".//vaadin-button[@theme='icon primary']");
    private static final By CHOICE_DATE = By.xpath(".//vaadin-date-picker");
    private static final By CHOICE_TYPE_LOG = By.xpath(".//vaadin-combo-box");
    private static final By NAVBAR = By.xpath(".//*[@slot='navbar']");
    private static final By NAVBAR_BUTTONS = By.xpath(".//*[contains(@theme,'icon')]");


    @Override
    protected void checkPage() {
        $(DO_LOG_BUTTON).shouldBe(visible.because("Нет кнопки для создания записи"));
        $(CHOICE_DATE).shouldBe(visible.because("Нет поля для выбора даты"));
        $(CHOICE_TYPE_LOG).shouldBe(visible.because("Нет выбора типа записи"));
        logger.info("Загрузилась стартовая страница участника");
    }

    @Override
    public SettingsPageFactory goToSettings() {
        logger.info("Кликаем на кнопку настроек");
        $(NAVBAR).$$(NAVBAR_BUTTONS)
                .get(4)
                .shouldBe(visible.because("Не отобразилась кнопка настроек"))
                .click();
        return new SettingsPageFactory();
    }

    public LoginPage logout() {
        $(NAVBAR).$$(NAVBAR_BUTTONS)
                .get(5)
                .parent()
                .shouldBe(visible.because("Нет кнопки выхода"))
                .click();
        return new LoginPage();
    }
}
