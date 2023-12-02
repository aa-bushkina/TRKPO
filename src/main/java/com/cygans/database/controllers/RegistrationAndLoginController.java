package com.cygans.database.controllers;

import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorService;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import com.cygans.security.db.RoleEnum;
import com.cygans.security.db.authorities.Authorities;
import com.cygans.security.db.authorities.AuthoritiesService;
import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoService;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
public class RegistrationAndLoginController {
    @Autowired
    private AuthoritiesService authoritiesService;
    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private MentorService mentorService;
    @Autowired
    private ParticipantService participantService;

    public void registrationUser(RoleEnum role) {
        Authorities authorities = new Authorities((String) VaadinSession.getCurrent().getAttribute("Login"), role.getValue());
        authoritiesService.saveAuthorities(authorities);
        this.authenticationUser(role);
        LoginInfo loginInfo = new LoginInfo(
                (String) VaadinSession.getCurrent().getAttribute("Login"),
                (String) VaadinSession.getCurrent().getAttribute("Password"),
                authoritiesService.getAuthoritiesIdByUsername((String) VaadinSession.getCurrent().getAttribute("Login")),
                (byte) 1
        );
        loginInfoService.saveLoginInfo(loginInfo);

        if (role == RoleEnum.MENTOR) {
            Mentor mentor = new Mentor(
                    (String) VaadinSession.getCurrent().getAttribute("FirstName"),
                    (String) VaadinSession.getCurrent().getAttribute("LastName"),
                    (String) VaadinSession.getCurrent().getAttribute("Login"),
                    (String) VaadinSession.getCurrent().getAttribute("Phone"),
                    (String) VaadinSession.getCurrent().getAttribute("Gender"),
                    (LocalDate) VaadinSession.getCurrent().getAttribute("Date"),
                    loginInfoService.findByLogin((String) VaadinSession.getCurrent().getAttribute("Login")).getId()
            );
            mentorService.saveMentor(mentor);
        } else if (role == RoleEnum.PARTICIPANT){
            Participant participant = new Participant(
                    (String) VaadinSession.getCurrent().getAttribute("FirstName"),
                    (String) VaadinSession.getCurrent().getAttribute("LastName"),
                    (String) VaadinSession.getCurrent().getAttribute("Login"),
                    (String) VaadinSession.getCurrent().getAttribute("Phone"),
                    (String) VaadinSession.getCurrent().getAttribute("Gender"),
                    (LocalDate) VaadinSession.getCurrent().getAttribute("Date"),
                    Integer.valueOf((String) VaadinSession.getCurrent().getAttribute("Height")),
                    Integer.valueOf((String) VaadinSession.getCurrent().getAttribute("Weight")),
                    Integer.valueOf((String) VaadinSession.getCurrent().getAttribute("Breast")),
                    Integer.valueOf((String) VaadinSession.getCurrent().getAttribute("Waist")),
                    Integer.valueOf((String) VaadinSession.getCurrent().getAttribute("Hip")),
                    loginInfoService.findByLogin((String) VaadinSession.getCurrent().getAttribute("Login")).getId()
            );
            participantService.saveParticipant(participant);
        }
    }

    public void authenticationUser(RoleEnum role) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                VaadinSession.getCurrent().getAttribute("Login"),
                VaadinSession.getCurrent().getAttribute("Password"),
                AuthorityUtils.createAuthorityList(role.getValue()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public boolean checkPresentLogin(String login) {
        return loginInfoService.findByLogin(login) != null;
    }

}
