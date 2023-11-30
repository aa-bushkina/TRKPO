package com.cygans.views.mentor;

import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorService;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import com.cygans.security.db.logInfo.LoginInfoService;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.cygans.views.mentor.participants.MentorAddParticipantView;
import com.cygans.views.mentor.participants.MentorParticipantDataView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Домашняя страница ментора
 */
@PageTitle("My Participants")
@Route(value = "mentor/my-participants")
public class MentorHomeView extends VerticalLayout {
    private final NotificationTypeService notificationTypeService;
    private Grid<Participant> grid;
    private ListDataProvider<Participant> dataProvider;
    private final Icon add = new Icon(VaadinIcon.PLUS_CIRCLE);
    private final Button addBtn = new Button(add);
    private final ParticipantMentorService participantMentorService;
    private final Mentor mentor;
    private final NotificationsService notificationsService;
    private final NotificationStatusService notificationStatusService;


    public MentorHomeView(LoginInfoService loginInfoService,
                          ParticipantMentorService participantMentorService,
                          MentorService mentorService,
                          NotificationTypeService notificationTypeService,
                          NotificationsService notificationsService,
                          NotificationStatusService notificationStatusService) {
        this.notificationStatusService = notificationStatusService;
        this.participantMentorService = participantMentorService;
        this.notificationTypeService = notificationTypeService;

        Long loginInfoId = loginInfoService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
        mentor = mentorService.getMentorByLoginInfoId(loginInfoId);
        this.notificationsService = notificationsService;

        add.setSize("50px");
        addBtn.setWidth("55px");
        addBtn.setHeight("55px");
        addBtn.addClickListener(e ->
                addBtn.getUI().ifPresent(ui ->
                        ui.navigate(MentorAddParticipantView.class))
        );

        HorizontalLayout hl = new HorizontalLayout();
        H3 title = new H3("Мои участники");
        hl.add(title, addBtn);
        hl.setHeight("20%");
        hl.setVerticalComponentAlignment(FlexComponent.Alignment.BASELINE, title, addBtn);

        createGrid();

        VerticalLayout vl = new VerticalLayout();
        vl.add(hl);
        vl.setPadding(true);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        Toolbar menu = new Toolbar(ToolbarType.MENTOR_HOME);
        add(vl, grid, menu);
        setPadding(true);
        setMargin(true);

        if (VaadinSession.getCurrent().getAttribute("Error") != null) {
            Notification notification = Notification.show("Неверный url: " + VaadinSession.getCurrent().getAttribute("Error"), 3000, Notification.Position.TOP_CENTER);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            VaadinSession.getCurrent().setAttribute("Error", null);
        }
    }

    private void createGrid() {
        grid = new Grid<>();
        grid.setAllRowsVisible(true);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        grid.setHeight("100%");

        // таблица берет данные из всех участников, найденных по ментору
        dataProvider = new ListDataProvider<>(participantMentorService.getParticipantsByMentor(mentor.getId()));
        grid.setDataProvider(dataProvider);
        grid.setAllRowsVisible(true);
        Grid.Column<Participant> firstNameColumn = grid.addColumn(Participant::getFirstName, "Firstname")
                .setHeader("Имя").setWidth("25%")
                .setFlexGrow(0);
        Grid.Column<Participant> lastNameColumn = grid.addColumn(Participant::getLastName, "Lastname")
                .setHeader("Фамилия").setWidth("25%")
                .setFlexGrow(0);
        Grid.Column<Participant> loginColumn = grid.addColumn(Participant::getLogin, "Login")
                .setHeader("Логин").setWidth("25%")
                .setFlexGrow(0);
        Grid.Column<Participant> deleteColumn = grid.addComponentColumn(participant ->
                new CustomButton("Удалить", participant.getId())).setHeader("Удалить из отслеживаемых").setWidth("25%").setFlexGrow(0);
        grid.addSelectionListener(
                selection -> {
                    VaadinSession.getCurrent()
                            .setAttribute("PatientID", selection.getFirstSelectedItem().get().getLoginInfoId());
                    grid.getUI().ifPresent(ui -> ui.navigate(MentorParticipantDataView.class));
                });

        HeaderRow filterRow = grid.appendHeaderRow();
        TextField firstNameFilter = new TextField();
        firstNameFilter.setPlaceholder("Фильтр");
        firstNameFilter.setClearButtonVisible(true);
        firstNameFilter.setWidth("100%");
        firstNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        firstNameFilter.addValueChangeListener(event -> dataProvider
                .addFilter(participant ->
                        StringUtils.containsIgnoreCase(participant.getFirstName(),
                                firstNameFilter.getValue())));
        filterRow.getCell(firstNameColumn).setComponent(firstNameFilter);

        TextField lastNameFilter = new TextField();
        lastNameFilter.setPlaceholder("Фильтр");
        lastNameFilter.setClearButtonVisible(true);
        lastNameFilter.setWidth("100%");
        lastNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        lastNameFilter.addValueChangeListener(event -> dataProvider
                .addFilter(participant ->
                        StringUtils.containsIgnoreCase(participant.getLastName(),
                                lastNameFilter.getValue())));
        filterRow.getCell(lastNameColumn).setComponent(lastNameFilter);

        TextField emailFilter = new TextField();
        emailFilter.setPlaceholder("Фильтр");
        emailFilter.setClearButtonVisible(true);
        emailFilter.setWidth("100%");
        emailFilter.setValueChangeMode(ValueChangeMode.EAGER);
        emailFilter.addValueChangeListener(event -> dataProvider
                .addFilter(participant ->
                        StringUtils.containsIgnoreCase(participant.getLogin(),
                                emailFilter.getValue())));
        filterRow.getCell(loginColumn).setComponent(emailFilter);
    }

    private class CustomButton extends Button {
        public CustomButton(String caption, Long participantId) {
            super(caption);
            addClickListener(e -> {
                participantMentorService.deletePatient(participantId);
                dataProvider.getItems().removeIf(participant -> participant.getId() == participantId);
                dataProvider.refreshAll();
                Notifications notification = new Notifications(
                        participantId,
                        mentor.getId(),
                        notificationTypeService.getNotificationTypeId(TypeOfNotification.DELETE_REQUEST),
                        notificationStatusService.getNotificationStatusId(StatusOfNotification.ANSWERED_NOT_SEEN)
                );
                notification.setShortMessage(mentor.getFirstName() + " " + mentor.getLastName() + " удалил тебя из отслеживания");
                notification.setAllMessage(
                        "Ментор " + mentor.getFirstName() + " " + mentor.getLastName() + " удалил тебя из отслеживания.\n\n" +
                                "Если тебе неизвестны причины такого решения - обратись в поддержку, чтобы они объяснили " +
                                "причину и помогли с подбором нового ментора.\n\n" +
                                "Дата: " + notification.getDate().toLocalDate() + "\n" +
                                "Время: " + notification.getDate().toLocalTime() + "\n"
                );
                notificationsService.saveNotification(notification);
            });
        }
    }
}
