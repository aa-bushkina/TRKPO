package com.cygans.database.sport_log_book;

import com.cygans.database.Logbook;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Table(name = "sport_log_book")
public class SportLogBook extends Logbook implements Comparable<SportLogBook> {

    @Column(name = "log_book_id")
    private long logBookId;

    @Column(name = "intensity_id")
    private long intensityId;

    @Column(name = "duration")
    private int duration;

    @Column(name = "time_type")
    private LocalDateTime timeType;

    @Column(name = "activity", length = 50)
    private String activity;

    @Column(name = "comments", length = 300)
    private String comments;

    public SportLogBook(long logBookId,
                        long intensityId,
                        int duration,
                        LocalDateTime timeType,
                        String activity,
                        String comments) {
        this.logBookId = logBookId;
        this.intensityId = intensityId;
        this.duration = duration;
        this.timeType = timeType;
        this.activity = activity;
        this.comments = comments;
    }

    public SportLogBook() {
    }

    public long getLogBookId() {
        return logBookId;
    }

    public void setLogBookId(long logBookId) {
        this.logBookId = logBookId;
    }

    public long getIntensityId() {
        return intensityId;
    }

    public void setIntensityId(long intensityId) {
        this.intensityId = intensityId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setTimeType(LocalDateTime timeType) {
        this.timeType = timeType;
    }

    public LocalDateTime getTimeType() {
        return timeType;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public int compareTo(SportLogBook that) {
        return this.timeType.compareTo(that.timeType);
    }

    @Override
    public String toString() {
        return "Sport logbook{" +
                "id=" + id +
                ",logBookId=" + logBookId +
                ", timeType=" + timeType +
                ", intensityId=" + intensityId +
                ", duration=" + duration +
                ", activity=" + activity +
                ", comments=" + comments +
                "}";
    }
}