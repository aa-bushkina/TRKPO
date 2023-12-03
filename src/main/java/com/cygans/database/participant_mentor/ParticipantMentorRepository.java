package com.cygans.database.participant_mentor;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ParticipantMentorRepository extends CrudRepository<ParticipantMentor,Long> {

    List<ParticipantMentor> findAll();

    List<ParticipantMentor> getAllByMentorId(Long mentorId);

    ParticipantMentor findByParticipantId(Long participantId);

    @Override
    void delete(ParticipantMentor participantMentor);

    @Override
    <S extends ParticipantMentor> S save(S s);
}
