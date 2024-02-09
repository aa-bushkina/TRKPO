package backTests.TestsMentor.TestsMentorService;

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
 * Тест проверяет вызов метода updateLogin сервиса Mentor
 */
@ExtendWith(MockitoExtension.class)
public class TestMentorUpdateLogin {

    private static final Long ID = 1L;
    private static final String NEW_LOGIN = "NewLogin";

    @Mock
    private MentorRepository repository;

    @InjectMocks
    private MentorService service;

    /**
     * Тест на успешное обновление логина
     */
    @Test
    public void testUpdateLogin() {
        Mentor mentor = new Mentor();
        when(repository.getMentorById(ID)).thenReturn(mentor);

        service.updateLogin(ID, NEW_LOGIN);

        assertEquals(NEW_LOGIN, mentor.getLogin(), "Неверный логин вернулся");
        verify(repository, times(1)).save(mentor);
    }

    /**
     * Тест на установку пустой строки в поле логина
     */
    @Test
    public void testUpdateLoginEmptyString() {
        assertThrows(NullPointerException.class, () -> service.updateLogin(ID, ""));
        verify(repository, never()).save(any());
    }

    /**
     * Тест на установку значения null в поле логина
     */
    @Test
    public void testUpdateLoginNullString() {
        assertThrows(NullPointerException.class, () -> service.updateLogin(ID, null));
        verify(repository, never()).save(any());
    }

    /**
     * Тест на проверку, что метод save вызывается только один раз
     */
    @Test
    public void testUpdateLoginSaveCalledOnce() {
        Mentor mentor = new Mentor();
        when(repository.getMentorById(ID)).thenReturn(mentor);

        service.updateLogin(ID, NEW_LOGIN);
        verify(repository, times(1)).save(mentor);
    }
}
