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
 * Тест проверяет вызов метода updateGender сервиса Mentor
 */
@ExtendWith(MockitoExtension.class)
public class TestMentorUpdateGender {

    private static final Long ID = 1L;

    @Mock
    private MentorRepository repository;

    @InjectMocks
    private MentorService service;

    /**
     * Тест на успешное обновление пола
     */
    @Test
    public void testUpdateGender() {
        String gender = "Male";
        Mentor mentor = new Mentor();
        when(repository.getMentorById(ID)).thenReturn(mentor);

        service.updateGender(ID, gender);

        assertEquals(gender, mentor.getGender(), "Неверный пол вернулся");
        verify(repository, times(1)).save(mentor);
    }

    /**
     * Тест на установку пустой строки в поле пола
     */
    @Test
    public void testUpdateGenderEmptyString() {
        assertThrows(NullPointerException.class, () -> service.updateGender(ID, ""));
        verify(repository, never()).save(any());
    }

    /**
     * Тест на установку значения null в поле пола
     */
    @Test
    public void testUpdateGenderNullString() {
        assertThrows(NullPointerException.class, () -> service.updateGender(ID, null));
        verify(repository, never()).save(any());
    }

    /**
     * Тест на проверку, что метод save вызывается только один раз
     */
    @Test
    public void testUpdateGenderSaveCalledOnce() {
        String gender = "Male";
        Mentor mentor = new Mentor();
        when(repository.getMentorById(ID)).thenReturn(mentor);

        service.updateGender(ID, gender);
        verify(repository, times(1)).save(mentor);
    }
}

