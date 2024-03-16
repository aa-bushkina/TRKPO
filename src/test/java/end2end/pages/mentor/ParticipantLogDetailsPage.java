package end2end.pages.mentor;

import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Страница просмотра деталей записи участника
 */
public class ParticipantLogDetailsPage extends PageBase {

    private static final By TITLE = By.xpath(".//vaadin-vertical-layout//h2");
    private static final By DATE = By.xpath(".//vaadin-vertical-layout//h3");
    private static final By DESC_TEXT = By.xpath(".//vaadin-text-area");

    @Override
    protected void checkPage() {
        $(TITLE).shouldBe(visible.because("Нет название записи"));
        $(DATE).shouldBe(visible.because("Нет даты записи"));
        $(DESC_TEXT).shouldBe(visible.because("Нет описания записи"));
        logger.info("Загрузилась страница просмотра деталей записи участника");
    }

    public String getDescription() {
        return $(DESC_TEXT).shouldBe(visible.because("Нет описания записи")).getValue();
    }

    public String getDate() {
        return $(DATE).shouldBe(visible.because("Нет даты записи")).getText();
    }

}
