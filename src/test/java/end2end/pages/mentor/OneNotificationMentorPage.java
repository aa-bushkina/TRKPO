package end2end.pages.mentor;

import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Страница подробного просмотра уведомления ментором
 */
public class OneNotificationMentorPage extends PageBase {

    private static final By MESSAGE = By.xpath(".//vaadin-text-area[1]");
    private static final By ANSWER = By.xpath(".//vaadin-text-area[2]");
    private static final By BACK_BUTTON = By.xpath(".//*[text()='Назад']");
    private static final By SEND_BUTTON = By.xpath(".//*[text()='Отправить']");
    private static final String READ_ONLY_ATTR = "readonly";

    @Override
    protected void checkPage() {
        $(MESSAGE).shouldBe(visible.because("Нет сообщения уведомления"));
        $(ANSWER).shouldBe(visible.because("Нет поля для ответа на уведолмение"));
        $(BACK_BUTTON).shouldBe(visible.because("Нет кнопки Назад"));
        logger.info("Загрузилась страница просмотра уведолменяи ментора");
    }

    public String getMessage() {
        return $(MESSAGE).getValue();
    }

    public OneNotificationMentorPage answerOnNotification(String answer) {
        logger.info("Отвечаем на уведомление " + answer);
        $(ANSWER).shouldBe(visible.because("Нет поля для ответа на уведолмение"));
        assertNull($(ANSWER).getAttribute(READ_ONLY_ATTR), "Поле для ответа нельзя редактировтаь");
        executeJavaScript("arguments[0].value = '';", $(ANSWER));
        $(ANSWER).sendKeys(answer);
        return this;
    }

    public NotificationsMentorPage sendAnswer() {
        logger.info("нажимаем кнопку Отправить");
        $(SEND_BUTTON).shouldBe(visible.because("Нет кнопки Отправить")).click();
        return new NotificationsMentorPage();
    }

}
