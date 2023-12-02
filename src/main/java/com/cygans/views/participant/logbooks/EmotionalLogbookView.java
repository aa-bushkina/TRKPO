package com.cygans.views.participant.logbooks;

import com.cygans.database.emotional_log_book.EmotionalLogBook;
import com.cygans.database.emotional_log_book.EmotionalLogBookService;
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
import com.cygans.security.db.logInfo.LoginInfoService;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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
import com.vaadin.flow.server.VaadinSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@PageTitle("Add Emotional Logbook Entry")
@Route(value = "participant/emotional-logbook")
public class EmotionalLogbookView extends Div {
    private final H3 title = new H3("Эмоциональное состояние");
    private final Button submitButton = new Button("Добавить");
    private final TextArea emotionalText = new TextArea();
    private final Long participantId;
    private final EmotionalLogBookService emotionalLogBookService;
    private final NotificationsService notificationsService;
    private final ParticipantService participantService;
    private final ParticipantMentorService participantMentorService;
    private final LogService logService;
    private final NotificationTypeService notificationTypeService;
    private final LogsTypeService logsTypeService;
    private final NotificationStatusService notificationStatusService;

    public EmotionalLogbookView(LoginInfoService loginInfoService,
                                EmotionalLogBookService emotionalLogBookService,
                                NotificationsService notificationsService,
                                ParticipantService participantService,
                                ParticipantMentorService participantMentorService,
                                LogService logService,
                                NotificationTypeService notificationTypeService,
                                LogsTypeService logsTypeService,
                                NotificationStatusService notificationStatusService) {
        this.notificationStatusService = notificationStatusService;
        this.emotionalLogBookService = emotionalLogBookService;
        this.notificationsService = notificationsService;
        this.participantService = participantService;
        this.participantMentorService = participantMentorService;
        this.logService = logService;
        this.notificationTypeService = notificationTypeService;
        this.logsTypeService = logsTypeService;


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        participantId = participantService.getParticipantByLoginInfoId(
                loginInfoService.findByLogin(
                        authentication.getName()
                ).getId()
        ).getId();

        Toolbar menu = new Toolbar(ToolbarType.PARTICIPANT_PAGES);
        add(menu);
        add(createFields());
    }

    private Component createFields() {
        var formLayout = new FormLayout();
        formLayout.add(
                emotionalText
        );

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("320px", 1)
        );

        formLayout.setColspan(emotionalText, 1);
        formLayout.setColspan(submitButton, 1);

        submitButton.setWidth("30%");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        emotionalText.setWidth("80%");
        emotionalText.setMaxLength(1000);
        emotionalText.setMinLength(1);
        emotionalText.setHeight("200px");


        submitButton.addClickListener(e -> {
            if (emotionalText.isInvalid() || emotionalText.isEmpty() || emotionalText.getValue().length() > 1000) {
                Notification notification = Notification.show("Введите от 1 до 1000 символов!", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }
            Log log = new Log(participantId,
                    (LocalDate) VaadinSession.getCurrent().getAttribute("date"),
                    logsTypeService.getLogTypeId(LogBookType.EMOTIONAL.getText()));
            logService.saveLog(log);
            EmotionalLogBook emotionalLogBook = new EmotionalLogBook(
                    log.getId(),
                    LocalDateTime.now(),
                    emotionalText.getValue()
            );
            emotionalLogBookService.saveEmotionalLog(emotionalLogBook);
            if (participantMentorService.checkParticipant(participantId)) {
                Notifications notification = new Notifications(
                        participantId,
                        participantMentorService.getMentorParticipantByParticipantId(participantId).getMentorId(), // Mentor uid
                        notificationTypeService.getNotificationTypeId(TypeOfNotification.NEW_LOG),
                        notificationStatusService.getNotificationStatusId(StatusOfNotification.NO_ANSWER)
                );
                notification.setShortMessage("Новая запись о эмоциональном состоянии");
                notification.setAllMessage(
                        participantService.getFirstname(participantId) + " " + participantService.getLastname(participantId)
                                + " добавил(-а) запись о свочем эмоциональном состоянии.\n" +
                                "\n" +
                                "Дата: " + notification.getDate().toLocalDate() + "\n" +
                                "Время: " + notification.getDate().toLocalTime() + "\n" +
                                "Содержание: " + emotionalText
                );
                notification.setLogBookId(log.getId());
                notificationsService.saveNotification(notification);
            }
            submitButton.getUI().ifPresent(ui ->
                    ui.navigate(ParticipantConfirmationView.class)
            );
        });
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(title);
        verticalLayout.add(formLayout);
        verticalLayout.add(submitButton);
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, title, submitButton);
        verticalLayout.setMaxWidth("40%");
        verticalLayout.setMargin(true);
        var horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(verticalLayout);
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        return horizontalLayout;
    }
}