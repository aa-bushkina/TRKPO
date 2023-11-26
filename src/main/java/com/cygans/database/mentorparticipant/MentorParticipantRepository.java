package com.cygans.database.mentorparticipant;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MentorParticipantRepository extends CrudRepository<MentorParticipant,Long> {
    MentorParticipant getMentorParticipantByParticipantId(Long participantId);

    List<MentorParticipant> getAllByMentorId(Long mentorId);

    MentorParticipant findByParticipantId(Long participantId);

    MentorParticipant getMentorParticipantById(long id);

    @Override
    void delete(MentorParticipant participantMentor);

    @Override
    <S extends MentorParticipant> S save(S s);
}

