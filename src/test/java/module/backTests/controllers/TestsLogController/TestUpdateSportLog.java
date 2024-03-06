package module.backTests.controllers.TestsLogController;

import com.cygans.database.controllers.LogController;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsService;
import com.cygans.database.sport_log_book.SportLogBook;
import com.cygans.database.sport_log_book.SportLogBookService;
import com.cygans.database.sport_log_book.intensity.IntensityService;
import com.cygans.database.sport_log_book.intensity.IntensityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestUpdateSportLog {

    @Mock
    private SportLogBookService sportLogBookService;

    @Mock
    private IntensityService intensityService;

    @Mock
    private NotificationsService notificationsService;

    @InjectMocks
    private LogController logController;

    @BeforeEach
    void setUp() {
        Notifications notification = new Notifications();
        notification.setAllMessage("Интенсивность: " + IntensityType.HIGH.getText() + "\n" +
                "Активность: мытье посуды\n" +
                "Продолжительность: 120 минут\n" +
                "Описание: капец у меня много посуды");
        when(notificationsService.getNotificationByLogBookId(1L)).thenReturn(notification);
    }

    @Test
    void testUpdateSportLogWithNonNullValues() {
        String sportDesc = "Переборщила с интенсивностью";
        String activityField = "мытье посуды";
        String intensity = IntensityType.LOWER.getText();
        Integer durationField = 60;
        Long logBookId = 1L;
        Long sportLogId = 0L;

        when(sportLogBookService.findByLogBookId(logBookId)).thenReturn(new SportLogBook());
        when(intensityService.getIntensityId(intensity)).thenReturn(456L);

        logController.updateSportLog(logBookId, sportDesc, activityField, intensity, durationField);

        verify(sportLogBookService, times(1)).updateSportDescription(sportLogId, sportDesc);
        verify(sportLogBookService, times(1)).updateActivity(sportLogId, activityField);
        verify(sportLogBookService, times(1)).updateIntensityId(sportLogId, 456L);
        verify(sportLogBookService, times(1)).updateDuration(sportLogId, durationField);
        verify(notificationsService, times(1)).updateNotificationAllMessage(anyLong(), anyString());
    }

    @Test
    void testUpdateSportLogWithNullValues() {
        String sportDesc = null;
        String activityField = null;
        String intensity = null;
        Integer durationField = null;
        Long logBookId = 1L;

        when(sportLogBookService.findByLogBookId(logBookId)).thenReturn(new SportLogBook());
        logController.updateSportLog(logBookId, sportDesc, activityField, intensity, durationField);

        verify(sportLogBookService, never()).updateSportDescription(anyLong(), anyString());
        verify(sportLogBookService, never()).updateActivity(anyLong(), anyString());
        verify(sportLogBookService, never()).updateIntensityId(anyLong(), anyLong());
        verify(sportLogBookService, never()).updateDuration(anyLong(), anyInt());
        verify(notificationsService, times(1)).updateNotificationAllMessage(anyLong(), anyString());
    }
}
