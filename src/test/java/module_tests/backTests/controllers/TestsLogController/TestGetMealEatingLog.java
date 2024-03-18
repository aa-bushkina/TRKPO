package module_tests.backTests.controllers.TestsLogController;


import com.cygans.database.controllers.LogController;
import com.cygans.database.eating_log_book.meal.MealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestGetMealEatingLog {

    @Mock
    private MealService mealService;

    @InjectMocks
    private LogController logController;

    @BeforeEach
    public void setUp() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "login",
                "password",
                AuthorityUtils.createAuthorityList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void testGetMealEatingLogWithValidMealId() {
        Long mealId = 1L;
        String expectedMealType = "Breakfast";

        when(mealService.getMealType(mealId)).thenReturn(expectedMealType);

        String result = logController.getMealEatingLog(mealId);

        assertEquals(expectedMealType, result);
    }

    @Test
    void testGetMealEatingLogWithInvalidMealId() {
        Long mealId = null;

        String result = logController.getMealEatingLog(mealId);

        assertEquals(null, result);
    }
}
