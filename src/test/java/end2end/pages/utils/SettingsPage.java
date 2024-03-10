package end2end.pages.utils;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SettingsPage extends PageBase implements ISettingsPage {
    private static final By TITLE = By.xpath(".//*[text()='Настройки']");
    private static final By CHANGE_PASS_BTN = By.xpath(".//*[text()='Изменить пароль']");
    private static final By CHANGE_INFO_BTN = By.xpath(".//*[text()='Редактировать']");

    @Override
    protected void checkPage() {
        $(TITLE).shouldBe(visible.because("Нет заголовка страницы настроек"));
        $(CHANGE_PASS_BTN).shouldBe(visible.because("Нет кнопки 'Изменить пароль'"));
        $(CHANGE_INFO_BTN).shouldBe(visible.because("Нет кнопки 'Редактировать'"));
        logger.info("Загрузилась страница настроек");
    }
}
