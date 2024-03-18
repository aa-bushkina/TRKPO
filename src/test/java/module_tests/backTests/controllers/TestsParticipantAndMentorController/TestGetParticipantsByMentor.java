package module_tests.backTests.controllers.TestsParticipantAndMentorController;


import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.mentor.Mentor;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestGetParticipantsByMentor {

    @InjectMocks
    private ParticipantAndMentorController controller;

    @Mock
    private ParticipantMentorService participantMentorService;


    @Test
    void testGetParticipantsByMentor() {
        Mentor mentor = new Mentor();
        mentor.setId(123L);

        when(participantMentorService.getParticipantsByMentor(anyLong())).thenReturn(new ArrayList<>());
        List<Participant> participants = controller.getParticipantsByMentor(mentor);

        verify(participantMentorService).getParticipantsByMentor(mentor.getId());
        assertEquals(ArrayList.class, participants.getClass());
    }
}
