package com.cygans.views.mentor.settings;

import com.cygans.security.db.RoleEnum;
import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

@PageTitle("Change Password")
@Route(value = "mentor/change-password")

public class MentorSettings2View extends HorizontalLayout {
    PasswordField oldPassword, newPassword, confirmPassword;
    Button confirmButton, cancelButton;
    VerticalLayout mainLayout;
    private final LoginInfoService loginInfoService;

    public MentorSettings2View(LoginInfoService loginInfoService) {
        this.loginInfoService = loginInfoService;
        //раскомментировать, когда будет Toolbar
//        add(new Toolbar(ToolbarType.MENTOR_PAGES));
        mainLayoutInit();
        oldPasswordInit();
        confirmPasswordInit();
        newPasswordInit();
        saveBtnInit();
        cancelBtnInit();
        setJustifyContentMode(JustifyContentMode.CENTER);
        mainLayout.add(new H1("  "),
                new H2("Изменить пароль"),
                oldPassword,
                newPassword,
                confirmPassword,
                new HorizontalLayout(cancelButton, confirmButton)
        );
        add(mainLayout);
    }

    private void mainLayoutInit() {
        mainLayout = new VerticalLayout();
        mainLayout.setMaxWidth("500px");
        mainLayout.setAlignItems(Alignment.STRETCH);
        mainLayout.setJustifyContentMode(JustifyContentMode.CENTER);
    }

    private void oldPasswordInit() {
        oldPassword = new PasswordField("Текущий пароль");
        oldPassword.setClearButtonVisible(true);
    }

    private void newPasswordInit() {
        newPassword = new PasswordField("Новый пароль (не менее 8 символов)");
        newPassword.setClearButtonVisible(true);
        // TODO раскоментировать для ограничений на пароль
        //password.setPattern("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d_]{8,15}");
        newPassword.setErrorMessage("Пароль должен включать букву в нижнем регистре, букву в верхнем регистре, цифру. Длина пароля 8 - 15 символов. Не используйте другие специальные символы кроме _");
        newPassword.addValueChangeListener(e -> confirmPassword.setPattern(newPassword.getValue()));
    }

    private void confirmPasswordInit() {
        confirmPassword = new PasswordField("Повторите пароль");
        confirmPassword.setClearButtonVisible(true);
    }

    private void saveBtnInit() {
        confirmButton = new Button("Сохранить");
        confirmButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        confirmButton.getElement().getStyle().set("margin-left", "1em");
        confirmButton.addClickListener(e -> {
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    LoginInfo loginInfo = loginInfoService.findByLogin(authentication.getName());

                    if (loginInfo.checkPassword(oldPassword.getValue())) {
                        if (!newPassword.isInvalid()) {
                            if (!confirmPassword.isInvalid()) {
                                loginInfoService.updateUserPassword(authentication.getName(), newPassword.getValue());
                                authentication = new UsernamePasswordAuthenticationToken(loginInfo.getLogin(), newPassword.getValue(),
                                        AuthorityUtils.createAuthorityList(RoleEnum.MENTOR.getValue()));
                                SecurityContextHolder.getContext().setAuthentication(authentication);
                                Notification.show("Изменения сохранены", 2000, Notification.Position.TOP_CENTER);
                                confirmButton.getUI().ifPresent(ui -> ui.navigate(MentorSettings1View.class));
                            } else {
                                Notification notification = Notification.show("Введенные пароли отличаются", 3000, Notification.Position.TOP_CENTER);
                                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                            }
                        } else {
                            Notification notification = Notification.show("Неверный пароль", 3000, Notification.Position.TOP_CENTER);
                            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                        }
                    } else {
                        Notification notification = Notification.show("Неверный пароль", 3000, Notification.Position.TOP_CENTER);
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    }
                }
        );
    }

    private void cancelBtnInit() {
        cancelButton = new Button("Отменить");
        cancelButton.getElement().getStyle().set("margin-right", "auto");
        cancelButton.addClickListener(e ->
                cancelButton.getUI().ifPresent(ui ->
                        ui.navigate(MentorSettings1View.class)
                )
        );
    }
}