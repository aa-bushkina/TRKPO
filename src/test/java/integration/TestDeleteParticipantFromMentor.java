package integration;

import com.cygans.Application;
import com.cygans.security.db.RoleEnum;
import com.vaadin.flow.server.VaadinSession;
import integration.base.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

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
        when(VaadinSession.getCurrent().getAttribute("Login")).thenReturn(LOGIN_MENTOR);
        registrationAndLoginController.authenticationUser(RoleEnum.MENTOR);
        participantAndMentorController.deleteParticipantFromMentor(participantAndMentorController.getParticipantByLogin(LOGIN_PARTICIPANT));

        logger.info("Проверяем, что пары больше нет");
        Long id = participantAndMentorController.getMentorIdOfParticipant(participantAndMentorController.getParticipantByLogin(LOGIN_PARTICIPANT));
        assertNull(id, "Пара ещё есть");
    }

    @AfterEach
    public void tearDown() {
        logger.info("Чистим БД");
        Long participantId = null;
        if (participantRepository.getParticipantByLogin(LOGIN_PARTICIPANT) != null) {
            participantId = participantRepository.getParticipantByLogin(LOGIN_PARTICIPANT).getId();
            participantRepository.delete(participantRepository.getParticipantByLogin(LOGIN_PARTICIPANT));
        }
        if (loginInfoRepository.findByLogin(LOGIN_PARTICIPANT) != null) {
            loginInfoRepository.delete(loginInfoRepository.findByLogin(LOGIN_PARTICIPANT));
        }
        if (authoritiesRepository.getAuthoritiesByUsername(LOGIN_PARTICIPANT) != null) {
            authoritiesRepository.delete(authoritiesRepository.getAuthoritiesByUsername(LOGIN_PARTICIPANT));
        }
        if (mentorRepository.getMentorByLogin(LOGIN_MENTOR) != null) {
            mentorRepository.delete(mentorRepository.getMentorByLogin(LOGIN_MENTOR));
        }
        if (loginInfoRepository.findByLogin(LOGIN_MENTOR) != null) {
            loginInfoRepository.delete(loginInfoRepository.findByLogin(LOGIN_MENTOR));
        }
        if (authoritiesRepository.getAuthoritiesByUsername(LOGIN_MENTOR) != null) {
            authoritiesRepository.delete(authoritiesRepository.getAuthoritiesByUsername(LOGIN_MENTOR));
        }
        if (participantId != null && participantMentorRepository.findByParticipantId(participantId) != null) {
            participantMentorRepository.delete(participantMentorRepository.findByParticipantId(participantId));
        }
    }
}
