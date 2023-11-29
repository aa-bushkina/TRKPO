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
//    public String bulkcreate(){
//        LocalDate test=LocalDate.now();
//
//        repository.save(new Log(Long.fromString("8115af8e-1b82-4395-b410-c3395f73cfe9"),test.minusDays(4),2,2));
//        repository.save(new Log(Long.fromString("9bb703ed-e4af-444c-a6f1-fcba0cab81aa"),test.minusDays(1),3));
//        repository.save(new Log(Long.fromString("113d2815-54fb-4396-94fb-9a071393c336"),test.minusDays(8),1));
//        repository.save(new Log(Long.fromString("1e522787-854c-41f6-81b1-cd169b6d3c3d"),test.minusDays(3),1));
//
//        return "Log is created";
//    }

    public void saveLog(Log logbook){
        repository.save(logbook);
    }

    public List<Log> findLogBooksByParticipantId(Long participantId){
        List<Log> log = repository.findByParticipantId(participantId);
        if(log!=null) Collections.sort(log);
        return log;
    }

    public List<Log> findLogBooksBetweenDate(LocalDate start, LocalDate end, Long participantId){
        List<Log> log = repository.findByDateBetweenAndParticipantId(start,end,participantId);
        if (log!=null) Collections.sort(log);
        return log;
    }

    public List<Log> findByDateAndParticipantId(LocalDate date, Long participantId){
        return repository.findByDateAndParticipantId(date,participantId);
    }

}
