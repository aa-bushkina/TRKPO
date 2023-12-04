package com.cygans.views.mentor.participants;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.mentor.Mentor;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import com.cygans.database.participant.Participant;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Objects;


@PageTitle("Марафон")
@Route(value = "mentor/add-participant")
public class MentorAddParticipantView extends Div {
    private final TextField participantLogin = new TextField("Введите логин участника");
    private final Button add = new Button("Добавить");
    private Participant participant;
    private Mentor mentor;

    public MentorAddParticipantView(ParticipantAndMentorController participantAndMentorController,
                                    NotificationController notificationController) {
        mentor = participantAndMentorController.getIdNowMentorByAuthentication();

        Icon searchIcon = new Icon(VaadinIcon.SEARCH);
        searchIcon.setColor("white");
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add.setEnabled(true);

        participantLogin.setMaxLength(256);
        participantLogin.addFocusListener(change -> add.setEnabled(true));

        add.addClickListener(e -> {
                    participant = participantAndMentorController.getParticipantByLogin(participantLogin.getValue());
                    if (participant == null) {
                        Notification.show("Участник не найден", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
                    } else if (notificationController.getNotificationWithAnswerNotSeenParticipant(false, participant)
                            .stream()
                            .anyMatch(notification -> notification.getMentorId().equals(mentor.getId())
                                    && notification.getNotificationTypeId().equals(notificationController.getNotificationTypeId(TypeOfNotification.ADD_REQUEST)))) {
                        Notification.show("Вы уже отправили запрос этому участнику", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
                    } else if (participantAndMentorController.participantHaveMentor(participant)) {
                        if (Objects.equals(participantAndMentorController.getMentorIdOfParticipant(participant), mentor.getId())) {
                            Notification.show("Участник уже добавлен к вам", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
                        } else {
                            Notification.show("За участником уже закреплен ментор! Пожалуйста, связитесь с поддержкой", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
                        }
                    } else {
                        add.setEnabled(true);
                        Notification.show("Запрос отправлен", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                        notificationController.addRequestToParticipantNotificationNowMentor(participant);
                        UI.getCurrent().getPage().reload();
                    }
                }
        );

        HorizontalLayout hl = new HorizontalLayout();
        hl.add(participantLogin, add);
        hl.setAlignItems(FlexComponent.Alignment.BASELINE);
        VerticalLayout vl = new VerticalLayout();
        vl.add(new H2("Добавить участника"), hl, add);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        Toolbar menu = new Toolbar(ToolbarType.MENTOR_PAGES);
        add(menu, vl);
    }
}
