package module.backTests.TestsMentor.TestsMentorService;

import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorRepository;
import com.cygans.database.mentor.MentorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Тест проверяет вызов метода saveMentor сервиса Mentor
 */
@ExtendWith(MockitoExtension.class)
public class TestMentorSaveMentor {

    @Mock
    private MentorRepository repository;

    @InjectMocks
    private MentorService service;

    /**
     * Тест проверяет, что метод save вызывается с правильным объектом Mentor
     */
    @Test
    public void testSaveMentor() {
        Mentor mentorToSave = new Mentor();
        service.saveMentor(mentorToSave);

        verify(repository, times(1)).save(mentorToSave);
    }

    /**
     * Тест проверяет, что метод save вызывается только один раз
     */
    @Test
    public void testSaveMentorSaveCalledOnce() {
        Mentor mentorToSave = new Mentor();
        service.saveMentor(mentorToSave);

        verify(repository, times(1)).save(mentorToSave);
    }
}

