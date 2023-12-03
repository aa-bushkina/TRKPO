package com.cygans.database.controllers;

import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorService;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentor;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import com.cygans.security.db.logInfo.LoginInfoService;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ParticipantAndMentorController {

    @Autowired
    public ParticipantService participantService;
    @Autowired
    public MentorService mentorService;
    @Autowired
    public ParticipantMentorService participantMentorService;
    @Autowired
    private LoginInfoService loginInfoService;

    public boolean participantHaveMentor(Participant participant) {
        return participantMentorService.existByParticipantId(participant.getId());
    }

    public Long getMentorIdOfParticipant(Participant participant) {
        ParticipantMentor participantMentor = participantMentorService.getMentorParticipantByParticipantId(participant.getId());
        return participantMentor == null ? null: participantMentor.getMentorId();
    }

    public Participant getParticipantByLogin(String login) {
        return participantService.searchParticipantId(login);
    }

    public List<Participant> getParticipantsByMentor(Mentor mentor) {
        return participantMentorService.getParticipantsByMentor(mentor.getId());
    }

    public Participant getParticipantById(Long id) {
        return participantService.getParticipantById(id);
    }

    public Mentor getIdNowMentorByAuthentication() {
        return mentorService
                .getMentorByLoginInfoId(loginInfoService.findByLogin(SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName())
                        .getId()
                );
    }

    public Participant getNowParticipantByAuthentication() {
        return participantService
                .getParticipantByLoginInfoId(
                        loginInfoService.findByLogin(
                                SecurityContextHolder.getContext().getAuthentication().getName()
                        ).getId()
                );
    }

    public Participant getNowParticipantByAttribute() {
        return participantService.getParticipantByLoginInfoId((Long) VaadinSession.getCurrent().getAttribute("ParticipantID"));
    }

    public void deleteParticipantFromMentor(Participant participant) {
        participantMentorService.deleteParticipant(participant.getId());
    }

}
