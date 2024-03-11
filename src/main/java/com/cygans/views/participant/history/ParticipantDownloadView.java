package com.cygans.views.participant.history;

import com.cygans.database.controllers.LogController;
import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.eating_log_book.EatingLogBook;
import com.cygans.database.emotional_log_book.EmotionalLogBook;
import com.cygans.database.log_book.Log;
import com.cygans.database.log_book.logs_type.LogBookType;
import com.cygans.database.participant.Participant;
import com.cygans.database.sport_log_book.SportLogBook;
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
import org.vaadin.olli.FileDownloadWrapper;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;


@PageTitle("Марафон")
@Route(value = "participant/download")

public class ParticipantDownloadView extends VerticalLayout {
    private final DatePicker PrintStartDate = new DatePicker("Дата начала:");
    private final DatePicker PrintEndDate = new DatePicker("Дата конца:");
    private final Participant participant;
    private final LogController logController;
    private final ParticipantAndMentorController participantAndMentorController;
    private LocalDate startDate = LocalDate.now().minusDays(4);
    private LocalDate endDate = LocalDate.now();
    private String exportData;

    public ParticipantDownloadView(LogController logController,
                                   ParticipantAndMentorController participantAndMentorController) {
        this.logController = logController;
        this.participantAndMentorController = participantAndMentorController;

        participant = participantAndMentorController.getNowParticipantByAuthentication();

        add(new Toolbar(ToolbarType.PARTICIPANT_PAGES));
        Locale locale = new Locale("ru", "RU");
        PrintStartDate.setLocale(locale);
        PrintStartDate.setValue(LocalDate.now(ZoneId.systemDefault()));
        PrintEndDate.setLocale(locale);
        PrintEndDate.setValue(LocalDate.now(ZoneId.systemDefault()));

        PrintStartDate.addValueChangeListener(e -> PrintEndDate.setMin(e.getValue()));
        PrintEndDate.addValueChangeListener(e -> endDate = PrintEndDate.getValue());

        Button exportData1 = new Button("Экспортировать");
        exportData1.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_PRIMARY);

        FileDownloadWrapper buttonWrapper = new FileDownloadWrapper(
                new StreamResource("History" + ".csv", () -> {
                    exportData = getData();
                    return new ByteArrayInputStream(exportData.getBytes(StandardCharsets.UTF_8));
                })
        );

        buttonWrapper.wrapComponent(exportData1);
        exportData1.addFocusListener(event -> {
            startDate = PrintStartDate.getValue();
            endDate = PrintEndDate.getValue();
        });

        HorizontalLayout StartEndDate = new HorizontalLayout(PrintStartDate, PrintEndDate);
        VerticalLayout downLoadpage_layout = new VerticalLayout(new H3("Скачать историю записей"), StartEndDate, buttonWrapper);
        downLoadpage_layout.setAlignItems(Alignment.CENTER);
        add(downLoadpage_layout);
    }

    public String getData() {
        StringBuilder str = new StringBuilder(
                "Name participant: " + participant.getLastName() + " " + participant.getFirstName()
                        + "\nStart date: " + startDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                        + "\nEnd date: " + endDate.toString() + "\n\n"
        );


        List<Log> logs = logController.getAllNowParticipantLogsBetweenDate(startDate, endDate, false, participant);

        for (Log log : logs) {
            if (log.getLogTypeId() == logController.getLogTypeIdByName(LogBookType.EMOTIONAL)) {
                String simplestring = EmotionalOut(log);
                str.append(simplestring).append("\n");
            }
            if (log.getLogTypeId() == logController.getLogTypeIdByName(LogBookType.SPORT)) {
                String comprehensivestring = SportOut(log);
                str.append(comprehensivestring).append("\n");
            }

            if (log.getLogTypeId() == logController.getLogTypeIdByName(LogBookType.EATING)) {
                String intensivestring = EatingOut(log);
                str.append(intensivestring).append("\n");
            }
        }

        return str.toString();
    }

    public String EmotionalOut(Log log) {
        EmotionalLogBook emotionalLogBook = logController.getEmotionalLogByLogbookId(log.getId());
        return "Emotional log\n"
                + "Date: " + log.getDate()
                + "\nDescription: " + emotionalLogBook.getDescription() + "\n";
    }

    public String EatingOut(Log log) {
        EatingLogBook eatingLogBook = logController.getEatingLogByLogbookId(log.getId());
        String meal = logController.getMealEatingLog(eatingLogBook.getMealId());
        switch (meal) {
            case "Завтрак":
                meal = "Breakfast";
                break;
            case "Обед":
                meal = "Launch";
                break;
            case "Ужин":
                meal = "Dinner";
                break;
            case "Другое":
                meal = "Other";
                break;
        }
        return "Eating log:\n"
                + "Date: " + log.getDate()
                + "\nTime eat: " + eatingLogBook.getTimeEat()
                + "\nMeal: " + meal
                + "\nDescription: " + eatingLogBook.getDescription() + "\n";
    }

    public String SportOut(Log log) {
        SportLogBook sportLogBook = logController.getSportLogByLogbookId(log.getId());
        String intensity = logController.getIntensitySportLog(sportLogBook.getIntensityId()).getType();
        switch (intensity) {
            case "Низкая":
                intensity = "Low";
                break;
            case "Средняя":
                intensity = "Middle";
                break;
            case "Высокая":
                intensity = "High";
                break;
        }
        return "Sport log:\n"
                + "Date: " + log.getDate()
                + "\nActivity: " + sportLogBook.getActivity()
                + "\nIntensity: " + intensity
                + "\nDuration: " + sportLogBook.getDuration() + " minute"
                + "\nDescription: " + sportLogBook.getComments() + "\n";
    }
}
