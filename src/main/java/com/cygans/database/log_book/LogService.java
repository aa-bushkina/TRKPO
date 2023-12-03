package com.cygans.database.log_book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class LogService {
    @Autowired
    LogRepository repository;

    public void saveLog(Log logbook){
        repository.save(logbook);
    }

    public List<Log> findLogBooksByParticipantId(Long participantId){
        List<Log> log = repository.findByParticipantId(participantId);
        if(log!=null) Collections.sort(log);
        return log;
    }

    public List<Log> findLogBooksBetweenDate(LocalDate start, LocalDate end, Long participantId){
        List<Log> log = repository.findByDateBetweenAndParticipantId(start, end, participantId);
        if (log!=null) Collections.sort(log);
        return log;
    }

    public List<Log> findByDateAndParticipantId(LocalDate date, Long participantId){
        return repository.findByDateAndParticipantId(date,participantId);
    }

}
