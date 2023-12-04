package com.cygans.views.mentor.settings;

import com.cygans.database.controllers.SettingsController;
import com.cygans.security.db.RoleEnum;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
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

@PageTitle("Марафон")
@Route(value = "mentor/change-password")

public class MentorSettings2View extends HorizontalLayout {
    private final SettingsController settingsController;
    private PasswordField oldPassword, newPassword, confirmPassword;
    private Button confirmButton, cancelButton;
    private VerticalLayout mainLayout;

    public MentorSettings2View(SettingsController settingsController) {
        this.settingsController = settingsController;
        add(new Toolbar(ToolbarType.MENTOR_PAGES));
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
        newPassword.setPattern("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d_]{8,15}");
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
            if (!settingsController.checkEqualsPassword(oldPassword.getValue())) {
                Notification.show("Неверный пароль", 3000, Notification.Position.TOP_CENTER)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (newPassword.isEmpty()) {
                Notification.show("Необходимо указать новый пароль", 3000, Notification.Position.TOP_CENTER)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (newPassword.isInvalid()) {
                Notification.show("Неверное значение нового пароля", 3000, Notification.Position.TOP_CENTER)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (confirmPassword.isEmpty()) {
                Notification.show("Необходимо повторно ввести пароль", 3000, Notification.Position.TOP_CENTER)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (!newPassword.getValue().equals(confirmPassword.getValue())) {
                Notification.show("Введенные пароли отличаются", 3000, Notification.Position.TOP_CENTER)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else {
                settingsController.changePassword(newPassword.getValue(), RoleEnum.PARTICIPANT);
                Notification.show("Изменения сохранены", 2000, Notification.Position.TOP_CENTER);
                confirmButton.getUI().ifPresent(ui -> ui.navigate(MentorSettings1View.class));
            }
        });
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