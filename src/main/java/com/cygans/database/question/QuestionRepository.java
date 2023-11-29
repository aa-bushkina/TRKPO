package com.cygans.database.question;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface QuestionRepository extends CrudRepository<Question, Long> {

    List<Question> getQuestionByParticipantId(Long id);

    Question getQuestionById(Long id);

    <S extends Question> S save(S s);
}
