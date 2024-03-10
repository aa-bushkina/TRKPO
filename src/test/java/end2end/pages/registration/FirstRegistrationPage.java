package end2end.pages.registration;

import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Страница выбора типа юзера
 */
public class FirstRegistrationPage extends PageBase {

    private static final By PARTICIPANT_BUTTON = By.xpath(".//*[text()='Участник марафона']");
    private static final By MENTOR_BUTTON = By.xpath(".//*[text()='Ментор']");

    @Override
    protected void checkPage() {
        $(PARTICIPANT_BUTTON).shouldBe(visible.because("Не видна кнопка для выбора участника"));
        $(MENTOR_BUTTON).shouldBe(visible.because("Не видна кнопка для выбора ментора"));
        logger.info("Загрузилась стартовая выбора типа юзера");
    }

    public SecondRegistrationPage clickParticipant() {
        logger.info("Выбираем участника");
        $(PARTICIPANT_BUTTON).shouldBe(visible.because("Не видна кнопка для выбора участника")).click();
        return new SecondRegistrationPage();
    }
    public SecondRegistrationPage clickMentor() {
        logger.info("Выбираем ментора");
        $(MENTOR_BUTTON).shouldBe(visible.because("Не видна кнопка для выбора ментора")).click();
        return new SecondRegistrationPage();
    }
}
