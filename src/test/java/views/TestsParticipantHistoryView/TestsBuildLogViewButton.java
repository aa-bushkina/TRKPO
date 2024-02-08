package views.TestsParticipantHistoryView;

import com.cygans.database.controllers.LogController;
import com.cygans.views.participant.history.ParticipantHistoryView;
import com.cygans.views.participant.logbooks.ParticipantPersonData;
import com.vaadin.flow.component.button.Button;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class TestsBuildLogViewButton {

    @Mock
    private LogController logController;

    @InjectMocks
    private ParticipantHistoryView participantHistoryView;


    @Test
    void testBuildLogViewButton() {
        ParticipantPersonData mockPersonData = new ParticipantPersonData();
        Button mockButton = participantHistoryView.buildLogViewButton(mockPersonData);
        assertNotNull(mockButton);
    }

}
