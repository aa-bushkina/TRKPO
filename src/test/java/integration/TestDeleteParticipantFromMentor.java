package integration;

import com.cygans.Application;
import integration.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тест проверяет, что при удалении ментором участника из отслеживания исчезает связь участника и ментора в БД
 */
@SpringBootTest(classes = Application.class)
public class TestDeleteParticipantFromMentor extends BaseTest {

    private Long participantId;
    private Long mentorId;

    @BeforeEach
    public void setUp() {
        logger.info("Создаем участника, ментора и связываем их");
        registerParticipant();
        participantId = settingsController.getAuthoritiesParticipant().getId();
        registerMentor();
        mentorId = settingsController.getAuthoritiesMentor().getId();
        linkParticipantMentor(participantId, mentorId);
    }

    @Test
    public void testDeleteParticipantFromMentor() {
        logger.info("Тест проверяет, что при удалении ментором участника из отслеживания " +
                "исчезает связь участника и ментора в БД");

        logger.info("Удаляем участника ментором");
        loginMentor();
        participantAndMentorController.deleteParticipantFromMentor(participantAndMentorController.getParticipantByLogin(LOGIN_PARTICIPANT));

        logger.info("Проверяем, что пары больше нет");
        Long id = participantAndMentorController.getMentorIdOfParticipant(participantAndMentorController.getParticipantByLogin(LOGIN_PARTICIPANT));
        assertNull(id, "Пара ещё есть");
    }

}
