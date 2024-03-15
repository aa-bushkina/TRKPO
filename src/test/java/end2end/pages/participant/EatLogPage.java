package end2end.pages.participant;

import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Страница создания записи о приеме пищи
 */
public class EatLogPage extends PageBase {
    private static final By TITLE = By.xpath(".//*[text()='Приём пищи']");
    private static final By HOURS = By.xpath(".//vaadin-select[1]");
    private static final By MINUTES = By.xpath(".//vaadin-select[2]");
    private static final By DESCRIPTION = By.xpath(".//vaadin-text-area");
    private static final By EAT_TYPE = By.xpath(".//vaadin-select[3]");
    private static final By SEND_BTN = By.xpath(".//*[text()='Отправить']");
    private static final By BREAKFAST = By.xpath(".//vaadin-item[1]");

    protected void checkPage() {
        $(TITLE).shouldBe(visible.because("Нет заголовка страницы о приеме пищи"));
        $(HOURS).shouldBe(visible.because("Нет поля часов"));
        $(MINUTES).shouldBe(visible.because("Нет поля минут"));
        $(DESCRIPTION).shouldBe(visible.because("Нет поля описания"));
        $(EAT_TYPE).shouldBe(visible.because("Нет поля типа приема пищи"));
        $(SEND_BTN).shouldBe(visible.because("Нет кнопки 'Отправить'"));
        logger.info("Загрузилась страница добавления записи о приеме пищи");
    }

    public EatLogPage typeDescription(String text) {
        logger.info("Вводим описание");
        $(DESCRIPTION)
                .shouldBe(visible.because("Нет поля ввода описания"))
                .sendKeys(text);
        return this;
    }


    public SendedLogPage clickSend() {
        logger.info("Кликаем на кнопку отправку записи");
        $(SEND_BTN)
                .shouldBe(visible.because("Нет кнопки отправки записи"))
                .click();
        return new SendedLogPage();
    }

    public EatLogPage chooseBreakfastType() {
        logger.info("Выбираем тип приема пищи: завтрак");
        $(EAT_TYPE)
                .shouldBe(visible.because("Нет кнопки выбора типа приема пищи"))
                .click();
        $(BREAKFAST)
                .shouldBe(visible.because("Нет кнопки выбора типа приема пищи: завтрак"))
                .click();
        return this;
    }
}
