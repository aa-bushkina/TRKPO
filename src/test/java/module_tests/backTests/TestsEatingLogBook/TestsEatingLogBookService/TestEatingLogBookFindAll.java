package module_tests.backTests.TestsEatingLogBook.TestsEatingLogBookService;

import com.cygans.database.eating_log_book.EatingLogBook;
import com.cygans.database.eating_log_book.EatingLogBookRepository;
import com.cygans.database.eating_log_book.EatingLogBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestEatingLogBookFindAll {

    @Mock
    private EatingLogBookRepository repository;

    @InjectMocks
    private EatingLogBookService service;

    @Test
    public void testFindAll() {
        List<EatingLogBook> eatingLogBooksFromRepository = new ArrayList<>();
        eatingLogBooksFromRepository.add(new EatingLogBook(1L, LocalTime.NOON, "Description 1", 1L, LocalDateTime.now()));
        eatingLogBooksFromRepository.add(new EatingLogBook(2L, LocalTime.NOON, "Description 2", 2L, LocalDateTime.now()));

        when(repository.findAll()).thenReturn(eatingLogBooksFromRepository);

        List<EatingLogBook> result = service.findAll();

        assertEquals(eatingLogBooksFromRepository.size(), result.size());

        for (int i = 0; i < eatingLogBooksFromRepository.size(); i++) {
            EatingLogBook expectedLogBook = eatingLogBooksFromRepository.get(i);
            EatingLogBook actualLogBook = result.get(i);

            assertEquals(expectedLogBook.getLogBookId(), actualLogBook.getLogBookId());
            assertEquals(expectedLogBook.getDescription(), actualLogBook.getDescription());
            assertEquals(expectedLogBook.getMealId(), actualLogBook.getMealId());
            assertEquals(expectedLogBook.getTimeEat(), actualLogBook.getTimeEat());
        }

    }

    @Test
    public void testFindAllEmpty() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        List<EatingLogBook> result = service.findAll();

        assertTrue(result.isEmpty());
    }
}
