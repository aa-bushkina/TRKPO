package com.cygans.database.sport_log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SportLogBookService {
    @Autowired
    SportLogBookRepository repository;
    public void saveComprehensiveLog(SportLog SportLog){
        repository.save(SportLog);
    }

    public List<SportLog> findAll(){
        return new ArrayList<>(repository.findAll());
    }

    public SportLog findByLogBookId(Long logBookId) {
        return repository.findByLogBookId(logBookId);
    }

    public void updateSportDescription(Long id, String description) {
        SportLog SportLog = repository.findById(id).get();
        SportLog.setComments(description);
        repository.save(SportLog);
    }

    public void updateIntensityId(Long id, Long intensityId) {
        SportLog SportLog = repository.findById(id).get();
        SportLog.setIntensityId(intensityId);
        repository.save(SportLog);
    }

    public void updateActivity(Long id, String activity) {
        SportLog SportLog = repository.findById(id).get();
        SportLog.setActivity(activity);
        repository.save(SportLog);
    }

    public void updateDuration(Long id, int duration) {
        SportLog SportLog = repository.findById(id).get();
        SportLog.setDuration(duration);
        repository.save(SportLog);
    }

}