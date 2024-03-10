package end2end.pages;

import end2end.pages.mentor.StartMentorPage;
import end2end.pages.participant.StartParticipantPage;
import end2end.pages.registration.LoginPage;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public interface IStartPage {
    By TITLE = By.xpath(".//*[text()='Мои участники']");

    static IStartPage get() {
        if ($(TITLE).is(visible, Duration.ofSeconds(5))) {
            return new StartMentorPage();
        }
        return new StartParticipantPage();
    }

    SettingsPageFactory goToSettings();

    LoginPage logout();
}
