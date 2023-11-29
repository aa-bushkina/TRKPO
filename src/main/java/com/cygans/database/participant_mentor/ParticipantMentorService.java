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

    public void create(Long participantId, Long mentorId) {
        participantMentorRepository.save(new ParticipantMentor(participantId, mentorId));
    }

    public String search(long id) {
        return participantMentorRepository.findById(id).toString();
    }

    public List<ParticipantMentor> getMentorParticipantsList(Long mentorId){
        return participantMentorRepository.getAllByMentorId(mentorId);
    }

    public List<Participant> getParticipantsByMentor(Long mentorId) {
        List<ParticipantMentor> participantMentorList = participantMentorRepository.getAllByMentorId(mentorId);
        List<Participant> participants = new ArrayList<>();
        for (ParticipantMentor log : participantMentorList) {
            participants.add(participantService.getParticipantById(log.getParticipantId()));
        }
        return participants;
    }

    public void updateParticipantId(long id, Long participantId) {
        participantMentorRepository.getMentorParticipantById(id).setParticipantId(participantId);
    }

    public void updateMentorId(long id, Long mentorId) {
        participantMentorRepository.getMentorParticipantById(id).setMentorId(mentorId);
    }

    public boolean exist(Long participantId) {
        return participantMentorRepository.getMentorParticipantByParticipantId(participantId) != null;
    }

    public void deletePatient(Long patientuid) {
        ParticipantMentor delete = participantMentorRepository.getMentorParticipantByParticipantId(patientuid);
        participantMentorRepository.delete(delete);
    }

    public boolean checkParticipant(Long participantId) {
        return participantMentorRepository.findByParticipantId(participantId) != null;
    }

    public ParticipantMentor findByParticipantId(Long participantId) {
        return participantMentorRepository.findByParticipantId(participantId);
    }

    public ParticipantMentor getMentorParticipantByParticipantId(Long participantId) {
        return participantMentorRepository.getMentorParticipantByParticipantId(participantId);
    }

}
