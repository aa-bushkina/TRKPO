package views.TestsParticipantHistoryView;

import com.cygans.database.controllers.LogController;
import com.cygans.database.log_book.Log;
import com.cygans.views.participant.history.ParticipantHistoryView;
import com.cygans.views.participant.logbooks.ParticipantPersonData;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.select.Select;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class TestsIdentifyClick {

    @Mock
    private LogController logController;

    @InjectMocks
    private ParticipantHistoryView participantHistoryView;


    /**
     * Проверка метода identifyClick
     */
    @Test
    void testIdentifyClick() throws Exception {
        Grid<ParticipantPersonData> mockGrid = mock(Grid.class);
        ParticipantPersonData mockPersonData = new ParticipantPersonData();
        mockPersonData.setDate(LocalDate.now());
        mockPersonData.setLogBookType("Type1");
        Optional<ParticipantPersonData> mockOptional = Optional.of(mockPersonData);
        when(mockGrid.getUI()).thenReturn(Optional.empty());
        Class cl = ParticipantHistoryView.class;
        Field field = cl.getDeclaredField("historylist");
        field.setAccessible(true);
        Method method = cl.getDeclaredMethod("identifyClick");
        method.setAccessible(true);
        method.invoke(participantHistoryView);
        assertNotNull(mockGrid.getUI());
    }

}
