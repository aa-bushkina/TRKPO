package TestMentor.TestsMentorService;

import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorRepository;
import com.cygans.database.mentor.MentorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет вызов метода updateBirthday сервиса Mentor
 */
@ExtendWith(MockitoExtension.class)
public class TestMentorUpdateBirthday {

    private static final Long ID = 1L;

    @Mock
    private MentorRepository repository;

    @InjectMocks
    private MentorService service;

    /**
     * Тест на успешное обновление дня рождения
     */
    @Test
    public void testUpdateBirthday() {
        LocalDate birthday = LocalDate.of(1990, 5, 15);
        Mentor mentor = new Mentor();
        when(repository.getMentorById(ID)).thenReturn(mentor);

        service.updateBirthday(ID, birthday);

        assertEquals(birthday, mentor.getBirthday(), "Неверная дата рождения вернулась");
        verify(repository, times(1)).save(mentor);
    }

    /**
     * Тест на установку значения null в поле дня рождения
     */
    @Test
    public void testUpdateBirthdayNullDate() {
        assertThrows(NullPointerException.class, () -> service.updateBirthday(ID, null));
        verify(repository, never()).save(any());
    }

    /**
     * Тест на проверку, что метод save вызывается только один раз
     */
    @Test
    public void testUpdateBirthdaySaveCalledOnce() {
        LocalDate birthday = LocalDate.of(1990, 5, 15);
        Mentor mentor = new Mentor();
        when(repository.getMentorById(ID)).thenReturn(mentor);

        service.updateBirthday(ID, birthday);
        verify(repository, times(1)).save(mentor);
    }
}

