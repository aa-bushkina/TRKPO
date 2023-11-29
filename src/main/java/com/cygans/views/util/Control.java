package com.cygans.views.util;

import com.cygans.security.db.RoleEnum;
import com.cygans.views.StartView;
import com.cygans.views.mentor.MentorHomeView;
import com.cygans.views.participant.ParticipantHomeView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Контроллер для определения роутинга после регистрации
 */
@Route(value = "")
@Theme(themeFolder = "styles")
public class Control extends VerticalLayout implements BeforeEnterObserver {
    public void beforeEnter(BeforeEnterEvent event) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(RoleEnum.PARTICIPANT.getValue()))) {
            event.forwardTo(ParticipantHomeView.class);
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(RoleEnum.MENTOR.getValue()))) {
            event.forwardTo(MentorHomeView.class);
        } else {
            event.rerouteTo(StartView.class);
        }
    }
}
