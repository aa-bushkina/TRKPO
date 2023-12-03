package com.cygans.views.mentor;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.mentor.Mentor;
import com.cygans.database.participant.Participant;
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

/**
 * Домашняя страница ментора
 */
@PageTitle("Марафон")
@Route(value = "mentor/my-participants")
public class MentorHomeView extends VerticalLayout {
    private Grid<Participant> grid;
    private ListDataProvider<Participant> dataProvider;
    private final Icon add = new Icon(VaadinIcon.PLUS_CIRCLE);
    private final Button addBtn = new Button(add);
    private final Mentor mentor;
    private final NotificationController notificationController;
    private final ParticipantAndMentorController participantAndMentorController;

    public MentorHomeView(NotificationController notificationController,
                          ParticipantAndMentorController participantAndMentorController) {
        this.notificationController = notificationController;
        this.participantAndMentorController = participantAndMentorController;

        mentor = participantAndMentorController.getIdNowMentorByAuthentication();

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
        dataProvider = new ListDataProvider<>(participantAndMentorController.getParticipantsByMentor(mentor));
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
                new CustomButton("Удалить", participant)).setHeader("Удалить из отслеживаемых").setWidth("25%").setFlexGrow(0);
        grid.addSelectionListener(
                selection -> {
                    VaadinSession.getCurrent()
                            .setAttribute("ParticipantID", selection.getFirstSelectedItem().get().getLoginInfoId());
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
        public CustomButton(String caption, Participant participant) {
            super(caption);
            addClickListener(e -> {
                participantAndMentorController.deleteParticipantFromMentor(participant);
                dataProvider.getItems().removeIf(participan -> participan.getId() == participant.getId());
                dataProvider.refreshAll();
                notificationController.addDeleteParticipantNotificationNowMentor(participant);
            });
        }
    }
}
