package module.views.TestsAccessDenialView;

import com.cygans.views.AccessDenialView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class TestsConstructor {

    @InjectMocks
    private AccessDenialView accessDenialView;

    /**
     * Тестирование конструктора AccessDenialView.
     */
    @Test
    public void testConstructor() {
        assertNotNull(accessDenialView);
        assertTrue(accessDenialView.getChildren().anyMatch(child -> child instanceof VerticalLayout));

        VerticalLayout vl = accessDenialView.getChildren()
                .filter(child -> child instanceof VerticalLayout)
                .map(child -> (VerticalLayout) child)
                .findFirst().orElse(null);
        assertNotNull(vl);

        assertTrue(vl.getChildren().anyMatch(child -> child instanceof H3));
        assertTrue(vl.getChildren().anyMatch(child -> child instanceof Button));

        assertEquals(FlexComponent.Alignment.CENTER, vl.getAlignItems());
    }

}
