package com.cygans.views.mentor.notifications;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.notifications.Notifications;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.apache.commons.lang3.StringUtils;

import java.time.format.DateTimeFormatter;


@PageTitle("Марафон")
@Route(value = "mentor/notifications")
public class MentorNotificationView extends VerticalLayout {
    private Grid<Notifications> grid;
    private ListDataProvider<Notifications> dataProvider;
    private Grid.Column<Notifications> firstNameColumn;
    private Grid.Column<Notifications> lastNameColumn;
    private final ParticipantAndMentorController participantAndMentorController;
    private final NotificationController notificationController;

    public MentorNotificationView(NotificationController notificationController,
                                  ParticipantAndMentorController participantAndMentorController) {
        this.participantAndMentorController = participantAndMentorController;
        this.notificationController = notificationController;

        VerticalLayout vl = new VerticalLayout();
        setSizeFull();
        createGrid();
        vl.add(new H3("Уведомления"));
        vl.setPadding(true);
        vl.setMargin(true);
        vl.setAlignItems(Alignment.CENTER);
        add(new H5(" "), vl, grid, new Toolbar(ToolbarType.MENTOR_PAGES));
        setPadding(true);
        setMargin(true);
    }

    private void createGrid() {
        grid = new Grid<>();
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        grid.setHeight("100%");
        dataProvider = new ListDataProvider<>(notificationController.getAllNowMentorNotifications());
        grid.setDataProvider(dataProvider);
        grid.setAllRowsVisible(true);
        firstNameColumn = grid.addColumn(notif -> participantAndMentorController.getParticipantById(notif.getParticipantId()).getFirstName(), "FirstName")
                .setHeader("Имя участника")
                .setWidth("18%")
                .setFlexGrow(0);
        lastNameColumn = grid.addColumn(notif -> participantAndMentorController.getParticipantById(notif.getParticipantId()).getLastName(), "LastName").setHeader("Фамилия участника")
                .setWidth("18%").setFlexGrow(0);
        grid.addColumn(new LocalDateRenderer<>(Notifications::getDateNoTime, DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .setHeader("Дата")
                .setWidth("18%")
                .setFlexGrow(0);
        grid.addColumn(notificationController::getTypeNotification, "RequestType")
                .setHeader("Тип уведомления")
                .setWidth("25%")
                .setFlexGrow(0);
        grid.addComponentColumn(this::buildViewButton).setWidth("15%").setFlexGrow(0);

        addFiltersToGrid();
    }

    public Button buildViewButton(Notifications notifications) {
        Button button = new Button("Смотреть");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickListener(click -> {
            VaadinSession.getCurrent().setAttribute("NotificationID", notifications.getNotificationId());
            button.getUI().ifPresent(ui -> ui.navigate(MentorNotificationDetailsView.class));
        });
        return button;
    }

    private void addFiltersToGrid() {
        HeaderRow filterRow = grid.appendHeaderRow();

        TextField firstNameFilter = new TextField();
        firstNameFilter.setPlaceholder("Фильтр");
        firstNameFilter.setClearButtonVisible(true);
        firstNameFilter.setWidth("100%");
        firstNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        firstNameFilter.addValueChangeListener(event -> dataProvider
                .addFilter(notification -> StringUtils.containsIgnoreCase(participantAndMentorController.getParticipantById(notification.getParticipantId()).getFirstName(), firstNameFilter.getValue())));
        filterRow.getCell(firstNameColumn).setComponent(firstNameFilter);

        TextField lastNameFilter = new TextField();
        lastNameFilter.setPlaceholder("Фильтр");
        lastNameFilter.setClearButtonVisible(true);
        lastNameFilter.setWidth("100%");
        lastNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        lastNameFilter.addValueChangeListener(event -> dataProvider
                .addFilter(notification -> StringUtils.containsIgnoreCase(participantAndMentorController.getParticipantById(notification.getParticipantId()).getLastName(), lastNameFilter.getValue())));
        filterRow.getCell(lastNameColumn).setComponent(lastNameFilter);
    }
}
