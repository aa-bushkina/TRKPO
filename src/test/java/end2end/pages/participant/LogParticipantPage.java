package end2end.pages.participant;

import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Страница одной записи участника
 */
public class LogParticipantPage extends PageBase {

    private static final By TITLE = By.xpath(".//vaadin-vertical-layout//h2");
    private static final By DATE = By.xpath(".//vaadin-vertical-layout//h3");
    private static final By DESC_TEXT = By.xpath(".//vaadin-text-area");
    private static final By CHANGE_BTN = By.xpath(".//*[text()='Редактировать']");
    private static final By SAVE_BTN = By.xpath(".//*[text()='Сохранить']");
    private static final By HOME = By.xpath(".//vaadin-button[@theme='icon tertiary']");

    @Override
    protected void checkPage() {
        $(TITLE).shouldBe(visible.because("Нет название записи"));
        $(DATE).shouldBe(visible.because("Нет даты записи"));
        $(DESC_TEXT).shouldBe(visible.because("Нет описания записи"));
        logger.info("Загрузилась страница просмотра записи");
    }


    public LogParticipantPage clickChange() {
        logger.info("Нажимаем на Редактировать");
        $(CHANGE_BTN).shouldBe(visible.because("Нет кнопки Редактировать")).click();
        return new LogParticipantPage();
    }

    public LogParticipantPage addText(String text) {
        logger.info("Добавим в описание " + text);
        $(DESC_TEXT).shouldBe(visible.because("Нет описания записи")).sendKeys(text);
        return this;
    }

    public LogParticipantPage clickSave() {
        logger.info("Нажимаем сохранить");
        $(SAVE_BTN).shouldBe(visible.because("Нет кнопки Сохранить")).click();
        return new LogParticipantPage();
    }

    public String getDescription() {
        return $(DESC_TEXT).shouldBe(visible.because("Нет описания записи")).getValue();
    }

    public StartParticipantPage goToStartPage() {
        logger.info("Возвращаемся на стартовую страницу");
        $(HOME).shouldBe(visible.because("Нет кнопки домой")).click();
        return new StartParticipantPage();
    }

}
