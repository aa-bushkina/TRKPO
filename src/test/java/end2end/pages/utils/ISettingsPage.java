package end2end.pages.utils;

import end2end.pages.mentor.SettingsMentorPage;
import end2end.pages.participant.SettingsParticipantPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public interface ISettingsPage {
    By FIELDS = By.xpath(".//vaadin-text-field");
    int PARTICIPANT_FIELD_COUNT = 9;

    static ISettingsPage get() {
        if ($$(FIELDS).size() == PARTICIPANT_FIELD_COUNT) {
            return new SettingsParticipantPage();
        }
        return new SettingsMentorPage();
    }
}
