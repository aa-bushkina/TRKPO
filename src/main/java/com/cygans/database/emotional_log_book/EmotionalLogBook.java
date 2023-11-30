package com.cygans.database.emotional_log_book;


import com.cygans.database.Logbook;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Table(name = "emotional_log_book")
public class EmotionalLogBook extends Logbook implements Comparable<EmotionalLogBook> {

    @Column(name = "log_book_id")
    private Long logBookId;

    @Column(name = "time_type")
    private LocalDateTime timeType;

    @Column(name = "description", length = 1000)
    private String description;

    public EmotionalLogBook(Long logBookId,
                            LocalDateTime timeType,
                            String description) {
        this.logBookId = logBookId;
        this.timeType = timeType;
        this.description = description;
    }

    public EmotionalLogBook() {
    }

    public Long getLogBookId() {
        return logBookId;
    }

    public void setLogBookId(Long logBookId) {
        this.logBookId = logBookId;
    }

    public LocalDateTime getTimeType() {
        return timeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(EmotionalLogBook that) {
        return this.timeType.compareTo(that.timeType);
    }

    @Override
    public String toString() {
        return "Emotional logbook{" +
                "id=" + id +
                ", timeType=" + timeType +
                ", description=" + description +
                "}";
    }
}


