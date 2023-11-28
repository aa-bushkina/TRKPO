package com.cygans.views.participant.signup;


import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import com.cygans.security.db.RoleEnum;
import com.cygans.security.db.authorities.Authorities;
import com.cygans.security.db.authorities.AuthoritiesService;
import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoService;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.cygans.views.util.Control;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;


/**
 * Страница регистрации участника 3
 */
@PageTitle("Participant Sign Up")
@Route(value = "participantSignUp3")
public class ParticipantSignUp3View extends Div {
    private TextField height;
    private TextField weight;
    private TextField breast;
    private TextField waist;
    private TextField hips;
    private Button nextBtn, previousBtn;
    private final LoginInfoService loginInfoService;
    private final AuthoritiesService authoritiesService;
    private final ParticipantService participantService;

    public ParticipantSignUp3View(LoginInfoService loginInfoService,
                                  AuthoritiesService authoritiesService,
                                  ParticipantService participantService) {
        this.loginInfoService = loginInfoService;
        this.authoritiesService = authoritiesService;
        this.participantService = participantService;
        add(new Toolbar(ToolbarType.LOGIN));

        heightSetUp();
        weightSetUp();
        waistSetUp();
        hipsSetUp();
        breastSetUp();
        previousBtnInit();
        submitBtnInit();

        HorizontalLayout hl = new HorizontalLayout();
        HorizontalLayout btnsHl = new HorizontalLayout();
        btnsHl.add(previousBtn);
        btnsHl.add(nextBtn);

        HorizontalLayout firstLineHl = new HorizontalLayout();
        firstLineHl.add(height);
        firstLineHl.add(weight);

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        verticalLayout.add(new H2("Параметры"));
        verticalLayout.add(
                firstLineHl,
                breast,
                waist,
                hips,
                btnsHl
        );
        verticalLayout.setMaxWidth("600px");
        verticalLayout.setPadding(false);
        btnsHl.setWidth(verticalLayout.getWidth());

        hl.add(verticalLayout);
        hl.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(hl);
    }

    private void heightSetUp() {
        height = new TextField("Рост");
        height.setClearButtonVisible(true);
        height.setPattern("([6][7-9]|[7-9][0-9]|[1][0-9][0-9]|[2][0-4][0-9]|[2][5][0])");
        height.setErrorMessage("Неверный формат роста");
        if (VaadinSession.getCurrent().getAttribute("Height") != null) {
            height.setValue((String) VaadinSession.getCurrent().getAttribute("Height"));
        }
    }

    private void weightSetUp() {
        weight = new TextField("Вес");
        weight.setClearButtonVisible(true);
        weight.setPattern("([2-9]|[1-9][0-9]|[1-5][0-9][0-9]|[6][0][0])");
        weight.setErrorMessage("Неверный формат веса");
        if (VaadinSession.getCurrent().getAttribute("Weight") != null) {
            weight.setValue((String) VaadinSession.getCurrent().getAttribute("Weight"));
        }
    }

    private void waistSetUp() {
        waist = new TextField("Обхват талии");
        waist.setClearButtonVisible(true);
        waist.setPattern("([3-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|2[5][0])");
        waist.setErrorMessage("Неверный формат обхвата талии");
        if (VaadinSession.getCurrent().getAttribute("Waist") != null) {
            waist.setValue((String) VaadinSession.getCurrent().getAttribute("Waist"));
        }
    }

    private void hipsSetUp() {
        hips = new TextField("Обхват бедер");
        hips.setClearButtonVisible(true);
        hips.setPattern("([3-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|2[5][0])");
        hips.setErrorMessage("Неверный формат обхвата бедер");
        if (VaadinSession.getCurrent().getAttribute("Hip") != null) {
            hips.setValue((String) VaadinSession.getCurrent().getAttribute("Hip"));
        }
    }

    private void breastSetUp() {
        breast = new TextField("Обхват груди");
        breast.setClearButtonVisible(true);
        breast.setPattern("([3-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|2[5][0])");
        breast.setErrorMessage("Неверный формат обхвата груди");
        if (VaadinSession.getCurrent().getAttribute("Breast") != null) {
            breast.setValue((String) VaadinSession.getCurrent().getAttribute("Breast"));
        }
    }

    private void submitBtnInit() {
        nextBtn = new Button("Завершить");
        nextBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        nextBtn.getElement().getStyle().set("margin-left", "auto");
        nextBtn.addClickListener(e -> {
            if (height.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать рост", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (height.isInvalid()) {
                Notification notification = Notification.show("Неверный формат роста", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (weight.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать вес", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (weight.isInvalid()) {
                Notification notification = Notification.show("Неверный формат веса", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (breast.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать обхват груди", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (breast.isInvalid()) {
                Notification notification = Notification.show("Неверный формат обхвата груди", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (waist.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать обхват талии", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (waist.isInvalid()) {
                Notification notification = Notification.show("Неверный формат обхвата талии", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (hips.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать обхват бедер", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (hips.isInvalid()) {
                Notification notification = Notification.show("Неверный формат обхвата бедер", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else {
                setSession();

                Authorities authorities = new Authorities((String) VaadinSession.getCurrent().getAttribute("Login"), RoleEnum.PARTICIPANT.getValue());
                authoritiesService.saveAuthorities(authorities);

                Authentication authentication = new UsernamePasswordAuthenticationToken(VaadinSession.getCurrent().getAttribute("Login"), VaadinSession.getCurrent().getAttribute("Password"),
                        AuthorityUtils.createAuthorityList(RoleEnum.PARTICIPANT.getValue()));
                SecurityContextHolder.getContext().setAuthentication(authentication);


                LoginInfo loginInfo = new LoginInfo(
                        (String) VaadinSession.getCurrent().getAttribute("Login"),
                        (String) VaadinSession.getCurrent().getAttribute("Password"),
                        authoritiesService.getAuthoritiesIdByUsername((String) VaadinSession.getCurrent().getAttribute("Login")),
                        (byte) 1
                );
                loginInfoService.saveLoginInfo(loginInfo);

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

                getUI().get().getSession().close();
                nextBtn.getUI().ifPresent(ui -> ui.navigate(Control.class));
            }
        });
    }

    private void previousBtnInit() {
        previousBtn = new Button("Назад", new Icon(VaadinIcon.ARROW_LEFT));
        previousBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        previousBtn.getElement().getStyle().set("margin-right", "auto");
        previousBtn.addClickListener(e -> {
            setSession();
            previousBtn.getUI().ifPresent(ui -> ui.navigate(ParticipantSignUp2View.class));
        });
    }

    private void setSession() {
        VaadinSession.getCurrent().setAttribute("Height", height.getValue());
        VaadinSession.getCurrent().setAttribute("Weight", weight.getValue());
        VaadinSession.getCurrent().setAttribute("Breast", breast.getValue());
        VaadinSession.getCurrent().setAttribute("Waist", waist.getValue());
        VaadinSession.getCurrent().setAttribute("Hip", hips.getValue());
    }
}
