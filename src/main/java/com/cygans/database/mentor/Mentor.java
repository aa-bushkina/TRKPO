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
        this.firstname = firstName;
        this.lastname = lastName;
        this.login = login;
        this.phone = phone;
        this.gender = gender;
        this.birthday = birthday;
        this.loginInfoId = loginInfoId;
    }

    public Mentor() {}

    @Override
    public String toString() {
        return String.format("Mentor[id=%d, firstName='%s', lastName='%s']", id, firstname, lastname);
    }
}
