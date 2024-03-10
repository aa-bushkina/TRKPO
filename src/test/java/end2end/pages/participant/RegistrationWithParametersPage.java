package end2end.pages.participant;

import end2end.pages.utils.PageBase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Страница ввода параметров участника при регистрации
 */
public class RegistrationWithParametersPage extends PageBase {

    public static final By HEIGHT = By.xpath(".//vaadin-text-field[1]");
    public static final By WEIGHT = By.xpath(".//vaadin-text-field[2]");
    public static final By WIDTH_BREAST = By.xpath(".//vaadin-text-field[1]");
    public static final By WIDTH_WAIST = By.xpath(".//vaadin-text-field[2]");
    public static final By WIDTH_HIPS = By.xpath(".//vaadin-text-field[3]");
    public static final By END_REGISTRATION_BUTTON = By.xpath(".//*[text()='Завершить']");

    @Override
    protected void checkPage() {
        $(HEIGHT).shouldBe(visible.because("Нет поля ввода для роста"));
        $(WEIGHT).shouldBe(visible.because("Нет поля ввода для веса"));
        $$(WIDTH_BREAST).get(1).shouldBe(visible.because("Нет поля ввода для обхвата груди"));
        $$(WIDTH_WAIST).get(1).shouldBe(visible.because("Нет поля ввода для обхвата талии"));
        $(WIDTH_HIPS).shouldBe(visible.because("Нет поля ввода для обхвата бедер"));
        $(END_REGISTRATION_BUTTON).shouldBe(visible.because("Нет кнопки 'Завершить'"));
        logger.info("Загрузилась страница ввода параметров участника при регистрации");
    }

    public RegistrationWithParametersPage typeHeight(String height) {
        logger.info("Вводим рост " + height);
        $(HEIGHT).shouldBe(visible.because("Нет поля ввода для роста")).sendKeys(height);
        return this;
    }

    public RegistrationWithParametersPage typeWeight(String weight) {
        logger.info("Вводим вес " + weight);
        $(WEIGHT).shouldBe(visible.because("Нет поля ввода для веса")).sendKeys(weight);
        return this;
    }

    public RegistrationWithParametersPage typeBreast(String breast) {
        logger.info("Вводим обхват груди " + breast);
        $$(WIDTH_BREAST).get(1).shouldBe(visible.because("Нет поля ввода для обхвата груди")).sendKeys(breast);
        return this;
    }

    public RegistrationWithParametersPage typeWaist(String waist) {
        logger.info("Вводим обхват талии " + waist);
        $$(WIDTH_WAIST).get(1).shouldBe(visible.because("Нет поля ввода для обхвата талии")).sendKeys(waist);
        return this;
    }

    public RegistrationWithParametersPage typeHips(String hips) {
        logger.info("Вводим обхват бедер " + hips);
        $(WIDTH_HIPS).shouldBe(visible.because("Нет поля ввода для обхвата бедер")).sendKeys(hips);
        return this;
    }

    public StartParticipantPage endRegistration() {
        logger.info("Нажимаем на 'Завершить'");
        $(END_REGISTRATION_BUTTON).shouldBe(visible.because("Нет кнопки 'Завершить'")).click();
        return new StartParticipantPage();
    }

}
