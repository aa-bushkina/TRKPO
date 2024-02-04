package com.cygans.database.eating_log_book;

import com.cygans.database.Logbook;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "eating_log_book")
public class EatingLogBook extends Logbook implements Comparable<EatingLogBook> {

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

    public EatingLogBook(long logBookId,
                         LocalTime timeEat,
                         String description,
                         long mealId,
                         LocalDateTime timeType) {
        if (logBookId < 0) {
            throw new IllegalArgumentException("logBookId должен быть больше 0");
        }
        if (mealId < 0) {
            throw new IllegalArgumentException("mealId должен быть больше 0");
        }
        if (timeEat == null) {
            throw new IllegalArgumentException("timeEat должно иметь значение отличное от null");
        }
        if (timeType == null) {
            throw new IllegalArgumentException("timeType должно иметь значение отличное от null");
        }
        this.logBookId = logBookId;
        this.timeEat = timeEat;
        this.description = description;
        this.mealId = mealId;
        this.timeType = timeType;
    }

    public EatingLogBook() {
    }

    public long getMealId() {
        return mealId;
    }

    public void setMealId(long mealId) {
        if (mealId < 0) {
            throw new IllegalArgumentException("mealId должен быть больше 0");
        }
        this.mealId = mealId;
    }

    public LocalDateTime getTimeType() {
        return timeType;
    }

    public void setTimeType(LocalDateTime timeType) {
        if (timeType == null) {
            throw new IllegalArgumentException("timeType должно иметь значение отличное от null");
        }
        this.timeType = timeType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        if (logBookId < 0) {
            throw new IllegalArgumentException("logBookId должен быть больше 0");
        }
        this.logBookId = logBookId;
    }

    public LocalTime getTimeEat() {
        return timeEat;
    }

    public void setTimeEat(LocalTime timeEat) {
        if (timeEat == null) {
            throw new IllegalArgumentException("timeEat должно иметь значение отличное от null");
        }
        this.timeEat = timeEat;
    }

    @Override
    public int compareTo(EatingLogBook that) {
        return this.getTimeEat().compareTo(that.getTimeEat());
    }

    @Override
    public String toString() {
        return "Eating logbook{" +
                "id=" + id +
                ", logBookId=" + logBookId +
                ", timeEat=" + timeEat +
                ", description=" + description +
                ", mealId=" + mealId +
                ", timeType=" + timeType +
                "}";
    }
}