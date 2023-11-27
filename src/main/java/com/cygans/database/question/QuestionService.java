package com.cygans.database.question;

import com.cygans.database.question.question_status.QuestionStatusRepository;
import com.cygans.database.question.question_status.StatusOfQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionStatusRepository questionStatusRepository;

    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    public List<Question> getAllParticipantQuestion(Long id) {
        return questionRepository.getQuestionByParticipantId(id);
    }

    public Question getQuestionById(Long id) {
        return questionRepository.getQuestionById(id);
    }

    public void addAnswer(Long id, String text) {
        Question question = questionRepository.getQuestionById(id);
        question.setAnswer(text);
        question.setStatusId(questionStatusRepository.findQuestionStatusByStatus(StatusOfQuestion.YES_ANSWER.getText()).getId());
        questionRepository.save(question);
    }

}
