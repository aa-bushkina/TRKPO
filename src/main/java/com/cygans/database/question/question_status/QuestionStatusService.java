package com.cygans.database.question.question_status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionStatusService {

    @Autowired
    private QuestionStatusRepository questionStatusRepository;

    public Long geQuestionStatusId(StatusOfQuestion status) {
        return questionStatusRepository.findQuestionStatusByStatus(status.getText()).getId();
    }

    public String getQuestionType(Long id) {
        return questionStatusRepository.findQuestionStatusById(id).getStatus();
    }

    public void fill() {
        if (questionStatusRepository.count() == 0) {
            questionStatusRepository.save(new QuestionStatus(StatusOfQuestion.NO_ANSWER.getText()));
            questionStatusRepository.save(new QuestionStatus(StatusOfQuestion.YES_ANSWER.getText()));
        } else if (questionStatusRepository.count() > 2) {
            System.out.println("Что-то идет не так, почистите таблицу question_status!!! В ней должно быть только 2 заранее добавленные записи!!!");
        }
    }

}
