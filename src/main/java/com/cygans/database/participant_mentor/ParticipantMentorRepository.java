package com.cygans.database.participant_mentor;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ParticipantMentorRepository extends CrudRepository<ParticipantMentor,Long> {
    ParticipantMentor getMentorParticipantByParticipantId(Long participantId);

    List<ParticipantMentor> getAllByMentorId(Long mentorId);

    ParticipantMentor findByParticipantId(Long participantId);

    ParticipantMentor getMentorParticipantById(long id);

    @Override
    void delete(ParticipantMentor participantMentor);

    @Override
    <S extends ParticipantMentor> S save(S s);
}
