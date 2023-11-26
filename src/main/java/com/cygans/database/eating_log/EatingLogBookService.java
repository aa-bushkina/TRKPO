package com.cygans.database.eating_log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class EatingLogBookService {
    @Autowired
    EatingLogBookRepository repository;

    public void saveEatingLog(EatingLog EatingLog) {
        repository.save(EatingLog);
    }

    public List<EatingLog> findAll() {
        List<EatingLog> EatingLogs = repository.findAll();
        List<EatingLog> EatingLogObjects = new ArrayList<>();
        for (EatingLog EatingLog : EatingLogs) {
            EatingLogObjects.add(
                    new EatingLog(EatingLog.getLogBookId(),
                            EatingLog.getTimeEat(),
                            EatingLog.getDescription(),
                            EatingLog.getMealId(),
                            LocalDateTime.now()));
        }
        return EatingLogObjects;
    }

    public void updateEatingDescription(Long id, String description) {
        EatingLog EatingLog = repository.findById(id).get();
        EatingLog.setDescription(description);
        repository.save(EatingLog);
    }

    public void updateFoodTime(Long id, LocalTime time) {
        EatingLog EatingLog = repository.findById(id).get();
        EatingLog.setTimeEat(time);
        repository.save(EatingLog);
    }

    public void updateMale(Long id, Long maleId) {
        EatingLog EatingLog = repository.findById(id).get();
        EatingLog.setMealId(maleId);
        repository.save(EatingLog);
    }

    public EatingLog findByLogBookId(Long logBookId) {
        return repository.findByLogBookId(logBookId);
    }

}