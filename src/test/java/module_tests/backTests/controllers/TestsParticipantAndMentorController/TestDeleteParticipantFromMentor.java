package module_tests.backTests.controllers.TestsParticipantAndMentorController;


import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TestDeleteParticipantFromMentor {

    @InjectMocks
    private ParticipantAndMentorController controller;

    @Mock
    private ParticipantMentorService participantMentorService;

    @Test
    void testDeleteParticipantFromMentor() {
        Participant participant = new Participant();
        participant.setId(123L);

        controller.deleteParticipantFromMentor(participant);

        verify(participantMentorService).deleteParticipant(participant.getId());
    }
}
