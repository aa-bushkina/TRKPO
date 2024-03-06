package module.backTests.controllers.TestsLogController;

import com.cygans.database.controllers.LogController;
import com.cygans.database.eating_log_book.EatingLogBook;
import com.cygans.database.eating_log_book.EatingLogBookService;
import com.cygans.database.eating_log_book.meal.MealService;
import com.cygans.database.eating_log_book.meal.MealType;
import com.cygans.database.notifications.Notifications;
import com.cygans.database.notifications.NotificationsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestUpdateEatingLog {

    @Mock
    private EatingLogBookService eatingLogBookService;

    @Mock
    private MealService mealService;

    @Mock
    private NotificationsService notificationsService;

    @InjectMocks
    private LogController logController;

    @BeforeEach
    void setUp() {
        Notifications notification = new Notifications();
        notification.setAllMessage("Время приема пищи: 10:15\n" +
                "Прием пищи: " + MealType.BREAKFAST.getText() + "\n" +
                "Содержание: " + "Вкусновая картошка");
        when(notificationsService.getNotificationByLogBookId(1L)).thenReturn(notification);
    }

    @Test
    void testUpdateEatingLogWithNonNullValues() {
        String foodDesc = "Передумал, не вкусновая картошка";
        LocalTime time = LocalTime.now();
        String meal = MealType.DINNER.getText();
        Long logBookId = 1L;
        Long eatingLogId = 0L;

        when(eatingLogBookService.findByLogBookId(logBookId)).thenReturn(new EatingLogBook());
        when(mealService.getMealId(meal)).thenReturn(456L);

        logController.updateEatingLog(logBookId, foodDesc, time, meal);

        verify(eatingLogBookService, times(1)).updateEatingDescription(eatingLogId, foodDesc);
        verify(eatingLogBookService, times(1)).updateFoodTime(eatingLogId, time);
        verify(eatingLogBookService, times(1)).updateMale(eatingLogId, 456L);
        verify(notificationsService, times(1)).updateNotificationAllMessage(anyLong(), anyString());
    }

    @Test
    void testUpdateEatingLogWithNullValues() {
        String foodDesc = null;
        LocalTime time = null;
        String meal = null;
        Long logBookId = 1L;

        when(eatingLogBookService.findByLogBookId(logBookId)).thenReturn(new EatingLogBook());

        logController.updateEatingLog(logBookId, foodDesc, time, meal);

        verify(eatingLogBookService, never()).updateEatingDescription(anyLong(), anyString());
        verify(eatingLogBookService, never()).updateFoodTime(anyLong(), any());
        verify(eatingLogBookService, never()).updateMale(anyLong(), anyLong());
        verify(notificationsService, times(1)).updateNotificationAllMessage(anyLong(), anyString());
    }
}
