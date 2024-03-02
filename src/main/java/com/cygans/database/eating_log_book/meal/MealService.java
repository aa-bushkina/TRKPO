package com.cygans.database.eating_log_book.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealService {

    private final MealRepository mealRepository;

    @Autowired
    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public Long getMealId(String type) {
        return mealRepository.findMealByType(type).getId();
    }

    public String getMealType(Long id) {
        return mealRepository.findMealById(id).getType();
    }

    public void fill() {
        if (mealRepository.count() == 0) {
            mealRepository.save(new Meal(MealType.BREAKFAST.getText()));
            mealRepository.save(new Meal(MealType.LAUNCH.getText()));
            mealRepository.save(new Meal(MealType.DINNER.getText()));
            mealRepository.save(new Meal(MealType.OTHER.getText()));
        } else if (mealRepository.count() != 4) {
            throw new IllegalArgumentException("Что-то идет не так, почистите таблицу meal!!! В ней должно быть только 4 заранее добавленные записи!!!");
        }
    }

}
