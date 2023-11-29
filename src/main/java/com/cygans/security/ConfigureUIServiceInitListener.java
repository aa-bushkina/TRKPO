package com.cygans.security;

import com.cygans.views.AccessDenialView;
import com.cygans.views.SignUp1View;
import com.cygans.views.SignUpRolesView;
import com.cygans.views.StartView;
import com.cygans.views.mentor.signup.MentorSignUp2View;
import com.cygans.views.participant.signup.ParticipantSignUp2View;
import com.cygans.views.participant.signup.ParticipantSignUp3View;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.stereotype.Component;


@Component
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::authenticateNavigation);
        });
    }

    private void authenticateNavigation(BeforeEnterEvent event) {
        if (!StartView.class.equals(event.getNavigationTarget()) && !SecurityUtils.isUserLoggedIn()) {
            if (SignUpRolesView.class.equals(event.getNavigationTarget())) {
                event.forwardTo(SignUpRolesView.class);
            } else if (SignUp1View.class.equals(event.getNavigationTarget())) {
                event.forwardTo(SignUp1View.class);
            } else if (ParticipantSignUp2View.class.equals(event.getNavigationTarget())) {
                event.forwardTo(ParticipantSignUp2View.class);
            } else if (ParticipantSignUp3View.class.equals(event.getNavigationTarget())) {
                event.forwardTo(ParticipantSignUp3View.class);
            } else if (MentorSignUp2View.class.equals(event.getNavigationTarget())) {
                event.forwardTo(MentorSignUp2View.class);
            } else if (AccessDenialView.class.equals(event.getNavigationTarget())) {
                event.forwardTo(AccessDenialView.class);
            } else {
                //TODO раскоментить при готовом StartView.class
                //event.rerouteTo(StartView.class);
            }
        }
    }
}

