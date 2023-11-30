package com.cygans.views.participant;

import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.cygans.views.participant.logbooks.EatingLogbookView;
import com.cygans.views.participant.logbooks.EmotionalLogbookView;
import com.cygans.views.participant.logbooks.SportLogbookView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;


@PageTitle("Start Page")
@Route(value = "/participant/start-page")
@RolesAllowed("PARTICIPANT")
public class ParticipantHomeView extends VerticalLayout {
    private ComboBox<String> logbookType;
    private DatePicker datePicker;
    private Icon update;
    private Button updateBtn;

    public ParticipantHomeView() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        authentication.getName();
        add(new Toolbar(ToolbarType.PARTICIPANT_HOME));

        datePicker = new DatePicker("Дата");
        Locale locale = new Locale("ru", "RU");
        datePicker.setLocale(locale);
        datePicker.setValue(LocalDate.now(ZoneId.systemDefault()));
        datePicker.setMax(LocalDate.now());
        logbookType = new ComboBox<>();
        logbookType.setLabel("Тип записи");
        logbookType.setItems("Эмоциональное состояние", "Спортивная активность", "Приём пищи");

        update = new Icon(VaadinIcon.UPLOAD);
        update.setSize("15%");
        updateBtn = new Button(update);
        updateBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        updateBtn.setWidth("120px");
        updateBtn.setHeight("120px");
        logbookType.setItems("Эмоциональное состояние", "Спортивная активность", "Приём пищи");

        add(new H2("Добавить запись"));

        updateBtn.addClickListener(e -> {
                    VaadinSession.getCurrent().setAttribute("date", datePicker.getValue());
                    if (logbookType.getValue().equals("Эмоциональное состояние")) {
                        updateBtn.getUI().ifPresent(ui ->
                                ui.navigate(EmotionalLogbookView.class)
                        );
                    } else if (logbookType.getValue().equals("Спортивная активность")) {
                        updateBtn.getUI().ifPresent(ui ->
                                ui.navigate(SportLogbookView.class)
                        );
                    } else if (logbookType.getValue().equals("Приём пищи")) {
                        updateBtn.getUI().ifPresent(ui ->
                                ui.navigate(EatingLogbookView.class)
                        );
                    }
                }
        );

        setAlignItems(Alignment.CENTER);
        add(datePicker, logbookType, updateBtn);
        if (VaadinSession.getCurrent().getAttribute("Error") != null) {
            com.vaadin.flow.component.notification.Notification notification = Notification.show("WRONG URL" + VaadinSession.getCurrent().getAttribute("Error"), 3000, Notification.Position.TOP_CENTER);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            VaadinSession.getCurrent().setAttribute("Error", null);
        }
    }
}