package module_tests.views.Toolbar;

import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsSetStyles {

    private Toolbar participantToolbar;

    @BeforeEach
    void setUp() {
        participantToolbar = new Toolbar(ToolbarType.PARTICIPANT_HOME);
    }

    /**
     * Проверяем, что стили иконок и кнопок устанавливаются правильно
     */
    @Test
    void setStyles_checkIconAndButtonStyles() throws Exception {
        participantToolbar.setStyles();
        Class cl = Toolbar.class;
        Field homeIco = cl.getDeclaredField("homeIco");
        homeIco.setAccessible(true);
        assertEquals("65px", ((Icon) homeIco.get(participantToolbar)).getStyle().get("width"));
        assertEquals("65px", ((Icon) homeIco.get(participantToolbar)).getStyle().get("height"));
        assertEquals("white", ((Icon) homeIco.get(participantToolbar)).getColor());
        Field settingsIco = cl.getDeclaredField("settingsIco");
        settingsIco.setAccessible(true);
        assertEquals("45px", ((Icon) settingsIco.get(participantToolbar)).getStyle().get("width"));
        assertEquals("45px", ((Icon) settingsIco.get(participantToolbar)).getStyle().get("height"));
        assertEquals("white", ((Icon) settingsIco.get(participantToolbar)).getColor());
        Field historyIco = cl.getDeclaredField("historyIco");
        historyIco.setAccessible(true);
        assertEquals("45px", ((Icon) historyIco.get(participantToolbar)).getStyle().get("width"));
        assertEquals("45px", ((Icon) historyIco.get(participantToolbar)).getStyle().get("height"));
        assertEquals("white", ((Icon) historyIco.get(participantToolbar)).getColor());
        Field notificationIco = cl.getDeclaredField("notificationIco");
        notificationIco.setAccessible(true);
        assertEquals("45px", ((Icon) notificationIco.get(participantToolbar)).getStyle().get("width"));
        assertEquals("45px", ((Icon) notificationIco.get(participantToolbar)).getStyle().get("height"));
        assertEquals("white", ((Icon) notificationIco.get(participantToolbar)).getColor());
        Field logoutIco = cl.getDeclaredField("logoutIco");
        logoutIco.setAccessible(true);
        assertEquals("45px", ((Icon) logoutIco.get(participantToolbar)).getStyle().get("width"));
        assertEquals("45px", ((Icon) logoutIco.get(participantToolbar)).getStyle().get("height"));
        assertEquals("white", ((Icon) logoutIco.get(participantToolbar)).getColor());
        Field questionsIco = cl.getDeclaredField("questionsIco");
        questionsIco.setAccessible(true);
        assertEquals("45px", ((Icon) questionsIco.get(participantToolbar)).getStyle().get("width"));
        assertEquals("45px", ((Icon) questionsIco.get(participantToolbar)).getStyle().get("height"));
        assertEquals("white", ((Icon) questionsIco.get(participantToolbar)).getColor());

        Field notificationBtn = cl.getDeclaredField("notificationBtn");
        notificationBtn.setAccessible(true);
        assertEquals("65px", ((Button) notificationBtn.get(participantToolbar)).getStyle().get("width"));
        assertEquals("65px", ((Button) notificationBtn.get(participantToolbar)).getStyle().get("height"));
        Field questionsBtn = cl.getDeclaredField("questionsBtn");
        questionsBtn.setAccessible(true);
        assertEquals("65px", ((Button) questionsBtn.get(participantToolbar)).getStyle().get("width"));
        assertEquals("65px", ((Button) questionsBtn.get(participantToolbar)).getStyle().get("height"));
        Field settingsBtn = cl.getDeclaredField("settingsBtn");
        settingsBtn.setAccessible(true);
        assertEquals("65px", ((Button) settingsBtn.get(participantToolbar)).getStyle().get("width"));
        assertEquals("65px", ((Button) settingsBtn.get(participantToolbar)).getStyle().get("height"));
        Field logoutBtn = cl.getDeclaredField("logoutBtn");
        logoutBtn.setAccessible(true);
        assertEquals("65px", ((Button) logoutBtn.get(participantToolbar)).getStyle().get("width"));
        assertEquals("65px", ((Button) logoutBtn.get(participantToolbar)).getStyle().get("height"));
        Field historyBtn = cl.getDeclaredField("historyBtn");
        historyBtn.setAccessible(true);
        assertEquals("65px", ((Button) historyBtn.get(participantToolbar)).getStyle().get("width"));
        assertEquals("65px", ((Button) historyBtn.get(participantToolbar)).getStyle().get("height"));

    }

}
