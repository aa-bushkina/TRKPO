package com.cygans.database.log.log_type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "logs_type")
public class LogType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type")
    private String type;

    public LogType() {}

    public LogType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
