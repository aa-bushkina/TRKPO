package end2end.tests;

import end2end.pages.mentor.AddParticipantPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест проверяет, что ментор не может добавить участника, если он уже прикреплен к другому ментору
 */
public class TestEnd2EndBusyParticipant extends TestBase {

    @BeforeEach
    public void setUp() {
        logger.info("Регистрируем участника, ментора и связываем их, а также второго ментора");
        registerParticipant();
        registerMentor();
        linkParticipantMentor();
        registerSecondMentor();
    }

    @Test
    public void testEnd2EndBusyParticipant() {
        logger.info("Тест проверяет, что ментор не может добавить участника, если он уже прикреплен к другому ментору");

        logger.info("Логинимся ментором и переходим на страницу добавления участника");
        AddParticipantPage addParticipantPage = getLoginPage()
                .login(LOGIN_SECOND_MENTOR, PASSWORD)
                .andReturnStartMentorPage()
                .addParticipant();

        logger.info("Отправляем запрос участнику " + LOGIN_PARTICIPANT);
        addParticipantPage = addParticipantPage.typeLogin(LOGIN_PARTICIPANT).sendRequest();

        logger.info("Проверяем, что страница не обновилась и логин остался в инпуте");
        assertEquals(LOGIN_PARTICIPANT, addParticipantPage.getLoginInInput(), "В инпуте неверный логин");

        logger.info("Тест прошел");
    }
    @AfterEach
    public void logout() {
        logoutMentor();
    }
}
