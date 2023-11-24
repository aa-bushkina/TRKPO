package com.cygans.database.sport_log;

import com.cygans.database.Logbook;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "sport_log_book")
public class SportLog extends Logbook implements Comparable<SportLog> {

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

    public SportLog(long logBookId, long intensityId, int duration, LocalDateTime timeType, String activity, String comments) {
        this.logBookId = logBookId;
        this.intensityId = intensityId;
        this.duration = duration;
        this.timeType = timeType;
        this.activity = activity;
        this.comments = comments;
    }

    public SportLog() {
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

    public LocalDateTime getTimeType() {
        return timeType;
    }

    public void setTimeType(LocalDateTime timeType) {
        this.timeType = timeType;
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
    public int compareTo(SportLog o) {
        return 0;
    }

}
