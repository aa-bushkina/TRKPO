package integration;

import com.cygans.Application;
import com.cygans.database.controllers.QuestionController;
import com.cygans.database.controllers.SettingsController;
import com.cygans.database.question.Question;
import com.vaadin.flow.server.VaadinSession;
import integration.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет, что после вызова метода контроллера добавления вопроса – вопрос может быть получена
 * при получении всех вопросов участника, и все поля совпадают с установленными
 */
@SpringBootTest(classes = Application.class)
public class TestIntAddQuestion extends BaseTest {
    private static final LocalDate DATE = LocalDate.now();
    private static final String QUESTION = "Надоело писать тесты, как перестать заедать этот стресс";

    @Autowired
    private QuestionController questionController;
    @Autowired
    private SettingsController settingsController;

    @BeforeEach
    public void setUp() {
        logger.info("Создаем участника");
        registerParticipant();
    }

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
        long participantId = settingsController.getAuthoritiesParticipant().getId();
        assertAll(
                () -> assertEquals(1, allQuestions.size(),
                        "У пользователя нет вопросов"),
                () -> assertEquals(logId, allQuestions.get(0).getId(),
                        "Вопроса нет среди всех вопросов пользователя"),
                () -> assertEquals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        allQuestions.stream().filter(log -> log.getId().equals(logId))
                                .findFirst()
                                .get()
                                .getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        "Не совпадает значение date"),
                () -> assertEquals(participantId, allQuestions.get(0).getParticipantId(),
                        "Не совпадает значение participant_id")
        );

        logger.info("Проверяем, что поля вопроса соответствуют ожидаемым значениям");
        assertEquals(QUESTION, allQuestions.get(0).getQuestion(), "Не сопадает значение question");

        logger.info("Тест успешно пройден");
    }
}
