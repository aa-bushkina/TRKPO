package end2end.pages.mentor;

import end2end.pages.utils.PageBase;
import end2end.pages.utils.StartPageFactory;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Стартовая страница ментора
 */
public class StartMentorPage extends PageBase implements StartPageFactory {

    private static final By TITLE = By.xpath(".//h3[text()='Мои участники']");
    private static final By TABLE = By.xpath(".//vaadin-grid[@theme='column-borders no-border']");
    private static final By ADD_PARTICIPANT_BTN = By.xpath(".//h3[text()='Мои участники']/..//vaadin-button[@theme='icon']");

    @Override
    protected void checkPage() {
        $(TITLE).shouldBe(visible.because("Нет заголовка страницы моих записей"));
        $(ADD_PARTICIPANT_BTN).shouldBe(visible.because("Нет кнопки дабовления участника"));
        $(TABLE).shouldBe(visible.because("Нет таблицы участников"));
        logger.info("Загрузилась стартовая страница ментора");
    }

}
