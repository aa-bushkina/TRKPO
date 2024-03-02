package backTests.TestsMentor.TestsMentorService;

import com.cygans.database.mentor.Mentor;
import com.cygans.database.mentor.MentorRepository;
import com.cygans.database.mentor.MentorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет вызов метода isNeedToAddHardcodedUser сервиса Mentor
 */
@ExtendWith(MockitoExtension.class)
public class TestMentorIsNeedToAddHardcodedUser {

    @Mock
    private MentorRepository repository;

    @InjectMocks
    private MentorService service;

    /**
     * Тест на случай, когда в репозитории есть пользователи
     */
    @Test
    public void testIsNeedToAddHardcodedUserUsersExist() {
        List<Mentor> userList = new ArrayList<>();
        userList.add(new Mentor());
        when(repository.findAll()).thenReturn(userList);

        assertFalse(service.isNeedToAddHardcodedUser(), "В репозитории есть пользователи, метод вернул true");
    }

    /**
     * Тест на случай, когда в репозитории нет пользователей
     */
    @Test
    public void testIsNeedToAddHardcodedUserNoUsers() {
        when(repository.findAll()).thenReturn(new ArrayList<>());

        assertTrue(service.isNeedToAddHardcodedUser(), "В репозитории нет пользователей, метод вернул false");
    }
}

