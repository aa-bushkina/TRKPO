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
        if (firstName == null || firstName.isEmpty() ||
                lastName == null || lastName.isEmpty() ||
                login == null || login.isEmpty() ||
                phone == null || phone.isEmpty() ||
                gender == null || gender.isEmpty() ||
                birthday == null ||
                height == null ||
                weight == null ||
                hips == null ||
                waist == null ||
                breast == null ||
                loginInfoId == null) {
            throw new IllegalArgumentException("Обязательные поля при создании участника не могут быть пустыми");
        }
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

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHips() {
        return hips;
    }

    public void setHips(Integer hips) {
        this.hips = hips;
    }

    public Integer getWaist() {
        return waist;
    }

    public void setWaist(Integer waist) {
        this.waist = waist;
    }

    public Integer getBreast() {
        return breast;
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
                "height=" + height +
                ", weight=" + weight +
                ", hips=" + hips +
                ", waist=" + waist +
                ", breast=" + breast +
                ", id=" + id +
                ", firstname=" + firstname +
                ", lastname=" + lastname +
                ", login=" + login +
                ", phone=" + phone +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", loginInfoId=" + loginInfoId +
                '}';
    }
}
