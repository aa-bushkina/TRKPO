package com.cygans.database.question;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "questions")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "participant_id")
    private Long participantId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "question", length = 300)
    private String question;

    @Column(name = "answer")
    private String answer;

    @Column(name = "question_status_id")
    private Long statusId;


    public Question(Long participantId,
                    LocalDate date,
                    String question,
                    Long statusId) {
        this.participantId = participantId;
        this.date = date;
        this.question = question;
        this.statusId = statusId;
    }

    public Question() {}

    public Long getId() {
        return id;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String text) {
        this.question = text;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}



