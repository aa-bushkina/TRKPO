package com.cygans.views.mentor.participants;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.mentor.MentorService;
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


@PageTitle("Марафон")
@Route(value = "mentor/add-participant")
public class MentorAddParticipantView extends Div {
    private final TextField participantLogin = new TextField("Введите логин участника");
    private final Button add = new Button("Добавить");
    private Long participantId;
    private final Long mentorId;
    private final  NotificationController notificationController;

    public MentorAddParticipantView(MentorService mentorService,
                                    ParticipantMentorService participantMentorService,
                                    ParticipantService participantService,
                                    LoginInfoService loginInfoService,
                                    NotificationController notificationController) {
        this.notificationController = notificationController;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        mentorId = mentorService.getMentorByLoginInfoId(loginInfoService.findByLogin(authentication.getName()).getId()).getId();

        Icon searchIcon = new Icon(VaadinIcon.SEARCH);
        searchIcon.setColor("white");
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add.setEnabled(true);

        participantLogin.addFocusListener(change -> add.setEnabled(true));

        add.addClickListener(e -> {
                    participantId = participantService.searchParticipantId(participantLogin.getValue());
                    if (participantId == null) {
                        Notification.show("Участник не найден", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
                    } else if (notificationController.getNotificationWithAnswerNotSeenParticipant(false, participantId)
                            .stream()
                            .anyMatch(notification -> notification.getMentorId().equals(mentorId)
                                    && notification.getNotificationTypeId().equals(notificationController.getNotificationTypeId(TypeOfNotification.ADD_REQUEST)))) {
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
                        notificationController.addRequestToParticipantNotificationNowMentor(participantId);
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
