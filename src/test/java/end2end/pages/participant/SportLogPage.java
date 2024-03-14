package end2end.pages.participant;

import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Страница создания записи о спорте
 */
public class SportLogPage extends PageBase {
    private static final By TITLE = By.xpath(".//*[text()='Спортивная активность']");
    private static final By DURATION = By.xpath(".//vaadin-text-field[1]");
    private static final By ACTIVITY = By.xpath(".//vaadin-text-field[2]");
    private static final By INTENSITY = By.xpath(".//vaadin-select");
    private static final By COMMENT = By.xpath(".//vaadin-text-area");
    private static final By SEND_BTN = By.xpath(".//*[text()='Отправить']");
    private static final By LOW = By.xpath(".//vaadin-item[1]");
    private static final By MIDDLE = By.xpath(".//vaadin-item[2]");
    private static final By HIGH = By.xpath(".//vaadin-item[3]");

    protected void checkPage() {
        $(TITLE).shouldBe(visible.because("Нет заголовка страницы спортивной записи"));
        $(DURATION).shouldBe(visible.because("Нет поля длительности"));
        $(ACTIVITY).shouldBe(visible.because("Нет поля типа активности"));
        $(INTENSITY).shouldBe(visible.because("Нет поля интенсивности"));
        $(COMMENT).shouldBe(visible.because("Нет поля комментария"));
        $(SEND_BTN).shouldBe(visible.because("Нет кнопки 'Отправить'"));
        logger.info("Загрузилась страница добавления записи о спорте");
    }

    public SportLogPage typeDuration(String text) {
        logger.info("Вводим длительность");
        $(DURATION)
                .shouldBe(visible.because("Нет поля ввода длительности"))
                .sendKeys(text);
        return this;
    }

    public SportLogPage typeActivity(String text) {
        logger.info("Вводим тип активности");
        $(ACTIVITY)
                .shouldBe(visible.because("Нет поля ввода типа активности"))
                .sendKeys(text);
        return this;
    }

    public SportLogPage typeComment(String text) {
        logger.info("Вводим комментарий");
        $(COMMENT)
                .shouldBe(visible.because("Нет поля ввода комментария"))
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

    public SportLogPage chooseLowIntensity() {
        logger.info("Выбираем тип активности: низкая");
        $(INTENSITY)
                .shouldBe(visible.because("Нет кнопки выбора типа активности"))
                .click();
        $(LOW)
                .shouldBe(visible.because("Нет кнопки выбора типа активности: низкая"))
                .click();
        return this;
    }

    public SportLogPage chooseMiddleIntensity() {
        logger.info("Выбираем тип активности: средняя");
        $(INTENSITY)
                .shouldBe(visible.because("Нет кнопки выбора типа активности"))
                .click();
        $(MIDDLE)
                .shouldBe(visible.because("Нет кнопки выбора типа активности: средняя"))
                .click();
        return this;
    }

    public SportLogPage chooseHighIntensity() {
        logger.info("Выбираем тип активности: высокая");
        $(INTENSITY)
                .shouldBe(visible.because("Нет кнопки выбора типа активности"))
                .click();
        $(HIGH)
                .shouldBe(visible.because("Нет кнопки выбора типа активности: высокая"))
                .click();
        return this;
    }
}
