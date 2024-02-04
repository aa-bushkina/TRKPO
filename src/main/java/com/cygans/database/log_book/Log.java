package com.cygans.database.log_book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "log_book")
public class Log implements Serializable, Comparable<Log> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "log_type_id")
    private long logTypeId;

    @Column(name = "participant_id")
    private Long participantId;

    public Log(Long participantId,
               LocalDate date,
               long logTypeId) {
        this.participantId = participantId;
        this.date = date;
        this.logTypeId = logTypeId;
    }

    public Log() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id не может быть отрицательным");
        }
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getLogTypeId() {
        return logTypeId;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    @Override
    public int compareTo(Log that) {
        return this.date.compareTo(that.date);
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", date=" + date +
                ", logBookType='" + logTypeId + '\'' +
                ", participantId=" + participantId +
                '}';
    }
}
