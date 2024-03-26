package end2end.tests;

import end2end.pages.participant.QuestionsParticipantPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест проверяет создание вопроса и отображение его у участника
 */
public class TestEnd2EndAddQuestion extends TestBase {
    private static final String QUESTION_TEXT = "вопрос";
    private static final String QUESTION_DATE = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    private static final String QUESTION_STATUS = "Нет ответа";

    @BeforeEach
    public void setUp() {
        logger.info("Регистрируем участника, ментора и связываем их");
        registerParticipant();
        registerMentor();
        linkParticipantMentor();
    }

    @Test
    public void testEnd2EndAddQuestion() {
        logger.info("Тест проверяет создание вопроса и отображение его у участника");

        logger.info("Логинимся участником и отправиляем вопрос");
        QuestionsParticipantPage questionPage = getLoginPage()
                .login(LOGIN_PARTICIPANT, PASSWORD)
                .andReturnStartParticipantPage()
                .goToQuestions()
                .typeQuestion(QUESTION_TEXT)
                .clickSend();
        logger.info("Проверяем, что вопрос отображаетсяна странице вопросов");
        assertAll(
                () -> assertEquals(QUESTION_DATE, questionPage.getQuestionDate(),
                        "Не сопадает дата вопроса с ожидаемой"),
                () -> assertEquals(QUESTION_TEXT, questionPage.getQuestionText(),
                        "Не сопадает текст вопроса с ожидаемой"),
                () -> assertEquals(QUESTION_STATUS, questionPage.getQuestionStatus(),
                        "Не сопадает статус вопроса с ожидаемой")
        );

        logger.info("Тест прошел успешно");
    }
}
