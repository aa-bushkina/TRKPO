package com.cygans.views.participant.notifications;

import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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

@PageTitle("Марафон")
@Route(value = "participant/notification-details")
public class ParticipantNotificationDetailsView extends Div {
    private final TextArea msg = new TextArea("Сообщение:");
    private final TextArea replyMsg = new TextArea();
    private final Button agreeBut = new Button("Принять");
    private Button backBut;
    private final NotificationTypeService notificationTypeService;
    private final NotificationStatusService notificationStatusService;
    private final ParticipantService participantService;
    private final NotificationsService notificationsService;
    private final ParticipantMentorService participantMentorService;
    private final Notifications thisNotification;

    public ParticipantNotificationDetailsView(ParticipantMentorService participantMentorService,
                                              NotificationsService notificationsService,
                                              NotificationTypeService notificationTypeService,
                                              ParticipantService participantService,
                                              NotificationStatusService notificationStatusService) {
        this.notificationTypeService = notificationTypeService;
        this.notificationStatusService = notificationStatusService;
        this.participantService = participantService;
        this.notificationsService = notificationsService;
        this.participantMentorService = participantMentorService;
        thisNotification = notificationsService.getNotificationById((long) VaadinSession.getCurrent().getAttribute("NotificationID"));

        backInit();
        setStyles();
        setNavigation();
        HorizontalLayout buttons = new HorizontalLayout(backBut, agreeBut);

        VerticalLayout vl = new VerticalLayout(msg, replyMsg, buttons);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        vl.setSpacing(true);

        add(new Toolbar(ToolbarType.PARTICIPANT_PAGES), new H3(" "), vl);

        if (!notificationTypeService.getNotificationTypeType(thisNotification.getNotificationTypeId()).equals(TypeOfNotification.ADD_REQUEST.getValue())) {
            notificationsService.updateNotificationStatus(thisNotification.getNotificationId(),
                    notificationStatusService.getNotificationStatusId(StatusOfNotification.ANSWERED_SEEN));
        }
    }

    private void backInit() {
        if (notificationTypeService.getNotificationTypeType(thisNotification.getNotificationTypeId()).equals(TypeOfNotification.ADD_REQUEST.getValue())) {
            backBut = new Button("Отказать");
        } else {
            backBut = new Button("Назад");
        }
    }

    private void setStyles() {
        agreeBut.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        agreeBut.setVisible(false);
        backBut.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        msg.setValue(thisNotification.getAllMessage());
        msg.setReadOnly(true);
        msg.setWidth("50%");
        msg.setMinHeight("80%");
        msg.setMaxHeight("300px");
        replyMsg.setWidth("50%");
        replyMsg.setMinHeight("80%");
        replyMsg.setMaxHeight("300px");

        if (notificationTypeService.getNotificationTypeType(thisNotification.getNotificationTypeId())
                .equals(TypeOfNotification.ADD_REQUEST.getValue())) {
            if (thisNotification.getNotificationStatusId().equals(notificationStatusService.getNotificationStatusId(StatusOfNotification.ANSWERED_NOT_SEEN))) {
                replyMsg.setVisible(false);
                agreeBut.setVisible(true);
            } else {
                replyMsg.setLabel("Твой ответ:");
                replyMsg.setValue(thisNotification.getReplyMessage());
                replyMsg.setReadOnly(true);
            }
        } else {
            if (thisNotification.getNotificationStatusId().equals(notificationStatusService.getNotificationStatusId(StatusOfNotification.ANSWERED_NOT_SEEN))) {
                replyMsg.setVisible(false);
            } else {
                replyMsg.setLabel("Ответ от ментора:");
                replyMsg.setValue(thisNotification.getReplyMessage());
                replyMsg.setReadOnly(true);
            }
        }
    }

    private void setNavigation() {
        agreeBut.addClickListener(e -> {
            Notification.show("Ответ отправлен", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            participantMentorService.create(thisNotification.getParticipantId(), thisNotification.getMentorId());
            notificationsService.resolveRequest(thisNotification.getNotificationId());
            notificationsService.reply(thisNotification.getNotificationId(),
                    participantService.getFirstname(thisNotification.getParticipantId()) + " "
                            + participantService.getLastname(thisNotification.getParticipantId())
                            + " принял запрос на менторство.");
            notificationsService.updateNotificationStatus(thisNotification.getNotificationId(),
                    notificationStatusService.getNotificationStatusId(StatusOfNotification.NO_ANSWER));
            if (notificationTypeService.getNotificationTypeType(thisNotification.getNotificationTypeId()).equals(TypeOfNotification.ADD_REQUEST.getValue())) {
                notificationsService.updateNotificationStatus(thisNotification.getNotificationId(),
                        notificationStatusService.getNotificationStatusId(StatusOfNotification.ANSWERED_SEEN));
            }
            agreeBut.getUI().ifPresent(ui ->
                    ui.navigate(ParticipantNotificationView.class)
            );
        });
        backBut.addClickListener(e -> {
            if (notificationTypeService.getNotificationTypeType(thisNotification.getNotificationTypeId()).equals(TypeOfNotification.ADD_REQUEST.getValue())) {
                notificationsService.updateNotificationStatus(thisNotification.getNotificationId(),
                        notificationStatusService.getNotificationStatusId(StatusOfNotification.NO_ANSWER));
                notificationsService.updateNotificationType(thisNotification.getNotificationId(),
                        notificationTypeService.getNotificationTypeId(TypeOfNotification.DECLINE_MENTOR));
            }
            backBut.getUI().ifPresent(ui ->
                    ui.navigate(ParticipantNotificationView.class)
            );
        });
    }
}