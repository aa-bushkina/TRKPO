package com.cygans.views;

import com.cygans.views.util.Control;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Error")
@Route(value = "error/access-denied")
public class AccessDenialView extends Div {
    private final Button homeBut;

    public AccessDenialView() {
        VerticalLayout vl = new VerticalLayout();
        H3 errorMsg = new H3("Ошибка: у вас нет доступа к этой странице!");
        homeBut = new Button("Вернуться к начальной странице");
        homeBut.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        homeBut.addClickListener(e ->
                homeBut.getUI().ifPresent(ui ->
                        ui.navigate(Control.class)));
        vl.add(errorMsg, homeBut);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        add(vl);

    }
}
