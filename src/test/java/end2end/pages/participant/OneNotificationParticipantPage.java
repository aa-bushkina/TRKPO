package end2end.pages.participant;

import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Страница подробного просмотра уведомления участнику
 */
public class OneNotificationParticipantPage extends PageBase {

    private static final By TEXT = By.xpath(".//vaadin-text-area[@readonly]");
    private static final By ACCEPT_BUTTON = By.xpath(".//*[text()='Принять']");

    @Override
    protected void checkPage() {
        $(TEXT).shouldBe(visible.because("Нет текста уведомления"));
        logger.info("Загрузилась страница уведомления");
    }

    public NotificationsParticipantPage acceptRequest() {
        logger.info("Принимаем запрос на отслеживание");
        $(ACCEPT_BUTTON).shouldBe(visible.because("Нет кнопки 'Принять'")).click();
        return new NotificationsParticipantPage();
    }

}
