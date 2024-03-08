package integration;

import com.cygans.Application;
import com.cygans.database.controllers.QuestionController;
import com.cygans.database.question.Question;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Тест проверяет, что после вызова метода контроллера добавления вопроса – вопрос может быть получена
 * при получении всех вопросов участника, и все поля совпадают с установленными
 */
@SpringBootTest(classes = Application.class)
public class TestIntAddQuestion extends BaseTest {
    private static final Long PARTICIPANT_ID = 1L;
    private static final String QUESTION = "Надоело писать тесты, как перестать заедать этот стресс";

    @Autowired
    private QuestionController questionController;

    @Test
    public void testIntAddQuestion() {
        logger.info("Тест проверяет, что после вызова метода контроллера добавления вопроса – вопрос может " +
                "быть получена при получении всех вопросов участника, и все поля совпадают с установленными");

        logger.info("Вызываем метод сохранения вопроса");
        Long logId = questionController.addNewQuestionForNowParticipant(QUESTION);

        logger.info("Проверяем, что id вопроса существует");
        assertNotNull(logId, "Id вопроса null");

        logger.info("Получаем все вопросы участника и проверяем, что среди них есть добавленный вопрос");
        List<Question> allQuestions = questionController.getAllQuestionNowParticipant();
        assertAll(
                () -> assertEquals(1, allQuestions.size(),
                        "У пользователя нет вопросов"),
                () -> assertEquals(logId, allQuestions.get(0).getId(),
                        "Вопроса нет среди всех вопросов пользователя"),
                () -> assertEquals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        allQuestions.get(0).getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        "Не совпадает значение date"),
                () -> assertEquals(PARTICIPANT_ID, allQuestions.get(0).getParticipantId(),
                        "Не совпадает значение participant_id")
        );

        logger.info("Проверяем, что поля вопроса соответствуют ожидаемым значениям");
        assertEquals(QUESTION, allQuestions.get(0).getQuestion(), "Не сопадает значение question");

        logger.info("Тест успешно пройден");
    }

    @AfterEach
    public void tearDown() {
        logger.info("Удаляем из БД добавленные записи");
        //TODO
    }
}
