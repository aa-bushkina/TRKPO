package com.cygans.database.emotional_log;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmotionalLogBookRepository extends CrudRepository<EmotionalLog, Long>{

    List<EmotionalLog> findAll();

    EmotionalLog findByLogBookId(Long logBookId);

    @Override
    <S extends EmotionalLog> S save(S s);

}