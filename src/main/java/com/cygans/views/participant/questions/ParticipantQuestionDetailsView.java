package com.cygans.views.participant.questions;

import com.cygans.database.controllers.QuestionController;
import com.cygans.database.question.Question;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@PageTitle("Марафон")
@Route(value = "participant/question-details")
public class ParticipantQuestionDetailsView extends Div {
    private final TextArea questionText = new TextArea("Сообщение:");
    private final TextArea answerText = new TextArea();
    private final Button backBtn = new Button("Назад");
    private final QuestionController questionController;

    public ParticipantQuestionDetailsView(QuestionController questionController) {
        this.questionController = questionController;
        removeAll();
        add(new Toolbar(ToolbarType.PARTICIPANT_PAGES));
        LocalDate selectDate = (LocalDate) VaadinSession.getCurrent().getAttribute("CheckDate");

        setStyles();

        VerticalLayout vl = new VerticalLayout(new H3("Вопрос от " + selectDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))),
                questionText, answerText, backBtn);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        vl.setSpacing(true);
        add(vl);
    }

    private void setStyles() {
        backBtn.addClickListener(click -> backBtn.getUI().ifPresent(ui -> ui.navigate(ParticipantQuestionsView.class)));

        Question question = questionController.getNowQuestionInSession();
        questionText.setReadOnly(true);
        questionText.setWidth("50%");
        questionText.setMinHeight("80%");
        questionText.setMaxHeight("300px");
        answerText.setReadOnly(true);
        answerText.setWidth("50%");
        answerText.setMinHeight("80%");
        answerText.setMaxHeight("300px");
        answerText.setVisible(false);

        questionText.setLabel("Вопрос:");
        questionText.setValue(question.getQuestion());

        if (question.getAnswer() != null) {
            answerText.setVisible(true);
            answerText.setLabel("Ответ:");
            answerText.setValue(question.getAnswer());
        }
    }
}