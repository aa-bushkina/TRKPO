package end2end.tests;

import end2end.pages.participant.LogsParticipantPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест проверяет добавление записи о спорте участником и отображение ее в списке записей у участника
 */
public class TestEnd2EndFilterLogByDate extends TestBase {
    private static final String EMOTIONAL_TEXT = "Плакала и ела";
    private static final String DATE_1 = "10.03.1994";
    private static final String DATE_2 = "11.03.1994";

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
                .chooseDate(DATE_1)
                .chooseEmotionalType()
                .typeComment(EMOTIONAL_TEXT)
                .clickSend()
                .clickOk()
                .chooseDate(DATE_2)
                .chooseEmotionalType()
                .typeComment(EMOTIONAL_TEXT)
                .clickSend()
                .clickOk()
                .goToLogs();
        logger.info("Проверяем, что по фильтру отображается верное количество записей");
        assertAll(
                () -> assertEquals(2, logsPage.getLogsCount(),
                        "Не сопадает количество записей с ожидаемым без фильтра"),
                () -> assertEquals(1, logsPage.chooseDate(DATE_1).getLogsCount(),
                        "Не сопадает количество записей с ожидаемым по фильтру: " + DATE_1),
                () -> assertEquals(1, logsPage.chooseDate(DATE_2).getLogsCount(),
                        "Не сопадает количество записей с ожидаемым по фильтру: " + DATE_2)
        );

        logger.info("Тест прошел успешно");
    }

    @AfterEach
    public void logout() {
        logoutParticipant();
    }
}
