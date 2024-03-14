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
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;


@PageTitle("Марафон")
@Route(value = "/participant/start-page")
@RolesAllowed("PARTICIPANT")
public class ParticipantHomeView extends VerticalLayout {
    private final Select<String> logbookType;
    private final DatePicker datePicker;
    private final Button updateBtn;

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
        logbookType = new Select<>();
        logbookType.setWidth("25%");
        logbookType.setLabel("Тип записи");
        logbookType.setItems("Эмоциональное состояние", "Спортивная активность", "Приём пищи");

        Icon update = new Icon(VaadinIcon.UPLOAD);
        update.setSize("15%");
        updateBtn = new Button(update);
        updateBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        updateBtn.setWidth("120px");
        updateBtn.setHeight("120px");
        logbookType.setItems("Эмоциональное состояние", "Спортивная активность", "Приём пищи");

        add(new H2("Добавить запись"));

        updateBtn.addClickListener(e -> {
                    VaadinSession.getCurrent().setAttribute("date", datePicker.getValue());
                    if (datePicker.isEmpty() || datePicker.isInvalid()) {
                        Notification.show("Выберите дату из календаря", 3000, Notification.Position.TOP_CENTER)
                                .addThemeVariants(NotificationVariant.LUMO_ERROR);
                    } else if (logbookType.isEmpty() || logbookType.isInvalid()) {
                        Notification.show("Выберите тип записи", 3000, Notification.Position.TOP_CENTER)
                                .addThemeVariants(NotificationVariant.LUMO_ERROR);
                    } else if (logbookType.getValue().equals("Эмоциональное состояние")) {
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