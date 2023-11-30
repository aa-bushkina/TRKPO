package com.cygans.views.participant.questions;

import com.cygans.database.notifications.NotificationService;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import com.cygans.database.question.Question;
import com.cygans.database.question.QuestionService;
import com.cygans.database.question.question_status.QuestionStatusService;
import com.cygans.database.question.question_status.StatusOfQuestion;
import com.cygans.security.db.logInfo.LoginInfoService;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Странице вопросов участника
 */
@PageTitle("Questions View")
@Route(value = "participant/questions")
public class ParticipantQuestionsView extends Div {
    private final TextArea textArea = new TextArea("Мой вопрос");
    private final Button submit = new Button("Задать");
    private final HorizontalLayout searchPanel = new HorizontalLayout();
    private final DatePicker period = new DatePicker("Период с");
    private final Button viewDataBtn = new Button("Показать");
    private final Grid<Question> historyList = new Grid<>(Question.class, false);
    private final QuestionService questionService;
    private final QuestionStatusService questionStatusService;
    private final Long participantId;
    private final NotificationStatusService notificationStatusService;


    public ParticipantQuestionsView(LoginInfoService loginInfoService,
                                    QuestionService questionService,
                                    NotificationService notificationService,
                                    ParticipantService participantService,
                                    ParticipantMentorService participantMentorService,
                                    NotificationTypeService notificationTypeService,
                                    QuestionStatusService questionStatusService,
                                    NotificationStatusService notificationStatusService) {
        this.notificationStatusService = notificationStatusService;
        this.questionService = questionService;
        this.questionStatusService = questionStatusService;
        participantId = participantService.getParticipantByLoginInfoId(
                loginInfoService.findByLogin(SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName())
                        .getId()).getId();
        submit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submit.setWidth("30%");
        submit.setHeight("50px");

        configSearch();
        configureHV();
        identifyClick();

        submit.addClickListener(e -> {
                    //Если нет ментора - не даем ничего отправить
                    if (!participantMentorService.checkParticipant(participantId)) {
                        Notification.show("Чтобы задавать вопросы ментору, нужно принять его заявку", 3000, Notification.Position.TOP_CENTER)
                                .addThemeVariants(NotificationVariant.LUMO_ERROR);
                    } else if (textArea.isEmpty()) {
                        Notification notification = Notification.show("Необходимо ввести текст", 3000, Notification.Position.TOP_CENTER);
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    } else if (textArea.isInvalid()) {
                        Notification notification = Notification.show("Максимальное количетво символов - 300", 3000, Notification.Position.TOP_CENTER);
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    } else {
                        Question question = new Question(
                                participantId,
                                LocalDate.now(),
                                textArea.getValue(),
                                questionStatusService.geQuestionStatusId(StatusOfQuestion.NO_ANSWER)
                        );
                        questionService.saveQuestion(question);


                        Notifications notification = new Notifications(
                                participantId,
                                participantMentorService.getMentorParticipantByParticipantId(participantId).getMentorId(),
                                notificationTypeService.getNotificationTypeId(TypeOfNotification.QUESTION),
                                notificationStatusService.getNotificationStatusId(StatusOfNotification.NO_ANSWER)
                        );
                        notification.setQuestionId(question.getId());


                        String completeMsg =
                                participantService.getFirstname(participantId) + " " + participantService.getLastname(participantId) + " отправил вопрос.\n" +
                                        "\n" +
                                        "Дата: " + notification.getDate().toLocalDate() + "\n" +
                                        "Время: " + notification.getDate().toLocalTime() + "\n";

                        completeMsg = completeMsg + " ," + textArea.getValue();

                        notification.setAllMessage(completeMsg);
                        notificationService.saveNotification(notification);

                        UI.getCurrent().getPage().reload();
                    }
                }
        );
        textArea.setWidth("50%");
        textArea.setPlaceholder("Например: я отжался 10 раз - можно ли съесть пиццу?");
        textArea.setClearButtonVisible(true);
        textArea.setMaxLength(300);
        textArea.setMinLength(1);
        textArea.setHeight("200px");

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(new Toolbar(ToolbarType.PARTICIPANT_PAGES), new H2("Вопросы"), textArea, submit, historyList);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        add(verticalLayout);
    }

    private void configSearch() {
//        period.setMax(LocalDate.now());
//        period.setLocale(new Locale("ru", "RU"));
//        searchPanel.add(period, viewDataBtn);
//        searchPanel.setAlignItems(FlexComponent.Alignment.BASELINE);
//        period.addValueChangeListener(e -> checkDate = e.getValue());
//        viewDataBtn.addClickListener(view -> {
//            Log logBook = logService.findByDateAndParticipantId(checkDate, participantId);
//            String logbookType;
//            VaadinSession.getCurrent().setAttribute("CheckDate", checkDate);
//            if (logBook != null) {
//                logbookType = logsTypeService.getLogTypeById(logBook.getLogTypeId());
//                VaadinSession.getCurrent().setAttribute("LogbookType", logbookType);
//                viewDataBtn.getUI().ifPresent((ui -> ui.navigate(ParticipantLogbookView.class)));
//            } else
//                Notification.show("Нет записей", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
//        });
    }

    private void configureHV() {
        historyList.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        historyList.setHeight("100%");
        historyList.setWidth("100%");
        historyList.setAllRowsVisible(true);
        historyList.addColumn(Question::getDate)
                .setHeader("Дата")
                .setWidth("20%")
                .setFlexGrow(0);
        historyList.addColumn(Question::getQuestion)
                .setHeader("Вопрос")
                .setWidth("30%")
                .setFlexGrow(0);
        historyList.addComponentColumn(this::buildStatusBadge).setHeader("Статус ответа")
                .setWidth("23%");
        historyList.addComponentColumn(this::buildParticipantViewButton)
                .setWidth("23%");
        List<Question> allQuestions = questionService.getAllParticipantQuestion(participantId);
        Collections.reverse(allQuestions);
       historyList.setDataProvider(new ListDataProvider<>(allQuestions));
        //historyList.setItems(new ListDataProvider<>(Collections.reverse(questionService.getAllParticipantQuestion(participantId))));
    }

    public Span buildStatusBadge(Question question) {
        String status = questionStatusService.getQuestionType(question.getStatusId());
        Span statusBadge = new Span(status);
        if (status.equals(StatusOfQuestion.NO_ANSWER.getText())) {
            statusBadge.getElement().getThemeList().add("badge error");
        } else {
            statusBadge.getElement().getThemeList().add("badge success");
        }
        return statusBadge;
    }

    private void identifyClick() {
        historyList.addSelectionListener(selection -> {
            Optional<Question> question = selection.getFirstSelectedItem();
            VaadinSession.getCurrent().setAttribute("QuestionId", question.get().getId());
            VaadinSession.getCurrent().setAttribute("CheckDate", question.get().getDate());
            historyList.getUI().ifPresent(ui -> ui.navigate(ParticipantQuestionDetailsView.class));
        });
    }

    public Button buildParticipantViewButton(Question question) {
        Button button = new Button("Смотреть");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickListener(click -> {
            VaadinSession.getCurrent().setAttribute("QuestionId", question.getId());
            VaadinSession.getCurrent().setAttribute("CheckDate", question.getDate());
            button.getUI().ifPresent(ui -> ui.navigate(ParticipantQuestionDetailsView.class));
        });
        return button;
    }
}
