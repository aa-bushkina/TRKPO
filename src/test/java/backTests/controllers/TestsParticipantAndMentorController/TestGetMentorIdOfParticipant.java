package backTests.controllers.TestsParticipantAndMentorController;


import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant_mentor.ParticipantMentor;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestGetMentorIdOfParticipant {

    @InjectMocks
    private ParticipantAndMentorController controller;

    @Mock
    private ParticipantMentorService participantMentorService;

    @Test
    void testGetMentorIdOfParticipantWhenParticipantMentorExists() {
        Participant participant = new Participant();
        participant.setId(123L);
        ParticipantMentor participantMentor = new ParticipantMentor();
        participantMentor.setMentorId(456L);

        when(participantMentorService.getMentorParticipantByParticipantId(participant.getId())).thenReturn(participantMentor);

        Long mentorId = controller.getMentorIdOfParticipant(participant);

        verify(participantMentorService).getMentorParticipantByParticipantId(123L);
        assertEquals(456L, mentorId);
    }

    @Test
    void testGetMentorIdOfParticipantWhenParticipantMentorIsNull() {
        Participant participant = new Participant();
        participant.setId(123L);

        when(participantMentorService.getMentorParticipantByParticipantId(participant.getId())).thenReturn(null);

        Long mentorId = controller.getMentorIdOfParticipant(participant);

        verify(participantMentorService).getMentorParticipantByParticipantId(123L);
        assertEquals(null, mentorId);
    }
}
