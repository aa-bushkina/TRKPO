package com.cygans.database.emotional_log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmotionalLogBookService {
    @Autowired
    EmotionalLogBookRepository repository;

    public List<EmotionalLog> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    public void updateEmotionalDescription(Long id, String description) {
        EmotionalLog EmotionalLog = repository.findById(id).get();
        EmotionalLog.setDescription(description);
        repository.save(EmotionalLog);
    }

    public void saveEmotionalLog(EmotionalLog EmotionalLog) {
        repository.save(EmotionalLog);
    }

    public EmotionalLog findByLogBookId(Long logBookId) {
        return repository.findByLogBookId(logBookId);
    }

}
