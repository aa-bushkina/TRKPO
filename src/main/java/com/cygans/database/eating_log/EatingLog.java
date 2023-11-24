package com.cygans.database.eating_log;


import com.cygans.database.Logbook;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "eating_log_book")
public class EatingLog extends Logbook implements Comparable<EatingLog> {

    @Column(name = "log_book_id")
    private long logBookId;

    @Column(name = "meal_id")
    private long mealId;

    @Column(name = "time_eat")
    private LocalTime timeEat;

    @Column(name = "time_type")
    private LocalDateTime timeType;

    @Column(name = "description", length = 300)
    private String description;

    public EatingLog() {}

    public EatingLog(long logBookId, LocalTime timeEat, String description, long mealId, LocalDateTime timeType) {
        this.logBookId = logBookId;
        this.timeEat = timeEat;
        this.description = description;
        this.mealId = mealId;
        this.timeType = timeType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getMealId() {
        return mealId;
    }

    public void setMealId(long mealId) {
        this.mealId = mealId;
    }

    public LocalDateTime getTimeType() {
        return timeType;
    }
    public void setTimeType(LocalDateTime timeType) {
        this.timeType = timeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getLogBookId() {
        return logBookId;
    }

    public void setLogBookId(long logBookId) {
        this.logBookId = logBookId;
    }

    public LocalTime getTimeEat() {
        return timeEat;
    }

    public void setTimeEat(LocalTime timeEat) {
        this.timeEat = timeEat;
    }

    @Override
    public int compareTo(EatingLog that) {
        return this.getTimeEat().compareTo(that.getTimeEat());
    }

}
