package integration;

import com.cygans.Application;
import com.cygans.database.controllers.LogController;
import com.cygans.database.controllers.SettingsController;
import com.cygans.database.log_book.Log;
import com.cygans.database.sport_log_book.SportLogBook;
import com.cygans.database.sport_log_book.intensity.IntensityService;
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
 * Тест проверяет, что после вызова метода контроллера добавления записи о спорте запись может быть получена
 * при получении записей участника и по id, и все поля совпадают с установленными
 */
@SpringBootTest(classes = Application.class)
public class TestIntAddSportLog extends BaseTest {
    private static final LocalDate DATE = LocalDate.now();
    private static final String INTENSITY = "Низкая";
    private static final String DURATION = "25";
    private static final String ACTIVITY = "Утренняя зарядка";
    private static final String COMMENTS = "Простые упражнения";

    @Autowired
    private LogController logController;

    @Autowired
    private IntensityService intensityService;
    @Autowired
    private SettingsController settingsController;

    @BeforeEach
    public void setUp() {
        logger.info("Создаем участника");
        registerParticipant();

        logger.info("Мокируем аттрибут даты");
        when(VaadinSession.getCurrent().getAttribute("date"))
                .thenReturn(DATE);
    }

    @Test
    public void testIntAddSportLog() {
        logger.info("Тест проверяет, что после вызова метода контроллера добавления записи о спорте запись может " +
                "быть получена при получении записей участника и по id, и все поля совпадают с установленными");

        logger.info("Вызываем метод сохранения записи о спорте");
        Long logId = logController.saveSportLog(INTENSITY, DURATION, ACTIVITY, COMMENTS);

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

        logger.info("Получаем запись о спорте по id и проверяем, что запись существует");
        SportLogBook retrievedSportLog = logController.getSportLogByLogbookId(logId);
        assertNotNull(retrievedSportLog, "Запись, полученная по id не существует");

        logger.info("Проверяем, что поля записи соответствуют ожидаемым значениям");
        long expectedIntensity = intensityService.getIntensityId(INTENSITY);
        assertAll(
                () -> assertEquals(expectedIntensity, retrievedSportLog.getIntensityId(),
                        "Не сопадает значение intensity"),
                () -> assertEquals(Integer.valueOf(DURATION), retrievedSportLog.getDuration(),
                        "Не сопадает значение duration"),
                () -> assertEquals(ACTIVITY, retrievedSportLog.getActivity(),
                        "Не сопадает значение activity"),
                () -> assertEquals(COMMENTS, retrievedSportLog.getComments(),
                        "Не сопадает значение comments")
        );

        logger.info("Тест успешно пройден");
    }
}
