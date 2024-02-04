package com.cygans.database.notifications.notification_type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "notification_type")
public class NotificationType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type")
    private String type;

    public NotificationType() {
    }

    public NotificationType(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Обязательные поля при создании Типа нотификации не могут быть пустыми");
        }
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
