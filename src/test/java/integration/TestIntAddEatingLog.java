package integration;

import com.cygans.database.eating_log_book.EatingLogBook;
import com.cygans.database.log_book.Log;
import com.vaadin.flow.server.VaadinSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Тест проверяет, что после вызова метода контроллера добавления записи  о приеме пищи запись может быть получена
 * при получении записей участника и по id, и все поля совпадают с установленными
 */
public class TestIntAddEatingLog extends TestIntBase {
    private static final LocalTime TIME = LocalTime.of(10, 0);
    private static final String DESCRIPTION = "Мне было очень грустно и я съела весь Буше";
    private static final String MEAL_TYPE = "Завтрак";
    private static final LocalDate DATE = LocalDate.now();
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

        logger.info("Вызываем метод сохранения записи о приеме пищи состоянии");
        logId = logController.saveEatingLog(TIME, DESCRIPTION, MEAL_TYPE);

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

        logger.info("Получаем запись о еде по id и проверяем, что запись существует");
        EatingLogBook retrievedEatingLog = logController.getEatingLogByLogbookId(logId);
        assertNotNull(retrievedEatingLog, "Запись, полученная по id не существует");

        logger.info("Проверяем, что поля записи соответствуют ожидаемым значениям");
        long expectedMealId = mealService.getMealId(MEAL_TYPE);
        assertAll(
                () -> assertEquals(TIME, retrievedEatingLog.getTimeEat(),
                        "Не сопадает значение time"),
                () -> assertEquals(DESCRIPTION, retrievedEatingLog.getDescription(),
                        "Не сопадает значение description"),
                () -> assertEquals(expectedMealId, retrievedEatingLog.getMealId(),
                        "Не сопадает значение mealTypeId")
        );
        logger.info("Тест успешно пройден");
    }

    @AfterEach
    public void clear() {
        logger.info("Удаляем запись");
        if (eatingLogBookRepository.findByLogBookId(logId) != null) {
            eatingLogBookRepository.delete(eatingLogBookRepository.findByLogBookId(logId));
        }
    }

}
