package module_tests.backTests.TestsMentor.TestsMentorService;

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
 * Тест проверяет вызов метода updateLastname сервиса Mentor
 */
@ExtendWith(MockitoExtension.class)
public class TestMentorUpdateLastname {
    private static final Long ID = 1L;
    @Mock
    private MentorRepository repository;

    @InjectMocks
    private MentorService service;

    /**
     * Тест на успешное обновление фамилии
     */
    @Test
    public void testUpdateLastname() {
        String lastname = "NewLastname";
        Mentor mentor = new Mentor();
        when(repository.getMentorById(ID)).thenReturn(mentor);
        service.updateLastname(ID, lastname);

        assertEquals(lastname, mentor.getLastName(), "Неверная фамилия вернулась");
        verify(repository, times(1)).save(mentor);
    }

    /**
     * Тест на установку пустой строки
     */
    @Test
    public void testUpdateLastnameEmptyString() {
        assertThrows(NullPointerException.class, () -> service.updateLastname(ID, ""));
        verify(repository, never()).save(any());
    }

    /**
     * Тест на установку null
     */
    @Test
    public void testUpdateLastnameNullString() {
        assertThrows(NullPointerException.class, () -> service.updateLastname(ID, null));
        verify(repository, never()).save(any());
    }

    /**
     * Тест на проверку, что метод save вызывается только один раз
     */
    @Test
    public void testUpdateLastnameSaveCalledOnce() {
        String lastname = "NewLastname";
        Mentor mentor = new Mentor();
        when(repository.getMentorById(ID)).thenReturn(mentor);

        service.updateLastname(ID, lastname);
        verify(repository, times(1)).save(mentor);
    }
}
