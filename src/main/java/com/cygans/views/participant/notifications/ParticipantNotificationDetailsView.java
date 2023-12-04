package com.cygans.views.participant.notifications;

import com.cygans.database.controllers.LogController;
import com.cygans.database.controllers.NotificationController;
import com.cygans.database.log_book.Log;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.cygans.views.participant.logbooks.ParticipantLogbookView;
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

import java.util.Objects;

@PageTitle("Марафон")
@Route(value = "participant/notification-details")
public class ParticipantNotificationDetailsView extends Div {
    private final TextArea msg = new TextArea("Сообщение:");
    private final TextArea replyMsg = new TextArea();
    private final Button agreeBut = new Button("Принять");
    private final Notifications thisNotification;
    private final NotificationController notificationController;
    private final LogController logController;
    private Button backBut;

    public ParticipantNotificationDetailsView(NotificationController notificationController, LogController logController) {
        this.notificationController = notificationController;
        this.logController = logController;
        thisNotification = notificationController.getNotificationByIdFromAttribute();

        backInit();
        setStyles();
        setNavigation();
        HorizontalLayout buttons = new HorizontalLayout(backBut, agreeBut);

        VerticalLayout vl = new VerticalLayout(msg, replyMsg, buttons);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        vl.setSpacing(true);

        add(new Toolbar(ToolbarType.PARTICIPANT_PAGES), new H3(" "), vl);

        if (!notificationController.getTypeNotification(thisNotification).equals(TypeOfNotification.ADD_REQUEST.getValue())) {
            notificationController.changeTypeOrStatusNotification(thisNotification.getNotificationId(),
                    null,
                    notificationController.getNotificationStatusId(StatusOfNotification.ANSWERED_SEEN));
        }
    }

    private void backInit() {
        if (notificationController.getTypeNotification(thisNotification).equals(TypeOfNotification.ADD_REQUEST.getValue())) {
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
        replyMsg.setMaxLength(1000);

        if (notificationController.getTypeNotification(thisNotification).equals(TypeOfNotification.ADD_REQUEST.getValue())) {
            if (thisNotification.getNotificationStatusId().equals(notificationController.getNotificationStatusId(StatusOfNotification.ANSWERED_NOT_SEEN))) {
                replyMsg.setVisible(false);
                agreeBut.setVisible(true);
            } else {
                replyMsg.setLabel("Твой ответ:");
                replyMsg.setValue(thisNotification.getReplyMessage());
                replyMsg.setReadOnly(true);
            }
        } else if (notificationController.getTypeNotification(thisNotification).equals(TypeOfNotification.ANSWER_ON_LOG.getValue())) {
            agreeBut.setVisible(true);
            agreeBut.setText("Перейти к записи");
            replyMsg.setVisible(false);
        } else if (notificationController.getTypeNotification(thisNotification).equals(TypeOfNotification.ANSWER_ON_QUESTION.getValue())) {
            replyMsg.setLabel("Ответ от ментора:");
            replyMsg.setValue(thisNotification.getReplyMessage());
            replyMsg.setReadOnly(true);
        } else {
            if (thisNotification.getNotificationStatusId().equals(notificationController.getNotificationStatusId(StatusOfNotification.ANSWERED_NOT_SEEN))) {
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
            if (Objects.equals(thisNotification.getNotificationTypeId(), notificationController.getNotificationTypeId(TypeOfNotification.ADD_REQUEST))) {
                notificationController.replyParticipantToMentorRequest(thisNotification);
            }
            notificationController.changeTypeOrStatusNotification(thisNotification.getNotificationId(),
                    null,
                    notificationController.getNotificationStatusId(StatusOfNotification.NO_ANSWER));

            if (notificationController.getTypeNotification(thisNotification).equals(TypeOfNotification.ANSWER_ON_LOG.getValue())) {
                notificationController.changeTypeOrStatusNotification(thisNotification.getNotificationId(),
                        null,
                        notificationController.getNotificationStatusId(StatusOfNotification.ANSWERED_SEEN));
                Log log = logController.getLogByLogbookId(thisNotification.getLogBookId());
                VaadinSession.getCurrent().setAttribute("LogbookType", logController.getLogsLogtype(log));
                VaadinSession.getCurrent().setAttribute("CheckDate", log.getDate());
                VaadinSession.getCurrent().setAttribute("LogbookId", log.getId());
                agreeBut.getUI().ifPresent(ui ->
                        ui.navigate(ParticipantLogbookView.class)
                );
            } else {
                Notification.show("Ответ отправлен", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                if (notificationController.getTypeNotification(thisNotification).equals(TypeOfNotification.ADD_REQUEST.getValue())) {
                    notificationController.changeTypeOrStatusNotification(thisNotification.getNotificationId(),
                            null,
                            notificationController.getNotificationStatusId(StatusOfNotification.ANSWERED_SEEN));
                }
                agreeBut.getUI().ifPresent(ui ->
                        ui.navigate(ParticipantNotificationView.class)
                );
            }
        });
        backBut.addClickListener(e -> {
            if (notificationController.getTypeNotification(thisNotification).equals(TypeOfNotification.ADD_REQUEST.getValue())) {
                notificationController.addDeclineToMentorNotificationNowParticipant(thisNotification.getMentorId());
                notificationController.changeTypeOrStatusNotification(thisNotification.getNotificationId(),
                        null,
                        notificationController.getNotificationStatusId(StatusOfNotification.ANSWERED_SEEN));
            }
            backBut.getUI().ifPresent(ui ->
                    ui.navigate(ParticipantNotificationView.class)
            );
        });
    }
}