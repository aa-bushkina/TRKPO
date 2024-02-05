package com.cygans.database.question;

import com.cygans.database.question.question_status.QuestionStatusRepository;
import com.cygans.database.question.question_status.StatusOfQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    private final QuestionStatusRepository questionStatusRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository,
                           QuestionStatusRepository questionStatusRepository) {
        this.questionStatusRepository = questionStatusRepository;
        this.questionRepository = questionRepository;
    }

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
