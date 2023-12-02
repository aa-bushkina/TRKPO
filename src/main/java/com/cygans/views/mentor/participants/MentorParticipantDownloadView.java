package com.cygans.views.mentor.participants;

import com.cygans.database.eating_log_book.EatingLogBookService;
import com.cygans.database.emotional_log_book.EmotionalLogBookService;
import com.cygans.database.log_book.Log;
import com.cygans.database.log_book.LogService;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.sport_log_book.SportLogBookService;
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
import com.vaadin.flow.server.VaadinSession;
import org.vaadin.olli.FileDownloadWrapper;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;

@PageTitle("Mentor Download Data and Graph")
@Route(value = "mentor/download")

public class MentorParticipantDownloadView extends VerticalLayout {
    private final DatePicker printStartDate = new DatePicker("Дата начала:"); //calendar to choose start date
    private final DatePicker printEndDate = new DatePicker("Дата конца:"); //calendar to choose end date
    private LocalDate startDate = LocalDate.now().minusDays(4);
    private LocalDate endDate = LocalDate.now();
    private String exportData;
    private final Long patientId;
    private final LogService logData;
    private final ParticipantService participantService;


    public MentorParticipantDownloadView(EmotionalLogBookService emotionalLogBookService,
                                         SportLogBookService sportLogBookService,
                                         EatingLogBookService eatingLogBookService,
                                         ParticipantService participantService,
                                         LogService logData,
                                         ParticipantService participantService1,
                                         LoginInfoService loginInfoService) {
        this.logData = logData;
        this.participantService = participantService1;

        patientId = participantService.getParticipantByLoginInfoId((Long) VaadinSession.getCurrent().getAttribute("PatientID")).getId();

        add(new Toolbar(ToolbarType.MENTOR_PAGES));
        Locale locale = new Locale("ru", "RU");
        printStartDate.setLocale(locale);
        printStartDate.setValue(LocalDate.now(ZoneId.systemDefault()));
        printStartDate.setHelperText("Формат: ДД.ММ.ГГГГ");
        printEndDate.setLocale(locale);
        printEndDate.setValue(LocalDate.now(ZoneId.systemDefault()));
        printEndDate.setHelperText("Формат: ДД.ММ.ГГГГ");

        printStartDate.addValueChangeListener(e -> printEndDate.setMin(e.getValue()));
        printEndDate.addValueChangeListener(e -> endDate = printEndDate.getValue());

        Button exportData1 = new Button("Скачать");
        exportData1.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        FileDownloadWrapper buttonWrapper = new FileDownloadWrapper(
                new StreamResource("Datafile" + ".csv", () -> {
                    exportData = OutputData();
                    return new ByteArrayInputStream(exportData.getBytes(StandardCharsets.UTF_8));
                })
        );

        buttonWrapper.wrapComponent(exportData1);
        exportData1.addFocusListener(event -> {
            startDate = printStartDate.getValue();
            endDate = printEndDate.getValue();
        });

        HorizontalLayout StartEndDate = new HorizontalLayout(printStartDate, printEndDate);
        VerticalLayout downLoadpage_layout = new VerticalLayout(new H3("Скачать " + participantService.getParticipantById(patientId).getFirstName() + " " + participantService.getParticipantById(patientId).getLastName() + "'s Logbook Data"), StartEndDate, buttonWrapper);
        downLoadpage_layout.setAlignItems(Alignment.CENTER);
        add(downLoadpage_layout);
    }

    public String OutputData() {
        //TODO сделать нормльный вывод
        StringBuilder finaloutput =
                new StringBuilder("Start date" + "," + startDate.toString() + "," + "End date" + "," + endDate.toString() + "\n" +
                        "Participant name" + "," + participantService.searchParticipantName(patientId) + "\n" +
                        "Logbook Type" +
                        "," + "Date" +
                        "," + "Time" +
                        "\n");

        List<Log> PatientData;
        PatientData = logData.findLogBooksBetweenDate(startDate, endDate, patientId);

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
        String emotionalout = new String();//set up returned string
//        List<EmotionalLogBook> simpledata=SimplelogData.findLogByDateAndPatientuid(checkdate,patientUid);
//        for(EmotionalLogBook eachdata:simpledata) {
//            emotionalout += eachdata.toString()+"\n";
//        }
        return emotionalout;
    }

    public String SportOut(LocalDate checkdate) {
        String sportout = new String();
//        List<SportLogBook> comprehensivedata=ComprehensivelogData.findLogByDateAndPatientuid(checkdate,patientUid);
//        for(SportLogBook eachdata:comprehensivedata) {
//            sportout += eachdata.toString()+"\n";
//        }
        return sportout;
    }

    public String EatingOut(LocalDate checkdate) {
        String eatingOut = new String();
//        List<EatingLogBook> intensivedata=IntensivelogData.findLogByDateAndParticipantId(checkdate,patientUid);
//        for(EatingLogBook eachdata:intensivedata) {
//            eatingOut += eachdata.toString()+"\n";
//        }
        return eatingOut;
    }

}