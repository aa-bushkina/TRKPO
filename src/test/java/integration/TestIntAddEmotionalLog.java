package integration;

import com.cygans.Application;
import com.cygans.database.controllers.LogController;
import com.cygans.database.controllers.SettingsController;
import com.cygans.database.emotional_log_book.EmotionalLogBook;
import com.cygans.database.log_book.Log;
import com.vaadin.flow.server.VaadinSession;
import integration.base.BaseTest;
import org.junit.jupiter.api.AfterEach;
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
 * Тест проверяет, что после вызова метода контроллера добавления записи об эмоциональном состоянии запись может быть получена
 * при получении записей участника и по id, и все поля совпадают с установленными
 */
@SpringBootTest(classes = Application.class)
public class TestIntAddEmotionalLog extends BaseTest {
    private static final LocalDate DATE = LocalDate.now();
    private static final String COMMENTS = "Я плакала сильно, поэтому хотела есть, только чикенбургерами спасаюсь";
    private Long logId;

    @BeforeEach
    public void setUp() {
        logger.info("Создаем участника");
        registerParticipant();

        logger.info("Мокируем аттрибут даты");
        when(VaadinSession.getCurrent().getAttribute("date"))
                .thenReturn(DATE);
    }

    @Test
    public void testIntAddEmotionalLog() {
        logger.info("Тест проверяет, что после вызова метода контроллера добавления записи об эмоциональном состоянии запись может " +
                "быть получена при получении записей участника и по id, и все поля совпадают с установленными");

        logger.info("Вызываем метод сохранения записи об эмоциональном состоянии");
        logId = logController.saveEmotionalLog(COMMENTS);

        logger.info("Проверяем, что id записи существует");
        assertNotNull(logId, "Id записи null");

        logger.info("Получаем все записи участника и проверяем, что среди них есть добавленная запись");
        List<Log> allLogs = logController.getAllNowParticipantLogs(true);
        long participantId = settingsController.getAuthoritiesParticipant().getId();
        assertAll(
                () -> assertEquals(1, allLogs.size(),
                        "У пользователя нет записей"),
                () -> assertEquals(logId, allLogs.get(0).getId(),
                        "Записи нет среди всех записей пользователя"),
                () -> assertEquals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        allLogs.stream().filter(log -> log.getId() == logId)
                                .findFirst()
                                .get()
                                .getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        "Не совпадает значение date"),
                () -> assertEquals(participantId, allLogs.get(0).getParticipantId(),
                        "Не совпадает значение participant_id")
        );

        logger.info("Получаем запись об эмоциях по id и проверяем, что запись существует");
        EmotionalLogBook retrievedEmotionalLog = logController.getEmotionalLogByLogbookId(logId);
        assertNotNull(retrievedEmotionalLog, "Запись, полученная по id не существует");

        logger.info("Проверяем, что поля записи соответствуют ожидаемым значениям");
        assertEquals(COMMENTS, retrievedEmotionalLog.getDescription(), "Не сопадает значение comments");

        logger.info("Тест успешно пройден");
    }

    @AfterEach
    public void clear() {
        logger.info("Удаляем запись");
        if (emotionalLogBookRepository.findByLogBookId(logId) != null) {
            emotionalLogBookRepository.delete(emotionalLogBookRepository.findByLogBookId(logId));
        }
    }

}
