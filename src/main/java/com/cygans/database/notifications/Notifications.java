package com.cygans.database.notifications;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "notifications")
public class Notifications implements Serializable, Comparable<Notifications> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "all_message")
    private String allMessage;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "participant_id")
    private Long participantId;
    @Column(name = "mentor_id")
    private Long mentorId;
    @Column(name = "reply_message")
    private String replyMessage;
    @Column(name = "notification_type_id")
    private Long notificationTypeId;
    @Column(name = "short_message")
    private String shortMessage;
    @Column(name = "notification_status_id")
    private Long notificationStatusId;
    @Column(name = "question_id")
    private Long questionId;
    @Column(name = "log_book_id")
    private Long logBookId;

    public Notifications(
            Long participantId,
            Long mentorId,
            Long notificationTypeId,
            Long notificationStatusId) {
        this.participantId = participantId;
        this.mentorId = mentorId;
        this.date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        this.notificationTypeId = notificationTypeId;
        this.notificationStatusId = notificationStatusId;
    }

    public Notifications() {
    }

    public long getNotificationId() {
        return id;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public Long getMentorId() {
        return mentorId;
    }

    public void setQuestionId(Long id) {
        this.questionId = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setLogBookId(Long id) {
        this.logBookId = id;
    }

    public Long getLogBookId() {
        return logBookId;
    }

    public Long getNotificationTypeId() {
        return notificationTypeId;
    }

    public void setNotificationTypeId(Long id) {
        this.notificationTypeId = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDate getDateNoTime() {
        return date.toLocalDate();
    }

    public Long getNotificationStatusId() {
        return notificationStatusId;
    }

    public void setNotificationStatusId(Long statusId) {
        this.notificationStatusId = statusId;
    }

    public String getShortMessage() {
        return shortMessage;
    }

    public void setShortMessage(String shortMessage) {
        this.shortMessage = shortMessage;
    }

    public String getAllMessage() {
        return allMessage;
    }

    public void setAllMessage(String completeMessage) {
        this.allMessage = completeMessage;
    }

    public String getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }

    @Override
    public int compareTo(Notifications that) {
        return -1 * this.date.compareTo(that.date);
    }
}
