package com.cygans.views;

import com.cygans.database.eating_log_book.meal.MealService;
import com.cygans.database.log_book.logs_type.LogsTypeService;
import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorService;
import com.cygans.database.notifications.notification_status.NotificationStatusService;
import com.cygans.database.notifications.notification_type.NotificationTypeService;
import com.cygans.database.participant.Participant;
import com.cygans.database.participant.ParticipantService;
import com.cygans.database.participant_mentor.ParticipantMentorService;
import com.cygans.database.question.question_status.QuestionStatusService;
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
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.Theme;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.SQLOutput;
import java.time.LocalDate;


/**
 * Страница логина
 */
@Route(value = "login")
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
          createParticipant("Катька", "Волосова", "katya", "katkatkat", "89383170126");
          createParticipant("Петька", "Совкин", "petka_super", "tarakan1", "89358127132");
          createParticipant("Наташка", "Мирянцева", "natashaNataly", "leonardo", "89217132831");
          createParticipant("Олежка", "Питулин", "olegBoss777", "telefon", "89991833614");
          partId = createParticipant("1", "1", "1", "1", "1");
        }
        if (participantMentorService.isNeedToConnectUsers(mentId, partId))
        {
            participantMentorService.create(partId, mentId);
            System.out.println("Users connected to each other\n");
        }

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
                     ParticipantMentorService participantMentorService) {

        this.authoritiesService = authoritiesService;
        this.loginInfoService = loginInfoService;
        this.mentorService = mentorService;
        this.participantService = participantService;
        this.participantMentorService = participantMentorService;

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
