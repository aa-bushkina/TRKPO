package views.TestsStartView;

import com.cygans.views.StartView;
//import com.cygans.views.util.Filler;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.QueryParameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestsBeforeEnter {

//    @Mock
//    private Filler filler;

    @InjectMocks
    private StartView startView;

    /**
     * Тестирование метода beforeEnter, когда параметр "error" отсутствует.
     */
    @Test
    public void testBeforeEnter_NoErrorParameter() throws Exception {
        BeforeEnterEvent event = mock(BeforeEnterEvent.class);
        Location location = mock(Location.class);
        QueryParameters queryParameters = mock(QueryParameters.class);
        when(event.getLocation()).thenReturn(location);
        when(location.getQueryParameters()).thenReturn(queryParameters);
        when(queryParameters.getParameters()).thenReturn(new HashMap<>());
        startView.beforeEnter(event);
        Class<? extends StartView> startViewClass = StartView.class;
        Field serialNumberField = startViewClass.getDeclaredField("login");
        serialNumberField.setAccessible(true);
        LoginForm serialNumberValue = (LoginForm) serialNumberField.get(startView);
        assertFalse(serialNumberValue.isError());
    }

    /**
     * Тестирование метода beforeEnter, когда параметр "error" присутствует.
     */
    @Test
    public void testBeforeEnter_WithErrorParameter() throws Exception {
        BeforeEnterEvent event = mock(BeforeEnterEvent.class);
        Location location = mock(Location.class);
        QueryParameters queryParameters = mock(QueryParameters.class);
        Map<String, List<String>> parameters = new HashMap<>();
        parameters.put("error", new ArrayList<>(Collections.singleton("true")));

        when(event.getLocation()).thenReturn(location);
        when(location.getQueryParameters()).thenReturn(queryParameters);
        when(queryParameters.getParameters()).thenReturn(parameters);

        startView.beforeEnter(event);
        Class<? extends StartView> startViewClass = StartView.class;
        Field serialNumberField = startViewClass.getDeclaredField("login");
        serialNumberField.setAccessible(true);
        LoginForm serialNumberValue = (LoginForm) serialNumberField.get(startView);
        assertTrue(serialNumberValue.isError());
    }


}
