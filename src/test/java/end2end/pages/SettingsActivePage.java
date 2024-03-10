package end2end.pages;

import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Страница настроек с возможностью редактирования полей
 */
public class SettingsActivePage extends PageBase implements ISettingsActivePage {
    private static final By TITLE = By.xpath(".//*[text()='Настройки']");
    private static final By CANCEL_BTN = By.xpath(".//*[text()='Отменить']");
    private static final By SAVE_BTN = By.xpath(".//*[text()='Сохранить']");

    @Override
    protected void checkPage() {
        $(TITLE).shouldBe(visible.because("Нет заголовка страницы настроек"));
        $(CANCEL_BTN).shouldBe(visible.because("Нет кнопки 'Отменить'"));
        $(SAVE_BTN).shouldBe(visible.because("Нет кнопки 'Сохранить'"));
        logger.info("Загрузилась страница настроек с возможностью редактирования");
    }

    @Override
    public SettingsPage saveChanges() {
        logger.info("Кликаем на кнопку 'Сохранить'");
        $(SAVE_BTN).shouldBe(visible.because("Нет кнопки 'Сохранить'"));
        return new SettingsPage();
    }
}
