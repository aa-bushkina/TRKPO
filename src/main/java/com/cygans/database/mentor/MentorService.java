package com.cygans.database.mentor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Service
public class MentorService {
    @Autowired
    MentorRepository repository;

    public Boolean isNeedToAddHardcodedUser()
    {
        return repository.findAll().size() == 0;
    }

    public void updateLastname(Long loginInfoId, String lastname) {
        Mentor mentor = repository.getMentorByLoginInfoId(loginInfoId);
        mentor.setLastName(lastname);
        repository.save(mentor);
    }

    public void updateFirstname(Long loginInfoId, String firstname) {
        Mentor mentor = repository.getMentorByLoginInfoId(loginInfoId);
        mentor.setFirstName(firstname);
        repository.save(mentor);
    }

    public void updateLogin(Long loginInfoId, String login) {
        Mentor mentor = repository.getMentorByLoginInfoId(loginInfoId);
        mentor.setLogin(login);
        repository.save(mentor);
    }

    public void updatePhone(Long loginInfoId, String phone) {
        Mentor mentor = repository.getMentorByLoginInfoId(loginInfoId);
        mentor.setPhone(phone);
        repository.save(mentor);
    }

    public void updateGender(Long loginInfoId, String gender) {
        Mentor mentor = repository.getMentorByLoginInfoId(loginInfoId);
        mentor.setGender(gender);
        repository.save(mentor);
    }

    public void updateBirthday(Long loginInfoId, LocalDate birthday) {
        Mentor mentor = repository.getMentorByLoginInfoId(loginInfoId);
        mentor.setBirthday(birthday);
        repository.save(mentor);
    }

    public Mentor getMentorByLoginInfoId(Long id) {
        return repository.getMentorByLoginInfoId(id);
    }

    public void saveMentor(Mentor mentor) {
        repository.save(mentor);
    }
}