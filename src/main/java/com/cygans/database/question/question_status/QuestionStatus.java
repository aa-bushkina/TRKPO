package com.cygans.database.question.question_status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "question_status")
public class QuestionStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status")
    private String status;

    public QuestionStatus(String status) {
        if (status == null || status.isEmpty())
        {
            throw new IllegalArgumentException("Illegal argument inconstuctor\n");
        }
        this.status = status;
    }

    public QuestionStatus() {
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

}
