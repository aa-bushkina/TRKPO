package com.cygans.database.sport_log_book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SportLogBookService {
    @Autowired
    SportLogBookRepository repository;
    public void saveSportLog(SportLogBook sportLogBook){
        repository.save(sportLogBook);
    }

    public List<SportLogBook> findAll(){
        return new ArrayList<>(repository.findAll());
    }

    public SportLogBook findByLogBookId(Long logBookId) {
        return repository.findByLogBookId(logBookId);
    }

    public void updateSportDescription(Long id, String description) {
        SportLogBook sportLogBook = repository.findById(id).get();
        sportLogBook.setComments(description);
        repository.save(sportLogBook);
    }

    public void updateIntensityId(Long id, Long intensityId) {
        SportLogBook sportLogBook = repository.findById(id).get();
        sportLogBook.setIntensityId(intensityId);
        repository.save(sportLogBook);
    }

    public void updateActivity(Long id, String activity) {
        SportLogBook sportLogBook = repository.findById(id).get();
        sportLogBook.setActivity(activity);
        repository.save(sportLogBook);
    }

    public void updateDuration(Long id, int duration) {
        SportLogBook sportLogBook = repository.findById(id).get();
        sportLogBook.setDuration(duration);
        repository.save(sportLogBook);
    }

}