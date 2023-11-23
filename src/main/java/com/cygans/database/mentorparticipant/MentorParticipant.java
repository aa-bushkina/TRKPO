package com.cygans.database.mentorparticipant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "mentor_participant")
public class MentorParticipant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "participant_id")
    private Long participantId;

    @Column(name = "mentor_id")
    private Long mentorId;

    @Override
    public String toString() {
        return "MentorParticipant{" +
                "participantId=" + participantId +
                ", mentorId=" + mentorId +
                '}';
    }

    public MentorParticipant(Long participantId, Long mentorId) {
        this.participantId = participantId;
        this.mentorId = mentorId;
    }

    public MentorParticipant() {
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public Long getMentorId() {
        return mentorId;
    }

    public void setMentorId(Long mentorId) {
        this.mentorId = mentorId;
    }
}
