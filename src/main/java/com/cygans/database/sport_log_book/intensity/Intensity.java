package com.cygans.database.sport_log_book.intensity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "intensity")
public class Intensity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "type")
    private String type;

    public Intensity() {
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Intensity(String type) {
        this.type = type;
    }
}
