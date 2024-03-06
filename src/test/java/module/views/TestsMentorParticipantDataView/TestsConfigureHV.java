package module.views.TestsMentorParticipantDataView;

import com.cygans.database.controllers.LogController;
import com.cygans.views.mentor.participants.MentorParticipantDataView;
import com.cygans.views.participant.logbooks.ParticipantPersonData;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.select.Select;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsConfigureHV {

    @Mock
    private LogController logController;

    private MentorParticipantDataView mentorParticipantDataView;

    /**
     * Проверка configureHV
     */
    @Test
    void testsConfigureHV() throws Exception {
        when(logController.getParticipantFullNameByAttribute()).thenReturn("Михаил Иванов");
        mentorParticipantDataView = new MentorParticipantDataView(logController);
        Class c = MentorParticipantDataView.class;
        Method method = c.getDeclaredMethod("configureHV");
        method.setAccessible(true);
        method.invoke(mentorParticipantDataView);
        method = c.getDeclaredMethod("identifyClick");
        method.setAccessible(true);
        method.invoke(mentorParticipantDataView);
        Field field = c.getDeclaredField("historylist");
        field.setAccessible(true);
        Grid<ParticipantPersonData> arrayList = (Grid<ParticipantPersonData>) field.get(mentorParticipantDataView);
        assertEquals("100%", arrayList.getHeight());
        assertEquals(1, arrayList.getHeaderRows().size());
        assertEquals(6, arrayList.getColumns().size());
        assertTrue(arrayList.isAllRowsVisible());
        field = c.getDeclaredField("logsTypeFilter");
        field.setAccessible(true);
        Select<String> logsTypeFilter = (Select<String>) field.get(mentorParticipantDataView);
        assertEquals("Тип записи", logsTypeFilter.getLabel());
        assertEquals("340px", logsTypeFilter.getWidth());
        field = c.getDeclaredField("dateFilter");
        field.setAccessible(true);
        DatePicker dateFilter = (DatePicker) field.get(mentorParticipantDataView);
        assertEquals("Дата", dateFilter.getLabel());
        assertEquals("200px", dateFilter.getWidth());
        assertTrue(dateFilter.isClearButtonVisible());
    }

}
