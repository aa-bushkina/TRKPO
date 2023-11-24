package com.cygans.database.eating_log.meal;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "meal")
public class Meal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type")
    private String type;

    public Meal() {
    }
    public Meal(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

}
