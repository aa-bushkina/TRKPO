package end2end.pages.registration;

import end2end.pages.mentor.StartPageMentorPage;
import end2end.pages.participant.StartPageParticipantPage;
import end2end.pages.utils.PageBase;
import end2end.pages.participant.RegistrationWithParametersPage;
import end2end.pages.utils.Sex;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Третья страница регистрации
 */
public class ThirdRegistrationPage extends PageBase {

    public static final By WOMEN_RADIO_BUTTON = By.xpath(".//vaadin-radio-button[1]");
    public static final By MEN_RADIO_BUTTON = By.xpath(".//vaadin-radio-button[2]");
    public static final By INPUT_PHONE = By.xpath(".//vaadin-text-field");
    public static final By DATE_PICKER = By.xpath(".//vaadin-date-picker");
    public static final By CONTINUE_BUTTON = By.xpath(".//*[text()='Далее']");
    public static final By END_REGISTRATION_BUTTON = By.xpath(".//*[text()='Завершить']");

    @Override
    protected void checkPage() {
        $(MEN_RADIO_BUTTON).shouldBe(visible.because("Нет радио баттана для выбора М"));
        $(WOMEN_RADIO_BUTTON).shouldBe(visible.because("Нет радио баттана для выбора Ж"));
        $(INPUT_PHONE).shouldBe(visible.because("Нет инпута для ввода телефона"));
        $(DATE_PICKER).shouldBe(visible.because("Нет инпута для ввода даты рождения"));
        logger.info("Загрузилась третья страница регистрации");
    }

    public ThirdRegistrationPage choiceSex(Sex sex) {
        switch (sex) {
            case MEN:
                logger.info("Выбираем пол М");
                $(MEN_RADIO_BUTTON).shouldBe(visible.because("Нет радио баттана для выбора М")).click();
                break;
            case WOMEN:
                logger.info("Выбираем пол Ж");
                $(WOMEN_RADIO_BUTTON).shouldBe(visible.because("Нет радио баттана для выбора Ж")).click();
                break;
        }
        return this;
    }

    public ThirdRegistrationPage typePhone(String phone) {
        logger.info("Вводим телефон " + phone);
        $(INPUT_PHONE).shouldBe(visible.because("Нет инпута для ввода телефона")).sendKeys(phone);
        return this;
    }

    public ThirdRegistrationPage choiceDate(String date) {
        logger.info("Выбираем дату рождения " + date);
        $(DATE_PICKER).shouldBe(visible.because("Нет инпута для ввода даты рождения")).sendKeys(date);
        $(DATE_PICKER).sendKeys(Keys.ENTER);
        return this;
    }

    public RegistrationWithParametersPage doContinue() {
        logger.info("Нажимаем на далее");
        $(CONTINUE_BUTTON).shouldBe(visible.because("Нет кнопки 'Далее'")).click();
        return new RegistrationWithParametersPage();
    }

    public StartPageMentorPage endRegistration() {
        logger.info("Нажимаем на 'Завершить'");
        $(END_REGISTRATION_BUTTON).shouldBe(visible.because("Нет кнопки 'Завершить'")).click();
        return new StartPageMentorPage();
    }

}
