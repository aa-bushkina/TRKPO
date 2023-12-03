package com.cygans.database.sport_log_book;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SportLogBookRepository extends CrudRepository<SportLogBook, Long> {

    List<SportLogBook> findAll();

    SportLogBook findByLogBookId(Long logBookId);

    @Override
    <S extends SportLogBook> S save(S s);
}
