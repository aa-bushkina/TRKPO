package backTests.TestsEatingLogBook.TestMealService;


import com.cygans.database.eating_log_book.meal.Meal;
import com.cygans.database.eating_log_book.meal.MealRepository;
import com.cygans.database.eating_log_book.meal.MealService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestMealServiceGetMealId {

    @Mock
    private MealRepository mealRepositoryMock;

    @InjectMocks
    private MealService mealService;

    @Test
    public void testGetMealIdSuccessful() {
        String type = "Breakfast";
        Meal meal = new Meal(type);
        when(mealRepositoryMock.findMealByType(type)).thenReturn(meal);
        Long result = mealService.getMealId(type);

        assertNull(result, "Должен был вернуться null");
    }

    @Test
    public void testGetMealIdCallsFindMealByType() {
        String type = "Breakfast";
        Meal meal = new Meal(type);
        when(mealRepositoryMock.findMealByType(type)).thenReturn(meal);
        mealService.getMealId(type);

        verify(mealRepositoryMock, times(1)).findMealByType(type);
    }
}

