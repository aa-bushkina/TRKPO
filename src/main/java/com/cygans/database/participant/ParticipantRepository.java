package com.cygans.database.participant;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ParticipantRepository extends CrudRepository<Participant, Long> {

    Participant getParticipantByLogin(String email);

    Participant getParticipantById(Long id);

    Participant getParticipantByLoginInfoId(Long id);

    List<Participant> findAll();

    @Override
    <S extends Participant> S save(S s);

}