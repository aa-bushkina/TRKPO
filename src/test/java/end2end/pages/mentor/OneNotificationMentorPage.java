package end2end.pages.mentor;

import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Страница подробного просмотра уведомления ментором
 */
public class OneNotificationMentorPage extends PageBase {

    private static final By MESSAGE = By.xpath(".//vaadin-text-area[1]");
    private static final By ANSWER = By.xpath(".//vaadin-text-area[2]");
    private static final By BACK_BUTTON = By.xpath(".//*[text()='Назад']");

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

}
