package com.cygans.database.mentor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class MentorService {
    @Autowired
    MentorRepository repository;

    public String bulkcreate() {
        // save a single Mentor
//        repository.save(new Mentor("Yifei","Jin","jinjennifer@sohu.com","warwick","flat46","W8","London","34234432","Female",LocalDate.of(2002,3,8),Long.fromString("58864138-61ab-49c5-97ef-c98f8c981b0e")));
//        repository.save(new Mentor("Ming","Li","liming@sohu.com","flat1","room1","SW& $AX","London","12345","Male", LocalDate.of(2001,1,1), Long.fromString("111d2815-54fb-4396-94fb-9a071393c336")));
//        repository.save(new Mentor("Hong","Xiao","xiaohong@sohu.com","flat2","room3","SW& $AX2","London","12345","Male", LocalDate.of(2001,1,1),Long.fromString("222d2815-54fb-4396-94fb-9a071393c336")));
        return "Mentor created";
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