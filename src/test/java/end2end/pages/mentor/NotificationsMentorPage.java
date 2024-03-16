package end2end.pages.mentor;

import end2end.pages.participant.OneNotificationParticipantPage;
import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Страница уведомлений ментора
 */
public class NotificationsMentorPage extends PageBase {
    private static final By TITLE = By.xpath(".//*[text()='Уведомления']");
    private static final By TABLE = By.xpath(".//vaadin-grid");
    private static final By HOME_BTN = By.xpath(".//*[@icon='vaadin:home']/..");
    private static final By LOOK_BTN = By.xpath(".//vaadin-button[@theme='primary']");

    @Override
    protected void checkPage() {
        $(TITLE).shouldBe(visible.because("Нет заголовка страницы уведомлений"));
        $(TABLE).shouldBe(visible.because("Нет таблицы уведомлений"));
        logger.info("Загрузилась страница уведомлений");
    }

    public StartMentorPage goToStartPage() {
        logger.info("Кликаем на кнопку домой");
        $(HOME_BTN).parent()
                .shouldBe(visible.because("Нет кнопки домой"))
                .click();
        return new StartMentorPage();
    }

    public OneNotificationMentorPage lookNotification() {
        logger.info("Нажимаем Смотреть у единственного уведомления");
        $(LOOK_BTN).shouldBe(visible.because("Нет кнопки Смотреть")).click();
        return new OneNotificationMentorPage();
    }

}
