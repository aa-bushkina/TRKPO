package com.cygans.database.notifications.notification_status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "notification_status")
public class NotificationStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "status")
    private String status;

    public NotificationStatus(String status) {
        this.status = status;
    }

    public NotificationStatus() {
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
