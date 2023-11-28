package com.cygans.views.participant.logbooks;

import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.cygans.views.participant.ParticipantHomeView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Upload Successful")
@Route(value = "participant/upload-confirmation")

public class ParticipantConfirmationView extends VerticalLayout {
    private final Button OKButton;

    public ParticipantConfirmationView() {
        Image tick = new Image("images/check.png", "Загрузка успешна");
        tick.setHeight("160px");
        H2 header = new H2("Запись отправлена!");
        OKButton = new Button("Ок");
        OKButton.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        OKButton.setWidth("15%");
        OKButton.setHeight("40px");
        OKButton.addClickListener(e ->
                OKButton.getUI().ifPresent(ui ->
                        ui.navigate(ParticipantHomeView.class)));
        setMargin(true);
        setHorizontalComponentAlignment(Alignment.CENTER, header, tick, OKButton);
        add(new Toolbar(ToolbarType.PARTICIPANT_PAGES), header, tick, OKButton);
    }
}
