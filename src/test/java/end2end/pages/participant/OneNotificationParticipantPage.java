package end2end.pages.participant;

import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Страница подробного просмотра уведомления участнику
 */
public class OneNotificationParticipantPage extends PageBase {
    private static final By MESSAGE = By.xpath(".//vaadin-text-area[1]");
    private static final By ANSWER = By.xpath(".//vaadin-text-area[2]");
    private static final By ACCEPT_BUTTON = By.xpath(".//*[text()='Принять']");
    private static final By BACK_BUTTON = By.xpath(".//*[text()='Назад']");
    private static final By HOME = By.xpath(".//vaadin-button[@theme='icon tertiary']");

    @Override
    protected void checkPage() {
        $(MESSAGE).shouldBe(visible.because("Нет текста уведомления"));
        logger.info("Загрузилась страница уведомления");
    }

    public NotificationsParticipantPage acceptRequest() {
        logger.info("Принимаем запрос на отслеживание");
        $(ACCEPT_BUTTON).shouldBe(visible.because("Нет кнопки 'Принять'")).click();
        return new NotificationsParticipantPage();
    }

    public String getAnswer() {
        return $(ANSWER).shouldBe(visible.because("Нет ответа ментора")).getValue();
    }

    public NotificationsParticipantPage back() {
        logger.info("Возвращаемся на страницу уведомлений");
        $(BACK_BUTTON).shouldBe(visible.because("Нет кнопки назад")).click();
        return new NotificationsParticipantPage();
    }


}
