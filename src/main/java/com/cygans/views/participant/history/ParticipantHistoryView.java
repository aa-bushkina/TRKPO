package com.cygans.views.participant.history;


import com.cygans.database.log_book.Log;
import com.cygans.database.log_book.LogService;
import com.cygans.database.log_book.logs_type.LogsTypeService;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.question.Question;
import com.cygans.database.question.question_status.StatusOfQuestion;
import com.cygans.security.db.logInfo.LoginInfoService;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.cygans.views.participant.logbooks.ParticipantLogbookView;
import com.cygans.views.participant.logbooks.ParticipantPersonData;
import com.cygans.views.participant.questions.ParticipantQuestionDetailsView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@PageTitle("View Data History")
@Route(value = "participant/data-history")
public class ParticipantHistoryView extends VerticalLayout {
    private final Long participantId;
    private final Icon download = new Icon(VaadinIcon.DOWNLOAD);
    private final HorizontalLayout searchPanel = new HorizontalLayout();
    private final DatePicker period = new DatePicker("Период с");
    private final Button downloadBut = new Button(download);
    private final Button viewDataBtn = new Button("Показать");

    private final Grid<ParticipantPersonData> historylist = new Grid<>(ParticipantPersonData.class, false);
    private final ArrayList<ParticipantPersonData> HistoryDataShown = new ArrayList<>();
    private final LocalDate today = LocalDate.now();
    private LocalDate checkDate;
    private final LogService logService;
    private final LogsTypeService logsTypeService;

    public ParticipantHistoryView(LogService logService,
                                  LoginInfoService loginInfoService,
                                  LogsTypeService logsTypeService,
                                  ParticipantService participantService) {
        this.logService = logService;
        this.logsTypeService = logsTypeService;

        participantId = participantService
                .getParticipantByLoginInfoId(
                        loginInfoService.findByLogin(
                                SecurityContextHolder.getContext().getAuthentication().getName()
                        ).getId()
                ).getId();

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
                historylist);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(new Toolbar(ToolbarType.PARTICIPANT_PAGES));
    }


    private void configSearch() {
    }

    private void setupShownData() {
        List<Log> logbook = logService.findLogBooksByParticipantId(participantId);
        for (int day = 0; day < 30; day++) {
            for (Log log : logbook) {
                if (log.getDate().isEqual(today.minusDays(day))) {
                    ParticipantPersonData addData = new ParticipantPersonData();
                    addData.setDate(today.minusDays(day));
                    addData.setLogBookType(logsTypeService.getLogTypeById(log.getLogTypeId()));
                    addData.setLogBookId(log.getId());
//                    addData.setCompleteLogBook(true);
                    HistoryDataShown.add(addData);
                }
            }
        }
    }

    private void configureHV() {
        historylist.setHeightFull();
        historylist.addColumn(new LocalDateRenderer<>(ParticipantPersonData::getDate, DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .setHeader("Дата")
                .setWidth("25%");
        historylist.addColumn(ParticipantPersonData::getLogBookType).setHeader("Тип записи")
                .setWidth("55%");
        historylist.addComponentColumn(this::buildLogViewButton)
                .setWidth("20%");
        historylist.setItems(HistoryDataShown);
        historylist.setAllRowsVisible(true);
    }

    private void identifyClick() {
        historylist.addSelectionListener(selection -> {
            Optional<ParticipantPersonData> optionalPersonData = selection.getFirstSelectedItem();
            VaadinSession.getCurrent().setAttribute("CheckDate", optionalPersonData.get().getDate());
            VaadinSession.getCurrent().setAttribute("LogbookType", optionalPersonData.get().getLogBookType());
            VaadinSession.getCurrent().setAttribute("LogbookId", optionalPersonData.get().getLogBookId());
            historylist.getUI().ifPresent(ui -> ui.navigate(ParticipantLogbookView.class));
        });
    }

    public Button buildLogViewButton(ParticipantPersonData participantPersonData) {
        Button button = new Button("Смотреть");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickListener(click -> {
            VaadinSession.getCurrent().setAttribute("CheckDate", participantPersonData.getDate());
            VaadinSession.getCurrent().setAttribute("LogbookType", participantPersonData.getLogBookType());
            VaadinSession.getCurrent().setAttribute("LogbookId", participantPersonData.getLogBookId());
            historylist.getUI().ifPresent(ui -> ui.navigate(ParticipantLogbookView.class));
        });
        return button;
    }
}
