package end2end.tests;

import end2end.pages.participant.LogsParticipantPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест проверяет добавление записи о спорте участником и отображение ее в списке записей у участника
 */
public class TestEnd2EndAddSportLog extends TestBase {
    private static final String DURATION = "25";
    private static final String ACTIVITY = "Утренняя зарядка";
    private static final String COMMENTS = "Простые упражнения";
    private static final String LOG_TYPE = "Спортивная активность";
    private static final String LOG_DATE = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    @BeforeEach
    public void setUp() {
        logger.info("Регистрируем участника");
        registerParticipant();
    }

    @Test
    public void testEnd2EndAddSportLog() {
        logger.info("Тест проверяет добавление записи о спорте участником и отображение ее в списке записей у участника");

        logger.info("Логинимся участником и добавляем запись");
        LogsParticipantPage logsPage = getLoginPage()
                .login(LOGIN_PARTICIPANT, PASSWORD)
                .andReturnStartParticipantPage()
                .chooseSportType()
                .typeDuration(DURATION)
                .typeActivity(ACTIVITY)
                .chooseMiddleIntensity()
                .typeComment(COMMENTS)
                .clickSend()
                .clickOk()
                .goToLogs();
        logger.info("Проверяем, что запись отображаетсяна странице записей");
        assertAll(
                () -> assertEquals(LOG_DATE, logsPage.getLogDate(),
                        "Не сопадает дата записи с ожидаемой"),
                () -> assertEquals(LOG_TYPE, logsPage.getLogType(),
                        "Не сопадает тип записи с ожидаемым")
        );

        logger.info("Выходим из аккаунта");
        logsPage.goToStartPage().logout();

        logger.info("Тест прошел успешно");
    }
}
