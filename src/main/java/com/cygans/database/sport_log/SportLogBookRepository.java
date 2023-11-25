package com.cygans.database.sport_log;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SportLogBookRepository extends CrudRepository<SportLog, Long>{

    List<SportLog> findAll();

    SportLog findByLogBookId(Long logBookId);

    @Override
    <S extends SportLog> S save(S s);
}
