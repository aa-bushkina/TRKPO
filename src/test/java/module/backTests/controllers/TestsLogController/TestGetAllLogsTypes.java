package module.backTests.controllers.TestsLogController;


import com.cygans.database.controllers.LogController;
import com.cygans.database.log_book.logs_type.LogsTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestGetAllLogsTypes {

    @Mock
    private LogsTypeService logsTypeService;

    @InjectMocks
    private LogController logController;

    @BeforeEach
    void setUp() {
        List<String> mockTypes = new ArrayList<>();
        mockTypes.add("Type1");
        mockTypes.add("Type2");
        mockTypes.add("Type3");
        when(logsTypeService.getAllLogsTypes()).thenReturn(mockTypes);
    }

    @Test
    void testGetAllLogsTypes() {
        List<String> expectedTypes = new ArrayList<>();
        expectedTypes.add("Все");
        expectedTypes.add("Type1");
        expectedTypes.add("Type2");
        expectedTypes.add("Type3");

        List<String> result = logController.getAllLogsTypes();

        assertEquals(expectedTypes, result);
    }
}
