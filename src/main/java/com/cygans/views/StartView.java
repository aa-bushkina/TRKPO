package com.cygans.views;

import com.cygans.database.eating_log_book.EatingLogBook;
import com.cygans.database.eating_log_book.EatingLogBookService;
import com.cygans.database.eating_log_book.meal.MealService;
import com.cygans.database.emotional_log_book.EmotionalLogBook;
import com.cygans.database.emotional_log_book.EmotionalLogBookService;
import com.cygans.database.log_book.Log;
import com.cygans.database.log_book.LogService;
import com.cygans.database.log_book.logs_type.LogBookType;
import com.cygans.database.log_book.logs_type.LogsTypeService;
import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorService;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_status.StatusOfNotification;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.notifications.notification_type.TypeOfNotification;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import com.cygans.database.question.question_status.QuestionStatusService;
import com.cygans.database.sport_log_book.SportLogBook;
import com.cygans.database.sport_log_book.SportLogBookService;
import com.cygans.database.sport_log_book.intensity.IntensityService;
import com.cygans.security.db.RoleEnum;
import com.cygans.security.db.authorities.Authorities;
import com.cygans.security.db.authorities.AuthoritiesService;
import com.cygans.security.db.logInfo.LoginInfo;
import com.cygans.security.db.logInfo.LoginInfoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * Страница логина
 */
@Route(value = "Вход")
@Theme(themeFolder = "styles")
public class StartView extends VerticalLayout implements BeforeEnterObserver {
    private final LoginForm login = new LoginForm();
    private final Button signUpBtn;
    private Boolean isFirstTime = true;

    private AuthoritiesService authoritiesService;
    private LoginInfoService loginInfoService;
    private MentorService mentorService;
    private ParticipantService participantService;
    private ParticipantMentorService participantMentorService;
    private EatingLogBookService eatingLogBookService;
    private SportLogBookService sportLogBookService;
    private EmotionalLogBookService emotionalLogBookService;
    private LogService logService;
    private LogsTypeService logsTypeService;
    private IntensityService intensityService;
    private MealService mealService;
    private NotificationsService notificationsService;
    private NotificationTypeService notificationTypeService;
    private NotificationStatusService notificationStatusService;


    private DateTimeFormatter formatter;
    private long createParticipant(String name, String surname, String login, String passwd, String phone)
    {
        Authorities authorities = new Authorities(login, RoleEnum.PARTICIPANT.getValue());
        authoritiesService.saveAuthorities(authorities);

        Authentication authentication = new UsernamePasswordAuthenticationToken(login,  passwd,
          AuthorityUtils.createAuthorityList(RoleEnum.PARTICIPANT.getValue()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        LoginInfo loginInfo = new LoginInfo(
          login, passwd,
          authoritiesService.getAuthoritiesIdByUsername(login), (byte) 1);
        loginInfoService.saveLoginInfo(loginInfo);

        Participant participant = new Participant(
          name,
          surname,
          login,
          phone,
          "Жен",
          LocalDate.now(),
          123,
          123,
          123,
          123,
          123,
          loginInfoService.findByLogin(login).getId()
        );
        participantService.saveParticipant(participant);
        System.out.println("Participant created\n");
        return participant.getId();
    }

    private long createMentor(String name, String surname, String login, String passwd, String phone)
    {
        Authorities hardcode_authorities = new Authorities(login, RoleEnum.MENTOR.getValue());
        authoritiesService.saveAuthorities(hardcode_authorities);
        Authentication hardcode_authentication = new UsernamePasswordAuthenticationToken(
          login,
          passwd,
          AuthorityUtils.createAuthorityList(RoleEnum.MENTOR.getValue()));
        SecurityContextHolder.getContext().setAuthentication(hardcode_authentication);
        LoginInfo hardcode_login_info = new LoginInfo(
          login,
          passwd,
          authoritiesService.getAuthoritiesIdByUsername(login), (byte) 1);
        loginInfoService.saveLoginInfo(hardcode_login_info);

        Mentor hardcode_mentor = new Mentor(
          name,
          surname,
          login,
          phone,
          "Жен",
          LocalDate.now(),
          loginInfoService.findByLogin(login).getId()
        );
        mentorService.saveMentor(hardcode_mentor);
        System.out.println("Mentor created\n");
         return hardcode_mentor.getId();
    }

    private void createHardcodedUsers()
    {
        long mentId = -1;
        long partId = -1;

        if (mentorService.isNeedToAddHardcodedUser())
        {
            createMentor("Ванька","Головин", "vanya", "Vanvanvan", "89137197445");
            createMentor("Лёшка","Бойкин", "alexPro", "fox21century", "89137197445");
            createMentor("Илюшка","Работин", "ilya", "reddead", "89137197445");
            createMentor("Фиона","Павлова", "phionPAV", "alpachino", "89137197445");
            mentId = createMentor("2","2", "2", "2", "2");
        }

        if (participantService.isNeedToAddHardcodedUser()) {
          partId = createParticipant("Катька", "Волосова", "katya", "katkatkat", "89383170126");
          addLogbookToEmotional(partId, "Нужна для смерть для того чтобы ценить жизнь? Думала об этом весь день", "2023-11-07");
          addLogbookToEmotional(partId, "Весь день думала о пельменях и бургерах. Мне хорошо.", "2023-11-08");
          addLogbookToEmotional(partId, "Пока читала книгу о похудении съела роллы", "2023-11-09");
          addLogbookToEmotional(partId, "Сегодня был трудный день на работе", "2023-11-10");
          addLogbookToEmotional(partId, "Встретила старого друга, вспоминали старые времена", "2023-11-11");
          addLogbookToEmotional(partId, "Провела вечер в кино, отлично отдохнула", "2023-11-12");
          addLogbookToEating(partId, LocalTime.of(4, 30), "Поела картошку с борщом. Запила квасом.", "Ужин", "2023-11-10");
          addLogbookToEating(partId, LocalTime.of(12, 0), "Съела салат с курицей на обед", "Обед", "2023-11-11");
          addLogbookToEating(partId, LocalTime.of(20, 0), "Попробовала новый рецепт пасты", "Ужин", "2023-11-12");
          addLogbookToEating(partId, LocalTime.of(10, 0), "Поела фрукты и орехи", "Другое", "2023-11-10");
          addLogbookToEating(partId, LocalTime.of(16, 0), "Заварила зеленый чай с печеньем", "Другое", "2023-11-11");
          addLogbookToEating(partId, LocalTime.of(22, 0), "Попробовала экзотический фрукт", "Другое", "2023-11-12");
          addLogbookToSport(partId, "Высокая", 60, "Бег на свежем воздухе", "Пробежал 5 км", "2023-11-10");
          addLogbookToSport(partId, "Средняя", 45, "Йога в зале", "Фокус на дыхании и растяжке", "2023-11-12");
          addLogbookToSport(partId, "Низкая", 30, "Прогулка в парке", "На свежем воздухе", "2023-11-13");

          partId = createParticipant("Петька", "Совкин", "petka_super", "tarakan1", "89358127132");
          addLogbookToEmotional(partId, "Сегодняшний день начался с утренней пробежки", "2023-11-10");
          addLogbookToEmotional(partId, "Попробовал новый рецепт пасты, получилось вкусно", "2023-11-11");
          addLogbookToEmotional(partId, "Вечером посетил тренировку по йоге", "2023-11-12");
          addLogbookToEating(partId, LocalTime.of(6, 0), "Поел омлет с тостами на завтрак", "Завтрак", "2023-11-10");
          addLogbookToEating(partId, LocalTime.of(13, 30), "Обедал рыбным супом и рисом", "Обед", "2023-11-11");
          addLogbookToEating(partId, LocalTime.of(19, 0), "Ужинал пиццей и салатом", "Ужин", "2023-11-12");
          addLogbookToEating(partId, LocalTime.of(11, 30), "Поел белокачанную капусту", "Другое", "2023-11-10");
          addLogbookToEating(partId, LocalTime.of(17, 30), "Попил свежевыжатый сок", "Другое", "2023-11-11");
          addLogbookToEating(partId, LocalTime.of(20, 45), "Пробовал разные виды сыра", "Другое", "2023-11-12");
          addLogbookToSport(partId, "Средняя", 40, "Велосипедная прогулка", "По городскому парку", "2023-11-10");
          addLogbookToSport(partId, "Высокая", 55, "Тренировка в зале", "Силовые упражнения", "2023-11-12");
          addLogbookToSport(partId, "Низкая", 25, "Утренняя зарядка", "Простые упражнения", "2023-11-13");

          partId = createParticipant("Наташка", "Мирянцева", "natashaNataly", "leonardo", "89217132831");
          addLogbookToEmotional(partId, "Сегодня посетила выставку искусства", "2023-11-10");
          addLogbookToEmotional(partId, "Провела время с семьей, готовили ужин вместе", "2023-11-11");
          addLogbookToEmotional(partId, "Смотрела любимый фильм вечером", "2023-11-12");
          addLogbookToEating(partId, LocalTime.of(8, 0), "Поела йогурт с фруктами на завтрак", "Завтрак", "2023-11-10");
          addLogbookToEating(partId, LocalTime.of(14, 0), "Обедала супом с гренками", "Обед", "2023-11-11");
          addLogbookToEating(partId, LocalTime.of(18, 30), "Ужинала рыбой и овощами", "Ужин", "2023-11-12");
          addLogbookToEating(partId, LocalTime.of(9, 30), "Поела мед и овсянку", "Другое", "2023-11-10");
          addLogbookToEating(partId, LocalTime.of(15, 0), "Пробовала азиатские сладости", "Другое", "2023-11-11");
          addLogbookToEating(partId, LocalTime.of(19, 45), "Готовила экспериментальный десерт", "Другое", "2023-11-12");
          addLogbookToSport(partId, "Низкая", 30, "Плавание в бассейне", "Расслабляющая тренировка", "2023-11-10");
          addLogbookToSport(partId, "Средняя", 50, "Танцы в студии", "Энергичные танцы под музыку", "2023-11-12");
          addLogbookToSport(partId, "Высокая", 65, "Бег по пляжу", "Интенсивная тренировка", "2023-11-13");

          partId = createParticipant("Олежка", "Питулин", "olegBoss777", "telefon", "89991833614");
          addLogbookToEmotional(partId, "Сегодня успешно провел переговоры", "2023-11-10");
          addLogbookToEmotional(partId, "Получил новый заказ, радостный день", "2023-11-11");
          addLogbookToEmotional(partId, "Прогулка по парку после работы", "2023-11-12");
          addLogbookToEating(partId, LocalTime.of(7, 30), "Поел кашу с молоком на завтрак", "Завтрак", "2023-11-10");
          addLogbookToEating(partId, LocalTime.of(12, 45), "Обедал пастой с мясным соусом", "Обед", "2023-11-11");
          addLogbookToEating(partId, LocalTime.of(21, 0), "Ужинал суши и супом", "Ужин", "2023-11-12");
          addLogbookToEating(partId, LocalTime.of(8, 45), "Завтракал гречкой с медом", "Другое", "2023-11-10");
          addLogbookToEating(partId, LocalTime.of(14, 15), "Пробовал бразильский орех", "Другое", "2023-11-11");
          addLogbookToEating(partId, LocalTime.of(20, 30), "Поел марокканские фрукты", "Другое", "2023-11-12");
          addLogbookToSport(partId, "Высокая", 55, "Тренировка на открытом воздухе", "Функциональные упражнения", "2023-11-10");
          addLogbookToSport(partId, "Низкая", 40, "Ходьба по городу", "Посещение достопримечательностей", "2023-11-12");
          addLogbookToSport(partId, "Средняя", 50, "Теннис на корте", "С друзьями в выходной", "2023-11-13");

          partId = createParticipant("1", "1", "1", "1", "1");
        }
        if (participantMentorService.isNeedToConnectUsers(mentId, partId))
        {
            participantMentorService.create(partId, mentId);
            System.out.println("Users connected to each other\n");
        }

    }

    private void addLogbookToEmotional(long participantId, String text,String date)
    {
        Log log = new Log(participantId, LocalDate.parse(date, formatter), logsTypeService.getLogTypeId(LogBookType.EMOTIONAL.getText()));
        logService.saveLog(log);
        EmotionalLogBook emotionalLogBook = new EmotionalLogBook(log.getId(), LocalDateTime.now(), text);
        emotionalLogBookService.saveEmotionalLog(emotionalLogBook);
        addNotification(participantId, log, 0, text);
    }


    private void addLogbookToSport(long participantId, String intes, int duration, String active, String comments,String date)
    {
      Log log = new Log(participantId, LocalDate.parse(date, formatter), logsTypeService.getLogTypeId(LogBookType.SPORT.getText()));
      logService.saveLog(log);
      SportLogBook sportLogBook = new SportLogBook(log.getId(),
        intensityService.getIntensityId(intes), duration, LocalDateTime.now(), active, comments);
      sportLogBookService.saveSportLog(sportLogBook);
      addNotification(participantId, log, 2, active);
    }

    private void addNotification(long participantId, Log log, int notificationType, String text)
    {
      Long participantMentorId = null;
      if (participantMentorService.checkParticipant(participantId)) {
        participantMentorId = participantMentorService.getMentorParticipantByParticipantId(participantId).getMentorId();
      }
      Notifications notification = new Notifications(
        participantId,
        participantMentorId,
        notificationTypeService.getNotificationTypeId(TypeOfNotification.NEW_LOG),
        notificationStatusService.getNotificationStatusId(StatusOfNotification.NO_ANSWER)
      );
      String notificationText = "";
      if (notificationType == 0)
      {
        notificationText = "эмоциональном состоянии";
        notification.setShortMessage("Новая запись о " + notificationText);
      }
      if (notificationType == 1)
      {
        notificationText = "приёме пищи";
        notification.setShortMessage("Новая запись о " + notificationText);
      }
      if (notificationType == 2)
      {
        notificationText = "спортивной активности";
        notification.setShortMessage("Новая запись о " + notificationText);
      }
      notification.setAllMessage(
        participantService.getFirstname(participantId) + " " + participantService.getLastname(participantId)
          + " добавил(-а) запись о " + notificationText + ".\n" +
          "\n" +
          "Дата: " + notification.getDate().toLocalDate() + "\n" +
          "Время: " + notification.getDate().toLocalTime() + "\n" +
          "Содержание: " + text
      );
      notification.setLogBookId(log.getId());
      notificationsService.saveNotification(notification);
    }

  private void addLogbookToEating(long participantId, LocalTime timeEat, String description, String mealType,String date)
  {
    Log log = new Log(participantId, LocalDate.parse(date, formatter), logsTypeService.getLogTypeId(LogBookType.EATING.getText()));
    logService.saveLog(log);
    EatingLogBook eatingLogBook = new EatingLogBook(
      log.getId(), timeEat, description,
      mealService.getMealId(mealType), LocalDateTime.now());
    eatingLogBookService.saveEatingLog(eatingLogBook);
    addNotification(participantId, log, 1, description);

  }

    public StartView(NotificationTypeService notificationTypeService,
                     QuestionStatusService questionStatusService,
                     NotificationStatusService notificationStatusService,
                     MealService mealService,
                     IntensityService intensityService,
                     LogsTypeService logsTypeService,
                     AuthoritiesService authoritiesService,
                     LoginInfoService loginInfoService,
                     MentorService mentorService,
                     ParticipantService participantService,
                     ParticipantMentorService participantMentorService,
                     EmotionalLogBookService emotionalLogBookService,
                     EatingLogBookService eatingLogBookService,
                     SportLogBookService sportLogBookService,
                     LogService logService,
                     NotificationsService notificationsService) {

        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        this.logsTypeService = logsTypeService;
        this.logService = logService;
        this.authoritiesService = authoritiesService;
        this.loginInfoService = loginInfoService;
        this.mentorService = mentorService;
        this.participantService = participantService;
        this.participantMentorService = participantMentorService;
        this.eatingLogBookService = eatingLogBookService;
        this.sportLogBookService = sportLogBookService;
        this.emotionalLogBookService = emotionalLogBookService;
        this.mealService = mealService;
        this.intensityService = intensityService;
        this.notificationsService =  notificationsService;
        this.notificationTypeService = notificationTypeService;
        this.notificationStatusService = notificationStatusService;

        notificationTypeService.fill();
        questionStatusService.fill();
        notificationStatusService.fill();
        mealService.fill();
        intensityService.fill();
        logsTypeService.fill();
        createHardcodedUsers();


        Image logo = new Image("images/GC_logo.png", "logo");
        logo.setWidth("100px");

        login.setAction("login");
        login.setForgotPasswordButtonVisible(false);
        login.setI18n(createLoginI18n());

        signUpBtn = new Button("Регистрация");
        signUpBtn.setWidth("310px");
        signUpBtn.setHeight("50px");
        signUpBtn.addClickListener(e ->
                signUpBtn.getUI().ifPresent(ui ->
                        ui.navigate(SignUpRolesView.class)
                )
        );
        add(logo, login, signUpBtn);
        setAlignItems(Alignment.CENTER);
    }

    /**
     * Кастомизация стандартной vaadin логин формы
     */
    private LoginI18n createLoginI18n() {
        LoginI18n i18n = LoginI18n.createDefault();
        i18n.getForm().setUsername("Логин");
        i18n.getForm().setTitle("Вход");
        i18n.getForm().setSubmit("Войти");
        i18n.getForm().setPassword("Пароль");
        i18n.getErrorMessage().setTitle("Неверный логин или пароль");
        i18n.getErrorMessage().setMessage("Проверьте введенные данные и попробуйте еще раз");
        return i18n;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }
}
