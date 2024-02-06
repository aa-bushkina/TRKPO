package TestMentor.TestsMentorService;

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
 * Тест проверяет вызов метода updatePhone сервиса Mentor
 */
@ExtendWith(MockitoExtension.class)
public class TestMentorUpdatePhone {

    private static final Long ID = 1L;

    @Mock
    private MentorRepository repository;

    @InjectMocks
    private MentorService service;

    /**
     * Тест на успешное обновление номера телефона
     */
    @Test
    public void testUpdatePhone() {
        String phone = "123456789";
        Mentor mentor = new Mentor();
        when(repository.getMentorById(ID)).thenReturn(mentor);

        service.updatePhone(ID, phone);

        assertEquals(phone, mentor.getPhone(), "Неверный номер телефона вернулся");
        verify(repository, times(1)).save(mentor);
    }

    /**
     * Тест на установку пустой строки в поле номера телефона
     */
    @Test
    public void testUpdatePhoneEmptyString() {
        assertThrows(NullPointerException.class, () -> service.updatePhone(ID, ""));
        verify(repository, never()).save(any());
    }

    /**
     * Тест на установку значения null в поле номера телефона
     */
    @Test
    public void testUpdatePhoneNullString() {
        assertThrows(NullPointerException.class, () -> service.updatePhone(ID, null));
        verify(repository, never()).save(any());
    }

    /**
     * Тест на проверку, что метод save вызывается только один раз
     */
    @Test
    public void testUpdatePhoneSaveCalledOnce() {
        String phone = "123456789";
        Mentor mentor = new Mentor();
        when(repository.getMentorById(ID)).thenReturn(mentor);

        service.updatePhone(ID, phone);
        verify(repository, times(1)).save(mentor);
    }
}

