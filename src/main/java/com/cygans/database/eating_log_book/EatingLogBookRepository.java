package com.cygans.database.eating_log_book;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EatingLogBookRepository extends CrudRepository<EatingLogBook, Long> {
    List<EatingLogBook> findAll();

    EatingLogBook findByLogBookId(Long logBookId);

    @Override
    <S extends EatingLogBook> S save(S s);

}