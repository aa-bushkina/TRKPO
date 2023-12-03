package com.cygans.database.controllers;

import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorService;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentor;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import com.cygans.security.db.logInfo.LoginInfoService;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class NotificationController {

    @Autowired
    private NotificationsService notificationsService;
    @Autowired
    private NotificationTypeService notificationTypeService;
    @Autowired
    private NotificationStatusService notificationStatusService;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private ParticipantMentorService participantMentorService;
    @Autowired
    private MentorService mentorService;

    public void addNewQuestionNotification(Long questionId, String questionText) {
        Long participantId = getIdNowParticipantByAuthentication();
        Notifications notification = new Notifications(
                participantId,
                getMentorIdByParticipant(participantId),
                notificationTypeService.getNotificationTypeId(TypeOfNotification.QUESTION),
                notificationStatusService.getNotificationStatusId(StatusOfNotification.NO_ANSWER)
        );
        notification.setQuestionId(questionId);
        String completeMsg =
                participantService.getFirstname(participantId) + " " + participantService.getLastname(participantId) + " отправил вопрос.\n" +
                        "\n" +
                        "Дата: " + notification.getDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "\n" +
                        "Время: " + notification.getDate().toLocalTime() + "\n";
        completeMsg = completeMsg + questionText;
        notification.setAllMessage(completeMsg);
        notificationsService.saveNotification(notification);
    }

    public void addNewEmotionalLogNotification(Long logId, String emotionalText) {
        Long participantId = getIdNowParticipantByAuthentication();
        Notifications notification = new Notifications(
                participantId,
                getMentorIdByParticipant(participantId),
                notificationTypeService.getNotificationTypeId(TypeOfNotification.NEW_LOG),
                notificationStatusService.getNotificationStatusId(StatusOfNotification.NO_ANSWER)
        );
        notification.setShortMessage("Новая запись о эмоциональном состоянии");
        notification.setAllMessage(
                participantService.getFirstname(participantId) + " " + participantService.getLastname(participantId)
                        + " добавил(-а) запись о свочем эмоциональном состоянии.\n" +
                        "\n" +
                        "Дата: " + notification.getDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "\n" +
                        "Время: " + notification.getDate().toLocalTime() + "\n" +
                        "Содержание: " + emotionalText
        );
        notification.setLogBookId(logId);
        notificationsService.saveNotification(notification);
    }

    public void addNewSportLogNotification(Long logId, String intensity, String duration, String activity, String comments) {
        Long participantId = getIdNowParticipantByAuthentication();
        Notifications notification = new Notifications(
                participantId,
                getMentorIdByParticipant(participantId),
                notificationTypeService.getNotificationTypeId(TypeOfNotification.NEW_LOG),
                notificationStatusService.getNotificationStatusId(StatusOfNotification.NO_ANSWER)
        );
        notification.setShortMessage("Новая запись о спортивной активности");
        notification.setAllMessage(
                participantService.getFirstname(participantId) + " " + participantService.getLastname(participantId)
                        + " добавил(-а) запись о своей спортивной активности.\n" +
                        "\n" +
                        "Дата: " + notification.getDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "\n" +
                        "Время: " + notification.getDate().toLocalTime() + "\n" +
                        "Интенсивность: " + intensity + "\n" +
                        "Активность: " + activity + "\n" +
                        "Продолжительность: " + Integer.parseInt(duration) + " минут\n" +
                        "Описание: " + comments
        );
        notification.setLogBookId(logId);
        notificationsService.saveNotification(notification);
    }

    public void addNewEatingLogNotification(Long logId, LocalTime time, String description, String meal_type) {
        Long participantId = getIdNowParticipantByAuthentication();
        Notifications notification = new Notifications(
                participantId,
                getMentorIdByParticipant(participantId),
                notificationTypeService.getNotificationTypeId(TypeOfNotification.NEW_LOG),
                notificationStatusService.getNotificationStatusId(StatusOfNotification.NO_ANSWER)
        );
        notification.setShortMessage("Новая запись о приеме пищи");
        notification.setAllMessage(
                participantService.getFirstname(participantId) + " " + participantService.getLastname(participantId)
                        + " добавил(-а) запись о своем приеме пищи.\n" +
                        "\n" +
                        "Дата: " + notification.getDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "\n" +
                        "Время: " + notification.getDate().toLocalTime() + "\n" +
                        "Время приема пищи: " + time + "\n" +
                        "Прием пищи: " + meal_type + "\n" +
                        "Содержание: " + description + "\n"
        );
        notification.setLogBookId(logId);
        notificationsService.saveNotification(notification);
    }

    public void addDeleteParticipantNotificationNowMentor(Participant participant) {
        Mentor mentor = mentorService.getMentorByLoginInfoId(
                loginInfoService.findByLogin(
                        SecurityContextHolder.getContext().getAuthentication().getName()
                ).getId()
        );
        Notifications notification = new Notifications(
                participant.getId(),
                mentor.getId(),
                notificationTypeService.getNotificationTypeId(TypeOfNotification.DELETE_REQUEST),
                notificationStatusService.getNotificationStatusId(StatusOfNotification.ANSWERED_NOT_SEEN)
        );
        notification.setShortMessage(mentor.getFirstName() + " " + mentor.getLastName() + " удалил тебя из отслеживания");
        notification.setAllMessage(
                "Ментор " + mentor.getFirstName() + " " + mentor.getLastName() + " удалил тебя из отслеживания.\n\n" +
                        "Если тебе неизвестны причины такого решения - обратись в поддержку, чтобы они объяснили " +
                        "причину и помогли с подбором нового ментора.\n\n" +
                        "Дата: " + notification.getDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "\n" +
                        "Время: " + notification.getDate().toLocalTime() + "\n"
        );
        notificationsService.saveNotification(notification);
    }

    public void addRequestToParticipantNotificationNowMentor(Participant participant) {
        Mentor mentor = mentorService.getMentorById(getIdNowMentorByAuthentication());
        Notifications n = new Notifications(
                participant.getId(),
                mentor.getId(),
                notificationTypeService.getNotificationTypeId(TypeOfNotification.ADD_REQUEST),
                notificationStatusService.getNotificationStatusId(StatusOfNotification.ANSWERED_NOT_SEEN)
        );
        n.setShortMessage(mentor.getFirstName() + " " + mentor.getLastName() + " хочет стать твоим ментором");
        n.setAllMessage(
                "Ментор " + mentor.getFirstName() + " " + mentor.getLastName() + " хочет стать твоим ментором.\n\n" +
                        "Напоминание: Принимая запрос, ты соглашаешься с тем, что твой ментор будет видеть все твои записи и вопросы\n\n" +
                        "Дата: " + n.getDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "\n" +
                        "Время: " + n.getDate().toLocalTime() + "\n"
        );
        notificationsService.saveNotification(n);
    }

    public void addDeclineToMentorNotificationNowParticipant(Long mentorId) {
        Participant participant = participantService.getParticipantById(getIdNowParticipantByAuthentication());
        Notifications n = new Notifications(
                participant.getId(),
                mentorId,
                notificationTypeService.getNotificationTypeId(TypeOfNotification.DECLINE_MENTOR),
                notificationStatusService.getNotificationStatusId(StatusOfNotification.NO_ANSWER)
        );
        n.setShortMessage(participant.getFirstName() + " " + participant.getLastName() + " отказал в отслеживании");
        n.setAllMessage(
                "Участник " + participant.getFirstName() + " " + participant.getLastName() + " отказал в отслеживании.\n\n" +
                        "Если вы считаете, что отказ ошибочен, или у вас возникли проблемы с участником, то обратитетсь в поддержку.\n" +
                        "\n" +
                        "Дата: " + n.getDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "\n" +
                        "Время: " + n.getDate().toLocalTime() + "\n"
        );
        notificationsService.saveNotification(n);
    }

    public void addAnswerToParticipantLogNotification(Notifications notifications, String replyMsg) {
        Participant participant = participantService.getParticipantById(notifications.getParticipantId());
        Mentor mentor = mentorService.getMentorById(notifications.getMentorId());
        Notifications n = new Notifications(
                participant.getId(),
                mentor.getId(),
                notificationTypeService.getNotificationTypeId(TypeOfNotification.ANSWER_ON_LOG),
                notificationStatusService.getNotificationStatusId(StatusOfNotification.ANSWERED_NOT_SEEN)
        );
        n.setShortMessage(TypeOfNotification.ANSWER_ON_LOG.getValue());
        n.setAllMessage(
                "Ментор " + mentor.getFirstName() + " " + mentor.getLastName() + " ответил на вашу запись.\n\n" +
                        "Дата: " + n.getDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "\n" +
                        "Время: " + n.getDate().toLocalTime() + "\n"
        );
        n.setReplyMessage(replyMsg);
        n.setLogBookId(notifications.getLogBookId());
        notificationsService.updateNotificationLogId(notifications.getNotificationId(), null);
        notificationsService.saveNotification(n);
    }

    public List<Notifications> getAllNowMentorNotifications() {
        return notificationsService.getMentorNotificationlist(getIdNowMentorByAuthentication());
    }

    private Long getMentorIdByParticipant(Long participantId) {
        ParticipantMentor participantMentor = participantMentorService.getMentorParticipantByParticipantId(participantId);
        return participantMentor == null ? null : participantMentor.getMentorId();
    }

    public void openNotification(Notifications notification) {
        VaadinSession.getCurrent().setAttribute("NotificationID", notification.getNotificationId());
        notification.setNotificationStatusId(notificationStatusService.getNotificationStatusId(StatusOfNotification.ANSWERED_SEEN));
    }

    public List<Notifications> getNotificationWithAnswerNotSeenParticipant(boolean byAuthentication, Participant participant) {
        if (byAuthentication) {
            Long participantIdAuth = getIdNowParticipantByAuthentication();
            return notificationsService.getNotificationsWithAnswerNotSeenList(participantIdAuth);
        } else {
            return notificationsService.getNotificationsWithAnswerNotSeenList(participant.getId());
        }
    }

    public Long getIdNowMentorByAuthentication() {
        return mentorService
                .getMentorByLoginInfoId(loginInfoService.findByLogin(SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName())
                        .getId())
                .getId();
    }

    public Long getIdNowParticipantByAuthentication() {
        return participantService
                .getParticipantByLoginInfoId(
                        loginInfoService.findByLogin(
                                SecurityContextHolder.getContext().getAuthentication().getName()
                        ).getId()
                ).getId();
    }

    public String getTypeNotification(Notifications notification) {
        return notificationTypeService.getNotificationTypeType(notification.getNotificationTypeId());
    }

    public Long getNotificationStatusId(StatusOfNotification statusOfNotification) {
        return notificationStatusService.getNotificationStatusId(statusOfNotification);
    }

    public Long getNotificationTypeId(TypeOfNotification type) {
        return notificationTypeService.getNotificationTypeId(type);
    }

    public Notifications getNotificationByIdFromAttribute() {
        return notificationsService.getNotificationById((long) VaadinSession.getCurrent().getAttribute("NotificationID"));
    }

    public void changeTypeOrStatusNotification(Long id, Long typeId, Long statusId) {
        if (typeId != null) {
            notificationsService.updateNotificationStatus(id, typeId);
        }
        if (statusId != null) {
            notificationsService.updateNotificationStatus(id, statusId);
        }
    }

    public void replyMentorToParticipantNotification(Notifications thisNotification, String replyMsg) {
        notificationsService.reply(thisNotification.getNotificationId(), replyMsg);
        notificationsService.resolveRequest(thisNotification.getNotificationId());
    }

    public void replyParticipantToMentorRequest(Notifications thisNotification) {
        participantMentorService.create(thisNotification.getParticipantId(), thisNotification.getMentorId());
        notificationsService.reply(thisNotification.getNotificationId(),
                participantService.getFirstname(thisNotification.getParticipantId()) + " "
                        + participantService.getLastname(thisNotification.getParticipantId())
                        + " принял запрос на менторство.");
        notificationsService.resolveRequest(thisNotification.getNotificationId());
    }

}
