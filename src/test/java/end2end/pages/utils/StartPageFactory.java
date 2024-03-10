package end2end.pages.utils;

import end2end.pages.mentor.StartMentorPage;
import end2end.pages.participant.StartParticipantPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public interface StartPageFactory {
    By TITLE = By.xpath(".//h3[text()='Мои участники']");

    public static StartPageFactory get() {
        if ($(TITLE).is(visible)) {
            return new StartMentorPage();
        }
        return new StartParticipantPage();
    }
}
