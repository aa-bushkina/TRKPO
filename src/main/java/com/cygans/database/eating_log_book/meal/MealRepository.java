package com.cygans.database.eating_log_book.meal;

import org.springframework.data.repository.CrudRepository;

public interface MealRepository extends CrudRepository<Meal, Long> {
    Meal findMealById(Long id);
    Meal findMealByType(String type);
    @Override
    <S extends Meal> S save(S s);

}
