package com.cygans.views.participant.logbooks;

import java.time.LocalDate;

public class ParticipantPersonData {
    private LocalDate date;
    private String logBookType;
    private long logBookId;

    public String getLogBookType() {
        return logBookType;
    }

    public void setLogBookType(String LogBokType) {
        this.logBookType = LogBokType;
    }

    public Long getLogBookId() {
        return logBookId;
    }

    public void setLogBookId(Long logBookId) {
        this.logBookId = logBookId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}