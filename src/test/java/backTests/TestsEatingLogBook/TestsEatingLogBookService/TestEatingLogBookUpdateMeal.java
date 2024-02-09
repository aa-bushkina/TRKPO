package backTests.TestsEatingLogBook.TestsEatingLogBookService;

import com.cygans.database.eating_log_book.EatingLogBook;
import com.cygans.database.eating_log_book.EatingLogBookRepository;
import com.cygans.database.eating_log_book.EatingLogBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestEatingLogBookUpdateMeal {

    @Mock
    private EatingLogBookRepository repository;

    @InjectMocks
    private EatingLogBookService service;

    @Test
    public void testUpdateMeal() {
        Long logBookId = 1L;
        Long newMealId = 10L;
        EatingLogBook eatingLogBookToUpdate = new EatingLogBook();
        eatingLogBookToUpdate.setMealId(5L);

        when(repository.findById(logBookId)).thenReturn(Optional.of(eatingLogBookToUpdate));

        service.updateMale(logBookId, newMealId);

        verify(repository, times(1)).findById(logBookId);
        verify(repository, times(1)).save(eatingLogBookToUpdate);
        assertEquals(eatingLogBookToUpdate.getMealId(), newMealId);
    }

    @Test
    public void testUpdateMealNotFound() {
        Long logBookId = 1L;
        Long newMealId = 10L;

        when(repository.findById(logBookId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            service.updateMale(logBookId, newMealId);
        });

        verify(repository, never()).save(any());
    }
}
