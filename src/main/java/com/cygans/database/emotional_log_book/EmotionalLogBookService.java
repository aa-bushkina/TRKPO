package com.cygans.database.emotional_log_book;

import com.cygans.database.eating_log_book.EatingLogBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmotionalLogBookService {

    private final EmotionalLogBookRepository repository;

    @Autowired
    public EmotionalLogBookService(EmotionalLogBookRepository emotionalLogBookRepository) {
        this.repository = emotionalLogBookRepository;
    }

    public List<EmotionalLogBook> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    public void updateEmotionalDescription(Long id, String description) {
        EmotionalLogBook emotionalLogBook = repository.findById(id).get();
        emotionalLogBook.setDescription(description);
        repository.save(emotionalLogBook);
    }

    public void saveEmotionalLog(EmotionalLogBook emotionalLogBook) {
        repository.save(emotionalLogBook);
    }

    public EmotionalLogBook findByLogBookId(Long logBookId) {
        return repository.findByLogBookId(logBookId);
    }

}
