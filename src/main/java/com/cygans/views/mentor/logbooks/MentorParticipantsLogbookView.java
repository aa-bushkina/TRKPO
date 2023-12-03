package com.cygans.views.mentor.logbooks;

import com.cygans.database.controllers.LogController;
import com.cygans.database.eating_log_book.EatingLogBook;
import com.cygans.database.log_book.logs_type.LogBookType;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.sport_log_book.SportLogBook;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.cygans.views.mentor.participants.MentorParticipantDataView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.time.LocalDate;

@PageTitle("Марафон")
@Route(value = "mentor/view-participant-logbook-details")
public class MentorParticipantsLogbookView extends VerticalLayout {
    private Long logBookId;
    private LocalDate selectDate;
    private String logBookType;
    private Toolbar menu = new Toolbar(ToolbarType.MENTOR_PAGES);
    private Button back_button;
    private TextArea foodDesc, emotionalDesc, sportDesc, answerField;
    private TextField meal_type, hourFood, minuteFood, intensity_type, activityField, durationField;
    private final VerticalLayout mainLayout = new VerticalLayout();
    private final FormLayout formLayout = new FormLayout();
    private final NotificationsService notificationsService;
    private final LogController logController;


    public MentorParticipantsLogbookView(LogController logController,
                                         NotificationsService notificationsService) {
        removeAll();
        backInit();

        this.logController = logController;
        this.notificationsService = notificationsService;
        logBookType = (String) VaadinSession.getCurrent().getAttribute("LogbookType");
        selectDate = (LocalDate) VaadinSession.getCurrent().getAttribute("CheckDate");
        logBookId = (Long) VaadinSession.getCurrent().getAttribute("LogbookId");

        if (logBookType.equals(LogBookType.EMOTIONAL.getText())) {
            showEmotionalLogBookView();
        } else if (logBookType.equals(LogBookType.EATING.getText())) {
            showEatingLogBookView();
        } else if (logBookType.equals(LogBookType.SPORT.getText())) {
            showSportLogBookView();
        }

        if (notificationsService.getNotificationByLogBookId(logBookId).getReplyMessage() != null) {
            addAnswerField(notificationsService.getNotificationByLogBookId(logBookId).getReplyMessage());
            formLayout.add(answerField);
        }

        mainLayout.add(
                new H2(logBookType),
                new H3("Запись от " + selectDate),
                formLayout,
                back_button
        );

        mainLayout.setMaxWidth("600px");
        mainLayout.setPadding(false);
        setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, new Toolbar(ToolbarType.PARTICIPANT_PAGES), mainLayout);
        add(new Toolbar(ToolbarType.MENTOR_PAGES), mainLayout);
    }

    private void backInit() {
        back_button = new Button("Назад");
        back_button.getElement().getStyle().set("margin-right", "auto");
        back_button.addClickListener(click -> back_button.getUI().ifPresent(ui -> ui.navigate(MentorParticipantDataView.class)));
    }

    private void addAnswerField(String answer) {
        answerField = new TextArea("Ваш ответ");
        answerField.setValue(answer);
        answerField.setClearButtonVisible(true);
        answerField.setReadOnly(true);
    }

    private void showEatingLogBookView() {
        EatingLogBook log = logController.getEatingLogByLogbookId(logBookId);
        mealFoodTypeInit(logController.getMealEatingLog(log.getMealId()));
        descFoodInit(log.getDescription());
        hourFoodTextInit(log.getTimeEat().toString().substring(0, 2));
        minuteFoodTextInit(log.getTimeEat().toString().substring(3, 5));
        formLayout.add(
                meal_type,
                hourFood,
                minuteFood,
                foodDesc
        );
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1)
        );
        formLayout.setColspan(meal_type, 1);
        formLayout.setColspan(hourFood, 1);
        formLayout.setColspan(minuteFood, 1);
        formLayout.setColspan(foodDesc, 1);
    }

    private void mealFoodTypeInit(String mealType) {
        meal_type = new TextField("Приём пищи");
        meal_type.setValue(mealType);
        meal_type.setClearButtonVisible(true);
        meal_type.setReadOnly(true);
    }

    private void descFoodInit(String stDescFood) {
        foodDesc = new TextArea("Описание еды");
        foodDesc.setValue(stDescFood);
        foodDesc.setClearButtonVisible(true);
        foodDesc.setReadOnly(true);
    }

    private void hourFoodTextInit(String hourFoodText) {
        hourFood = new TextField("Час прием пищи");
        hourFood.setValue(hourFoodText);
        hourFood.setClearButtonVisible(true);
        hourFood.setReadOnly(true);
    }

    private void minuteFoodTextInit(String minuteFoodText) {
        minuteFood = new TextField("Минуты прием пищи");
        minuteFood.setValue(minuteFoodText);
        minuteFood.setClearButtonVisible(true);
        minuteFood.setReadOnly(true);
    }

    private void showSportLogBookView() {
        SportLogBook log = logController.getSportLogByLogbookId((logBookId));
        intensityInit(logController.getIntensitySportLog(log.getIntensityId()));
        activityInit(log.getActivity());
        descSportInit(log.getComments());
        durationInit(String.valueOf(log.getDuration()));
        formLayout.add(
                intensity_type,
                activityField,
                sportDesc,
                durationField
        );
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1)
        );
        formLayout.setColspan(intensity_type, 1);
        formLayout.setColspan(activityField, 1);
        formLayout.setColspan(sportDesc, 1);
        formLayout.setColspan(durationField, 1);
    }

    public void intensityInit(String strIntensity) {
        intensity_type = new TextField("Интенсивность");
        intensity_type.setValue(strIntensity);
        intensity_type.setClearButtonVisible(true);
        intensity_type.setReadOnly(true);
    }

    private void activityInit(String act) {
        activityField = new TextField("Активность");
        activityField.setValue(act);
        activityField.setClearButtonVisible(true);
        activityField.setReadOnly(true);
    }

    private void descSportInit(String stDescSport) {
        sportDesc = new TextArea("Описание активности");
        sportDesc.setValue(stDescSport);
        sportDesc.setClearButtonVisible(true);
        sportDesc.setReadOnly(true);
    }

    private void durationInit(String dur) {
        durationField = new TextField("Продолжительность (в минутах)");
        durationField.setValue(dur);
        durationField.setClearButtonVisible(true);
        durationField.setReadOnly(true);
    }

    public void showEmotionalLogBookView() {
        emotionalDescInit(logController.getEmotionalLogByLogbookId(logBookId).getDescription());
        formLayout.add(
                emotionalDesc
        );
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1)
        );
        formLayout.setColspan(emotionalDesc, 1);
    }

    private void emotionalDescInit(String stEmotionalDesc) {
        emotionalDesc = new TextArea("Описание");
        emotionalDesc.setValue(stEmotionalDesc);
        emotionalDesc.setClearButtonVisible(true);
        emotionalDesc.setReadOnly(true);
    }

}
