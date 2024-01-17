package com.cygans.views.participant.logbooks;

import com.cygans.database.controllers.LogController;
import com.cygans.database.controllers.NotificationController;
import com.cygans.database.eating_log_book.meal.MealType;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalTime;
import java.util.ArrayList;


@PageTitle("Марафон")
@Route(value = "participant/eating-logbook")
public class EatingLogbookView extends Div {
    private final H3 title = new H3("Приём пищи");
    private final Button submitButton = new Button("Отправить");
    private final LogController logController;
    private final NotificationController notificationController;
    private ComboBox<String> hourPicker;
    private ComboBox<String> minutePicker;
    private TextArea description;
    private ComboBox<String> meal_type;
    private LocalTime time;

    public EatingLogbookView(LogController logController,
                             NotificationController notificationController) {
        this.logController = logController;
        this.notificationController = notificationController;

        init();
        add(new Toolbar(ToolbarType.PARTICIPANT_PAGES));
        add(createFields());
    }

    private void init() {
        this.hourPicker = new ComboBox<>("Часы");
        this.minutePicker = new ComboBox<>("Минуты");

        setTimePicker();

        this.description = new TextArea("Описание");
        this.meal_type = new ComboBox<>("Приём пищи");
        meal_type.setItems(MealType.BREAKFAST.getText(), MealType.LAUNCH.getText(), MealType.DINNER.getText(), MealType.OTHER.getText());
        setClearButtonVisible();
        setUnits();

        time = LocalTime.of(Integer.parseInt(hourPicker.getValue()), Integer.parseInt(minutePicker.getValue()));
    }

    private void setTimePicker() {
        ArrayList<String> h = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            h.add("0" + i);
        }
        for (int i = 10; i < 24; i++) {
            h.add(String.valueOf(i));
        }

        hourPicker.setItems(h);
        hourPicker.setValue("00");

        ArrayList<String> m = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            m.add("0" + i);
        }
        for (int i = 10; i < 60; i++) {
            m.add(String.valueOf(i));
        }
        minutePicker.setItems(m);
        minutePicker.setValue("30");
    }


    private void setUnits() {
        Div descr = new Div();
        descr.setText("d");

        Div type = new Div();
        type.setText("d");
    }

    private void setClearButtonVisible() {
        description.setClearButtonVisible(true);
        meal_type.setClearButtonVisible(true);
    }

    private Component createFields() {

        this.description.setWidth("80%");
        this.description.setHeight("200px");
        this.description.setMaxLength(300);

        var formLayout = new FormLayout();
        formLayout.add(
                hourPicker,
                minutePicker,
                description,
                meal_type
        );

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("320px", 1)
        );

        formLayout.setColspan(hourPicker, 1);
        formLayout.setColspan(minutePicker, 2);
        formLayout.setColspan(description, 1);
        formLayout.setColspan(meal_type, 1);


        submitButton.setWidth("30%");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.addClickListener(e -> {
            if (description.isEmpty()) {
                Notification notification = Notification.show("Описание пустое", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (hourPicker.isEmpty()) {
                Notification notification = Notification.show("Уточните час приема пищи", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (description.getValue().length() > 300) {
                Notification notification = Notification.show("Описание слишком длинное", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (minutePicker.isEmpty()) {
                Notification notification = Notification.show("Уточните минуты приема пищи", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (meal_type.isEmpty()) {
                Notification notification = Notification.show("Уточните тип вашего приема пищи", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else {
                Long logId = logController.saveEatingLog(time, description.getValue(), meal_type.getValue());
                notificationController.addNewEatingLogNotification(logId, time, description.getValue(), meal_type.getValue());
                submitButton.getUI().ifPresent(ui ->
                        ui.navigate(ParticipantConfirmationView.class)
                );
            }
        })
        ;
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, title, submitButton);
        verticalLayout.add(title);
        verticalLayout.add(formLayout);
        verticalLayout.add(submitButton);
        verticalLayout.setMaxWidth("40%");
        verticalLayout.setMargin(true);
        var horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(verticalLayout);
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        return horizontalLayout;
    }
}

