package com.cygans.views.participant.notifications;

import com.cygans.database.notifications.NotificationService;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import com.cygans.security.db.logInfo.LoginInfoService;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Страница оповещений участника
 */
@PageTitle("Оповещения")
@Route(value = "participant/notifications")
public class ParticipantNotificationView extends VerticalLayout {
    private Grid<Notifications> grid;
    private final Participant participant;
    private final NotificationService notificationService;
    private final NotificationTypeService notificationTypeService;
    private final NotificationStatusService notificationStatusService;

    public ParticipantNotificationView(NotificationService notificationService,
                                       LoginInfoService loginInfoService,
                                       ParticipantService participantService,
                                       NotificationTypeService notificationTypeService,
                                       NotificationStatusService notificationStatusService) {
        this.notificationService = notificationService;
        this.notificationStatusService = notificationStatusService;
        this.notificationTypeService = notificationTypeService;
        Long loginInfoId = loginInfoService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
        participant = participantService.getParticipantByLoginInfoId(loginInfoId);
        VerticalLayout vl = new VerticalLayout();
        setSizeFull();
        createGrid();
        vl.add(new H3("Оповещения"));
        vl.setPadding(true);
        vl.setMargin(true);
        vl.setAlignItems(Alignment.CENTER);
        add(new H5(" "), vl, grid, new Toolbar(ToolbarType.PARTICIPANT_PAGES));
        setPadding(true);
        setMargin(true);
    }

    private void createGrid() {
        grid = new Grid<>();
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        grid.setAllRowsVisible(true);
        grid.setDataProvider(new ListDataProvider<>(notificationService.getNotificationWithAnswerNotSeenList(participant.getId())));
        grid.setAllRowsVisible(true);
        grid.addColumn(Notifications::getDateNoTime, "Date")
                .setHeader("Дата")
                .setWidth("25%")
                .setFlexGrow(0);
        grid.addColumn(notification -> notificationTypeService.getNotificationTypeType(notification.getNotificationTypeId()), "RequestType")
                .setHeader("Событие")
                .setWidth("30%")
                .setFlexGrow(0);
        grid.addComponentColumn(this::buildParticipantViewButton)
                .setWidth("23%")
                .setFlexGrow(0);
    }

    public Button buildParticipantViewButton(Notifications notification) {
        Button button = new Button("Смотреть");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickListener(click -> {
            VaadinSession.getCurrent().setAttribute("NotificationID", notification.getNotificationId());
            notification.setNotificationStatusId(notificationStatusService.getNotificationStatusId(StatusOfNotification.ANSWERED_SEEN));
            button.getUI().ifPresent(ui -> ui.navigate(ParticipantNotificationDetailsView.class)); //change navigation class
        });
        return button;
    }
}