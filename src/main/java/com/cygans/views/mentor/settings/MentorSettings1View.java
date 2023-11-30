package com.cygans.views.mentor.settings;

import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorService;
import com.cygans.security.db.logInfo.LoginInfoService;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;


@PageTitle("Settings")
@Route(value = "/mentor/settings")

public class MentorSettings1View extends HorizontalLayout {
    String firstname;
    String lastname;
    String login;
    String phone;
    LocalDate birth;
    String gender;
    TextField firstnameField, lastnameField, phoneField, loginField;
    DatePicker birthSelect;
    Select<String> genderSelect;
    Button changeSetting, save, cancel, changePassword;
    VerticalLayout mainLayout;
    HorizontalLayout buttons;
    private final MentorService mentorService;


    public MentorSettings1View(MentorService mentorService, LoginInfoService loginInfoService) {
        this.mentorService = mentorService;
        Long loginInfoId = loginInfoService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
        init(mentorService.getMentorByLoginInfoId(loginInfoId));
        setJustifyContentMode(JustifyContentMode.CENTER);
        FormLayout formLayout = new FormLayout();
        formLayout.add(firstnameField, lastnameField,
                birthSelect, genderSelect,
                loginField, phoneField);
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("300px", 2)
        );
        formLayout.setColspan(firstnameField, 1);
        formLayout.setColspan(lastnameField, 1);
        formLayout.setColspan(birthSelect, 1);
        formLayout.setColspan(loginField, 1);
        formLayout.setColspan(phoneField, 1);
        formLayout.setColspan(genderSelect, 1);
        buttons.add(changePassword, changeSetting, save, cancel);
        mainLayout.add(new H1(" "), new H2("Настройки"), buttons, formLayout);
        add(new Toolbar(ToolbarType.MENTOR_PAGES), mainLayout);
    }

    private void init(Mentor mentor) {
        firstname = mentor.getFirstName();
        lastname = mentor.getLastName();
        login = mentor.getLogin();
        gender = mentor.getGender();
        phone = mentor.getPhone();
        birth = mentor.getBirthday();

        mainLayout = new VerticalLayout();
        mainLayout.setMaxWidth("600px");
        buttons = new HorizontalLayout();
        buttons.setWidth(mainLayout.getWidth());

        firstNameInit();
        lastNameInit();
        birthSelectInit();
        loginFieldInit();
        phoneInit();
        genderSelectInit();
        changeSettingInit();
        saveInit(mentor.getLoginInfoId());
        cancelInit();
        changePasswordInit();
    }

    private void firstNameInit() {
        firstnameField = new TextField("Имя");
        firstnameField.setValue(firstname);
        firstnameField.setClearButtonVisible(true);
        firstnameField.setReadOnly(true);
    }

    private void lastNameInit() {
        lastnameField = new TextField("Фамилия");
        lastnameField.setValue(lastname);
        lastnameField.setClearButtonVisible(true);
        lastnameField.setReadOnly(true);
    }

    private void phoneInit() {
        phoneField = new TextField();
        phoneField.setLabel("Номер телефона");
        phoneField.setValue(phone);
        phoneField.setClearButtonVisible(true);
        phoneField.setPlaceholder("+70000000000");
        phoneField.setReadOnly(true);
        // TODO раскоментировать для ограничений на телефон
        //phone.setPattern("\\+7\\d{10}");
    }

    private void loginFieldInit() {
        loginField = new TextField();
        loginField.setLabel("Логин");
        loginField.getElement().setAttribute("name", "Login");
        loginField.setErrorMessage("Используйте только латинские буквы, цифры и символы -_.");
        loginField.setValue(login);
        loginField.setReadOnly(true);
    }

    private void birthSelectInit() {
        birthSelect = new DatePicker("Дата рождения");
        birthSelect.setLocale(new Locale("ru", "RU"));
        LocalDate now = LocalDate.now(ZoneId.systemDefault());
        birthSelect.setMax(now);
        birthSelect.setMin(now.minusYears(100));
        birthSelect.setPlaceholder("ДД.ММ.ГГГГ");
        birthSelect.setValue(birth);
        birthSelect.setReadOnly(true);
    }


    private void genderSelectInit() {
        genderSelect = new Select<>("Жен", "Муж");
        genderSelect.setLabel("Пол");
        genderSelect.setValue(gender);
        genderSelect.setReadOnly(true);
    }

    private void changeSettingInit() {
        changeSetting = new Button("Редактировать");
        changeSetting.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        changeSetting.getElement().getStyle().set("margin-left", "auto");
        changeSetting.addClickListener(e -> {
            allSetReadOnly(false);
            changeSetting.setVisible(false);
            changePassword.setVisible(false);
            save.setVisible(true);
            cancel.setVisible(true);
        });
    }

    private void saveInit(Long id) {
        save = new Button("Сохранить");
        save.setVisible(false);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.getElement().getStyle().set("margin-left", "1em");
        save.addClickListener(e -> {
            if (firstnameField.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать имя", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (lastnameField.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать фамилию", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (gender.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать пол", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (birthSelect.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать дату рождения", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (birthSelect.isInvalid()) {
                Notification notification = Notification.show("Неверный формат даты рождения", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (phoneField.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать номер телефона", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (phoneField.isInvalid()) {
                Notification notification = Notification.show("Неверный формат номера телефона", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else {
                firstname = firstnameField.getValue();
                lastname = lastnameField.getValue();
                login = loginField.getValue();
                phone = phoneField.getValue();
                birth = birthSelect.getValue();
                gender = genderSelect.getValue();
                mentorService.updateFirstname(id, firstname);
                mentorService.updateLastname(id, lastname);
                mentorService.updateLogin(id, login);
                mentorService.updatePhone(id, phone);
                mentorService.updateBirthday(id, birth);
                mentorService.updateGender(id, gender);

                allSetReadOnly(true);
                changeSetting.setVisible(true);
                changePassword.setVisible(true);
                save.setVisible(false);
                cancel.setVisible(false);
                Notification.show("Изменения сохранены", 2000, Notification.Position.TOP_CENTER);
            }
        });
    }

    private void cancelInit() {
        cancel = new Button("Отменить");
        cancel.setVisible(false);
        cancel.getElement().getStyle().set("margin-left", "auto");
        cancel.addClickListener(e -> {
            firstnameField.setValue(firstname);
            lastnameField.setValue(lastname);
            birthSelect.setValue(birth);
            loginField.setValue(login);
            phoneField.setValue(phone);
            genderSelect.setValue(gender);
            changeSetting.setVisible(true);
            changePassword.setVisible(true);
            save.setVisible(false);
            cancel.setVisible(false);
            allSetReadOnly(true);
            Notification.show("Изменения отменены", 2000, Notification.Position.TOP_CENTER);
        });
    }

    private void changePasswordInit() {
        changePassword = new Button("Изменить пароль");
        changePassword.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.getElement().getStyle().set("margin-right", "auto");
        changePassword.addClickListener(e ->
                changePassword.getUI().ifPresent(ui ->
                        ui.navigate(MentorSettings2View.class)));
    }

    private void allSetReadOnly(boolean Boolean) {
        firstnameField.setReadOnly(Boolean);
        lastnameField.setReadOnly(Boolean);
        birthSelect.setReadOnly(Boolean);
        phoneField.setReadOnly(Boolean);
        genderSelect.setReadOnly(Boolean);
    }
}
