package end2end.tests;

import end2end.pages.participant.QuestionsParticipantPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест проверяет, что нельзя задать вопрос, пока нет ментора
 */
public class TestEnd2EndQuestionNoMentor extends TestBase {
    private static final String QUESTION = "вопрос";
    private static final String NOTIFICATION_TEXT = "Чтобы задавать вопросы ментору, нужно принять его заявку";

    @BeforeEach
    public void setUp() {
        logger.info("Регистрируем участника");
        registerParticipant();
    }

    @Test
    public void testEnd2EndSettingsParticipant() {
        logger.info("Тест проверяет, что нельзя задать вопрос, пока нет ментора");

        logger.info("Логинимся участником и пытаемся отправить вопрос");
        String text = getLoginPage()
                .login(LOGIN_PARTICIPANT, PASSWORD)
                .andReturnStartParticipantPage()
                .goToQuestions()
                .typeQuestion(QUESTION)
                .clickSend()
                .getNotificationText();
        assertEquals(NOTIFICATION_TEXT, text, "Текст нотификации не совпадает с ожидаемым");

        logger.info("Выходим из аккаунта");
        new QuestionsParticipantPage().goToStartPage().logout();

        logger.info("Тест прошел успешно");
    }
}
