package com.cygans.database.question.question_status;


import org.springframework.data.repository.CrudRepository;

public interface QuestionStatusRepository extends CrudRepository<QuestionStatus, Long> {
    QuestionStatus findQuestionStatusByStatus(String status);
    QuestionStatus findQuestionStatusById(Long id);
    @Override
    <S extends QuestionStatus> S save(S s);

}
