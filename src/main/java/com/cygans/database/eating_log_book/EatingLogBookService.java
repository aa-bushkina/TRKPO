package com.cygans.database.eating_log_book;

import com.cygans.database.eating_log_book.meal.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class EatingLogBookService {

    private final EatingLogBookRepository repository;

    @Autowired
    public EatingLogBookService(EatingLogBookRepository eatingLogBookRepository) {
        this.repository = eatingLogBookRepository;
    }

    public void saveEatingLog(EatingLogBook EatingLogBook) {
        repository.save(EatingLogBook);
    }

    public List<EatingLogBook> findAll() {
        List<EatingLogBook> eatingLogBooks = repository.findAll();
        List<EatingLogBook> eatingLogBookObjects = new ArrayList<>();
        for (EatingLogBook eatingLogBook : eatingLogBooks) {
            eatingLogBookObjects.add(
                    new EatingLogBook(eatingLogBook.getLogBookId(),
                            eatingLogBook.getTimeEat(),
                            eatingLogBook.getDescription(),
                            eatingLogBook.getMealId(),
                            LocalDateTime.now()));
        }
        return eatingLogBookObjects;
    }

    public void updateEatingDescription(Long id, String description) {
        EatingLogBook eatingLogBook = repository.findById(id).get();
        eatingLogBook.setDescription(description);
        repository.save(eatingLogBook);
    }

    public void updateFoodTime(Long id, LocalTime time) {
        EatingLogBook eatingLogBook = repository.findById(id).get();
        eatingLogBook.setTimeEat(time);
        repository.save(eatingLogBook);
    }

    public void updateMale(Long id, Long maleId) {
        EatingLogBook eatingLogBook = repository.findById(id).get();
        eatingLogBook.setMealId(maleId);
        repository.save(eatingLogBook);
    }

    public EatingLogBook findByLogBookId(Long logBookId) {
        return repository.findByLogBookId(logBookId);
    }

}