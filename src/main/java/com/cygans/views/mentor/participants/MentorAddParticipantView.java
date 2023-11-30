package com.cygans.views.mentor.participants;

import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorService;
import com.cygans.database.notifications.NotificationService;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import com.cygans.security.db.logInfo.LoginInfoService;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;


@PageTitle("Добавить участника")
@Route(value = "mentor/add-participant")
public class MentorAddParticipantView extends Div {
    private final TextField patientUsername = new TextField("Введите логин участника");
    private final Button add = new Button("Добавить");
    private Long participantId;
    private final Long mentorId;

    public MentorAddParticipantView(MentorService mentorService,
                                    ParticipantMentorService participantMentorService,
                                    ParticipantService participantService,
                                    LoginInfoService loginInfoService,
                                    NotificationService notificationService,
                                    NotificationTypeService notificationTypeService,
                                    NotificationStatusService notificationStatusService) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        mentorId = mentorService.getMentorByLoginInfoId(loginInfoService.findByLogin(authentication.getName()).getId()).getId();

        Icon searchIcon = new Icon(VaadinIcon.SEARCH);
        searchIcon.setColor("white");
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add.setEnabled(true);

        patientUsername.addFocusListener(change -> add.setEnabled(true));

        add.addClickListener(e -> {
                    participantId = participantService.searchParticipantId(patientUsername.getValue());

                    if (participantId == null) {
                        Notification.show("Участник не найден", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
                    } else if (notificationService.getNotificationWithAnswerNotSeenList(participantId)
                            .stream()
                            .anyMatch(notification -> notification.getMentorId().equals(mentorId)
                                    && notification.getNotificationTypeId().equals(notificationTypeService.getNotificationTypeId(TypeOfNotification.ADD_REQUEST)))) {
                        Notification.show("Вы уже отправили запрос этому участнику", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
                    } else if (participantMentorService.exist(participantId)) {
                        if (Objects.equals(participantMentorService.findByParticipantId(participantId).getMentorId(), mentorId)) {
                            Notification.show("Участник уже добавлен к вам", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
                        } else {
                            Notification.show("За участником уже закреплен ментор! Пожалуйста, связитесь с поддержкой", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
                        }
                    } else {
                        add.setEnabled(true);
                        Notification.show("Запрос отправлен", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_SUCCESS);

                        // Create and save a new notification to notify patient
                        Long loginInfoId = loginInfoService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId(); // mentor uid
                        Mentor mentor = mentorService.getMentorByLoginInfoId(loginInfoId);
                        Notifications n = new Notifications(
                                participantId,
                                mentor.getId(),
                                notificationTypeService.getNotificationTypeId(TypeOfNotification.ADD_REQUEST),
                                notificationStatusService.getNotificationStatusId(StatusOfNotification.ANSWERED_NOT_SEEN)
                        );
                        n.setShortMessage(mentor.getFirstName() + " " + mentor.getLastName() + " хочет стать твоим ментором");
                        n.setAllMessage(
                                "Mentor " + mentor.getFirstName() + " " + mentor.getLastName() + " хочет стать твоим ментором.\n\n" +
                                        "Напоминание: Принимая запрос, ты соглашаешься с тем, что твой ментор будет видеть все твои действия, " +
                                        "а также информацию о твоем росте, весе и теле.\n" +
                                        "\n" +
                                        "Дата: " + n.getDate().toLocalDate() + "\n" +
                                        "Время: " + n.getDate().toLocalTime() + "\n"
                        );
                        notificationService.saveNotification(n);

                        UI.getCurrent().getPage().reload();
                    }
                }
        );

        HorizontalLayout hl = new HorizontalLayout();
        hl.add(patientUsername, add);
        hl.setAlignItems(FlexComponent.Alignment.BASELINE);
        VerticalLayout vl = new VerticalLayout();
        vl.add(new H2("Добавить участника"), hl, add);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        Toolbar menu = new Toolbar(ToolbarType.MENTOR_PAGES);
        add(menu, vl);
    }
}
