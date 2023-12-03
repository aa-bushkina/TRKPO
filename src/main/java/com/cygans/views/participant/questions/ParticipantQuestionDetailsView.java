package com.cygans.views.participant.questions;

import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import com.cygans.database.question.Question;
import com.cygans.database.question.QuestionService;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.cygans.views.participant.history.ParticipantHistoryView;
import com.cygans.views.participant.notifications.ParticipantNotificationView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@PageTitle("Question Details")
@Route(value = "participant/question-details")
public class ParticipantQuestionDetailsView extends Div {

    private LocalDate selectDate;
    private Long questionId;

    private Toolbar menu = new Toolbar(ToolbarType.PARTICIPANT_PAGES);
    private final Button Back = new Button("Назад");
    private final TextArea msg = new TextArea("Сообщение:");
    private final TextArea replyMsg = new TextArea();
    private final Button agreeBut = new Button("Принять");
    private final Button backBut = new Button("Назад");
    private final NotificationTypeService notificationTypeService;
    private final NotificationStatusService notificationStatusService;
    private final QuestionService questionService;

    public ParticipantQuestionDetailsView(NotificationTypeService notificationTypeService,
                                          NotificationStatusService notificationStatusService,
                                          QuestionService questionService) {

        removeAll();
        add(menu);
        this.notificationTypeService = notificationTypeService;
        this.notificationStatusService = notificationStatusService;
        this.questionService = questionService;
        selectDate = (LocalDate) VaadinSession.getCurrent().getAttribute("CheckDate");
        questionId = (Long) VaadinSession.getCurrent().getAttribute("QuestionId");

        add(Back);
        Back.addClickListener(click -> Back.getUI().ifPresent(ui -> ui.navigate(ParticipantHistoryView.class)));

        add(
                Back,
                new HorizontalLayout(
                        new Paragraph("Дата вопроса: " + selectDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))))
        );

        Question question = questionService.getQuestionById(questionId);
        Span ques = new Span("Вопрос: " + question.getQuestion());
        Span answ = new Span("Ответ: " + question.getAnswer());
        VerticalLayout layout = new VerticalLayout(ques, answ);
        add(layout);

    }

    private void setStyles(Notifications thisNotifications) {
        agreeBut.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        agreeBut.setVisible(false);
        backBut.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        msg.setValue(thisNotifications.getAllMessage());
        msg.setReadOnly(true);
        msg.setWidth("50%");
        msg.setMinHeight("80%");
        msg.setMaxHeight("300px");
        replyMsg.setWidth("50%");
        replyMsg.setMinHeight("80%");
        replyMsg.setMaxHeight("300px");
        
        if (notificationTypeService.getNotificationTypeType(thisNotifications.getNotificationTypeId())
                .equals(TypeOfNotification.ADD_REQUEST.getValue())) {

            if (thisNotifications.getNotificationStatusId().equals(notificationStatusService.getNotificationStatusId(StatusOfNotification.ANSWERED_NOT_SEEN))) {
                replyMsg.setVisible(false);
                agreeBut.setVisible(true);
            } else {
                replyMsg.setLabel("Твой ответ:");
                replyMsg.setValue(thisNotifications.getReplyMessage());
                replyMsg.setReadOnly(true);
            }
        } else {
            if (thisNotifications.getNotificationStatusId().equals(notificationStatusService.getNotificationStatusId(StatusOfNotification.ANSWERED_NOT_SEEN))) {
                replyMsg.setVisible(false);
            } else {
                replyMsg.setLabel("Ответ от ментора:");
                replyMsg.setValue(thisNotifications.getReplyMessage());
                replyMsg.setReadOnly(true);
            }
        }
    }

    private void setNavigation(ParticipantMentorService participantMentorService,
                               NotificationsService NotificationsService,
                               Notifications thisNotifications,
                               ParticipantService participantService) {
        agreeBut.addClickListener(e -> {
            Notification.show("Ответ отправлен", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            participantMentorService.create(thisNotifications.getParticipantId(), thisNotifications.getMentorId());
            NotificationsService.resolveRequest(thisNotifications.getNotificationId());
            NotificationsService.reply(thisNotifications.getNotificationId(),
                    participantService.getFirstname(thisNotifications.getParticipantId()) + " "
                        + participantService.getLastname(thisNotifications.getParticipantId())
                        + " принял запрос на менторство.");
            agreeBut.getUI().ifPresent(ui ->
                    ui.navigate(ParticipantNotificationView.class)
            );
        });
        backBut.addClickListener(e ->
                backBut.getUI().ifPresent(ui ->
                        ui.navigate(ParticipantNotificationView.class)
                ));
    }
}