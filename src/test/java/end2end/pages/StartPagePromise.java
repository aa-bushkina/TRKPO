package end2end.pages;

import end2end.pages.mentor.StartMentorPage;
import end2end.pages.participant.StartParticipantPage;

public class StartPagePromise {
    public StartMentorPage andReturnStartMentorPage() {
        return new StartMentorPage();
    }

    public StartParticipantPage andReturnStartParticipantPage() {
        return new StartParticipantPage();
    }
}
