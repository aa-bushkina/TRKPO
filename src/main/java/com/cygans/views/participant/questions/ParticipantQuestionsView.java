package com.cygans.views.participant.questions;

import com.cygans.database.controllers.NotificationController;
import com.cygans.database.controllers.ParticipantAndMentorController;
import com.cygans.database.controllers.QuestionController;
import com.cygans.database.participant.Participant;
import com.cygans.database.question.Question;
import com.cygans.database.question.question_status.StatusOfQuestion;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Странице вопросов участника
 */
@PageTitle("Марафон")
@Route(value = "participant/questions")
public class ParticipantQuestionsView extends Div {
    private final TextArea textArea = new TextArea("Мой вопрос");
    private final Grid<Question> historyList = new Grid<>(Question.class, false);
    private final Participant participant;
    private final QuestionController questionController;


    public ParticipantQuestionsView(QuestionController questionController,
                                    ParticipantAndMentorController participantAndMentorController,
                                    NotificationController notificationController) {
        this.questionController = questionController;
        participant = participantAndMentorController.getNowParticipantByAuthentication();
        Button submit = new Button("Задать");
        submit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submit.setWidth("30%");
        submit.setHeight("50px");

        configureHV();
        identifyClick();

        submit.addClickListener(e -> {
                    //Если нет ментора - не даем ничего отправить
                    if (participantAndMentorController.getMentorIdOfParticipant(participant) == null) {
                        Notification.show("Чтобы задавать вопросы ментору, нужно принять его заявку", 3000, Notification.Position.TOP_CENTER)
                                .addThemeVariants(NotificationVariant.LUMO_ERROR);
                    } else if (textArea.isEmpty()) {
                        Notification notification = Notification.show("Необходимо ввести текст", 3000, Notification.Position.TOP_CENTER);
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    } else if (textArea.isInvalid()) {
                        Notification notification = Notification.show("Максимальное количетво символов - 300", 3000, Notification.Position.TOP_CENTER);
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    } else {
                        Long questionId = questionController.addNewQuestionForNowParticipant(textArea.getValue());
                        notificationController.addNewQuestionNotification(questionId, textArea.getValue());
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

    private void configureHV() {
        historyList.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        historyList.setHeight("100%");
        historyList.setWidth("100%");
        historyList.setAllRowsVisible(true);
        historyList.addColumn(new LocalDateRenderer<>(Question::getDate, DateTimeFormatter.ofPattern("dd.MM.yyyy")))
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
        List<Question> allQuestions = questionController.getAllQuestionNowParticipant();
        Collections.reverse(allQuestions);
        historyList.setDataProvider(new ListDataProvider<>(allQuestions));
    }

    public Span buildStatusBadge(Question question) {
        String status = questionController.getQuestionStatus(question);
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
