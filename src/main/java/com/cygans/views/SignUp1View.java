package com.cygans.views;

import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.security.db.RoleEnum;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.cygans.views.mentor.signup.MentorSignUp2View;
import com.cygans.views.participant.signup.ParticipantSignUp2View;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

/**
 * Страница регистрации участника 1
 */

@PageTitle("Марафон")
@Route(value = "signUp")
public class SignUp1View extends Div {
    private final RegistrationAndLoginController registrationAndLoginController;
    private TextField firstName, lastName, login;
    private PasswordField password, confirmPassword;
    private Button nextBtn;
    private FormLayout formLayout;
    private VerticalLayout mainLayout;

    public SignUp1View(RegistrationAndLoginController registrationAndLoginController) {
        this.registrationAndLoginController = registrationAndLoginController;
        add(new Toolbar(ToolbarType.LOGIN));

        mainLayoutSetUp();
        firstNameSetUp();
        lastNameSetUp();
        loginFieldSetUp();
        confirmPasswordSetUp();
        passwordSetUp();
        submitButtonSetUp();
        formLayoutSetUp();

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        mainLayout.add(new H2("Регистрация"));
        mainLayout.add(formLayout);
        mainLayout.add(nextBtn);
        horizontalLayout.add(mainLayout);
        add(horizontalLayout);
    }

    private void mainLayoutSetUp() {
        mainLayout = new VerticalLayout();
        mainLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        mainLayout.setMaxWidth("600px");
        mainLayout.setPadding(false);
    }

    private void submitButtonSetUp() {
        nextBtn = new Button("Далее", new Icon(VaadinIcon.ARROW_RIGHT));
        nextBtn.setIconAfterText(true);
        nextBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        nextBtn.getElement().getStyle().set("margin-left", "auto");
        nextBtn.addClickListener(e -> {
            if (firstName.isEmpty()) {
                Notification.show("Необходимо указать имя", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (lastName.isEmpty()) {
                Notification.show("Необходимо указать фамилию", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (login.isEmpty()) {
                Notification.show("Необходимо указать логин", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (login.isInvalid()) {
                Notification.show("Неверный формат логина", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (registrationAndLoginController.checkPresentLogin(login.getValue())) {
                Notification.show("Аккаунт с указанным логином уже существует\nУкажите другой логин", 5000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (password.isEmpty()) {
                Notification.show("Необходимо указать пароль", 10000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (password.isInvalid()) {
                Notification.show("Неверный формат пароля", 10000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (confirmPassword.isEmpty()) {
                Notification.show("Необходимо повторно ввести пароль", 10000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (!password.getValue().equals(confirmPassword.getValue())) {
                Notification.show("Пароли не совпадают", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else {
                VaadinSession.getCurrent().setAttribute("FirstName", firstName.getValue());
                VaadinSession.getCurrent().setAttribute("LastName", lastName.getValue());
                VaadinSession.getCurrent().setAttribute("Login", login.getValue());
                VaadinSession.getCurrent().setAttribute("Password", password.getValue());
                if (VaadinSession.getCurrent().getAttribute("RoleEnum") == RoleEnum.PARTICIPANT.getValue()) {
                    nextBtn.getUI().ifPresent(ui -> ui.navigate(ParticipantSignUp2View.class));
                } else if (VaadinSession.getCurrent().getAttribute("RoleEnum") == RoleEnum.MENTOR.getValue()) {
                    nextBtn.getUI().ifPresent(ui -> ui.navigate(MentorSignUp2View.class));
                }
            }
        });
    }

    private void formLayoutSetUp() {
        formLayout = new FormLayout();
        formLayout.add(firstName, lastName, login, password, confirmPassword);
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("320px", 2)
        );
        formLayout.setColspan(firstName, 1);
        formLayout.setColspan(lastName, 1);
        formLayout.setColspan(login, 2);
        formLayout.setColspan(password, 2);
        formLayout.setColspan(confirmPassword, 2);
    }

    private void firstNameSetUp() {
        firstName = new TextField("Имя");
        firstName.setClearButtonVisible(true);
        firstName.setPlaceholder("Имя");
        if (VaadinSession.getCurrent().getAttribute("FirstName") != null) {
            firstName.setValue((String) VaadinSession.getCurrent().getAttribute("FirstName"));
        }
    }

    private void lastNameSetUp() {
        lastName = new TextField("Фамилия");
        lastName.setClearButtonVisible(true);
        lastName.setPlaceholder("Фамилия");
        if (VaadinSession.getCurrent().getAttribute("LastName") != null) {
            lastName.setValue((String) VaadinSession.getCurrent().getAttribute("LastName"));
        }
    }

    private void passwordSetUp() {
        password = new PasswordField("Пароль (не менее 8 символов)");
        password.setPlaceholder("Пароль");
        password.setPattern("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d_]{8,15}");
        password.setErrorMessage("Пароль должен включать букву в нижнем регистре, букву в верхнем регистре, цифру. Длина пароля 8 - 15 символов. Не используйте другие специальные символы кроме _");
        password.setClearButtonVisible(true);
        if (VaadinSession.getCurrent().getAttribute("Password") != null) {
            password.setValue((String) VaadinSession.getCurrent().getAttribute("Password"));
        }
        password.addValueChangeListener(e -> confirmPassword.setPattern(password.getValue()));
    }

    private void confirmPasswordSetUp() {
        confirmPassword = new PasswordField("Повторите пароль");
        confirmPassword.setClearButtonVisible(true);
        confirmPassword.setPlaceholder("Пароль");
        if (VaadinSession.getCurrent().getAttribute("Password") != null) {
            confirmPassword.setValue((String) VaadinSession.getCurrent().getAttribute("Password"));
        }
    }

    private void loginFieldSetUp() {
        login = new TextField("Логин");
        login.getElement().setAttribute("name", "Login");
        login.setPlaceholder("Логин");
        login.setClearButtonVisible(true);
        login.setErrorMessage("Используйте только латинские буквы, цифры и символы -_.");
        if (registrationAndLoginController.checkPresentLogin(login.getValue())) {
            login.setErrorMessage("Аккаунт с таким логином уже существует");
        }
        if (VaadinSession.getCurrent().getAttribute("Login") != null) {
            login.setValue((String) VaadinSession.getCurrent().getAttribute("Login"));
        }
    }
}
