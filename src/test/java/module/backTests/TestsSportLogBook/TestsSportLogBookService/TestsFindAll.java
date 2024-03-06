package module.backTests.TestsSportLogBook.TestsSportLogBookService;

import com.cygans.database.sport_log_book.SportLogBook;
import com.cygans.database.sport_log_book.SportLogBookRepository;
import com.cygans.database.sport_log_book.SportLogBookService;
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
public class TestsFindAll {

    @Mock
    private SportLogBookRepository repository;

    @InjectMocks
    private SportLogBookService service;

    @BeforeEach
    public void setUp() {
        service = new SportLogBookService(repository);
    }


    /**
     * Тест проверяет, что метод findAll возвращает список, скопированный из репозитория.
     */
    @Test
    public void testFindAll() {
        List<SportLogBook> logBooks = new ArrayList<>();
        when(repository.findAll()).thenReturn(logBooks);
        List<SportLogBook> result = service.findAll();
        assertEquals(logBooks, result, "Вернулся неверный список");
    }


}
