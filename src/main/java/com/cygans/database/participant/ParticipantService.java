package com.cygans.database.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ParticipantService {
    @Autowired
    ParticipantRepository repository;

    public Boolean isNeedToAddHardcodedUser() {
        return repository.findAll().size() == 0;
    }

    public String getFirstname(Long participantId) {
        return repository.getParticipantById(participantId).getFirstName();
    }

    public String getLastname(Long participantId) {
        return repository.getParticipantById(participantId).getLastName();
    }

    public String searchParticipantName(Long participantId) {
        return repository.getParticipantById(participantId).getFirstName() + " "
                + repository.getParticipantById(participantId).getLastName();
    }

    public Participant searchParticipantId(String participantLogin) {
        return repository.getParticipantByLogin(participantLogin);
    }

    public void updateParticipantFirstName(Long id, String firstname) {
        Participant participant = repository.getParticipantById(id);
        participant.setFirstName(firstname);
        repository.save(participant);
    }

    public void updateParticipantLastName(Long id, String lastname) {
        Participant participant = repository.getParticipantById(id);
        participant.setLastName(lastname);
        repository.save(participant);
    }

    public void updateParticipantLogin(Long id, String login) {
        Participant participant = repository.getParticipantById(id);
        participant.setLogin(login);
        repository.save(participant);
    }

    public void updateParticipantPhone(Long id, String newPhoneNum) {
        Participant participant = repository.getParticipantById(id);
        participant.setPhone(newPhoneNum);
        repository.save(participant);
    }

    public void updateParticipantGender(Long id, String newGender) {
        Participant participant = repository.getParticipantById(id);
        participant.setGender(newGender);
        repository.save(participant);
    }

    public void updateParticipantBirthday(Long id, LocalDate newBirthday) {
        Participant participant = repository.getParticipantById(id);
        participant.setBirthday(newBirthday);
        repository.save(participant);
    }

    public void updateParticipantHeight(Long id, Integer height) {
        Participant participant = repository.getParticipantById(id);
        participant.setHeight(height);
        repository.save(participant);
    }

    public void updateParticipantWeight(Long id, Integer weight) {
        Participant participant = repository.getParticipantById(id);
        participant.setWeight(weight);
        repository.save(participant);
    }

    public void updateParticipantBreast(Long id, Integer breast) {
        Participant participant = repository.getParticipantById(id);
        participant.setBreast(breast);
        repository.save(participant);
    }

    public void updateParticipantWaist(Long id, Integer waist) {
        Participant participant = repository.getParticipantById(id);
        participant.setWaist(waist);
        repository.save(participant);
    }

    public void updateParticipantHips(Long id, Integer hips) {
        Participant participant = repository.getParticipantById(id);
        participant.setHips(hips);
        repository.save(participant);
    }

    public Participant getParticipantByLoginInfoId(Long id) {
        return repository.getParticipantByLoginInfoId(id);
    }

    public Participant getParticipantById(Long id) {
        return repository.getParticipantById(id);
    }

    public void saveParticipant(Participant participant) {
        repository.save(participant);
    }

}