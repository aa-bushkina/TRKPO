package end2end.pages;

import end2end.pages.mentor.SettingsActiveMentorPage;
import end2end.pages.participant.SettingsActiveParticipantPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public interface ISettingsActivePage {
    By FIELDS = By.xpath(".//vaadin-text-field");
    int PARTICIPANT_FIELD_COUNT = 9;

    static ISettingsActivePage get() {
        if ($$(FIELDS).size() == PARTICIPANT_FIELD_COUNT) {
            return new SettingsActiveParticipantPage();
        }
        return new SettingsActiveMentorPage();
    }

    SettingsPage saveChanges();
}
