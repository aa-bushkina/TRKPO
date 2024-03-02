package backTests.TestsEatingLogBook.TestMealService;

import com.cygans.database.eating_log_book.meal.Meal;
import com.cygans.database.eating_log_book.meal.MealRepository;
import com.cygans.database.eating_log_book.meal.MealService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestMealServiceFill {

    @Mock
    private MealRepository mealRepositoryMock;

    @InjectMocks
    private MealService mealService;

    @Test
    public void testFillNoRecords() {
        when(mealRepositoryMock.count()).thenReturn(0L);
        mealService.fill();

        verify(mealRepositoryMock, times(4)).save(any(Meal.class));
    }

    @Test
    public void testFillTooManyRecords() {
        when(mealRepositoryMock.count()).thenReturn(5L);
        assertThrows(RuntimeException.class, () -> mealService.fill());

        verify(mealRepositoryMock, never()).save(any(Meal.class));
    }

    @Test
    public void testFillCountCalledOnce() {
        mealService.fill();

        verify(mealRepositoryMock, times(1)).count();
    }

    @Test
    public void testFillAlreadyFilled() {
        when(mealRepositoryMock.count()).thenReturn(4L);
        assertDoesNotThrow(() -> mealService.fill());

        verify(mealRepositoryMock, never()).save(any(Meal.class));
    }
}
