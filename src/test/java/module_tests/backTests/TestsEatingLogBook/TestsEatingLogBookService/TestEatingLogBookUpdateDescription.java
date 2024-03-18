package module_tests.backTests.TestsEatingLogBook.TestsEatingLogBookService;

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
public class TestEatingLogBookUpdateDescription {

    @Mock
    private EatingLogBookRepository repository;

    @InjectMocks
    private EatingLogBookService service;

    @Test
    public void testUpdateEatingDescription() {
        Long logBookId = 1L;
        String newDescription = "New Description";
        EatingLogBook eatingLogBookToUpdate = new EatingLogBook(); // Создаем объект EatingLogBook, который был бы возвращен из репозитория
        eatingLogBookToUpdate.setDescription("Old Description");

        when(repository.findById(logBookId)).thenReturn(Optional.of(eatingLogBookToUpdate));

        service.updateEatingDescription(logBookId, newDescription);

        verify(repository, times(1)).findById(logBookId);
        verify(repository, times(1)).save(eatingLogBookToUpdate); // Проверяем, что метод save был вызван один раз для обновления
        assertEquals(eatingLogBookToUpdate.getDescription(), newDescription);
    }

    @Test
    public void testUpdateEatingDescriptionNotFound() {
        Long logBookId = 1L;
        String newDescription = "New Description";

        when(repository.findById(logBookId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            service.updateEatingDescription(logBookId, newDescription);
        });

        verify(repository, never()).save(any());
    }
}
