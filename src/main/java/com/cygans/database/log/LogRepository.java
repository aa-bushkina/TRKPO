package com.cygans.database.log;


import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface LogRepository extends CrudRepository<Log, Long> {

    List<Log> findAll();

    List<Log> findByParticipantId(Long participantId);

    List<Log> findByDateBetweenAndParticipantId(LocalDate startDate, LocalDate endDate, Long participantId);

    List<Log> findByDateAndParticipantId(LocalDate date, Long participantId);

    @Override
    <S extends Log> S save(S s);
}
