package module.backTests.TestsEatingLogBook.TestMealService;


import com.cygans.database.eating_log_book.meal.Meal;
import com.cygans.database.eating_log_book.meal.MealRepository;
import com.cygans.database.eating_log_book.meal.MealService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestMealServiceGetMealType {

    @Mock
    private MealRepository mealRepositoryMock;

    @InjectMocks
    private MealService mealService;

    @Test
    public void testGetMealTypeSuccessful() {
        Long id = 1L;
        String expectedType = "Breakfast";
        Meal meal = new Meal(expectedType);
        when(mealRepositoryMock.findMealById(id)).thenReturn(meal);
        String result = mealService.getMealType(id);

        assertEquals(expectedType, result);
    }

    @Test
    public void testGetMealTypeCallsFindMealById() {
        Long id = 1L;
        Meal meal = new Meal("Breakfast");
        when(mealRepositoryMock.findMealById(id)).thenReturn(meal);
        mealService.getMealType(id);

        verify(mealRepositoryMock, times(1)).findMealById(id);
    }
}

