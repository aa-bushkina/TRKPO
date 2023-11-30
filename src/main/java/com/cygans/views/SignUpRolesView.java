package com.cygans.views;

import com.cygans.security.db.RoleEnum;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@PageTitle("Sign Up | Choose Account Type")
@Route(value = "sign-up")
public class SignUpRolesView extends VerticalLayout {
    private final Button participantButton;
    private final Button mentorButton;

    public SignUpRolesView() {
        add(new Toolbar(ToolbarType.LOGIN));
        H2 header = new H2("Выберите тип аккаунта:");
        participantButton = new Button("Участник марафона");
        participantButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        participantButton.setWidth("30%");
        participantButton.setHeight("50px");
        participantButton.addClickListener(e -> {
                    VaadinSession.getCurrent().setAttribute( "RoleEnum", RoleEnum.PARTICIPANT.getValue());
                    participantButton.getUI().ifPresent(ui ->
                            ui.navigate(SignUp1View.class)
                    );
                }
        );
        mentorButton = new Button("Ментор");
        mentorButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        mentorButton.setWidth("30%");
        mentorButton.setHeight("50px");
        mentorButton.addClickListener(e -> {
                    VaadinSession.getCurrent().setAttribute( "RoleEnum", RoleEnum.MENTOR.getValue());
                    mentorButton.getUI().ifPresent(ui ->
                            ui.navigate(SignUp1View.class)
                    );
                }
        );
        setMargin(true);
        setSpacing(true);
        setHorizontalComponentAlignment(Alignment.CENTER, header, participantButton, mentorButton);
        add(header, participantButton, mentorButton);
    }
}
