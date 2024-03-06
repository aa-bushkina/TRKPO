package module.backTests.TestsMentor.TestsMentorService;

import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorRepository;
import com.cygans.database.mentor.MentorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет вызов метода updateFirstname сервиса Mentor
 */
@ExtendWith(MockitoExtension.class)
public class TestMentorUpdateFirstname {

    private static final Long ID = 1L;

    @Mock
    private MentorRepository repository;

    @InjectMocks
    private MentorService service;

    /**
     * Тест на успешное обновление имени
     */
    @Test
    public void testUpdateFirstname() {
        String firstname = "NewFirstname";
        Mentor mentor = new Mentor();
        when(repository.getMentorById(ID)).thenReturn(mentor);

        service.updateFirstname(ID, firstname);

        assertEquals(firstname, mentor.getFirstName(), "Неверное имя вернулось");
        verify(repository, times(1)).save(mentor);
    }

    /**
     * Тест на установку пустой строки в поле имени
     */
    @Test
    public void testUpdateFirstnameEmptyString() {
        assertThrows(NullPointerException.class, () -> service.updateFirstname(ID, ""));
        verify(repository, never()).save(any());
    }

    /**
     * Тест на установку значения null в поле имени
     */
    @Test
    public void testUpdateFirstnameNullString() {
        assertThrows(NullPointerException.class, () -> service.updateFirstname(ID, null));
        verify(repository, never()).save(any());
    }

    /**
     * Тест на проверку, что метод save вызывается только один раз
     */
    @Test
    public void testUpdateFirstnameSaveCalledOnce() {
        String firstname = "NewFirstname";
        Mentor mentor = new Mentor();
        when(repository.getMentorById(ID)).thenReturn(mentor);

        service.updateFirstname(ID, firstname);
        verify(repository, times(1)).save(mentor);
    }
}

