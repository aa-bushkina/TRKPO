package com.cygans.database.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ParticipantService {
    @Autowired
    ParticipantRepository repository;

    public String bulkcreate() {
        // save a single Participant
//        repository.save(new Participant(Long.fromString("113d2815-54fb-4396-94fb-9a071393c336"),"ZImu","Huo","zimuhuo@outlook.com","flat1","Kings Gate","SW& $AX","New Deli","12345","Male", LocalDate.of(2001,1,1),"Type I",true,true,false,false,"Syringe"));
//        repository.save(new Participant(Long.fromString("8115af8e-1b82-4395-b410-c3395f73cfe9"),"Gang","Wang","wanggang@sohu.com","flat2","Kings Gate","SW& $AX2","New Deli","12345","Male", LocalDate.of(2000,1,2),"Type II",true,false,true,false,"Injection pen"));
//        repository.save(new Participant(Long.fromString("9bb703ed-e4af-444c-a6f1-fcba0cab81aa"),"Ann","B","Annb@outlook.com","flat2","Kings Gate","SW& $AX2","New Deli","12345","Female", LocalDate.of(1985,3,10),"Type II",false,true,false,false,"Injection pen"));
//        repository.save(new Participant(Long.fromString("1e522787-854c-41f6-81b1-cd169b6d3c3d"),"Cindy","B","Cindyb@outlook.com","flat2","Kings Gate","SW& $AX2","New Deli","12345","Female", LocalDate.of(1995,10,24),"Type II",false,true,false,false,"Injection pen"));

        return "Participant created";
    }

    public String getFirstname(Long participantId) {
        return repository.getParticipantById(participantId).getFirstName();
    }

    public String getLastname(Long participantId) {
        return repository.getParticipantById(participantId).getLastName();
    }

    public String getLogin(Long participantId) {
        return repository.getParticipantById(participantId).getLogin();
    }

    public String searchParticipantName(Long participantId) {
        return repository.getParticipantById(participantId).getFirstName() + " "
                + repository.getParticipantById(participantId).getLastName();
    }

    public Long searchParticipantId(String participantLogin) {
        Long returnuid = null;
        Participant participant = repository.getParticipantByLogin(participantLogin);
        if (participant != null) returnuid = participant.getId();
        return returnuid;
    }

    public Participant searchById(Long participantId) {
        return repository.getParticipantById(participantId);
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

    public void updateLogbookType(Long id, String logbookType) {
        Participant participant = repository.getParticipantById(id);
        participant.setLogbooktype(logbookType);
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