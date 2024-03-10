package end2end.pages.utils;

import end2end.pages.mentor.ToolbarMentor;
import end2end.pages.participant.ToolbarParticipant;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public interface IToolbar {
    By NAVBAR = By.xpath(".//*[@slot='navbar']");
    By BUTTONS = By.xpath(".//*[contains(@theme,'icon')]");
    int PARTICIPANT_BUTTONS_COUNT = 6;

    public static IToolbar get() {
        if ($(NAVBAR).$$(BUTTONS).size() == PARTICIPANT_BUTTONS_COUNT) {
            return new ToolbarParticipant();
        }
        return new ToolbarMentor();
    }
}
