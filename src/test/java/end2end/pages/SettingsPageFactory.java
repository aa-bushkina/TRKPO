package end2end.pages;

import end2end.pages.mentor.SettingsMentorPage;
import end2end.pages.participant.SettingsParticipantPage;

public class SettingsPageFactory {
    public SettingsParticipantPage andReturnParticipantSettingsPage() {
        return new SettingsParticipantPage();
    }

    public SettingsMentorPage andReturnMentorSettingsPage() {
        return new SettingsMentorPage();
    }
}
