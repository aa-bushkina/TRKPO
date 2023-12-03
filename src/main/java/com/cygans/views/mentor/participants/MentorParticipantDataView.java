package com.cygans.views.mentor.participants;

import com.cygans.database.controllers.LogController;
import com.cygans.database.log_book.Log;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.cygans.views.mentor.logbooks.MentorParticipantsLogbookView;
import com.cygans.views.participant.logbooks.ParticipantPersonData;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@PageTitle("View Patients Data History")
@Route(value = "mentor/view-patient-history")
public class MentorParticipantDataView extends VerticalLayout {
    private final Icon download = new Icon(VaadinIcon.DOWNLOAD);
    private final HorizontalLayout SearchPanel = new HorizontalLayout();
    private final DatePicker SelectbyHand = new DatePicker("Период с");
    private final Button downloadBut = new Button(download);
    private final Button ViewData = new Button("Показать");
    private final Grid<ParticipantPersonData> Historylist = new Grid<>(ParticipantPersonData.class, false);
    private final ArrayList<ParticipantPersonData> HistoryDataShown = new ArrayList<>();
    private final LocalDate today = LocalDate.now();
    private LocalDate checkDate;
    private final LogController logController;


    public MentorParticipantDataView(LogController logController) {
        this.logController = logController;

        configSearch();
        setupShownData();
        configureHV();
        identifyclick();
        HorizontalLayout hl = new HorizontalLayout();
        download.setSize("50px");
        downloadBut.setHeight("60px");
        downloadBut.setWidth("60px");
        downloadBut.addClickListener(e ->
                downloadBut.getUI().ifPresent(ui ->
                        ui.navigate(MentorParticipantDownloadView.class)
                )
        );
        hl.add(new H3("История записей"), downloadBut);
        hl.setAlignItems(Alignment.BASELINE);
        H3 space = new H3(" ");
        add(space,
                hl,
                SearchPanel,
                Historylist);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(new Toolbar(ToolbarType.MENTOR_PAGES));
    }

    private void configSearch() {
        SelectbyHand.setMax(LocalDate.now());
        SelectbyHand.setLocale(new Locale("ru", "RU"));
        SearchPanel.add(SelectbyHand, ViewData);
        SearchPanel.setAlignItems(Alignment.BASELINE);
        SelectbyHand.addValueChangeListener(e -> checkDate = e.getValue());
        ViewData.addClickListener(view -> {
            List<Log> logBook = logController.getAllNowParticipantLogsBetweenDate(checkDate, today, false);
            if (!logBook.isEmpty()) {
                HistoryDataShown.addAll(logController.convertListLogToParticipantPersonData(logBook));
            } else
                Notification.show("Не ", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
        });

    }

    private void setupShownData() {
        List<Log> logbook = logController.getAllNowParticipantLogs(false);
        HistoryDataShown.addAll(logController.convertListLogToParticipantPersonData(logbook));
    }

    private void configureHV() {
        Historylist.setHeightFull();
        Historylist.addColumn(new LocalDateRenderer<>(ParticipantPersonData::getDate, DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .setHeader("Дата");
        Historylist.addColumn(ParticipantPersonData::getLogBookType).setHeader("Тип записи");
        Historylist.setItems(HistoryDataShown);
        Historylist.getColumns().forEach(col -> col.setAutoWidth(true));
        Historylist.setAllRowsVisible(true);
    }

    private void identifyclick() {
        Historylist.addSelectionListener(selection -> {
            Optional<ParticipantPersonData> optionalPersonData = selection.getFirstSelectedItem();
            VaadinSession.getCurrent().setAttribute("CheckDate", optionalPersonData.get().getDate());
            VaadinSession.getCurrent().setAttribute("LogbookType", optionalPersonData.get().getLogBookType());
            VaadinSession.getCurrent().setAttribute("LogbookId", optionalPersonData.get().getLogBookId());
            Historylist.getUI().ifPresent(ui -> ui.navigate(MentorParticipantsLogbookView.class));
        });
    }
}
