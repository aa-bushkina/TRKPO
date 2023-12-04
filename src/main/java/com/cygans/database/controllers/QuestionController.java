package com.cygans.database.controllers;

import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.question.Question;
import com.cygans.database.question.QuestionService;
import com.cygans.database.question.question_status.QuestionStatusService;
import com.cygans.database.question.question_status.StatusOfQuestion;
import com.cygans.security.db.logInfo.LoginInfoService;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionStatusService questionStatusService;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private NotificationsService notificationsService;
    @Autowired
    private NotificationTypeService notificationTypeService;

    public Long addNewQuestionForNowParticipant(String questionText) {
        Long participantId = getIdNowParticipantByAuthentication();
        Question question = new Question(
                participantId,
                LocalDate.now(),
                questionText,
                questionStatusService.geQuestionStatusId(StatusOfQuestion.NO_ANSWER)
        );
        questionService.saveQuestion(question);
        return question.getId();
    }

    public List<Question> getAllQuestionNowParticipant() {
        Long participantId = getIdNowParticipantByAuthentication();
        return questionService.getAllParticipantQuestion(participantId);
    }

    public Long getIdNowParticipantByAuthentication() {
        return participantService
                .getParticipantByLoginInfoId(
                        loginInfoService.findByLogin(
                                SecurityContextHolder.getContext().getAuthentication().getName()
                        ).getId()
                ).getId();
    }

    public String getQuestionStatus(Question question) {
        return questionStatusService.getQuestionType(question.getStatusId());
    }

    public void addAnswerToQuestion(Long questionId, Long notificationId, String answer) {
        questionService.addAnswer(questionId, answer);
        notificationsService.updateNotificationType(notificationId,
                notificationTypeService.getNotificationTypeId(TypeOfNotification.ANSWER_ON_QUESTION));
        notificationsService.updateNotificationAllMessage(notificationId,
                "Ваш вопрос: " + questionService.getQuestionById(questionId).getQuestion()
        + "\nДата: " + questionService.getQuestionById(questionId).getDate());
    }

    public Question getNowQuestionInSession() {
        return questionService.getQuestionById((Long) VaadinSession.getCurrent().getAttribute("QuestionId"));
    }

}
