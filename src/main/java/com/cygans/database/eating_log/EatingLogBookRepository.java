package com.cygans.database.eating_log;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EatingLogBookRepository extends CrudRepository<EatingLog, Long> {
    List<EatingLog> findAll();

    EatingLog findByLogBookId(Long logBookId);

    @Override
    <S extends EatingLog> S save(S s);

}