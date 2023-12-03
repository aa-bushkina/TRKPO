package com.cygans.views.participant.signup;


import com.cygans.database.controllers.RegistrationAndLoginController;
import com.cygans.security.db.RoleEnum;
import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.cygans.views.util.Control;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;


/**
 * Страница регистрации участника 3
 */
@PageTitle("Марафон")
@Route(value = "participantSignUp3")
public class ParticipantSignUp3View extends Div {
    private TextField height;
    private TextField weight;
    private TextField breast;
    private TextField waist;
    private TextField hips;
    private Button nextBtn, previousBtn;
    private RegistrationAndLoginController registrationAndLoginController;

    public ParticipantSignUp3View(RegistrationAndLoginController registrationAndLoginController) {
        this.registrationAndLoginController = registrationAndLoginController;
        add(new Toolbar(ToolbarType.LOGIN));

        heightSetUp();
        weightSetUp();
        waistSetUp();
        hipsSetUp();
        breastSetUp();
        previousBtnInit();
        submitBtnInit();

        HorizontalLayout hl = new HorizontalLayout();
        HorizontalLayout btnsHl = new HorizontalLayout();
        btnsHl.add(previousBtn);
        btnsHl.add(nextBtn);

        HorizontalLayout firstLineHl = new HorizontalLayout();
        firstLineHl.add(height);
        firstLineHl.add(weight);

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        verticalLayout.add(new H2("Параметры"));
        verticalLayout.add(
                firstLineHl,
                breast,
                waist,
                hips,
                btnsHl
        );
        verticalLayout.setMaxWidth("600px");
        verticalLayout.setPadding(false);
        btnsHl.setWidth(verticalLayout.getWidth());

        hl.add(verticalLayout);
        hl.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(hl);
    }

    private void heightSetUp() {
        height = new TextField("Рост");
        height.setClearButtonVisible(true);
        height.setPattern("([6][7-9]|[7-9][0-9]|[1][0-9][0-9]|[2][0-4][0-9]|[2][5][0])");
        height.setErrorMessage("Неверный формат роста");
        if (VaadinSession.getCurrent().getAttribute("Height") != null) {
            height.setValue((String) VaadinSession.getCurrent().getAttribute("Height"));
        }
    }

    private void weightSetUp() {
        weight = new TextField("Вес");
        weight.setClearButtonVisible(true);
        weight.setPattern("([2-9]|[1-9][0-9]|[1-5][0-9][0-9]|[6][0][0])");
        weight.setErrorMessage("Неверный формат веса");
        if (VaadinSession.getCurrent().getAttribute("Weight") != null) {
            weight.setValue((String) VaadinSession.getCurrent().getAttribute("Weight"));
        }
    }

    private void waistSetUp() {
        waist = new TextField("Обхват талии");
        waist.setClearButtonVisible(true);
        waist.setPattern("([3-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|2[5][0])");
        waist.setErrorMessage("Неверный формат обхвата талии");
        if (VaadinSession.getCurrent().getAttribute("Waist") != null) {
            waist.setValue((String) VaadinSession.getCurrent().getAttribute("Waist"));
        }
    }

    private void hipsSetUp() {
        hips = new TextField("Обхват бедер");
        hips.setClearButtonVisible(true);
        hips.setPattern("([3-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|2[5][0])");
        hips.setErrorMessage("Неверный формат обхвата бедер");
        if (VaadinSession.getCurrent().getAttribute("Hip") != null) {
            hips.setValue((String) VaadinSession.getCurrent().getAttribute("Hip"));
        }
    }

    private void breastSetUp() {
        breast = new TextField("Обхват груди");
        breast.setClearButtonVisible(true);
        breast.setPattern("([3-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|2[5][0])");
        breast.setErrorMessage("Неверный формат обхвата груди");
        if (VaadinSession.getCurrent().getAttribute("Breast") != null) {
            breast.setValue((String) VaadinSession.getCurrent().getAttribute("Breast"));
        }
    }

    private void submitBtnInit() {
        nextBtn = new Button("Завершить");
        nextBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        nextBtn.getElement().getStyle().set("margin-left", "auto");
        nextBtn.addClickListener(e -> {
            if (height.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать рост", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (height.isInvalid()) {
                Notification notification = Notification.show("Неверное значение роста", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (weight.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать вес", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (weight.isInvalid()) {
                Notification notification = Notification.show("Неверное значение веса", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (breast.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать обхват груди", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (breast.isInvalid()) {
                Notification notification = Notification.show("Неверное значение обхвата груди", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (waist.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать обхват талии", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (waist.isInvalid()) {
                Notification notification = Notification.show("Неверное значение обхвата талии", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (hips.isEmpty()) {
                Notification notification = Notification.show("Необходимо указать обхват бедер", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (hips.isInvalid()) {
                Notification notification = Notification.show("Неверное значение обхвата бедер", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else {
                setSession();
                registrationAndLoginController.registrationUser(RoleEnum.PARTICIPANT);
                getUI().get().getSession().close();
                nextBtn.getUI().ifPresent(ui -> ui.navigate(Control.class));
            }
        });
    }

    private void previousBtnInit() {
        previousBtn = new Button("Назад", new Icon(VaadinIcon.ARROW_LEFT));
        previousBtn.getElement().getStyle().set("margin-right", "auto");
        previousBtn.addClickListener(e -> {
            setSession();
            previousBtn.getUI().ifPresent(ui -> ui.navigate(ParticipantSignUp2View.class));
        });
    }

    private void setSession() {
        VaadinSession.getCurrent().setAttribute("Height", height.getValue());
        VaadinSession.getCurrent().setAttribute("Weight", weight.getValue());
        VaadinSession.getCurrent().setAttribute("Breast", breast.getValue());
        VaadinSession.getCurrent().setAttribute("Waist", waist.getValue());
        VaadinSession.getCurrent().setAttribute("Hip", hips.getValue());
    }
}
