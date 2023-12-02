package com.cygans.database.controllers;

import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorService;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import com.cygans.security.db.RoleEnum;
import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class SettingsController {
    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private MentorService mentorService;
    @Autowired
    private ParticipantService participantService;

    public void changePassword(String newPassword, RoleEnum role) {
        LoginInfo loginInfo = loginInfoService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        loginInfoService.updateUserPassword(SecurityContextHolder.getContext().getAuthentication().getName(), newPassword);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginInfo.getLogin(),
                newPassword,
                AuthorityUtils.createAuthorityList(role.getValue())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public boolean checkEqualsPassword(String password) {
        return loginInfoService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).checkPassword(password);
    }

    public void updateInfoUser(RoleEnum role,
                               String firstname,
                               String lastname,
                               String login,
                               String phone,
                               LocalDate birthday,
                               String gender,
                               Integer height,
                               Integer weight,
                               Integer breast,
                               Integer waist,
                               Integer hips) {
        if (role == RoleEnum.PARTICIPANT) {
            Long uid = getAuthoritiesParticipant().getId();
            participantService.updateParticipantFirstName(uid, firstname);
            participantService.updateParticipantLastName(uid, lastname);
            participantService.updateParticipantLogin(uid, login);
            participantService.updateParticipantPhone(uid, phone);
            participantService.updateParticipantBirthday(uid, birthday);
            participantService.updateParticipantGender(uid, gender);
            participantService.updateParticipantHeight(uid, height);
            participantService.updateParticipantWeight(uid, weight);
            participantService.updateParticipantBreast(uid, breast);
            participantService.updateParticipantWaist(uid, waist);
            participantService.updateParticipantHips(uid, hips);
        } else if (role == RoleEnum.MENTOR) {
            Long uid = getAuthoritiesMentor().getId();
            mentorService.updateFirstname(uid, firstname);
            mentorService.updateLastname(uid, lastname);
            mentorService.updateLogin(uid, login);
            mentorService.updatePhone(uid, phone);
            mentorService.updateBirthday(uid, birthday);
            mentorService.updateGender(uid, gender);
        }
    }

    public Participant getAuthoritiesParticipant() {
        Long loginInfoId = loginInfoService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
        return participantService.getParticipantByLoginInfoId(loginInfoId);
    }

    public Mentor getAuthoritiesMentor() {
        Long loginInfoId = loginInfoService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
        return mentorService.getMentorByLoginInfoId(loginInfoId);
    }

}
