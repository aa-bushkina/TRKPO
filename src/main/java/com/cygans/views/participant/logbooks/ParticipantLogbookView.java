package com.cygans.views.participant.logbooks;

import com.cygans.database.controllers.LogController;
import com.cygans.database.eating_log_book.EatingLogBook;
import com.cygans.database.eating_log_book.MealType;
import com.cygans.database.emotional_log_book.EmotionalLogBook;
import com.cygans.database.log_book.logs_type.LogBookType;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.sport_log_book.SportLogBook;
import com.cygans.database.sport_log_book.intensity.IntensityType;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.cygans.views.participant.history.ParticipantHistoryView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;


@PageTitle("My Logbook")
@Route(value = "participant/logbook")
public class ParticipantLogbookView extends VerticalLayout {
  private String logBookType, emotionalDescText,
          foodDescText, mealTypeText, hourFoodText, minuteFoodText,
          intensityTypeText, sportDescText, durationText, activityText;
  private Long logBookId;
  private LocalDate selectDate;
  private ComboBox<String> meal_type, hourFood, minuteFood, intensity_type;
  private TextField activityField, durationField;
  private TextArea foodDesc, emotionalDesc, sportDesc, answerField;
  private Button changeLog, save, cancel, back;
  private final FormLayout formLayout = new FormLayout();
  private final VerticalLayout mainLayout = new VerticalLayout();
  private final HorizontalLayout buttons = new HorizontalLayout();
  private final NotificationsService notificationsService;
  private final LogController logController;


  public ParticipantLogbookView(NotificationsService notificationsService,
                                LogController logController) {
    this.logController = logController;
    removeAll();
    init();

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

    buttons.setWidth(mainLayout.getWidth());
    mainLayout.add(
            new H2(logBookType),
            new H3("Запись от " + selectDate),
            formLayout,
            buttons
    );
    mainLayout.setMaxWidth("600px");
    mainLayout.setPadding(false);
    setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, new Toolbar(ToolbarType.PARTICIPANT_PAGES), mainLayout);
    add(new Toolbar(ToolbarType.PARTICIPANT_PAGES), mainLayout);
  }

    private void init() {
        saveSetUp();
        cancelInit();
        changeLogInit();
        backInit();
    }

    private void addAnswerField(String answer) {
        answerField = new TextArea("Ответ ментора");
        answerField.setValue(answer);
        answerField.setClearButtonVisible(true);
        answerField.setReadOnly(true);
    }

    private void saveSetUp() {
        save = new Button("Сохранить");
        save.setVisible(false);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.getElement().getStyle().set("margin-left", "1em");

        save.addClickListener(e -> {
            if (logBookType.equals(LogBookType.EMOTIONAL.getText())) {
                if (emotionalDesc.isInvalid() || emotionalDesc.isEmpty() || emotionalDesc.getValue().length() > 1000) {
                    Notification notification = Notification.show("Введите от 1 до 1000 символов!", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                } else {

          emotionalDescText = emotionalDesc.getValue();
          logController.updateEmotionalLog(logBookId, emotionalDescText);

                    allSetReadOnly(true);
                    changeLog.setVisible(true);
                    back.setVisible(true);
                    save.setVisible(false);
                    cancel.setVisible(false);
                    Notification.show("Изменения сохранены", 2000, Notification.Position.TOP_CENTER);
                }
            } else if (logBookType.equals(LogBookType.EATING.getText())) {
                if (foodDesc.isEmpty()) {
                    Notification notification = Notification.show("Описание пустое", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                } else if (hourFood.isEmpty()) {
                    Notification notification = Notification.show("Уточните час приема пищи", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                } else if (minuteFood.isEmpty()) {
                    Notification notification = Notification.show("Уточните минуты приема пищи", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                } else if (meal_type.isEmpty()) {
                    Notification notification = Notification.show("Уточните тип вашего приема пищи", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                } else {

                    mealTypeText = meal_type.getValue();
                    foodDescText = foodDesc.getValue();
                    hourFoodText = hourFood.getValue();
                    minuteFoodText = minuteFood.getValue();

          logController.updateEatingLog(
                  logBookId,
                  foodDesc.getValue(),
                  LocalTime.of(Integer.parseInt(hourFood.getValue()), Integer.parseInt(minuteFood.getValue())),
                  meal_type.getValue()
          );

                    allSetReadOnly(true);
                    changeLog.setVisible(true);
                    back.setVisible(true);
                    save.setVisible(false);
                    cancel.setVisible(false);
                    Notification.show("Изменения сохранены", 2000, Notification.Position.TOP_CENTER);
                }
            } else if (logBookType.equals(LogBookType.SPORT.getText())) {
                if (intensity_type.isEmpty()) {
                    Notification notification = Notification.show("Выберите интенсивность тренировок!", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                } else if (activityField.isEmpty()) {
                    Notification notification = Notification.show("Уточните вашу активность!", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                } else if (durationField.getValue().isEmpty()) {
                    Notification notification = Notification.show("Введите продолжительность вашей активности!", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                } else if (Integer.parseInt(durationField.getValue()) < 0) {
                    Notification notification = Notification.show("Продолжительность не может быть отрицательной!", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                } else if (Integer.parseInt(durationField.getValue()) > 1440) {
                    Notification notification = Notification.show("Вы не можете заниматься активностью больше 24 часов (1440 минут) в сутки!", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                } else {

                    activityText = activityField.getValue();
                    sportDescText = sportDesc.getValue();
                    durationText = durationField.getValue();
                    intensityTypeText = intensity_type.getValue();

          logController.updateSportLog(logBookId, sportDescText, activityText, intensity_type.getValue(), Integer.parseInt(durationText));

                    allSetReadOnly(true);
                    changeLog.setVisible(true);
                    back.setVisible(true);
                    save.setVisible(false);
                    cancel.setVisible(false);
                    Notification.show("Изменения сохранены", 2000, Notification.Position.TOP_CENTER);
                }
            }
        });
    }

    private void cancelInit() {
        cancel = new Button("Отменить");
        cancel.setVisible(false);
        cancel.getElement().getStyle().set("margin-right", "auto");
        cancel.addClickListener(e -> {
            if (logBookType.equals(LogBookType.EMOTIONAL.getText())) {
                emotionalDesc.setValue(emotionalDescText);
            } else if (logBookType.equals(LogBookType.EATING.getText())) {
                foodDesc.setValue(foodDescText);
                hourFood.setValue(hourFoodText);
                minuteFood.setValue(minuteFoodText);
                meal_type.setValue(mealTypeText);
            } else if (logBookType.equals(LogBookType.SPORT.getText())) {
                activityField.setValue(activityText);
                durationField.setValue(durationText);
                sportDesc.setValue(sportDescText);
                intensity_type.setValue(intensityTypeText);
            }
            changeLog.setVisible(true);
            back.setVisible(true);
            save.setVisible(false);
            cancel.setVisible(false);
            allSetReadOnly(true);
            Notification.show("Изменения отменены", 2000, Notification.Position.TOP_CENTER);
        });
    }

    private void changeLogInit() {
        changeLog = new Button("Редактировать");
        changeLog.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        changeLog.getElement().getStyle().set("margin-left", "auto");
        changeLog.addClickListener(e -> {
            allSetReadOnly(false);
            back.setVisible(false);
            changeLog.setVisible(false);
            save.setVisible(true);
            cancel.setVisible(true);
        });
    }

    private void backInit() {
        back = new Button("Назад");
        back.getElement().getStyle().set("margin-right", "auto");
        back.addClickListener(click -> back.getUI().ifPresent(ui -> ui.navigate(ParticipantHistoryView.class)));
    }

  private void showEatingLogBookView() {
    EatingLogBook log = logController.getEatingLogByLogbookId(logBookId);
    mealTypeText = logController.getMealEatingLog(log.getMealId());
    foodDescText = log.getDescription();
    hourFoodText = log.getTimeEat().toString().substring(0, 2);
    minuteFoodText = log.getTimeEat().toString().substring(3, 5);
    mealFoodTypeInit(mealTypeText);
    descFoodInit(foodDescText);
    hourFoodTextInit(hourFoodText);
    minuteFoodTextInit(minuteFoodText);
    if (log.getTimeType().plusDays(1).isAfter(LocalDateTime.now())
            && notificationsService.getNotificationByLogBookId(logBookId).getReplyMessage() == null) {
      buttons.add(back, changeLog, save, cancel);
    } else {
      buttons.add(back, save, cancel);
    }
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
        meal_type = new ComboBox<>("Приём пищи");
        meal_type.setItems(MealType.BREAKFAST.getText());
        meal_type.setItems(MealType.LAUNCH.getText());
        meal_type.setItems(MealType.DINNER.getText());
        meal_type.setItems(MealType.OTHER.getText());
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
        ArrayList<String> h = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            h.add("0" + i);
        }
        for (int i = 10; i < 24; i++) {
            h.add(String.valueOf(i));
        }
        hourFood = new ComboBox<>("Час прием пищи");
        hourFood.setItems(h);
        hourFood.setValue(hourFoodText);
        hourFood.setClearButtonVisible(true);
        hourFood.setReadOnly(true);
    }

    private void minuteFoodTextInit(String minuteFoodText) {
        ArrayList<String> m = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            m.add("0" + i);
        }
        for (int i = 10; i < 60; i++) {
            m.add(String.valueOf(i));
        }
        minuteFood = new ComboBox<>("Минуты прием пищи");
        minuteFood.setItems(m);
        minuteFood.setValue(minuteFoodText);
        minuteFood.setClearButtonVisible(true);
        minuteFood.setReadOnly(true);
    }

  private void showSportLogBookView() {
    SportLogBook log = logController.getSportLogByLogbookId(logBookId);
    intensityTypeText = logController.getIntensitySportLog(log.getIntensityId());
    sportDescText = log.getComments();
    activityText = log.getActivity();
    durationText = String.valueOf(log.getDuration());
    intensityInit(intensityTypeText);
    activityInit(activityText);
    descSportInit(sportDescText);
    durationInit(durationText);

    if (log.getTimeType().plusDays(1).isAfter(LocalDateTime.now())
            && notificationsService.getNotificationByLogBookId(logBookId).getReplyMessage() == null) {
      buttons.add(back, changeLog, save, cancel);
    } else {
      buttons.add(back, save, cancel);
    }

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
        intensity_type = new ComboBox<>("Интенсивность");
        intensity_type.setItems(IntensityType.LOWER.getText(), IntensityType.MIDDLE.getText(), IntensityType.HIGH.getText());
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

    private void durationInit(String dur) {
        durationField = new TextField("Продолжительность (в минутах)");
        durationField.setValue(dur);
        durationField.setClearButtonVisible(true);
        durationField.setReadOnly(true);
    }

    private void descSportInit(String stDescSport) {
        sportDesc = new TextArea("Описание активности");
        sportDesc.setValue(stDescSport);
        sportDesc.setClearButtonVisible(true);
        sportDesc.setReadOnly(true);
    }

  public void showEmotionalLogBookView() {
    EmotionalLogBook emotionalLogBook = logController.getEmotionalLogByLogbookId(logBookId);
    emotionalDescText = emotionalLogBook.getDescription();
    emotionalDescInit(emotionalDescText);
    if (emotionalLogBook.getTimeType().plusDays(1).isAfter(LocalDateTime.now())
            && notificationsService.getNotificationByLogBookId(logBookId).getReplyMessage() == null) {
      buttons.add(back, changeLog, save, cancel);
    } else {
      buttons.add(back, save, cancel);
    }
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

    private void allSetReadOnly(boolean Boolean) {
        if (logBookType.equals(LogBookType.EMOTIONAL.getText())) {
            emotionalDesc.setReadOnly(Boolean);
        } else if (logBookType.equals(LogBookType.EATING.getText())) {
            foodDesc.setReadOnly(Boolean);
            meal_type.setReadOnly(Boolean);
            hourFood.setReadOnly(Boolean);
            minuteFood.setReadOnly(Boolean);
        } else if (logBookType.equals(LogBookType.SPORT.getText())) {
            activityField.setReadOnly(Boolean);
            sportDesc.setReadOnly(Boolean);
            durationField.setReadOnly(Boolean);
            intensity_type.setReadOnly(Boolean);
        }
    }

}
