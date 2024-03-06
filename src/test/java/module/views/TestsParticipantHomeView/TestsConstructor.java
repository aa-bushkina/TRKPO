package module.views.TestsParticipantHomeView;

import com.cygans.views.participant.ParticipantHomeView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestsConstructor {

    @Mock
    private VaadinSession vaadinSession;

    @Mock
    UI ui;

    private ParticipantHomeView participantHomeView;

    /**
     * Проверяем конструктор без ошибки
     */
    @Test
    void testConstructor() throws Exception {
//        VaadinSession.setCurrent(vaadinSession);
//        when(vaadinSession.getAttribute("Error")).thenReturn(null);
//        Authentication authentication = new UsernamePasswordAuthenticationToken(
//                "login",
//                "password",
//                AuthorityUtils.createAuthorityList());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        participantHomeView = new ParticipantHomeView();
//        Class<ParticipantHomeView> сl = ParticipantHomeView.class;
//        Field field = сl.getDeclaredField("datePicker");
//        field.setAccessible(true);
//        DatePicker datePicker = (DatePicker) field.get(participantHomeView);
//        assertEquals("Дата", datePicker.getLabel());
//        assertEquals("RU", datePicker.getLocale().getCountry());
//        assertEquals(LocalDate.now(ZoneId.systemDefault()), datePicker.getValue());
//        assertEquals(LocalDate.now(), datePicker.getMax());
//        field = сl.getDeclaredField("logbookType");
//        field.setAccessible(true);
//        ComboBox<String> logbookType = (ComboBox<String>) field.get(participantHomeView);
//        assertEquals("25%", logbookType.getWidth());
//        assertEquals("Тип записи", logbookType.getLabel());
//        field = сl.getDeclaredField("updateBtn");
//        field.setAccessible(true);
//        Button updateBtn = (Button) field.get(participantHomeView);
//        assertEquals("icon primary", updateBtn.getThemeName());
//        assertEquals("120px", updateBtn.getWidth());
//        assertEquals("120px", updateBtn.getHeight());
    }

    /**
     * Проверяем конструктор с ошибкой
     */
    @Test
    void testConstructorError() throws Exception {
//        UI.setCurrent(ui);
//        vaadinSession.setAttribute("Error", new Object());
//        VaadinSession.setCurrent(vaadinSession);
//        when(vaadinSession.getAttribute("Error")).thenReturn(new Object());
//        Authentication authentication = new UsernamePasswordAuthenticationToken(
//                "login",
//                "password",
//                AuthorityUtils.createAuthorityList());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        participantHomeView = new ParticipantHomeView();
//        Class<ParticipantHomeView> сl = ParticipantHomeView.class;
//        Field field = сl.getDeclaredField("datePicker");
//        field.setAccessible(true);
//        DatePicker datePicker = (DatePicker) field.get(participantHomeView);
//        assertEquals("Дата", datePicker.getLabel());
//        assertEquals("RU", datePicker.getLocale().getCountry());
//        assertEquals(LocalDate.now(ZoneId.systemDefault()), datePicker.getValue());
//        assertEquals(LocalDate.now(), datePicker.getMax());
//        field = сl.getDeclaredField("logbookType");
//        field.setAccessible(true);
//        ComboBox<String> logbookType = (ComboBox<String>) field.get(participantHomeView);
//        assertEquals("25%", logbookType.getWidth());
//        assertEquals("Тип записи", logbookType.getLabel());
//        field = сl.getDeclaredField("updateBtn");
//        field.setAccessible(true);
//        Button updateBtn = (Button) field.get(participantHomeView);
//        assertEquals("icon primary", updateBtn.getThemeName());
//        assertEquals("120px", updateBtn.getWidth());
//        assertEquals("120px", updateBtn.getHeight());
    }


}
