package com.cygans.views.util;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
public class Filler {
  @Autowired
  private AuthoritiesService authoritiesService;
  @Autowired
  private LoginInfoService loginInfoService;
  @Autowired
  private MentorService mentorService;
  @Autowired
  private ParticipantService participantService;
  @Autowired
  private ParticipantMentorService participantMentorService;
  @Autowired
  private EatingLogBookService eatingLogBookService;
  @Autowired
  private SportLogBookService sportLogBookService;
  @Autowired
  private EmotionalLogBookService emotionalLogBookService;
  @Autowired
  private LogService logService;
  @Autowired
  private LogsTypeService logsTypeService;
  @Autowired
  private IntensityService intensityService;
  @Autowired
  private MealService mealService;
  @Autowired
  private NotificationsService notificationsService;
  @Autowired
  private NotificationTypeService notificationTypeService;
  @Autowired
  private NotificationStatusService notificationStatusService;

  private final DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
  @Autowired
  private QuestionStatusService questionStatusService;

  private final DateTimeFormatter formatter_time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  private static int hour = 0;

  private long createParticipant(String name, String surname, String login, String passwd, String phone) {
    Authorities authorities = new Authorities(login, RoleEnum.PARTICIPANT.getValue());
    authoritiesService.saveAuthorities(authorities);

    Authentication authentication = new UsernamePasswordAuthenticationToken(login, passwd,
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

  private long createMentor(String name, String surname, String login, String passwd, String phone) {
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

  public void createHardcodedUsers() {

    notificationTypeService.fill();
    questionStatusService.fill();
    notificationStatusService.fill();
    mealService.fill();
    intensityService.fill();
    logsTypeService.fill();
    long mentid = -1;
    long partid = -1;
    long mentId_0 = -1;
    long mentId_1 = -1;
    long mentId_2 = -1;
    long mentId_3 = -1;
    long partId_0 = -1;
    long partId_1 = -1;
    long partId_2 = -1;
    long partId_3 = -1;

    if (mentorService.isNeedToAddHardcodedUser()) {
      mentId_0 = createMentor("Ванька", "Головин", "vanya", "SecretPassword1", "+79137197445");
      mentId_1 = createMentor("Лёшка", "Бойкин", "alexPro", "Myst3ry_123", "+79137197445");
      mentId_2 = createMentor("Илюшка", "Работин", "ilya", "N1nja_Parad1se", "+79137197445");
      mentId_3 = createMentor("Фиона", "Павлова", "phionPAV", "ChocoLab_88", "+79137197445");
      mentid = createMentor("2", "2", "2", "2", "2");
    }
    boolean isAlreadyExists = false;
    if (participantService.isNeedToAddHardcodedUser()) {
      isAlreadyExists = true;
      partId_0 = createParticipant("Катька", "Волосова", "katya", "Qu_ntum_42", "+79383170126");
      partId_1 = createParticipant("Петька", "Совкин", "petka_super", "G0ld3nSun_7", "+79358127132");
      partId_2 = createParticipant("Наташка", "Мирянцева", "natashaNataly", "S_vvyPanda_9", "+79217132831");
      partId_3 = createParticipant("Олежка", "Питулин", "olegBoss777", "V0ltage_Star", "+79991833614");
      partid = createParticipant("1", "1", "1", "1", "1");
    }
    if (participantMentorService.isNeedToConnectUsers(mentId_0, partId_0)) {
      participantMentorService.create(partId_0, mentId_0);
      System.out.println("Users connected to each other\n");
    }
    if (participantMentorService.isNeedToConnectUsers(mentId_0, partId_1)) {
      participantMentorService.create(partId_1, mentId_0);
      System.out.println("Users connected to each other\n");
    }
    if (participantMentorService.isNeedToConnectUsers(mentId_2, partId_2)) {
      participantMentorService.create(partId_2, mentId_2);
      System.out.println("Users connected to each other\n");
    }
    if (participantMentorService.isNeedToConnectUsers(mentId_3, partId_3)) {
      participantMentorService.create(partId_3, mentId_3);
      System.out.println("Users connected to each other\n");
    }
    if (participantMentorService.isNeedToConnectUsers(mentid, partid)) {
      participantMentorService.create(partid, mentid);
      System.out.println("Users connected to each other\n");
    }
    if (isAlreadyExists) {
      addLogbookToEmotional(partId_0, "Нужна для смерть для того чтобы ценить жизнь? Думала об этом весь день", "2023-11-07");
      addLogbookToEmotional(partId_0, "Весь день думала о пельменях и бургерах. Мне хорошо.", "2023-11-08");
      addLogbookToEmotional(partId_0, "Пока читала книгу о похудении съела роллы", "2023-11-09");
      addLogbookToEmotional(partId_0, "Сегодня был трудный день на работе", "2023-11-10");
      addLogbookToEmotional(partId_0, "Встретила старого друга, вспоминали старые времена", "2023-11-11");
      addLogbookToEmotional(partId_0, "Провела вечер в кино, отлично отдохнула", "2023-11-12");
      addLogbookToEating(partId_0, LocalTime.of(4, 30), "Поела картошку с борщом. Запила квасом.", "Ужин", "2023-11-10");
      addLogbookToEating(partId_0, LocalTime.of(12, 0), "Съела салат с курицей на обед", "Обед", "2023-11-11");
      addLogbookToEating(partId_0, LocalTime.of(20, 0), "Попробовала новый рецепт пасты", "Ужин", "2023-11-12");
      addLogbookToEating(partId_0, LocalTime.of(10, 0), "Поела фрукты и орехи", "Другое", "2023-11-10");
      addLogbookToEating(partId_0, LocalTime.of(16, 0), "Заварила зеленый чай с печеньем", "Другое", "2023-11-11");
      addLogbookToEating(partId_0, LocalTime.of(22, 0), "Попробовала экзотический фрукт", "Другое", "2023-11-12");
      addLogbookToSport(partId_0, "Высокая", 60, "Бег на свежем воздухе", "Пробежал 5 км", "2023-11-10");
      addLogbookToSport(partId_0, "Средняя", 45, "Йога в зале", "Фокус на дыхании и растяжке", "2023-11-12");
      addLogbookToSport(partId_0, "Низкая", 30, "Прогулка в парке", "На свежем воздухе", "2023-11-13");

      addLogbookToEmotional(partId_1, "Сегодняшний день начался с утренней пробежки", "2023-11-10");
      addLogbookToEmotional(partId_1, "Попробовал новый рецепт пасты, получилось вкусно", "2023-11-11");
      addLogbookToEmotional(partId_1, "Вечером посетил тренировку по йоге", "2023-11-12");
      addLogbookToEating(partId_1, LocalTime.of(6, 0), "Поел омлет с тостами на завтрак", "Завтрак", "2023-11-10");
      addLogbookToEating(partId_1, LocalTime.of(13, 30), "Обедал рыбным супом и рисом", "Обед", "2023-11-11");
      addLogbookToEating(partId_1, LocalTime.of(19, 0), "Ужинал пиццей и салатом", "Ужин", "2023-11-12");
      addLogbookToEating(partId_1, LocalTime.of(11, 30), "Поел белокачанную капусту", "Другое", "2023-11-10");
      addLogbookToEating(partId_1, LocalTime.of(17, 30), "Попил свежевыжатый сок", "Другое", "2023-11-11");
      addLogbookToEating(partId_1, LocalTime.of(20, 45), "Пробовал разные виды сыра", "Другое", "2023-11-12");
      addLogbookToSport(partId_1, "Средняя", 40, "Велосипедная прогулка", "По городскому парку", "2023-11-10");
      addLogbookToSport(partId_1, "Высокая", 55, "Тренировка в зале", "Силовые упражнения", "2023-11-12");
      addLogbookToSport(partId_1, "Низкая", 25, "Утренняя зарядка", "Простые упражнения", "2023-11-13");

      addLogbookToEmotional(partId_2, "Сегодня посетила выставку искусства", "2023-11-10");
      addLogbookToEmotional(partId_2, "Провела время с семьей, готовили ужин вместе", "2023-11-11");
      addLogbookToEmotional(partId_2, "Смотрела любимый фильм вечером", "2023-11-12");
      addLogbookToEating(partId_2, LocalTime.of(8, 0), "Поела йогурт с фруктами на завтрак", "Завтрак", "2023-11-10");
      addLogbookToEating(partId_2, LocalTime.of(14, 0), "Обедала супом с гренками", "Обед", "2023-11-11");
      addLogbookToEating(partId_2, LocalTime.of(18, 30), "Ужинала рыбой и овощами", "Ужин", "2023-11-12");
      addLogbookToEating(partId_2, LocalTime.of(9, 30), "Поела мед и овсянку", "Другое", "2023-11-10");
      addLogbookToEating(partId_2, LocalTime.of(15, 0), "Пробовала азиатские сладости", "Другое", "2023-11-11");
      addLogbookToEating(partId_2, LocalTime.of(19, 45), "Готовила экспериментальный десерт", "Другое", "2023-11-12");
      addLogbookToSport(partId_2, "Низкая", 30, "Плавание в бассейне", "Расслабляющая тренировка", "2023-11-10");
      addLogbookToSport(partId_2, "Средняя", 50, "Танцы в студии", "Энергичные танцы под музыку", "2023-11-12");
      addLogbookToSport(partId_2, "Высокая", 65, "Бег по пляжу", "Интенсивная тренировка", "2023-11-13");

      addLogbookToEmotional(partId_3, "Сегодня успешно провел переговоры", "2023-11-10");
      addLogbookToEmotional(partId_3, "Получил новый заказ, радостный день", "2023-11-11");
      addLogbookToEmotional(partId_3, "Прогулка по парку после работы", "2023-11-12");
      addLogbookToEating(partId_3, LocalTime.of(7, 30), "Поел кашу с молоком на завтрак", "Завтрак", "2023-11-10");
      addLogbookToEating(partId_3, LocalTime.of(12, 45), "Обедал пастой с мясным соусом", "Обед", "2023-11-11");
      addLogbookToEating(partId_3, LocalTime.of(21, 0), "Ужинал суши и супом", "Ужин", "2023-11-12");
      addLogbookToEating(partId_3, LocalTime.of(8, 45), "Завтракал гречкой с медом", "Другое", "2023-11-10");
      addLogbookToEating(partId_3, LocalTime.of(14, 15), "Пробовал бразильский орех", "Другое", "2023-11-11");
      addLogbookToEating(partId_3, LocalTime.of(20, 30), "Поел марокканские фрукты", "Другое", "2023-11-12");
      addLogbookToSport(partId_3, "Высокая", 55, "Тренировка на открытом воздухе", "Функциональные упражнения", "2023-11-10");
      addLogbookToSport(partId_3, "Низкая", 40, "Ходьба по городу", "Посещение достопримечательностей", "2023-11-12");
      addLogbookToSport(partId_3, "Средняя", 50, "Теннис на корте", "С друзьями в выходной", "2023-11-13");
    }
  }

  private void addLogbookToEmotional(long participantId, String text,String date)
  {
    Log log = new Log(participantId, LocalDate.parse(date, formatter), logsTypeService.getLogTypeId(LogBookType.EMOTIONAL.getText()));
    logService.saveLog(log);
    String pref = " ";
    if (hour % 24 < 10)
    {
      pref = " 0";
    }
    date += pref + (hour++ % 24) +":00";
    EmotionalLogBook emotionalLogBook = new EmotionalLogBook(log.getId(),  LocalDateTime.parse(date, formatter_time), text);
    emotionalLogBookService.saveEmotionalLog(emotionalLogBook);
    addNotification(participantId, log, 0, text);
  }


  private void addLogbookToSport(long participantId, String intes, int duration, String active, String comments,String date)
  {
    Log log = new Log(participantId, LocalDate.parse(date, formatter), logsTypeService.getLogTypeId(LogBookType.SPORT.getText()));
    logService.saveLog(log);
    String pref = " ";
    if (hour % 24 < 10)
    {
      pref = " 0";
    }
    date += pref + (hour++ % 24) +":00";
    SportLogBook sportLogBook = new SportLogBook(log.getId(),
      intensityService.getIntensityId(intes), duration,  LocalDateTime.parse(date, formatter_time), active, comments);
    sportLogBookService.saveSportLog(sportLogBook);
    addNotification(participantId, log, 2, active);
  }

  private void addLogbookToEating(long participantId, LocalTime timeEat, String description, String mealType,String date)
  {
    Log log = new Log(participantId, LocalDate.parse(date, formatter), logsTypeService.getLogTypeId(LogBookType.EATING.getText()));
    logService.saveLog(log);
    String pref = " ";
    if (hour % 24 < 10)
    {
      pref = " 0";
    }
    date += pref + (hour++ % 24) +":00";
    EatingLogBook eatingLogBook = new EatingLogBook(
      log.getId(), timeEat, description,
      mealService.getMealId(mealType), LocalDateTime.parse(date, formatter_time));
    eatingLogBookService.saveEatingLog(eatingLogBook);
    addNotification(participantId, log, 1, description);

  }

  private void addNotification(long participantId, Log log, int notificationType, String text)
  {
    Long participantMentorId = null;
    if (participantMentorService.existByParticipantId(participantId)) {
      participantMentorId = participantMentorService.getMentorParticipantByParticipantId(participantId).getMentorId();
    }
    Notifications notification = new Notifications(
      participantId,
      participantMentorId,
      notificationTypeService.getNotificationTypeId(TypeOfNotification.NEW_LOG),
      notificationStatusService.getNotificationStatusId(StatusOfNotification.NO_ANSWER)
    );
    String notificationText = "";
    if (notificationType == 0) {
      notificationText = "эмоциональном состоянии";
      notification.setShortMessage("Новая запись о " + notificationText);
    }
    if (notificationType == 1) {
      notificationText = "приёме пищи";
      notification.setShortMessage("Новая запись о " + notificationText);
    }
    if (notificationType == 2) {
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
}
