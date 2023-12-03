package com.cygans.views.participant.notifications;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.notifications.Notifications;
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
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.format.DateTimeFormatter;

/**
 * Страница оповещений участника
 */
@PageTitle("Марафон")
@Route(value = "participant/notifications")
public class ParticipantNotificationView extends VerticalLayout {
    private final NotificationController notificationController;
    private Grid<Notifications> grid;

    public ParticipantNotificationView(NotificationController notificationController) {
        this.notificationController = notificationController;
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
        grid.setDataProvider(new ListDataProvider<>(notificationController.getNotificationWithAnswerNotSeenParticipant(true, null)));
        grid.setAllRowsVisible(true);
        grid.addColumn(new LocalDateRenderer<>(Notifications::getDateNoTime, DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .setHeader("Дата")
                .setWidth("25%")
                .setFlexGrow(0);
        grid.addColumn(notificationController::getTypeNotification, "RequestType")
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
            notificationController.openNotification(notification);
            button.getUI().ifPresent(ui -> ui.navigate(ParticipantNotificationDetailsView.class));
        });
        return button;
    }
}