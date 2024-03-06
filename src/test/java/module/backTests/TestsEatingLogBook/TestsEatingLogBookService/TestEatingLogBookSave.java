package module.backTests.TestsEatingLogBook.TestsEatingLogBookService;


import com.cygans.database.eating_log_book.EatingLogBook;
import com.cygans.database.eating_log_book.EatingLogBookRepository;
import com.cygans.database.eating_log_book.EatingLogBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestEatingLogBookSave {

    @Mock
    private EatingLogBookRepository repository;

    @InjectMocks
    private EatingLogBookService service;

    @Test
    public void testSaveEatingLog() {
        EatingLogBook eatingLogBookToSave = new EatingLogBook(/* передаем необходимые параметры */);

        service.saveEatingLog(eatingLogBookToSave);

        verify(repository, times(1)).save(eatingLogBookToSave);
    }

    @Test
    public void testSaveEatingLogSaveCalledOnce() {
        EatingLogBook eatingLogBookToSave = new EatingLogBook(/* передаем необходимые параметры */);

        service.saveEatingLog(eatingLogBookToSave);

        verify(repository, times(1)).save(eatingLogBookToSave);
    }
}
