package com.cygans.database.log_book.logs_type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "logs_type")
public class LogsType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "type")
    private String type;

    public LogsType() {
    }

    public LogsType(String type) {
        for (LogBookType logBookType : LogBookType.values()) {
            if (logBookType.getText().equals(type)) {
                this.type = type;
                return;
            }
        }
        throw new IllegalArgumentException("Недопустимое значение для типа: " + type);
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
