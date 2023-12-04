package com.cygans.views.mentor.signup;

import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.security.db.RoleEnum;
import com.cygans.views.SignUp1View;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.cygans.views.util.Control;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
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
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;


/**
 * Страница регистрации ментора 2
 */
@PageTitle("Марафон")
@Route(value = "mentorSignUp2")
public class MentorSignUp2View extends Div {
    private final RadioButtonGroup<String> sex;
    private final TextField phone;
    private final DatePicker datePicker;
    private final RegistrationAndLoginController registrationAndLoginController;
    private FormLayout formLayout;
    private Button nextBtn, previousBtn;

    public MentorSignUp2View(RegistrationAndLoginController registrationAndLoginController) {
        this.registrationAndLoginController = registrationAndLoginController;
        add(new Toolbar(ToolbarType.LOGIN));

        this.sex = new RadioButtonGroup<>();
        this.datePicker = new DatePicker("Дата рождения");
        this.phone = new TextField("Номер телефона");
        sexSetUp();
        datePickerSetUp();
        phoneSetUp();
        datePicker.setClearButtonVisible(true);
        datePicker.setLocale(new Locale("ru", "RU"));

        submitButtonInit();
        previousBtnInit();

        formlayoutSetUp();

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        verticalLayout.add(new H2("Персональные данные"));
        verticalLayout.add(formLayout);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidth(verticalLayout.getWidth());
        horizontalLayout.add(previousBtn);
        horizontalLayout.add(nextBtn);
        verticalLayout.add(horizontalLayout);
        verticalLayout.setMaxWidth("600px");
        verticalLayout.setPadding(false);
        HorizontalLayout hl = new HorizontalLayout();
        hl.add(verticalLayout);
        hl.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(hl);
    }

    private void formlayoutSetUp() {
        this.formLayout = new FormLayout();
        formLayout.add(sex, datePicker, phone);
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("200px", 2)
        );
        formLayout.setColspan(sex, 2);
        formLayout.setColspan(datePicker, 1);
        formLayout.setColspan(phone, 1);
    }

    private void sexSetUp() {
        sex.setLabel("Пол");
        sex.setItems("Жен", "Муж");
        if (VaadinSession.getCurrent().getAttribute("Gender") != null) {
            sex.setValue((String) VaadinSession.getCurrent().getAttribute("Gender"));
        }
    }

    private void phoneSetUp() {
        phone.setLabel("Номер телефона");
        phone.setClearButtonVisible(true);
        phone.setPlaceholder("+70000000000");
        phone.setPattern("\\+7\\d{10}");
        phone.setErrorMessage("Формат телефона: +70000000000");
        if (VaadinSession.getCurrent().getAttribute("Phone") != null) {
            phone.setValue((String) VaadinSession.getCurrent().getAttribute("Phone"));
        }
    }

    private void datePickerSetUp() {
        LocalDate now = LocalDate.now(ZoneId.systemDefault());
        datePicker.setMax(now);
        datePicker.setMin(now.minusYears(100));
        datePicker.setLocale(new Locale("ru", "RU"));
        datePicker.setPlaceholder("ДД.ММ.ГГГГ");
        datePicker.setInitialPosition(now.minusYears(30));
        datePicker.setErrorMessage("Неверное значение даты рождения");
        if (VaadinSession.getCurrent().getAttribute("Date") != null) {
            datePicker.setValue((LocalDate) VaadinSession.getCurrent().getAttribute("Date"));
        }
    }

    private void submitButtonInit() {
        nextBtn = new Button("Завершить", new Icon(VaadinIcon.ARROW_RIGHT));
        nextBtn.setIconAfterText(true);
        nextBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        nextBtn.getElement().getStyle().set("margin-left", "auto");
        nextBtn.addClickListener(e -> {
            if (sex.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать пол", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (datePicker.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать дату рождения", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (datePicker.isInvalid()) {
                Notification notification = Notification.show("Неверное значение даты рождения", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (phone.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать номер телефона", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (phone.isInvalid()) {
                Notification notification = Notification.show("Неверное значение номера телефона", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else {
                setSession();
                registrationAndLoginController.registrationUser(RoleEnum.MENTOR);
                getUI().get().getSession().close();
                nextBtn.getUI().ifPresent(ui ->
                        ui.navigate(Control.class)
                );
            }
        });
    }


    private void previousBtnInit() {
        previousBtn = new Button("Назад", new Icon(VaadinIcon.ARROW_LEFT));
        previousBtn.getElement().getStyle().set("margin-right", "0");
        previousBtn.addClickListener(e -> {
            setSession();
            previousBtn.getUI().ifPresent(ui -> ui.navigate(SignUp1View.class));
        });
    }

    private void setSession() {
        VaadinSession.getCurrent().setAttribute("Gender", sex.getValue());
        VaadinSession.getCurrent().setAttribute("Phone", phone.getValue());
        VaadinSession.getCurrent().setAttribute("Date", datePicker.getValue());
    }
}
