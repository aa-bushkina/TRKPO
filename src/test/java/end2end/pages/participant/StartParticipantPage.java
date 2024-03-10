package end2end.pages.participant;

import end2end.pages.utils.PageBase;
import end2end.pages.utils.StartPageFactory;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Стартовая страница участника
 */
public class StartParticipantPage extends PageBase implements StartPageFactory {

    private static final By DO_LOG_BUTTON = By.xpath(".//vaadin-button[@theme='icon primary']");
    private static final By CHOICE_DATE = By.xpath(".//vaadin-date-picker");
    private static final By CHOICE_TYPE_LOG = By.xpath(".//vaadin-combo-box");

    @Override
    protected void checkPage() {
        $(DO_LOG_BUTTON).shouldBe(visible.because("Нет кнопки для создания записи"));
        $(CHOICE_DATE).shouldBe(visible.because("Нет поля для выбора даты"));
        $(CHOICE_TYPE_LOG).shouldBe(visible.because("Нет выбора типа записи"));
        logger.info("Загрузилась стартовая страница участника");
    }

}
