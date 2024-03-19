package com.cygans.views;

import com.cygans.views.util.Filler;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;


/**
 * Страница логина
 */
@Route(value = "login")
@Theme(themeFolder = "styles")
public class StartView extends VerticalLayout implements BeforeEnterObserver {
    private final LoginForm login = new LoginForm();
    private final Button signUpBtn;

    public StartView(Filler filler) {
        filler.createHardcodedUsers();

        Image logo = new Image("images/GC_logo.png", "logo");
        logo.setWidth("100px");

        login.setAction("login");
        login.setForgotPasswordButtonVisible(false);
        login.setI18n(createLoginI18n());

        signUpBtn = new Button("Регистрация");
        signUpBtn.setWidth("310px");
        signUpBtn.setHeight("50px");
        signUpBtn.addClickListener(e ->
                signUpBtn.getUI().ifPresent(ui ->
                        ui.navigate(SignUpRolesView.class)
                )
        );
        add(logo, login, signUpBtn);
        setAlignItems(Alignment.CENTER);
    }

    /**
     * Кастомизация стандартной vaadin логин формы
     */
    private LoginI18n createLoginI18n() {
        LoginI18n i18n = LoginI18n.createDefault();
        i18n.getForm().setUsername("Логин");
        i18n.getForm().setTitle("Вход");
        i18n.getForm().setSubmit("Войти");
        i18n.getForm().setPassword("Пароль");
        i18n.getErrorMessage().setTitle("Неверный логин или пароль");
        i18n.getErrorMessage().setMessage("Проверьте введенные данные и попробуйте еще раз");
        return i18n;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }
}
