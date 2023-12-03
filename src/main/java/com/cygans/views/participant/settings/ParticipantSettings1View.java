package com.cygans.views.participant.settings;


import com.cygans.database.controllers.SettingsController;
import com.cygans.database.participant.Participant;
import com.cygans.security.db.RoleEnum;
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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;


@PageTitle("Марафон")
@Route(value = "participant/settings")
public class ParticipantSettings1View extends HorizontalLayout {
    private String firstname, lastname, login, phone, gender;
    private LocalDate birth;
    private Integer height, weight, breast, waist, hips;
    private TextField firstnameField, lastnameField;
    private DatePicker birthSelect;
    private TextField loginField, phoneField, heightField, weightField, breastField, waistField, hipsField;
    private Select<String> genderSelect;
    private Button changeSetting, save, cancel, changePassword;
    private SettingsController settingsController;

    public ParticipantSettings1View(SettingsController settingsController) {
        this.settingsController = settingsController;
        init();
        FormLayout formLayout = new FormLayout();
        formLayout.add(
                firstnameField, lastnameField,
                birthSelect, genderSelect,
                loginField, phoneField,
                breastField, heightField,
                waistField, weightField,
                hipsField
        );
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
        formLayout.setColspan(breastField, 1);
        formLayout.setColspan(heightField, 1);
        formLayout.setColspan(waistField, 1);
        formLayout.setColspan(weightField, 1);
        formLayout.setColspan(hipsField, 1);

        VerticalLayout mainLayout = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setWidth(mainLayout.getWidth());
        buttons.add(changePassword, changeSetting, cancel, save);

        mainLayout.add(
                new H1("  "),
                new H2("Настройки юзера"),
                buttons,
                formLayout
        );
        mainLayout.setMaxWidth("600px");
        mainLayout.setPadding(true);
        add(new Toolbar(ToolbarType.PARTICIPANT_PAGES), mainLayout);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

    private void init() {
        Participant participant = settingsController.getAuthoritiesParticipant();
        firstname = participant.getFirstName();
        lastname = participant.getLastName();
        login = participant.getLogin();
        gender = participant.getGender();
        phone = participant.getPhone();
        birth = participant.getBirthday();
        height = participant.getHeight();
        weight = participant.getWeight();
        breast = participant.getBreast();
        waist = participant.getWaist();
        hips = participant.getHips();

        firstNameInit();
        lastNameInit();
        birthSelectInit();
        loginFieldInit();
        phoneInit();
        genderSelectInit();
        heightInit();
        weightInit();
        breastInit();
        waistInit();
        hipsInit();
        changeSettingInit();
        saveSetUp(participant.getId());
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

    private void heightInit() {
        heightField = new TextField("Рост");
        heightField.setClearButtonVisible(true);
        heightField.setPattern("(6[7-9]|[78]\\d|9[0-9]|1\\d{2}|250)");
        heightField.setErrorMessage("Неверный формат роста");
        heightField.setValue(String.valueOf(height));
        heightField.setReadOnly(true);
    }

    private void weightInit() {
        weightField = new TextField("Вес");
        weightField.setClearButtonVisible(true);
        weightField.setPattern("([2-9]|[1-9][0-9]|[1-5][0-9][0-9]|[6][0][0])");
        weightField.setErrorMessage("Неверный формат веса");
        weightField.setValue(String.valueOf(weight));
        weightField.setReadOnly(true);
    }

    private void waistInit() {
        waistField = new TextField("Обхват талии");
        waistField.setClearButtonVisible(true);
        waistField.setPattern("([3-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|2[5][0])");
        waistField.setErrorMessage("Неверный формат обхвата талии");
        waistField.setValue(String.valueOf(waist));
        waistField.setReadOnly(true);
    }

    private void hipsInit() {
        hipsField = new TextField("Обхват бедер");
        hipsField.setClearButtonVisible(true);
        hipsField.setPattern("([3-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|2[5][0])");
        hipsField.setErrorMessage("Неверный формат обхвата бедер");
        hipsField.setValue(String.valueOf(hips));
        hipsField.setReadOnly(true);
    }

    private void breastInit() {
        breastField = new TextField("Обхват груди");
        breastField.setClearButtonVisible(true);
        breastField.setPattern("([3-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|2[5][0])");
        breastField.setErrorMessage("Неверный формат обхвата груди");
        breastField.setValue(String.valueOf(breast));
        breastField.setReadOnly(true);
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

    private void saveSetUp(Long uid) {
        save = new Button("Сохранить");
        save.setVisible(false);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.getElement().getStyle().set("margin-left", "18em");

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
            } else if (heightField.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать рост", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (heightField.isInvalid()) {
                Notification notification = Notification.show("Неверный формат роста", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (weightField.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать вес", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (weightField.isInvalid()) {
                Notification notification = Notification.show("Неверный формат веса", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (breastField.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать обхват груди", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (breastField.isInvalid()) {
                Notification notification = Notification.show("Неверный формат обхвата груди", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (waistField.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать обхват талии", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (waistField.isInvalid()) {
                Notification notification = Notification.show("Неверный формат обхвата талии", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (hipsField.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать обхват бедер", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (hipsField.isInvalid()) {
                Notification notification = Notification.show("Неверный формат обхвата бедер", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else {
                settingsController.updateInfoUser(RoleEnum.PARTICIPANT,
                        firstnameField.getValue(),
                        lastnameField.getValue(),
                        loginField.getValue(),
                        phoneField.getValue(),
                        birthSelect.getValue(),
                        genderSelect.getValue(),
                        Integer.valueOf(heightField.getValue()),
                        Integer.valueOf(weightField.getValue()),
                        Integer.valueOf(breastField.getValue()),
                        Integer.valueOf(waistField.getValue()),
                        Integer.valueOf(hipsField.getValue()));
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
        cancel.getElement().getStyle().set("margin-right", "auto");
        cancel.addClickListener(e -> {
            firstnameField.setValue(firstname);
            lastnameField.setValue(lastname);
            birthSelect.setValue(birth);
            loginField.setValue(login);
            phoneField.setValue(phone);
            genderSelect.setValue(gender);
            genderSelect.setValue(String.valueOf(height));
            genderSelect.setValue(String.valueOf(weight));
            genderSelect.setValue(String.valueOf(breast));
            genderSelect.setValue(String.valueOf(waist));
            genderSelect.setValue(String.valueOf(hips));
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
                        ui.navigate(ParticipantSettings2View.class)));
    }

    private void allSetReadOnly(boolean Boolean) {
        firstnameField.setReadOnly(Boolean);
        lastnameField.setReadOnly(Boolean);
        birthSelect.setReadOnly(Boolean);
        phoneField.setReadOnly(Boolean);
        genderSelect.setReadOnly(Boolean);
        heightField.setReadOnly(Boolean);
        weightField.setReadOnly(Boolean);
        breastField.setReadOnly(Boolean);
        waistField.setReadOnly(Boolean);
        hipsField.setReadOnly(Boolean);
    }
}