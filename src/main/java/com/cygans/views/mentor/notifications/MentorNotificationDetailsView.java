package com.cygans.views.mentor.notifications;

import com.cygans.database.notifications.NotificationService;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import com.cygans.database.question.QuestionService;
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

@PageTitle("Notification Details")
@Route(value = "mentor/notification-details")
public class MentorNotificationDetailsView extends Div {
    private final TextArea msg = new TextArea("Сообщение");
    private final TextArea replyMsg = new TextArea();
    private final Button sendBut = new Button("Отправить");
    private final Button backBut = new Button("Назад");
    private final NotificationTypeService notificationTypeService;
    private final NotificationStatusService notificationStatusService;
    private final NotificationService notificationService;
    private final QuestionService questionService;
    private final Notifications thisNotification;

    public MentorNotificationDetailsView(NotificationService notificationService,
                                         NotificationTypeService notificationTypeService,
                                         QuestionService questionService,
                                         NotificationStatusService notificationStatusService) {
        this.notificationStatusService = notificationStatusService;
        this.notificationTypeService = notificationTypeService;
        this.notificationService = notificationService;
        this.questionService = questionService;

        thisNotification = notificationService.getNotificationById((long) VaadinSession.getCurrent().getAttribute("NotificationID"));
        setStyles();
        setNavigation();

        HorizontalLayout buttons = new HorizontalLayout(sendBut, backBut);

        VerticalLayout vl = new VerticalLayout(msg, replyMsg, buttons);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        vl.setSpacing(true);

        add(new Toolbar(ToolbarType.MENTOR_PAGES), new H3(" "), vl);

        if (thisNotification.getNotificationTypeId().equals(notificationTypeService.getNotificationTypeId(TypeOfNotification.DECLINE_MENTOR))) {
            notificationService.updateNotificationStatus(thisNotification.getNotificationId(),
                    notificationStatusService.getNotificationStatusId(StatusOfNotification.ANSWERED_SEEN));
        }
    }

    private void setStyles() {
        sendBut.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        sendBut.setVisible(false);
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
                .equals(TypeOfNotification.DECLINE_MENTOR.getValue())) {
            if (thisNotification.getNotificationStatusId().equals(notificationStatusService.getNotificationStatusId(StatusOfNotification.NO_ANSWER))) {
                replyMsg.setVisible(false);
            } else {
                replyMsg.setLabel("Ответ участника");
                replyMsg.setValue(thisNotification.getReplyMessage());
                replyMsg.setReadOnly(true);
            }
        } else {
            if (thisNotification.getNotificationStatusId().equals(notificationStatusService.getNotificationStatusId(StatusOfNotification.NO_ANSWER))) {
                replyMsg.setLabel("Ответ");
                replyMsg.setClearButtonVisible(true);
                sendBut.setVisible(true);
            } else {
                replyMsg.setLabel("Ваш ответ");
                replyMsg.setValue(thisNotification.getReplyMessage());
                replyMsg.setReadOnly(true);
            }
        }
    }

    private void setNavigation() {
        sendBut.addClickListener(e -> {
            Notification.show("Ответ отправлен", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            notificationService.reply(thisNotification.getNotificationId(), replyMsg.getValue());
            notificationService.resolveRequest(thisNotification.getNotificationId());
            if (thisNotification.getNotificationTypeId().equals(notificationTypeService.getNotificationTypeId(TypeOfNotification.QUESTION))) {
                questionService.addAnswer(thisNotification.getQuestionId(), replyMsg.getValue());
                notificationService.updateNotificationType(thisNotification.getNotificationId(),
                        notificationTypeService.getNotificationTypeId(TypeOfNotification.ANSWER_ON_QUESTION));
            } else {
                notificationService.updateNotificationType(thisNotification.getNotificationId(),
                        notificationTypeService.getNotificationTypeId(TypeOfNotification.ANSWER_ON_LOG));
            }
            sendBut.getUI().ifPresent(ui -> ui.navigate(MentorNotificationView.class));
        });
        backBut.addClickListener(e -> {
            backBut.getUI().ifPresent(ui -> ui.navigate(MentorNotificationView.class));
        });
    }
}
