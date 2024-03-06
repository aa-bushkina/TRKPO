package module.views.Toolbar;

import com.cygans.views.components.Toolbar;
import com.cygans.views.components.ToolbarType;
import com.vaadin.flow.component.button.Button;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestsConstructor {

    private Toolbar participantToolbar;
    private Toolbar mentorToolbar;

    @BeforeEach
    void setUp() {
        participantToolbar = new Toolbar(ToolbarType.PARTICIPANT_HOME);
        mentorToolbar = new Toolbar(ToolbarType.MENTOR_HOME);
    }

    /**
     * Проверка работы метода с PARTICIPANT_HOME
     */
    @Test
    void constructor_participantHome_checkButtonsVisibility() throws Exception {
        Class cl = Toolbar.class;
        Field notificationBtn = cl.getDeclaredField("notificationBtn");
        notificationBtn.setAccessible(true);
        assertTrue(((Button) notificationBtn.get(participantToolbar)).isVisible());
        Field historyBtn = cl.getDeclaredField("historyBtn");
        historyBtn.setAccessible(true);
        assertTrue(((Button) historyBtn.get(participantToolbar)).isVisible());
        Field questionsBtn = cl.getDeclaredField("questionsBtn");
        questionsBtn.setAccessible(true);
        assertTrue(((Button) questionsBtn.get(participantToolbar)).isVisible());
        Field settingsBtn = cl.getDeclaredField("settingsBtn");
        settingsBtn.setAccessible(true);
        assertTrue(((Button) settingsBtn.get(participantToolbar)).isVisible());
        Field logoutBtn = cl.getDeclaredField("logoutBtn");
        logoutBtn.setAccessible(true);
        assertTrue(((Button) logoutBtn.get(participantToolbar)).isVisible());
    }

    /**
     * Проверка работы метода с MENTOR_HOME
     */
    @Test
    void constructor_mentorHome_checkButtonsVisibility() throws Exception {
        Class cl = Toolbar.class;
        Field notificationBtn = cl.getDeclaredField("notificationBtn");
        notificationBtn.setAccessible(true);
        assertTrue(((Button) notificationBtn.get(mentorToolbar)).isVisible());
        Field historyBtn = cl.getDeclaredField("historyBtn");
        historyBtn.setAccessible(true);
        assertTrue(((Button) historyBtn.get(mentorToolbar)).isVisible());
        Field questionsBtn = cl.getDeclaredField("historyBtn");
        questionsBtn.setAccessible(true);
        assertTrue(((Button) questionsBtn.get(mentorToolbar)).isVisible());
        Field settingsBtn = cl.getDeclaredField("settingsBtn");
        settingsBtn.setAccessible(true);
        assertTrue(((Button) settingsBtn.get(mentorToolbar)).isVisible());
        Field logoutBtn = cl.getDeclaredField("logoutBtn");
        logoutBtn.setAccessible(true);
        assertTrue(((Button) logoutBtn.get(mentorToolbar)).isVisible());
    }

}
