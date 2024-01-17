package com.cygans.views.mentor.notifications;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.controllers.QuestionController;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
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

@PageTitle("Марафон")
@Route(value = "mentor/notification-details")
public class MentorNotificationDetailsView extends Div {
    private final TextArea msg = new TextArea("Сообщение");
    private final TextArea replyMsg = new TextArea();
    private final Button sendBut = new Button("Отправить");
    private final Button backBut = new Button("Назад");
    private final QuestionController questionController;
    private final NotificationController notificationController;
    private final Notifications thisNotification;

    public MentorNotificationDetailsView(QuestionController questionController,
                                         NotificationController notificationController) {
        this.questionController = questionController;
        this.notificationController = notificationController;

        thisNotification = notificationController.getNotificationByIdFromAttribute();
        setStyles();
        setNavigation();

        HorizontalLayout buttons = new HorizontalLayout(backBut, sendBut);

        VerticalLayout vl = new VerticalLayout(msg, replyMsg, buttons);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        vl.setSpacing(true);

        add(new Toolbar(ToolbarType.MENTOR_PAGES), new H3(" "), vl);

        if (thisNotification.getNotificationTypeId().equals(notificationController.getNotificationTypeId(TypeOfNotification.DECLINE_MENTOR))) {
            notificationController.changeTypeOrStatusNotification(thisNotification.getNotificationId(),
                    null,
                    notificationController.getNotificationStatusId(StatusOfNotification.ANSWERED_SEEN));
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
        replyMsg.setMinLength(1);
        replyMsg.setMaxLength(1000);

        if (notificationController.getTypeNotification(thisNotification).equals(TypeOfNotification.DECLINE_MENTOR.getValue())) {
            if (thisNotification.getNotificationStatusId().equals(notificationController.getNotificationStatusId(StatusOfNotification.NO_ANSWER))) {
                replyMsg.setVisible(false);
            } else {
                replyMsg.setLabel("Ответ участника");
                replyMsg.setValue(thisNotification.getReplyMessage());
                replyMsg.setReadOnly(true);
            }
        } else {
            if (thisNotification.getNotificationStatusId().equals(notificationController.getNotificationStatusId(StatusOfNotification.NO_ANSWER))) {
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
            if (replyMsg.isInvalid() || replyMsg.isEmpty() || replyMsg.getValue().length() > 1000) {
                Notification.show("Ответ должен сожержать от 1 до 1000 символов", 3000, Notification.Position.TOP_CENTER)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else {
                Notification.show("Ответ отправлен", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                notificationController.replyMentorToParticipantNotification(thisNotification, replyMsg.getValue());
                if (thisNotification.getNotificationTypeId().equals(notificationController.getNotificationTypeId(TypeOfNotification.QUESTION))) {
                    questionController.addAnswerToQuestion(thisNotification.getQuestionId(), thisNotification.getNotificationId(), replyMsg.getValue());
                } else {
                    notificationController.changeTypeOrStatusNotification(thisNotification.getNotificationId(),
                            null,
                            notificationController.getNotificationStatusId(StatusOfNotification.ANSWERED_SEEN));
                    notificationController.addAnswerToParticipantLogNotification(thisNotification, replyMsg.getValue());
                }
                sendBut.getUI().ifPresent(ui -> ui.navigate(MentorNotificationView.class));
            }
        });
        backBut.addClickListener(e -> backBut.getUI().ifPresent(ui -> ui.navigate(MentorNotificationView.class)));
    }
}
