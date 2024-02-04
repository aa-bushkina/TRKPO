package com.cygans.database.mentor;

import com.cygans.database.Person;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name = "mentor")
public class Mentor extends Person {

    public Mentor(
            String firstName,
            String lastName,
            String login,
            String phone,
            String gender,
            LocalDate birthday,
            Long loginInfoId) {
        if (firstName == null || firstName.isEmpty() ||
                lastName == null || lastName.isEmpty() ||
                login == null || login.isEmpty() ||
                phone == null || phone.isEmpty() ||
                gender == null || gender.isEmpty() ||
                birthday == null ||
                loginInfoId == null) {
            throw new IllegalArgumentException("Обязательные поля при создании Ментора не могут быть пустыми");
        }
        this.firstname = firstName;
        this.lastname = lastName;
        this.login = login;
        this.phone = phone;
        this.gender = gender;
        this.birthday = birthday;
        this.loginInfoId = loginInfoId;
    }

    public Mentor() {
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "id=" + id +
                ", firstName=" + firstname +
                ", lastName=" + lastname +
                ", login=" + login +
                ", phone=" + phone +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", loginInfoId=" + loginInfoId +
                "}";
    }
}
