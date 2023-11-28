package com.cygans.database.emotional_log_book;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmotionalLogBookRepository extends CrudRepository<EmotionalLogBook, Long>{

    List<EmotionalLogBook> findAll();

    EmotionalLogBook findByLogBookId(Long logBookId);

    @Override
    <S extends EmotionalLogBook> S save(S s);

}