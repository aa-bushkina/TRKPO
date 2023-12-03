package com.cygans.views.participant.history;


import com.cygans.database.controllers.LogController;
import com.cygans.database.log_book.Log;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.cygans.views.participant.logbooks.ParticipantLogbookView;
import com.cygans.views.participant.logbooks.ParticipantPersonData;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@PageTitle("View Data History")
@Route(value = "participant/data-history")
public class ParticipantHistoryView extends VerticalLayout {
    private final Icon download = new Icon(VaadinIcon.DOWNLOAD);
    private final HorizontalLayout searchPanel = new HorizontalLayout();
    private final DatePicker period = new DatePicker("Период с");
    private final Button downloadBut = new Button(download);
    private final Button viewDataBtn = new Button("Показать");

    private final Grid<ParticipantPersonData> Historylist = new Grid<>(ParticipantPersonData.class, false);
    private final ArrayList<ParticipantPersonData> HistoryDataShown = new ArrayList<>();
    private LocalDate checkDate;
    private final LogController logController;

    public ParticipantHistoryView(LogController logController) {
        this.logController = logController;

        configSearch();
        setupShownData();
        configureHV();
        identifyClick();


        HorizontalLayout hl = new HorizontalLayout();
        download.setSize("50px");
        downloadBut.setHeight("60px");
        downloadBut.setWidth("60px");
        downloadBut.addClickListener(e ->
                downloadBut.getUI().ifPresent(ui ->
                        ui.navigate(ParticipantDownloadView.class)
                )
        );
        hl.add(new H3("История записей"), downloadBut);
        hl.setAlignItems(Alignment.BASELINE);

        add(new H3("   "),
                hl,
                searchPanel,
                Historylist);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(new Toolbar(ToolbarType.PARTICIPANT_PAGES));
    }


    private void configSearch() {
    }

    private void setupShownData() {
        List<Log> logbook = logController.getAllNowParticipantLogs(true);
        HistoryDataShown.addAll(logController.convertListLogToParticipantPersonData(logbook));
    }

    private void configureHV() {
        Historylist.setHeightFull();
        Historylist.addColumn(ParticipantPersonData::getDate).setHeader("Дата (последние 30 дней)");
        Historylist.addColumn(ParticipantPersonData::getLogBookType).setHeader("Тип записи");
        Historylist.setItems(HistoryDataShown);
        Historylist.getColumns().forEach(col -> col.setAutoWidth(true));
        Historylist.setAllRowsVisible(true);

    }

    private void identifyClick() {
        Historylist.addSelectionListener(selection -> {
            Optional<ParticipantPersonData> optionalPersonData = selection.getFirstSelectedItem();
            VaadinSession.getCurrent().setAttribute("CheckDate", optionalPersonData.get().getDate());
            VaadinSession.getCurrent().setAttribute("LogbookType", optionalPersonData.get().getLogBookType());
            VaadinSession.getCurrent().setAttribute("LogbookId", optionalPersonData.get().getLogBookId());
            Historylist.getUI().ifPresent(ui -> ui.navigate(ParticipantLogbookView.class));
        });
    }
}
