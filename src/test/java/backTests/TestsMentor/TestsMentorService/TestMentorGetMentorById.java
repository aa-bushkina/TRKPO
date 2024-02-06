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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет вызов метода getMentorById сервиса Mentor
 */
@ExtendWith(MockitoExtension.class)
public class TestMentorGetMentorById {

    @Mock
    private MentorRepository repository;

    @InjectMocks
    private MentorService service;

    /**
     * Тест проверяет, что метод getMentorById вызывается с правильным идентификатором пользователя
     */
    @Test
    public void testGetMentorById() {
        Long idToGet = 1L;
        Mentor mentorToReturn = new Mentor();
        when(repository.getMentorById(idToGet)).thenReturn(mentorToReturn);

        Mentor result = service.getMentorById(idToGet);

        verify(repository, times(1)).getMentorById(idToGet);
        assertEquals(mentorToReturn, result, "Возвращен неверный объект Mentor");
    }

    /**
     * Тест проверяет, что метод getMentorById возвращает null, если пользователя с указанным идентификатором не существует
     */
    @Test
    public void testGetMentorByIdNonExistingId() {
        Long nonExistingId = 99L;
        when(repository.getMentorById(nonExistingId)).thenReturn(null);

        Mentor result = service.getMentorById(nonExistingId);

        verify(repository, times(1)).getMentorById(nonExistingId);
        assertNull(result, "Метод вернул не null для несуществующего идентификатора пользователя");
    }

    /**
     * Тест проверяет, что метод getMentorById вызывается только один раз
     */
    @Test
    public void testGetMentorByIdCalledOnce() {
        Long idToGet = 1L;
        Mentor mentorToReturn = new Mentor();
        when(repository.getMentorById(idToGet)).thenReturn(mentorToReturn);
        service.getMentorById(idToGet);

        verify(repository, times(1)).getMentorById(idToGet);
    }
}
