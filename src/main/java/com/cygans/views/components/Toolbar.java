package com.cygans.views.components;

import com.cygans.security.SecurityService;
import com.cygans.views.StartView;
import com.cygans.views.mentor.MentorHomeView;
import com.cygans.views.mentor.notifications.MentorNotificationView;
import com.cygans.views.mentor.settings.MentorSettings1View;
import com.cygans.views.participant.ParticipantHomeView;
import com.cygans.views.participant.history.ParticipantHistoryView;
import com.cygans.views.participant.notifications.ParticipantNotificationView;
import com.cygans.views.participant.questions.ParticipantQuestionsView;
import com.cygans.views.participant.settings.ParticipantSettings1View;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Objects;

public class Toolbar extends AppLayout {
    private final Icon homeIco = new Icon(VaadinIcon.HOME);
    private final Button home = new Button(homeIco);
    private final Icon historyIco = new Icon(VaadinIcon.CALENDAR_CLOCK);
    private final Button historyBtn = new Button(historyIco);
    private final Icon notificationIco = new Icon(VaadinIcon.ENVELOPES);
    private final Button notificationBtn = new Button(notificationIco);
    private final Icon settingsIco = new Icon(VaadinIcon.COG_O);
    private final Button settingsBtn = new Button(settingsIco);
    private final Icon logoutIco = new Icon(VaadinIcon.SIGN_OUT);
    private final Button logoutBtn = new Button(logoutIco);
    private final Icon questionsIco = new Icon(VaadinIcon.QUESTION_CIRCLE);
    private final Button questionsBtn = new Button(questionsIco);
    private final SecurityService securityService = new SecurityService();


    public Toolbar(ToolbarType toolbarType) {
        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        HorizontalLayout horizontalLayout1 = new HorizontalLayout();
        VerticalLayout verticalLayout1 = new VerticalLayout();
        HorizontalLayout horizontalLayout2 = new HorizontalLayout();
        setStyles();

        logoutBtn.addClickListener(e -> securityService.logout());

        home.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        if (Objects.equals(toolbarType, ToolbarType.PARTICIPANT_PAGES)) {
            home.addClickListener(e ->
                    home.getUI().ifPresent(ui ->
                            ui.navigate(ParticipantHomeView.class)
                    )
            );
        } else if (Objects.equals(toolbarType, ToolbarType.MENTOR_PAGES)) {
            home.addClickListener(e ->
                    home.getUI().ifPresent(ui ->
                            ui.navigate(MentorHomeView.class)
                    )
            );
        } else if (Objects.equals(toolbarType, ToolbarType.LOGIN)) {
            home.addClickListener(e -> {
                        getUI().get().getSession().close();
                        home.getUI().ifPresent(ui ->
                                ui.navigate(StartView.class)
                        );
                    }

            );
        }

        if (Objects.equals(toolbarType, ToolbarType.PARTICIPANT_HOME)) {
            horizontalLayout.add(notificationBtn, historyBtn, questionsBtn, settingsBtn, logoutBtn);
            historyBtn.addClickListener(e ->
                    historyBtn.getUI().ifPresent(ui ->
                            ui.navigate(ParticipantHistoryView.class)
                    )
            );
            questionsBtn.addClickListener(e ->
                    questionsBtn.getUI().ifPresent(ui ->
                            ui.navigate(ParticipantQuestionsView.class)
                    )
            );
            settingsBtn.addClickListener(e ->
                    settingsBtn.getUI().ifPresent(ui ->
                            ui.navigate(ParticipantSettings1View.class)
                    )
            );
            notificationBtn.addClickListener(e ->
                    notificationBtn.getUI().ifPresent(ui ->
                            ui.navigate(ParticipantNotificationView.class)
                    )
            );
        } else if (Objects.equals(toolbarType, ToolbarType.MENTOR_HOME)) {
            horizontalLayout.add(notificationBtn, settingsBtn, logoutBtn);
            settingsBtn.addClickListener(e ->
                    settingsBtn.getUI().ifPresent(ui ->
                            ui.navigate(MentorSettings1View.class)
                    )
            );
            notificationBtn.addClickListener(e ->
                    notificationBtn.getUI().ifPresent(ui ->
                            ui.navigate(MentorNotificationView.class)
                    )
            );
        }

        horizontalLayout.setPadding(false);
        horizontalLayout.setSpacing(false);

        horizontalLayout1.add(home);
        verticalLayout1.add(horizontalLayout1);
        verticalLayout1.setHorizontalComponentAlignment(FlexComponent.Alignment.START, home);

        verticalLayout.add(horizontalLayout);
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.END, horizontalLayout);
        verticalLayout.setPadding(false);
        horizontalLayout2.add(verticalLayout1, verticalLayout);

        horizontalLayout2.getStyle().set("height", "70px")
                .set("width", "100%")
                .set("background-image", "url('images/bgBar.png')")
                .set("margin", "0");
        addToNavbar(horizontalLayout2);
    }

    public void setStyles() {
        //Иконки
        homeIco.setSize("65px");
        homeIco.setColor("white");
        settingsIco.setSize("45px");
        settingsIco.setColor("white");
        historyIco.setSize("45px");
        historyIco.setColor("white");
        notificationIco.setSize("45px");
        notificationIco.setColor("white");
        logoutIco.setSize("45px");
        logoutIco.setColor("white");
        questionsIco.setSize("45px");
        questionsIco.setColor("white");

        //Кнопки
        notificationBtn.setWidth("65px");
        notificationBtn.setHeight("65px");
        questionsBtn.setHeight("65px");
        questionsBtn.setWidth("65px");
        settingsBtn.setWidth("65px");
        settingsBtn.setHeight("65px");
        logoutBtn.setWidth("65px");
        logoutBtn.setHeight("65px");
        historyBtn.setWidth("65px");
        historyBtn.setHeight("65px");
    }
}
