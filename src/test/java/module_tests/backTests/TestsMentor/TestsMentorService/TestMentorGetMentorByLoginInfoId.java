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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет вызов метода getMentorByLoginInfoId сервиса Mentor
 */
@ExtendWith(MockitoExtension.class)
public class TestMentorGetMentorByLoginInfoId {

    @Mock
    private MentorRepository repository;

    @InjectMocks
    private MentorService service;

    /**
     * Тест проверяет, что метод getMentorByLoginInfoId вызывается с правильным идентификатором LoginInfo
     */
    @Test
    public void testGetMentorByLoginInfoId() {
        Long loginInfoIdToGet = 1L;
        Mentor mentorToReturn = new Mentor();
        when(repository.getMentorByLoginInfoId(loginInfoIdToGet)).thenReturn(mentorToReturn);

        Mentor result = service.getMentorByLoginInfoId(loginInfoIdToGet);

        verify(repository, times(1)).getMentorByLoginInfoId(loginInfoIdToGet);
        assertEquals(mentorToReturn, result, "Возвращен неверный объект Mentor");
    }

    /**
     * Тест проверяет, что метод getMentorByLoginInfoId возвращает null, если пользователя с указанным идентификатором LoginInfo не существует
     */
    @Test
    public void testGetMentorByLoginInfoIdNonExistingLoginInfoId() {
        Long nonExistingLoginInfoId = 99L;
        when(repository.getMentorByLoginInfoId(nonExistingLoginInfoId)).thenReturn(null);

        Mentor result = service.getMentorByLoginInfoId(nonExistingLoginInfoId);

        verify(repository, times(1)).getMentorByLoginInfoId(nonExistingLoginInfoId);
        assertNull(result, "Метод вернул не null для несуществующего идентификатора LoginInfo");
    }

    /**
     * Тест проверяет, что метод getMentorByLoginInfoId вызывается только один раз
     */
    @Test
    public void testGetMentorByLoginInfoIdCalledOnce() {
        Long loginInfoIdToGet = 1L;
        Mentor mentorToReturn = new Mentor();
        when(repository.getMentorByLoginInfoId(loginInfoIdToGet)).thenReturn(mentorToReturn);

        service.getMentorByLoginInfoId(loginInfoIdToGet);

        verify(repository, times(1)).getMentorByLoginInfoId(loginInfoIdToGet);
    }
}

