package com.cygans.views.participant.logbooks;


import com.cygans.database.log_book.Log;
import com.cygans.database.log_book.LogService;
import com.cygans.database.log_book.logs_type.LogBookType;
import com.cygans.database.log_book.logs_type.LogsTypeService;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import com.cygans.database.sport_log_book.SportLogBook;
import com.cygans.database.sport_log_book.SportLogBookService;
import com.cygans.database.sport_log_book.intensity.IntensityService;
import com.cygans.database.sport_log_book.intensity.IntensityType;
import com.cygans.security.db.logInfo.LoginInfoService;
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
import com.vaadin.flow.server.VaadinSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.LocalDateTime;


@PageTitle("Add sport logbook")
@Route(value = "participant/sport-logbook")
public class SportLogbookView extends Div {
    private ComboBox<String> intensity;
    private TextField duration;
    private TextField activity;
    private TextArea comments;
    private final Button submitButton = new Button("Добавить");
    private final H3 title = new H3("Спортивная активность");
    private final Long participantId;
    private final SportLogBookService sportLogBookService;
    private final LogService logService;
    private final IntensityService intensityService;
    private final LogsTypeService logsTypeService;
    private final ParticipantMentorService participantMentorService;
    private final NotificationsService notificationsService;
    private final NotificationTypeService notificationTypeService;
    private final ParticipantService participantService;
    private final NotificationStatusService notificationStatusService;


    public SportLogbookView(LoginInfoService loginInfoService,
                            SportLogBookService sportLogBookService,
                            LogService logService,
                            IntensityService intensityService,
                            LogsTypeService logsTypeService,
                            ParticipantMentorService participantMentorService,
                            NotificationsService notificationsService,
                            NotificationTypeService notificationTypeService,
                            ParticipantService participantService,
                            NotificationStatusService notificationStatusService) {
        this.sportLogBookService = sportLogBookService;
        this.logService = logService;
        this.intensityService = intensityService;
        this.logsTypeService = logsTypeService;
        this.participantMentorService = participantMentorService;
        this.notificationsService = notificationsService;
        this.notificationTypeService = notificationTypeService;
        this.participantService = participantService;
        this.notificationStatusService = notificationStatusService;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        participantId = participantService.getParticipantByLoginInfoId(
                        loginInfoService.findByLogin(
                                        authentication.getName())
                                .getId())
                .getId();
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
        this.duration.setPlaceholder("15");
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
            Log log = new Log(participantId,
                    (LocalDate) VaadinSession.getCurrent().getAttribute("date"),
                    logsTypeService.getLogTypeId(LogBookType.SPORT.getText()));
            logService.saveLog(log);
            SportLogBook sportLogBook = new SportLogBook(log.getId(),
                    intensityService.getIntensityId(intensity.getValue()),
                    Integer.parseInt(duration.getValue()),
                    LocalDateTime.now(),
                    activity.getValue(),
                    comments.getValue());
            sportLogBookService.saveSportLog(sportLogBook);
            submitButton.getUI().ifPresent(ui -> ui.navigate(ParticipantConfirmationView.class));
            Long participantMentorId = null;
            if (participantMentorService.checkParticipant(participantId)) {
                participantMentorId = participantMentorService.getMentorParticipantByParticipantId(participantId).getMentorId();
            }
            Notifications notification = new Notifications(
                    participantId,
                    participantMentorId,
                    notificationTypeService.getNotificationTypeId(TypeOfNotification.NEW_LOG),
                    notificationStatusService.getNotificationStatusId(StatusOfNotification.NO_ANSWER)
            );
            notification.setShortMessage("Новая запись о спортивной активности");
            notification.setAllMessage(
                    participantService.getFirstname(participantId) + " " + participantService.getLastname(participantId)
                            + " добавил(-а) запись о своей спортивной активности.\n" +
                            "\n" +
                            "Дата: " + notification.getDate().toLocalDate() + "\n" +
                            "Время: " + notification.getDate().toLocalTime() + "\n" +
                            "Интенсивность: " + intensityService.getIntensityId(intensity.getValue()) + "\n" +
                            "Активность: " + activity.getValue() + "\n" +
                            "Продолжительность: " + Integer.parseInt(duration.getValue()) + " минут\n" +
                            "Описание: " + comments.getValue()
            );
            notification.setLogBookId(log.getId());
            notificationsService.saveNotification(notification);

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