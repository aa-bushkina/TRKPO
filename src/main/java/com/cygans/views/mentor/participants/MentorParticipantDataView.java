package com.cygans.views.mentor.participants;

import com.cygans.database.controllers.LogController;
import com.cygans.database.log_book.Log;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.cygans.views.mentor.logbooks.MentorParticipantsLogbookView;
import com.cygans.views.participant.history.ParticipantDownloadView;
import com.cygans.views.participant.logbooks.ParticipantPersonData;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@PageTitle("Марафон")
@Route(value = "mentor/view-participant-history")
public class MentorParticipantDataView extends VerticalLayout {
    private final Icon download = new Icon(VaadinIcon.DOWNLOAD);
    private final Button downloadBut = new Button(download);
    private final LogController logController;
    private final Grid<ParticipantPersonData> historylist = new Grid<>(ParticipantPersonData.class, false);
    private final ArrayList<ParticipantPersonData> historyDataShown = new ArrayList<>();
    private final Select<String> logsTypeFilter = new Select<>();
    private final DatePicker dateFilter = new DatePicker("Дата");


    public MentorParticipantDataView(LogController logController) {
        this.logController = logController;

        setupShownData();
        configureHV();
        identifyClick();


        HorizontalLayout hl = new HorizontalLayout();
        download.setSize("50px");
        downloadBut.setHeight("60px");
        downloadBut.setWidth("60px");
        downloadBut.addClickListener(e ->
                downloadBut.getUI().ifPresent(ui ->
                        ui.navigate(MentorParticipantDownloadView.class)
                )
        );
        String s = logController.getParticipantFullNameByAttribute();
        if (s.length() > 40)
        {
            s = s.substring(0, 40) + "...";
        }
        H3 title = new H3("История записей участника: " + s);

        hl.add(title, downloadBut);
        hl.setAlignItems(Alignment.BASELINE);

        HorizontalLayout searchPanel = new HorizontalLayout();
        add(new H3("   "),
                hl,
                searchPanel,
                historylist);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(new Toolbar(ToolbarType.MENTOR_PAGES));
    }


    private void setupShownData() {
        List<Log> logbook = logController.getAllNowParticipantLogs(false);
        historyDataShown.addAll(logController.convertListLogToParticipantPersonData(logbook));
    }

    private void configureHV() {
        historylist.setHeightFull();

        logsTypeFilter.setLabel("Тип записи");
        logsTypeFilter.setWidth("340px");
        logsTypeFilter.setItems(logController.getAllLogsTypes());
        logsTypeFilter.addValueChangeListener(event -> {
            ListDataProvider<ParticipantPersonData> dataProvider = (ListDataProvider<ParticipantPersonData>) historylist.getDataProvider();
            dataProvider.addFilter(personData -> {
                String selectedLogsType = logsTypeFilter.getValue();
                return selectedLogsType == null || selectedLogsType.equals("Все") || personData.getLogBookType().equals(selectedLogsType);
            });
        });

        dateFilter.setLabel("Дата");
        dateFilter.setWidth("200px");
        dateFilter.setClearButtonVisible(true);
        dateFilter.addValueChangeListener(event -> {
            ListDataProvider<ParticipantPersonData> dataProvider = (ListDataProvider<ParticipantPersonData>) historylist.getDataProvider();
            dataProvider.addFilter(personData -> {
                LocalDate selectedDate = dateFilter.getValue();
                return selectedDate == null || personData.getDate().isEqual(selectedDate);
            });
        });

        historylist.addColumn(new LocalDateRenderer<>(ParticipantPersonData::getDate, DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .setHeader(dateFilter)
                .setWidth("25%");
        historylist.addColumn(ParticipantPersonData::getLogBookType)
                .setHeader(logsTypeFilter)
                .setWidth("55%");
        historylist.addComponentColumn(this::buildLogViewButton)
                .setWidth("20%");
        historylist.setItems(historyDataShown);
        historylist.setAllRowsVisible(true);
    }

    private void identifyClick() {
        historylist.addSelectionListener(selection -> {
            Optional<ParticipantPersonData> optionalPersonData = selection.getFirstSelectedItem();
            VaadinSession.getCurrent().setAttribute("CheckDate", optionalPersonData.get().getDate());
            VaadinSession.getCurrent().setAttribute("LogbookType", optionalPersonData.get().getLogBookType());
            VaadinSession.getCurrent().setAttribute("LogbookId", optionalPersonData.get().getLogBookId());
            historylist.getUI().ifPresent(ui -> ui.navigate(MentorParticipantsLogbookView.class));
        });
    }

    public Button buildLogViewButton(ParticipantPersonData participantPersonData) {
        Button button = new Button("Смотреть");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickListener(click -> {
            VaadinSession.getCurrent().setAttribute("CheckDate", participantPersonData.getDate());
            VaadinSession.getCurrent().setAttribute("LogbookType", participantPersonData.getLogBookType());
            VaadinSession.getCurrent().setAttribute("LogbookId", participantPersonData.getLogBookId());
            historylist.getUI().ifPresent(ui -> ui.navigate(MentorParticipantsLogbookView.class));
        });
        return button;
    }
}
