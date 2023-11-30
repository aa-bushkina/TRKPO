package com.cygans.views.participant.history;

import com.cygans.database.eating_log_book.EatingLogBookService;
import com.cygans.database.emotional_log_book.EmotionalLogBookService;
import com.cygans.database.log_book.Log;
import com.cygans.database.log_book.LogService;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.sport_db.SportLogBookService;
import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoService;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.vaadin.olli.FileDownloadWrapper;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;

@PageTitle("Download Data and Graph")
@Route(value = "participant/download")

public class ParticipantDownloadView extends VerticalLayout {
    private final DatePicker PrintStartDate = new DatePicker("Дата начала:");
    private final DatePicker PrintEndDate = new DatePicker("Дата конца:");
    private LocalDate StartDate = LocalDate.now().minusDays(4);
    private LocalDate EndDate = LocalDate.now();
    private String exportData;
    private final Long patientId;
    private final EmotionalLogBookService emotionalLogBookService;
    private final SportLogBookService sportLogBookService;
    private final EatingLogBookService eatingLogBookService;
    private final LogService logService;
    private final ParticipantService participantService;

    public ParticipantDownloadView(EmotionalLogBookService emotionalLogBookService,
                                   SportLogBookService sportLogBookService,
                                   EatingLogBookService eatingLogBookService,
                                   ParticipantService participantService,
                                   LogService logService,
                                   ParticipantService patientData,
                                   LoginInfoService loginInfoService) {
        this.emotionalLogBookService = emotionalLogBookService;
        this.sportLogBookService = sportLogBookService;
        this.eatingLogBookService = eatingLogBookService;
        this.logService = logService;
        this.participantService = participantService;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        LoginInfo loginInfo = loginInfoService.findByLogin(authentication.getName()); //return loginInfo
        patientId = loginInfo.getId();

        add(new Toolbar(ToolbarType.PARTICIPANT_PAGES));
        Locale locale = new Locale("ru", "RU");
        PrintStartDate.setLocale(locale);
        PrintStartDate.setValue(LocalDate.now(ZoneId.systemDefault()));
        PrintEndDate.setLocale(locale);
        PrintEndDate.setValue(LocalDate.now(ZoneId.systemDefault()));

        PrintStartDate.addValueChangeListener(e -> PrintEndDate.setMin(e.getValue()));
        PrintEndDate.addValueChangeListener(e -> EndDate = PrintEndDate.getValue());

        Button exportData1 = new Button("Экспортировать");
        exportData1.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_PRIMARY);

        FileDownloadWrapper buttonWrapper = new FileDownloadWrapper(
                new StreamResource("Datafile" + ".csv", () -> {
                    exportData = OutputData();
                    return new ByteArrayInputStream(exportData.getBytes(StandardCharsets.UTF_8));
                })
        );

        buttonWrapper.wrapComponent(exportData1);
        exportData1.addFocusListener(event -> {
            StartDate = PrintStartDate.getValue();
            EndDate = PrintEndDate.getValue();
        });

        HorizontalLayout StartEndDate = new HorizontalLayout(PrintStartDate, PrintEndDate);
        VerticalLayout downLoadpage_layout = new VerticalLayout(new H3("Скачать историю записей"), StartEndDate, buttonWrapper);
        downLoadpage_layout.setAlignItems(Alignment.CENTER);
        add(downLoadpage_layout);
    }

    public String OutputData() {
        //TODO сделать нормльный вывод
        StringBuilder finaloutput =
                new StringBuilder("Дата начала" + "," + StartDate.toString() + "," + "Дата конца" + "," + EndDate.toString() + "\n" +
                        "Participant name" + "," + participantService.searchParticipantName(patientId) + "\n" +
                        "Logbook Type" +
                        "," + "Date" +
                        "," + "Time" +
                        "\n");

        List<Log> PatientData;
        PatientData = logService.findLogBooksBetweenDate(StartDate, EndDate, patientId);

        for (Log eachdata : PatientData) {
            if (eachdata.getLogTypeId() == 1) {
                String simplestring = EmotionalOut(eachdata.getDate());
                finaloutput.append(simplestring).append("\n");

            }
            if (eachdata.getLogTypeId() == 2) {
                String comprehensivestring = SportOut(eachdata.getDate());
                finaloutput.append(comprehensivestring).append("\n");
            }
            if (eachdata.getLogTypeId() == 3) {
                String intensivestring = EatingOut(eachdata.getDate());
                finaloutput.append(intensivestring).append("\n");
            }
        }
        return finaloutput.toString();
    }

    public String EmotionalOut(LocalDate checkdate) {
        StringBuilder emotionalOut = new StringBuilder();
//        List<EmotionalLogBook> simpledata = SimplelogData.findLogByDateAndPatientuid(checkdate, patientId);
//        for (EmotionalLogBook eachdata : simpledata) {
//            emotionalOut.append(eachdata.toString()).append("\n");
//        }
        return emotionalOut.toString();
    }

    public String SportOut(LocalDate checkdate) {
        StringBuilder sportOut = new StringBuilder();
//        List<SportLogBook> comprehensivedata = ComprehensivelogData.findLogByDateAndPatientuid(checkdate, patientId);
//        for (SportLogBook eachdata : comprehensivedata) {
//            sportOut.append(eachdata.toString()).append("\n");
//        }
        return sportOut.toString();
    }

    public String EatingOut(LocalDate checkdate) {
        StringBuilder eatingOut = new StringBuilder();
//        List<EatingLogBook> intensivedata = IntensivelogData.findLogByDateAndParticipantId(checkdate, patientId);
//        for (EatingLogBook eachdata : intensivedata) {
//            eatingOut.append(eachdata.toString()).append("\n");
//        }
        return eatingOut.toString();
    }
}