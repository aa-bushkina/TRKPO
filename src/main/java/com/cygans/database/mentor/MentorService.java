package com.cygans.database.mentor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class MentorService {
    @Autowired
    MentorRepository repository;

    public Boolean isNeedToAddHardcodedUser()
    {
        return repository.findAll().size() == 0;
    }

    public void updateLastname(Long id, String lastname) {
        Mentor mentor = repository.getMentorById(id);
        mentor.setLastName(lastname);
        repository.save(mentor);
    }

    public void updateFirstname(Long id, String firstname) {
        Mentor mentor = repository.getMentorById(id);
        mentor.setFirstName(firstname);
        repository.save(mentor);
    }

    public void updateLogin(Long id, String login) {
        Mentor mentor = repository.getMentorById(id);
        mentor.setLogin(login);
        repository.save(mentor);
    }

    public void updatePhone(Long id, String phone) {
        Mentor mentor = repository.getMentorById(id);
        mentor.setPhone(phone);
        repository.save(mentor);
    }

    public void updateGender(Long id, String gender) {
        Mentor mentor = repository.getMentorById(id);
        mentor.setGender(gender);
        repository.save(mentor);
    }

    public void updateBirthday(Long id, LocalDate birthday) {
        Mentor mentor = repository.getMentorById(id);
        mentor.setBirthday(birthday);
        repository.save(mentor);
    }

    public Mentor getMentorByLoginInfoId(Long id) {
        return repository.getMentorByLoginInfoId(id);
    }

    public Mentor getMentorById(Long id) {
        return repository.getMentorById(id);
    }

    public void saveMentor(Mentor mentor) {
        repository.save(mentor);
    }
}