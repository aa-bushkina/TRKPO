package com.cygans.database.participant;


import com.cygans.database.Person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "participant")
public class Participant extends Person implements Comparable<Participant> {
    @Column(name = "height")
    private Integer height;
    @Column(name = "weight")
    private Integer weight;
    @Column(name = "hips")
    private Integer hips;
    @Column(name = "waist")
    private Integer waist;
    @Column(name = "breast")
    private Integer breast;

    public Participant(String firstName,
                       String lastName,
                       String login,
                       String phone,
                       String gender,
                       LocalDate birthday,
                       Integer height,
                       Integer weight,
                       Integer hips,
                       Integer waist,
                       Integer breast,
                       Long loginInfoId) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.login = login;
        this.phone = phone;
        this.gender = gender;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.hips = hips;
        this.waist = waist;
        this.breast = breast;
        this.loginInfoId = loginInfoId;
    }

    public Participant() {
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getHips() {
        return hips;
    }

    public Integer getWaist() {
        return waist;
    }

    public Integer getBreast() {
        return breast;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setHips(Integer hips) {
        this.hips = hips;
    }

    public void setWaist(Integer waist) {
        this.waist = waist;
    }

    public void setBreast(Integer breast) {
        this.breast = breast;
    }

    @Override
    public int compareTo(Participant that) {
        return this.birthday.compareTo(that.birthday);
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", firstname='" + firstname +
                ", lastname='" + lastname +
                ", login='" + login +
                ", phone='" + phone +
                ", gender='" + gender +
                ", birthday=" + birthday +
                '}';
    }
}
