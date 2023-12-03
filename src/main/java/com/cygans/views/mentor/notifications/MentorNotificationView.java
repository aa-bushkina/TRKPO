package com.cygans.views.mentor.notifications;

import com.cygans.database.mentor.MentorService;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.participant.ParticipantService;
import com.cygans.security.db.logInfo.LoginInfoService;
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
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;


@PageTitle("Notifications")
@Route(value = "mentor/notifications")
public class MentorNotificationView extends VerticalLayout {
    private Grid<Notifications> grid;
    private ListDataProvider<Notifications> dataProvider;
    private Grid.Column<Notifications> firstNameColumn;
    private Grid.Column<Notifications> lastNameColumn;
    private final NotificationsService NotificationsService;
    private final NotificationTypeService notificationTypeService;
    private final ParticipantService participantService;
    private final Long mentorId;

    public MentorNotificationView(NotificationsService NotificationsService,
                                  NotificationTypeService notificationTypeService,
                                  LoginInfoService loginInfoService,
                                  ParticipantService participantService,
                                  MentorService mentorService) {
        this.NotificationsService = NotificationsService;
        this.notificationTypeService = notificationTypeService;
        this.participantService = participantService;
        mentorId = mentorService
                .getMentorByLoginInfoId(loginInfoService
                        .findByLogin(SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName())
                        .getId())
                .getId();

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
        dataProvider = new ListDataProvider<>(NotificationsService.getMentorNotificationlist(mentorId));
        grid.setDataProvider(dataProvider);
        grid.setAllRowsVisible(true);

        firstNameColumn = grid.addColumn(notif -> participantService.getFirstname(notif.getParticipantId()), "FirstName")
                .setHeader("Имя участника")
                .setWidth("18%")
                .setFlexGrow(0);
        lastNameColumn = grid.addColumn(notif -> participantService.getLastname(notif.getParticipantId()), "LastName").setHeader("Фамилия участника")
                .setWidth("18%").setFlexGrow(0);
        grid.addColumn(Notifications::getDateNoTime, "Date").setHeader("Дата")
                .setWidth("18%")
                .setFlexGrow(0);
        grid.addColumn(notification -> notificationTypeService.getNotificationTypeType(notification.getNotificationTypeId()), "RequestType")
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
                .addFilter(notification -> StringUtils.containsIgnoreCase(participantService.getFirstname(notification.getParticipantId()), firstNameFilter.getValue())));
        filterRow.getCell(firstNameColumn).setComponent(firstNameFilter);

        TextField lastNameFilter = new TextField();
        lastNameFilter.setPlaceholder("Фильтр");
        lastNameFilter.setClearButtonVisible(true);
        lastNameFilter.setWidth("100%");
        lastNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        lastNameFilter.addValueChangeListener(event -> dataProvider
                .addFilter(notification -> StringUtils.containsIgnoreCase(participantService.getLastname(notification.getParticipantId()), lastNameFilter.getValue())));
        filterRow.getCell(lastNameColumn).setComponent(lastNameFilter);
    }
}
