package com.cygans.database.participant_mentor;

import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipantMentorService {
    @Autowired
    ParticipantMentorRepository participantMentorRepository;
    @Autowired
    ParticipantService participantService;

    public Boolean isNeedToConnectUsers(long mentId, long partId)
    {
        List<ParticipantMentor> participantMentorList= participantMentorRepository.findAll();
        for (ParticipantMentor participantMentor : participantMentorList) {
            if (participantMentor.getMentorId() == mentId && participantMentor.getParticipantId() == partId)
                return false;
        }
        return true;
    }

    public void create(Long participantId, Long mentorId) {
        participantMentorRepository.save(new ParticipantMentor(participantId, mentorId));
    }

    public List<Participant> getParticipantsByMentor(Long mentorId) {
        List<ParticipantMentor> participantMentorList = participantMentorRepository.getAllByMentorId(mentorId);
        List<Participant> participants = new ArrayList<>();
        for (ParticipantMentor log : participantMentorList) {
            participants.add(participantService.getParticipantById(log.getParticipantId()));
        }
        return participants;
    }

    public boolean existByParticipantId(Long participantId) {
        return participantMentorRepository.findByParticipantId(participantId) != null;
    }

    public void deleteParticipant(Long id) {
        ParticipantMentor delete = participantMentorRepository.findByParticipantId(id);
        participantMentorRepository.delete(delete);
    }

    public ParticipantMentor getMentorParticipantByParticipantId(Long participantId) {
        return participantMentorRepository.findByParticipantId(participantId);
    }

}
