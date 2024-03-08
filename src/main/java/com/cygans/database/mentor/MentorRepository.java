package com.cygans.database.mentor;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface MentorRepository extends CrudRepository<Mentor, Long> {
    List<Mentor> findAll();

    Mentor getMentorById(Long id);
    Mentor getMentorByLogin(String login);

    Mentor getMentorByLoginInfoId(Long id);

    @Override
    <S extends Mentor> S save(S s);


}