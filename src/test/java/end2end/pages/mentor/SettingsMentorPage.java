package end2end.pages.mentor;

import end2end.pages.ChangePasswordPage;
import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Страница настроек ментора
 */
public class SettingsMentorPage extends PageBase {
    private static final By TITLE = By.xpath(".//*[text()='Настройки']");
    private static final By CHANGE_PASS_BTN = By.xpath(".//*[text()='Изменить пароль']");
    private static final By CHANGE_INFO_BTN = By.xpath(".//*[text()='Редактировать']");
    private static final By HOME_BTN = By.xpath(".//*[@icon='vaadin:home']/../..");

    @Override
    protected void checkPage() {
        $(TITLE).shouldBe(visible.because("Нет заголовка страницы настроек"));
        $(CHANGE_PASS_BTN).shouldBe(visible.because("Нет кнопки 'Изменить пароль'"));
        $(CHANGE_INFO_BTN).shouldBe(visible.because("Нет кнопки 'Редактировать'"));
        logger.info("Загрузилась страница настроек");
    }

    public ChangePasswordPage changePassword() {
        logger.info("Кликаем на 'Изменить пароль'");
        $(CHANGE_PASS_BTN).shouldBe(visible.because("Нет кнопки 'Изменить пароль'")).click();
        return new ChangePasswordPage();
    }

    public SettingsActiveMentorPage changeInfo() {
        logger.info("Кликаем на 'Редактировать'");
        $(CHANGE_INFO_BTN).shouldBe(visible.because("Нет кнопки 'Редактировать'")).click();
        return new SettingsActiveMentorPage();
    }

    public StartMentorPage goToStartPage() {
        logger.info("Кликаем на кнопку домой");
        $(HOME_BTN).shouldBe(visible.because("Нет кнопки домой")).click();
        return new StartMentorPage();
    }
}
