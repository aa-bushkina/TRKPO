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
import com.cygans.views.util.Filler;
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

    public StartView() {

        Filler filler = new Filler();
        filler.createHardcodedUsers();

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
