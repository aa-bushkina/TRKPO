package com.cygans.views.participant.logbooks;


import com.cygans.database.controllers.LogController;
import com.cygans.database.controllers.NotificationController;
import com.cygans.database.sport_log_book.intensity.IntensityType;
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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Марафон")
@Route(value = "participant/sport-logbook")
public class SportLogbookView extends Div {
    private ComboBox<String> intensity;
    private TextField duration;
    private TextField activity;
    private TextArea comments;
    private final Button submitButton = new Button("Добавить");
    private final H3 title = new H3("Спортивная активность");
    private final LogController logController;
    private final NotificationController notificationController;


    public SportLogbookView(LogController logController,
                            NotificationController notificationController) {
        this.logController = logController;
        this.notificationController = notificationController;

        init();
        add(new Toolbar(ToolbarType.PARTICIPANT_PAGES));
        add(createFields());
    }

    private void init() {
        this.intensity = new ComboBox<>("Интенсивность");
        this.activity = new TextField("Вид деятельности");
        this.comments = new TextArea("Комментарии");
        this.duration = new TextField("Продолжительность (в минутах)");
        intensity.setItems(IntensityType.LOWER.getText(), IntensityType.MIDDLE.getText(), IntensityType.HIGH.getText());
    }

    private Component createFields() {
        var formLayout = new FormLayout();
        formLayout.add(
                duration, intensity, activity, comments
        );

        this.comments.setWidth("80%");
        this.comments.setHeight("200px");

        formLayout.setMaxWidth("40%");
        submitButton.setWidth("12%");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.addClickListener(e -> {
            if (duration.getValue().isEmpty()) {
                Notification.show("Введите продолжительность вашей активности!", 3000, Notification.Position.TOP_CENTER)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }
            if (Integer.parseInt(duration.getValue()) < 0) {
                Notification.show("Продолжительность не может быть отрицательной!", 3000, Notification.Position.TOP_CENTER)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }
            if (Integer.parseInt(duration.getValue()) > 1440) {
                Notification.show("Вы не можете заниматься активностью больше 24 часов (1440 минут) в сутки!", 3000, Notification.Position.TOP_CENTER)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }
            if (duration.isInvalid()) {
                Notification.show("Введите продолжительность вашей активности в минутах", 3000, Notification.Position.TOP_CENTER)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }
            if (intensity.isEmpty() || intensity.isInvalid()) {
                Notification.show("Выберите интенсивность тренировок!", 3000, Notification.Position.TOP_CENTER)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }
            if (activity.isEmpty() || activity.isInvalid()) {
                Notification.show("Уточните вашу активность!", 3000, Notification.Position.TOP_CENTER)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }
            Long logId = logController.saveSportLog(intensity.getValue(), duration.getValue(), activity.getValue(), comments.getValue());
            notificationController.addNewSportLogNotification(logId, intensity.getValue(), duration.getValue(), activity.getValue(), comments.getValue());
            submitButton.getUI().ifPresent(ui -> ui.navigate(ParticipantConfirmationView.class));
        });


        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(title);
        verticalLayout.add(formLayout);
        verticalLayout.add(submitButton);
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, title, formLayout, submitButton);
        verticalLayout.setMargin(true);
        var horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(verticalLayout);
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        return horizontalLayout;
    }

}